package lesson07;
import java.io.*;
import java.util.*;
import java.time.*; // для работы с датой и временем

public class FlyTeamTime {

    // =================== КЛАСС МЕСТА ===================
    static class Seat implements Serializable {
        int number;
        String seatClass;
        String status;           // "свободен", "забронирован", "оплачен"
        String passengerName;
        LocalDateTime bookingTime; // время бронирования

        Seat(int number, String seatClass) {
            this.number = number;
            this.seatClass = seatClass;
            this.status = "свободен";
            this.passengerName = "";
            this.bookingTime = null;
        }

        void book(String name) {
            status = "забронирован";
            passengerName = name;
            bookingTime = LocalDateTime.now();
        }

        void pay() {
            if (status.equals("забронирован")) {
                status = "оплачен";
                System.out.println("Бронь успешно оплачена!");
            } else {
                System.out.println("Нельзя оплатить — место не забронировано.");
            }
        }

        void cancel() {
            status = "свободен";
            passengerName = "";
            bookingTime = null;
        }

        boolean isExpired() {
            if (status.equals("забронирован") && bookingTime != null) {
                Duration diff = Duration.between(bookingTime, LocalDateTime.now());
                return diff.toMinutes() > 24;
            }
            return false;
        }

        public String toString() {
            String info = "Место " + number + " [" + seatClass + "] - " + status.toUpperCase();
            if (!passengerName.isEmpty()) info += " (" + passengerName + ")";
            if (bookingTime != null) info += " | забронировано: " + bookingTime.toString();
            return info;
        }
    }

    // =================== КЛАСС САМОЛЁТА ===================
    static class Airplane implements Serializable {
        ArrayList<Seat> seats = new ArrayList<>();
        LocalDate flightDate; // дата вылета

        Airplane(LocalDate flightDate) {
            this.flightDate = flightDate;
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
            System.out.println("\n--- Список мест на рейс " + flightDate + " ---");
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
                if (s.isExpired()) s.cancel(); // 🕒 авто-сброс просроченной брони
                System.out.print(s.status.equals("свободен") ? "[O]" :
                    s.status.equals("забронирован") ? "[B]" : "[P]");
                if (i % 5 == 0) System.out.println();
            }
        }
    }

    // =================== КЛАСС СИСТЕМЫ БРОНИРОВАНИЯ ===================
    static class BookingSystem {
        Airplane airplane;
        String fileName = "seats.dat";

        BookingSystem() {
            airplane = load();
            if (airplane == null) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Введите дату вылета (в формате ГГГГ-ММ-ДД): ");
                String input = sc.nextLine();
                LocalDate date = LocalDate.parse(input);
                airplane = new Airplane(date);
            }
        }

        void showSeats() {
            airplane.showAll();
            airplane.showVisual();
            save();
        }

        void bookSeat(int number, String name) {
            Seat seat = airplane.findSeat(number);
            if (seat == null) {
                System.out.println("Такого места нет.");
                return;
            }
            if (!seat.status.equals("свободен")) {
                System.out.println("Место уже занято или оплачено.");
                return;
            }
            seat.book(name);
            save();
            System.out.println("Место успешно забронировано на имя " + name);
        }

        void payForSeat(int number) {
            Seat seat = airplane.findSeat(number);
            if (seat == null) {
                System.out.println("Такого места нет.");
                return;
            }
            seat.pay();
            save();
        }

        void cancelBooking(int number) {
            Seat seat = airplane.findSeat(number);
            if (seat == null) {
                System.out.println("Такого места нет.");
                return;
            }
            if (seat.status.equals("свободен")) {
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

    // =================== MAIN ===================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1. Показать все места");
            System.out.println("2. Забронировать место");
            System.out.println("3. Оплатить бронь");
            System.out.println("4. Снять бронь");
            System.out.println("5. Информация о месте");
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
                system.payForSeat(num);
            } else if (choice == 4) {
                System.out.print("Введите номер места: ");
                int num = sc.nextInt();
                sc.nextLine();
                system.cancelBooking(num);
            } else if (choice == 5) {
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
