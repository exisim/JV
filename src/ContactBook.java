import java.sql.*;


public class ContactBook {
    Profile[] profiles;
    private int size;
    private Connection connection;

    public ContactBook() {
        connect();
        //profiles = getProfiles();
    }

    void printAllContact(){
        profiles = getProfiles();
        for (int p = 0; p < profiles.length; p++) {
            System.out.println(profiles[p]);
        }
    }

    void pushContact(String name, String number){
        if (!existContact(number)){
            newContact(name, number);
            System.out.println("номер успешно добавлен");
        } else {
            Profile pr = getProfileByNumber(number);
            System.out.println("такой контакт уже существует с именем " + pr.getName());
        }
    }

    void update(String name, String newNumber){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "UPDATE Profiles SET number = '"+newNumber+"' WHERE name = '"+name+"'";
            st.execute(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    void removeByName(String name){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "DELETE FROM Profiles WHERE name = '"+name+"'";
            st.execute(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    void remove(String number){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "DELETE FROM Profiles WHERE number = '"+number+"'";
            st.execute(sqlQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void pushContact(String name, String number, int age){
        Profile pr = getProfileByNumber(number);
        if (existContact(number)){
            newContact(name, number,age);
        } else {
            System.out.println("такой контакт уже существует с именем " + pr.getName());
        }
    }

    void newContact(String name, String number){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "INSERT INTO `Profiles`" +
                    "(`name`,`number`) VALUES ('"+name+"','"+number+"');";
            st.execute(sqlQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void newContact(String name, String number, int age){
        //INSERT INTO `Profiles`(`name`,`number`,`age`) VALUES ('','',NULL);
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "INSERT INTO `Profiles`" +
            "(`name`,`number`, `age`) VALUES ('"+ name +"','"+ number +"', "+age+");";
            st.execute(sqlQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    void connect(){
        try{
            //CREATE TABLE `na` (
            //	`name`	TEXT NOT NULL,
            //	`number`	INTEGER NOT NULL UNIQUE,
            //	`age`	INTEGER
            //);
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:book.db");
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    boolean existContactByName(String name){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "SELECT EXISTS(SELECT * FROM Profiles WHERE name = '"+name+"' )";
            ResultSet rs = st.executeQuery(sqlQuery);
            if (rs.getInt(1) == 1){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    boolean existContact(String number){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "SELECT EXISTS(SELECT * FROM Profiles WHERE number = '"+number+"' )";
            ResultSet rs = st.executeQuery(sqlQuery);
            if (rs.getInt(1) == 1){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    Profile[] getProfiles(){
        int a = 0;
        Profile[] profiles1 = new Profile[getCountTable()];

        try {
            Statement st = connection.createStatement();
            String sqlQuery = "SELECT * FROM Profiles";
            ResultSet rs = st.executeQuery(sqlQuery);
            while(rs.next()){
                profiles1[a] = new Profile(rs.getString(1),
                        rs.getString(2),
                            rs.getInt(3));
                a++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return profiles1;
    }


    Profile getProfileByName(String name){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "SELECT * FROM Profiles WHERE name = '"+name+"'";
            ResultSet rs = st.executeQuery(sqlQuery);
            Profile pr = new Profile(rs.getString(1),
                    rs.getString(2), rs.getInt(3));
            return pr;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    Profile getProfileByNumber(String number){
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "SELECT * FROM Profiles WHERE number = '"+number+"'";
            ResultSet rs = st.executeQuery(sqlQuery);
            Profile pr = new Profile(rs.getString(1),
                    rs.getString(2), rs.getInt(3));
            return pr;
        } catch (SQLException e){
           e.printStackTrace();
        }
        return null;
    }

    int getCountTable(){
        int res = 0;
        try {
            Statement st = connection.createStatement();
            String sqlQuery = "SELECT COUNT(*) from Profiles";
            ResultSet rs = st.executeQuery(sqlQuery);
            res = rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        size = res;
        return res;
    }
}
