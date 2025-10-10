package lesson05;

import java.util.ArrayList;

public class CourseManagment {
    public static void main(String[] args) {
        class Person{
            int age;
            String name;

            void introduce() {
                System.out.println("Привет меня зовут: " + name + " мне " + age + "лет");
            }
        }

        class  Rectangle {
            double width;
            double length;

            double calculateSquare(){
                return length * width;
            }
        }
        class Car{
            String country;
            String model;
            int yearOfManufactury;
            void printCarInfo(){
                System.out.println("Выпущена в стране :" + country + "Модель машины: "+ model + "год выпуска: " + yearOfManufactury);
            }
        }
        class BankAccount{
            String flo;
            int accauntNumber;
            int balance;
            void deposit(double amount){
                balance += amount;
                System.out.println("Пополнение: " + amount + "Баланс: " + balance);
            }
            void withdraw(double amount){
                if (balance >= amount){
                    balance -= amount;
                    System.out.println("Снятие: " + amount + " Баланс: " + balance);
                }
                else{
                    System.out.println("Недостаточно средств");
                }
            }
            double getBalance(){
                return balance;
            }
        }
        class Book{
            String isbn;
            String name;
            String author;
            int year;
            boolean status;

            void getBookinfo(){
                System.out.println(isbn + " | " + name + " | " + author + " | " + year + " | " + (status ? "Свободна" : "Зарезервирована"));
            }
            static ArrayList<Book> books = new ArrayList<>();

            static void getBooks(){
                for(Book b : books){
                    b.getBookinfo();
                }
            }
            static void addNewBook(Book book){
                books.add(book);
            }
            void resevBook(){
                if(status ){
                    status = false;
                    System.out.println("Книга :" + name + "Зарезервирована");
                }
                else{
                    System.out.println("Книга:" + name + "занята");
                }
            }
        }
        class OnlineStore{
            int code;
            String name;
            int price;
            int count;

            static ArrayList<OnlineStore> products = new ArrayList<>();

            void addProducts(OnlineStore product){
                products.add(product);
            }
            void buyProducts(int quantity){
                if (count >= quantity){
                    count -= quantity;
                    System.out.println("Куплено " + quantity + " шт. " + name + ". Остаток: " + count);
                }
                else{
                    System.out.println("Недостаточно средств ");
                }
            }
            void getproductsInfo(){
                System.out.println("Цена:" + price + "Название: " + name + "Кол-во: " + count );
            }
            static void getProfucts(){
                for(OnlineStore p : products){
                    p.getproductsInfo();
                }
            }
        }
        class Banksystem{
            int accountNumber;
            String fio;
            int balance;

            void getAccountinfo(){
                System.out.println("Номер аккаунта: " + accountNumber + "ФИО: " + fio + "Баланс: " + balance);
            }
            void addAccount(Banksystem acc){
                //accounts.add(acc);
            }
        }
    }
}




