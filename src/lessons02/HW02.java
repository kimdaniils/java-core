package lessons02;

import java.util.Scanner;

public class HW02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println("\n Выберите задачу: ");
            System.out.println("1 - Обмен значений");
            System.out.println("2- Таблица умножения");
            System.out.println("3 - Конвертер валют");
            System.out.println("4 - Чётное или нечётное");
            System.out.println("5 - Максимум из трёх");
            System.out.println("6 - Калькулятор");
            System.out.println("7 - Среднее арифметическое");
            System.out.println("8 - Поиск элемента");
            System.out.println("9 - Мини-игра \"Угадай число\"");
            System.out.println("0 - Выход");
            int  choice = scan.nextInt();

            switch (choice){
                case 1 -> {
                    System.out.print("Введите первое число: ");
                    int a = scan.nextInt();
                    System.out.print("Введите второе число: ");
                    int b = scan.nextInt();
                    int c = 0;
                    c = a;
                    a = b;
                    b = c;
                    System.out.println("Первое число: " + a );
                    System.out.println("Второе число: " + b );
                }
                case 2 -> {
                    for(int i = 1; i <= 10; i++){
                        for (int j = 1; j <= 10; j++){
                            System.out.print((i * j ) + "\t");
                        }
                        System.out.println();
                    }
                }
                case 3 ->{
                    int exchange = 12500;
                    System.out.print("Введите сумму в долларах: ");
                    int number = scan.nextInt();
                    long sum = 0;
                    sum = exchange * number;
                    System.out.print("Конвертация в суммы: " + sum);
                }
                case 4 ->{
                    System.out.print("Введите число: ");
                    int N = scan.nextInt();
                    if( N % 2 == 0){
                        System.out.print("Число четное: " + N);
                    }
                    else{
                        System.out.print("Число не четное " + N);
                    }
                }
                case 5 -> {
                    System.out.print("Введите первое число: ");
                    int a = scan.nextInt();
                    System.out.println("Введите второе число: ");
                    int b = scan.nextInt();
                    System.out.println("Введите третье число: ");
                    int c = scan.nextInt();

                    if (a > b && a > c){
                        System.out.print("Первое число больше всех: " + a);
                    }
                    else if (b > a && b >c) {
                        System.out.print("Второе число больше: " + b);
                    }
                    else{
                        System.out.print("Третье число больше: " + c);
                    }
                }
                case 6 -> {
                    System.out.print("Введите первое число: ");
                    double a = scan.nextInt();
                    System.out.println("Введите действие (+, -, *, /): ");
                    char op = scan.next().charAt(0);
                    System.out.println("Введите второе число: ");
                    double b = scan.nextInt();

                    if(op == '+'){
                        System.out.println(a + b);
                    }
                    else if (op == '-') {
                        System.out.println(a - b);
                    }
                    else if (op == '*'){
                        System.out.println(a * b);
                    }
                    else{
                        System.out.println(a /b);
                    }
                }
                case 7 -> {
                    System.out.print("Введите размер массива: ");
                    int n = scan.nextInt();

                    int[] arr = new int[n];

                    System.out.println("Введите " + n + " чисел: ");
                    for(int i = 0; i < n; i++){
                        arr[i] = scan.nextInt();
                    }
                    System.out.print("Введите число для поиска: ");
                    int target = scan.nextInt();

                    boolean found = false;
                    for (int i = 0; i < n; i++ ){
                        if(arr[i] == target){
                            found = true;
                            break;
                        }
                    }
                    if (found){
                        System.out.print("Число найдено: ");
                    }
                    else{
                        System.out.println("Число не найдено ");
                    }

                }
                case 0 -> {
                    System.out.println("Выход ");
                    return;
                }
            }
        }
    }
}
