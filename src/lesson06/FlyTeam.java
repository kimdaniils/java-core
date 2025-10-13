package lesson06;
import java.io.*;
import java.util.*;

public class FlyTeam {

    static class Seat implements Serializable {
        int number;
        String seatClass;
        boolean booked;
        String passengerName;

        Seat(int number, String seatClass) {
            this.number = number;
            this.seatClass = seatClass;
            this.booked = false;
            this.passengerName = "";
        }

        void book(String name) {
            booked = true;
            passengerName = name;
        }

        void cancel() {
            booked = false;
            passengerName = "";
        }

        public String toString() {
            if (booked)
                return "Место " + number + " [" + seatClass + "] - ЗАНЯТО (" + passengerName + ")";
            else
                return "Место " + number + " [" + seatClass + "] - СВОБОДНО";
        }
    }

    static class Airplane implements Serializable {
        ArrayList<Seat> seats = new ArrayList<>();

        Airplane() {
            for (int i = 1; i <= 10; i++)
                seats.add(new Seat(i, "Бизнес"));
            for (int i = 11; i <= 20; i++)
                seats.add(new Seat(i, "Эконом"));
        }

        Seat findSeat(int number) {
            for (Seat s : seats)
                if (s.number == number)
                    return s;
            return null;
        }

        void showAll() {
            System.out.println("\n--- Список всех мест ---");
            for (Seat s : seats)
                System.out.println(s);
        }

        void showVisual() {
            System.out.println("\nБизнес-класс:");
            printRange(1, 10);
            System.out.println("Эконом-класс:");
            printRange(11, 20);
        }

        void printRange(int start, int end) {
            for (int i = start; i <= end; i++) {
                Seat s = findSeat(i);
                System.out.print(s.booked ? "[X]" : "[O]");
                if (i % 5 == 0) System.out.println();
            }
        }
    }

    static class BookingSystem {
        Airplane airplane;
        String fileName = "seats.dat";

        BookingSystem() {
            airplane = load();
            if (airplane == null)
                airplane = new Airplane();
        }

        void showSeats() {
            airplane.showAll();
            airplane.showVisual();
        }

        void bookSeat(int number, String name) {
            Seat seat = airplane.findSeat(number);
            if (seat == null) {
                System.out.println("Такого места нет.");
                return;
            }
            if (seat.booked) {
                System.out.println("Место уже занято.");
                return;
            }
            seat.book(name);
            save();
            System.out.println("Место успешно забронировано!");
        }

        void cancelBooking(int number) {
            Seat seat = airplane.findSeat(number);
            if (seat == null) {
                System.out.println("Такого места нет.");
                return;
            }
            if (!seat.booked) {
                System.out.println("Место и так свободно.");
                return;
            }
            seat.cancel();
            save();
            System.out.println("Бронь снята.");
        }

        void showInfo(int number) {
            Seat seat = airplane.findSeat(number);
            if (seat == null)
                System.out.println("Такого места нет.");
            else
                System.out.println(seat);
        }

        void save() {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
                out.writeObject(airplane);
                out.close();
            } catch (Exception e) {
                System.out.println("Ошибка при сохранении данных.");
            }
        }

        Airplane load() {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
                Airplane a = (Airplane) in.readObject();
                in.close();
                return a;
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1. Показать все места");
            System.out.println("2. Забронировать место");
            System.out.println("3. Снять бронь");
            System.out.println("4. Информация о месте");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Введите число!");
                continue;
            }

            if (choice == 1) {
                system.showSeats();
            } else if (choice == 2) {
                System.out.print("Введите номер места (1–20): ");
                int num = sc.nextInt();
                sc.nextLine();
                System.out.print("Введите имя пассажира: ");
                String name = sc.nextLine();
                system.bookSeat(num, name);
            } else if (choice == 3) {
                System.out.print("Введите номер места: ");
                int num = sc.nextInt();
                sc.nextLine();
                system.cancelBooking(num);
            } else if (choice == 4) {
                System.out.print("Введите номер места: ");
                int num = sc.nextInt();
                sc.nextLine();
                system.showInfo(num);
            } else if (choice == 0) {
                System.out.println("Выход. Данные сохранены.");
                break;
            } else {
                System.out.println("Неверный выбор.");
            }
        }
    }
}
