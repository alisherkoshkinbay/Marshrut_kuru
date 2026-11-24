import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Қайдан: ");
        String from = scanner.nextLine();
        System.out.print("Қайда: ");
        String to = scanner.nextLine();
        System.out.print("Қашықтық (км): ");
        double distance = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));

        Navigator navigator = new Navigator(new WalkingStrategy());

        while (true) {
            System.out.println("\n1 - Жаяу\n2 - Көлікпен\n3 - Қоғамдық көлік\n0 - Шығу");
            System.out.print("Тәсілді таңдаңыз: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println("Бағдарлама аяқталды.");
                break;
            }

            switch (choice) {
                case "1":
                    navigator.setStrategy(new WalkingStrategy());
                    break;
                case "2":
                    navigator.setStrategy(new CarStrategy());
                    break;
                case "3":
                    navigator.setStrategy(new PublicTransportStrategy());
                    break;
                default:
                    System.out.println("Қате таңдау, қайта енгізіңіз.");
                    continue;
            }

            System.out.println("\n" + navigator.buildRoute(from, to, distance));
        }
        scanner.close();
    }
}
