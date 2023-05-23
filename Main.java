package Library;

import java.util.Scanner;

public class Main {
        static Scanner s;
        static Database database;
        public static void main(String[] args) {
                database = new Database();
                System.out.println("Welcome to Salient!");
                int num;

                        System.out.println("0. Exit\n"+ "1. Login\n2. New user");
                        s = new Scanner(System.in);
                        num = s.nextInt();

                        switch (num) {
                                case 1:
                                        login();
                                        break;
                                case 2:
                                        newUser();
                                        break;
                                default:
                                        System.out.println("Please enter 1 to login or 2 for a new user");
                        }

        }


        private static void login() {
                System.out.println("Enter your phone number");
                String phoneNumber = s.next();
                System.out.println("Enter your email");
                String email = s.next();
                int n = database.login(phoneNumber,email);
                if(n != -1){
                        User user = database.getUser(n);
                        user.menu(database, user);
                } else{
                        System.out.println("Doesn't exist!");;
                }
        }

        private static void newUser(){
                System.out.println("Enter your name");
                String name = s.next();
                if(database.userExists(name)){
                        System.out.println("There is a user with this name!");
                        newUser();
                }
                System.out.println("Enter your phone number");
                String phoneNumber = s.next();
                System.out.println("Enter your email");
                String email = s.next();
                System.out.println("1. Admin\n2. Regular User");
                int n2 = s.nextInt();
                User user;
                if(n2==1){
                        user = new Admin(name, email, phoneNumber);

                }else {
                        user = new RegularUser(name, email, phoneNumber);
                }
                database.AddUser(user);
                user.menu(database, user);
        }
}