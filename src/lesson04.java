import java.util.Scanner;
public class lesson04 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int exchange = 12500;
        System.out.print("Введите сумму в долларах: ");
        int number = scan.nextInt();
        long sum = 0;
        sum = exchange * number;
        System.out.print("Конвертация в суммы: " + sum);
    }
}
