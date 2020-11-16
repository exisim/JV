import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.unstatic();
    }

    void unstatic(){
        ContactBook cb= new ContactBook();
        Scanner sc = new Scanner(System.in);
        System.out.println("подключение прошло успешно");
        while (true){
            String res = sc.nextLine();
            switch (res.toLowerCase()){ //HELP => help, Add => add, seArch By NaMbEr
                case "help":
                    help();
                    break;
                case "printAllContact":
                    cb.printAllContact();
                    break;
                case "search by number":
                    searchByNumber(cb,sc);
                    break;
                case "search by name":
                    searchByName(cb,sc);
                    break;
                case "push":
                    push(cb, sc);
                    break;
                case "remove":
                    remove(cb, sc);
                    break;
                case "remove by name":
                    remopveByName(cb, sc);
                    break;
                case "update":
                    update(cb, sc);
                    break;
                default:
                    System.out.println("для справки введите 'help'");
                    break;
            }
        }
    }

    void help(){
        System.out.println("для вывода всех контактов введите 'all'" +
                "\nдля добавления контактов введите 'push'"+
                "\nдля поиска определенного контакта по номеру введите 'search by number'"+
                "\nдля поиска определенного контакта по имени введите 'search by name'"+
                "\nдля удаления определенного контакта по номеру введите 'remove'"+
                "\nдля удаления определенного контакта по имени введите 'remove by name'");
    }

    void searchByNumber(ContactBook cb, Scanner sc){
        do {
            System.out.println("введите по какому номеру ищите контакт");
            String a = sc.nextLine();
            if (cb.existContact(a))
                System.out.println(cb.getProfileByNumber(a));
            else
                System.out.println("такого контакта не существует");
        } while (false);
    }

    void searchByName(ContactBook cb, Scanner sc){
        do {
            System.out.println("введите по какому имени ищите контакт");
            String a = sc.nextLine();
            if (cb.existContactByName(a))
                System.out.println(cb.getProfileByName(a));
            else
                System.out.println("такого контакта не существует");
        } while (false);
    }

    void push(ContactBook cb, Scanner sc){
        System.out.println("введите имя контакта");
        String name = sc.nextLine();
        System.out.println("введите номер контакта");
        String number = sc.nextLine();
        cb.pushContact(name,number);
    }

    void remove(ContactBook cb, Scanner sc){
        System.out.println("введите номер контакта который будет удален");
        String a = sc.nextLine();
        if (cb.existContact(a)) {
            cb.remove(a);
            System.out.println("контакт успешно удалён");
        }else
            System.out.println("данного контакта не существует");
    }

    void remopveByName(ContactBook cb, Scanner sc){
        System.out.println("введите имя контакта который будет удален");
        String a = sc.nextLine();
        if (cb.existContactByName(a)){
            cb.removeByName(a);
            System.out.println("контакт успешно удалён");
        } else
            System.out.println("данного контакта не существует");
    }

    void update(ContactBook cb, Scanner sc){
        boolean exist = false;
        do {
            System.out.println("введите имя контакта");
            String a = sc.nextLine();
            if (cb.existContactByName(a)){
                System.out.println("введите новый номер контакта");
                String b = sc.nextLine();
                cb.update(a,b);
                System.out.println("обновление контакта прошло успешно");
                exist = false;
            } else {
                System.out.println("данного контакта не существует");
                exist = true;
            }
        } while (exist);
    }
}
