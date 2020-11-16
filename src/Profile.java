public class Profile {
    private String name;
    private String number;
    private int age;

    //POJO - класс (хранилище данных)

    public Profile(String name, String number, int age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        if (age == 0){
            return ("[" + name + "] <" + number + ">");
        } else {
            return ("[" + name + "] <" + number + "> age = " + age);
        }
    }
}
