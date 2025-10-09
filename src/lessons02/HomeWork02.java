package lessons02;
import java.util.Scanner;

public class HomeWork02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        while(true){
            System.out.println("\nВыбери задачу:");
            System.out.println("1 - Вывести числа от 1 до 100 (по 10 в строке)");
            System. out.println("2 - Сумма чисел от 1 до N");
            System.out.println("3 - Произведение чисел от 1 до N");
            System.out.println("4 - Сумма чётных чисел до N");
            System.out.println("5 - Сумма цифр числа");
            System.out.println("6 - Разворот числа");
            System.out.println("7 - Факториал числа");
            System.out.println("8 - Первое число > 1000, делящееся на 7");
            System.out.println("9 - Простые числа до N");
            System.out.println("10 - Таблица умножения");
            System.out.println("0 - Выход");

            int choice = scan.nextInt();

            switch(choice) {
                case 1 -> {
                    for(int i = 1; i <= 100; i++){
                        System.out.print(i + " ");
                        if(i % 10 == 0) System.out.println();
                    }
                }
                case 2 ->{
                    System.out.print("Введите число: ");
                    int N = scan.nextInt();
                    long sum = 0;
                    for (int i = 1; i <= N; i++) sum += i;
                    System.out.print("Сумма = " + sum);

                }
                case 3 ->{
                    System.out.print("Введите число: ");
                    int N = scan.nextInt();
                    long sum = 1;
                    for(int i = 1; i <= N; i++){
                        sum *= i;
                    }
                    System.out.print("Произведение = "+ sum);
                }
                case 4 ->{
                    System.out.print("Введите число: ");
                    int N = scan.nextInt();
                    long sum = 0;
                    for( int i = 2; i <= N; i += 2 ){
                        sum += i;
                    }
                    System.out.print("Cумма четных = " + sum);
                }
                case 5 -> {
                    System.out.print("Введите число: ");
                    int N  = scan.nextInt();
                    int sum = 0;
                    while (N > 0 ){
                        sum += N % 10;
                        N /= 10;
                    }
                    System.out.print("Сумма цифр = " + sum);
                }
                case 6 ->{
                    System.out.print("Введите число: ");
                    int N = scan.nextInt();
                    int rev = 0;
                    while (N > 0) {
                        rev = rev * 10 + N % 10;
                        N /= 10;
                    }
                    System.out.print("Разворот = " + rev);
                }
                case 7 ->{
                    System.out.print("Введите число: ");
                    int n = scan.nextInt();
                    long sum = 1;
                    for ( int i = 1;  i <= n; i++){
                        sum *= i;
                    }
                    System.out.print("Факториал = " + sum);
                }
                case 8 ->{
                    int i = 1000;
                    while(i % 7 != 0){
                        i++;
                    }
                    System.out.print("Первое число > 1000, делящееся на 7: " + i);
                }
                case 9 ->{
                    int N = scan.nextInt();
                    for (int i = 2; i <= N; i++){
                        boolean prime = true;
                        for (int j = 2; j * j <= i; j++ ){
                            if (i % j == 0){
                                prime = false;
                                break;
                            }
                        }
                        if (prime) System.out.print(i + " ");
                    }
                    System.out.println();
                }
                case 0 ->{
                    System.out.print("Выход");
                    return;
                }
                default -> System.out.println("Нет такой задачи");
            }
        }
    }
}
