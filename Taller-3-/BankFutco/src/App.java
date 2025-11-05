package app;

import java.util.Scanner;
import model.Account;
import model.Balance;
import model.Card;
import model.Loan;
import services.*;

public class App {

    private static final AccountService accountService = new AccountService();
    private static final BalanceService balanceService = new BalanceService();
    private static final LoansService loansService = new LoansService();
    private static final CardsService cardsService = new CardsService();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();

                switch (option) {
                    case "1" -> runAccountMenu(sc);
                    case "2" -> runBalanceMenu(sc);
                    case "3" -> runLoanMenu(sc);
                    case "4" -> runCardMenu(sc);
                    case "0" -> {
                        running = false;
                        System.out.println("👋 Saliendo...");
                    }
                    default -> System.out.println("❌ Opción no válida. Intente de nuevo.");
                }
            }
        }
    }

    // ---------- Menú principal ----------
    private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }


    private static void runAccountMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Account");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1" -> {
                    System.out.println("Crear nueva cuenta:");
                    System.out.print("Número de cuenta: ");
                    String id = sc.nextLine();
                    System.out.print("Titular: ");
                    String name = sc.nextLine();
                    System.out.print("Correo: ");
                    String email = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String phone = sc.nextLine();
                    System.out.print("Tipo de cuenta: ");
                    String type = sc.nextLine();
                    System.out.print("Dirección: ");
                    String address = sc.nextLine();

                    Account acc = new Account(id, name, email, phone, type, address);
                    accountService.save(acc);
                    System.out.println("✅ Cuenta registrada con éxito.");
                }
                case "2" -> {
                    System.out.print("Ingrese número de cuenta: ");
                    String id = sc.nextLine();
                    accountService.findById(id).ifPresentOrElse(
                        a -> System.out.println("✅ Encontrado: " + a),
                        () -> System.out.println("⚠️ No se encontró la cuenta.")
                    );
                }
                case "3" -> accountService.findAll().forEach(System.out::println);
                case "4" -> {
                    System.out.print("Ingrese número de cuenta a actualizar: ");
                    String id = sc.nextLine();
                    System.out.print("Nuevo titular: ");
                    String name = sc.nextLine();
                    System.out.print("Nuevo correo: ");
                    String email = sc.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String phone = sc.nextLine();
                    System.out.print("Nuevo tipo de cuenta: ");
                    String type = sc.nextLine();
                    System.out.print("Nueva dirección: ");
                    String address = sc.nextLine();

                    Account updated = new Account(id, name, email, phone, type, address);
                    accountService.save(updated);
                    System.out.println("✅ Cuenta actualizada.");
                }
                case "5" -> {
                    System.out.print("Ingrese número de cuenta a eliminar: ");
                    String id = sc.nextLine();
                    if (accountService.deleteById(id))
                        System.out.println("🗑️ Cuenta eliminada.");
                    else
                        System.out.println("⚠️ No se encontró la cuenta a eliminar.");
                }
                case "0" -> back = true;
                default -> System.out.println("❌ Opción no válida.");
            }
        }
    }

    private static void runBalanceMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Balance");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1" -> {
                    System.out.println("Crear nuevo balance:");
                    System.out.print("Número de cuenta: ");
                    String id = sc.nextLine();
                    System.out.print("Monto: ");
                    double amount = sc.nextDouble();
                    sc.nextLine(); // limpiar buffer
                    Balance b = new Balance(id, amount);
                    balanceService.save(b);
                    System.out.println("✅ Balance guardado.");
                }
                case "2" -> {
                    System.out.print("Número de cuenta: ");
                    String id = sc.nextLine();
                    balanceService.findById(id).ifPresentOrElse(
                        bal -> System.out.println("✅ Encontrado: " + bal),
                        () -> System.out.println("⚠️ No se encontró el balance.")
                    );
                }
                case "3" -> balanceService.findAll().forEach(System.out::println);
                case "4" -> {
                    System.out.print("Número de cuenta a actualizar: ");
                    String id = sc.nextLine();
                    System.out.print("Nuevo monto: ");
                    double newAmount = sc.nextDouble();
                    sc.nextLine();
                    Balance updated = new Balance(id, newAmount);
                    balanceService.save(updated);
                    System.out.println("✅ Balance actualizado.");
                }
                case "5" -> {
                    System.out.print("Número de cuenta a eliminar: ");
                    String id = sc.nextLine();
                    if (balanceService.deleteById(id))
                        System.out.println("🗑️ Balance eliminado.");
                    else
                        System.out.println("⚠️ No se encontró el balance a eliminar.");
                }
                case "0" -> back = true;
                default -> System.out.println("❌ Opción inválida.");
            }
        }
    }

    private static void runLoanMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Loan");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1" -> {
                    System.out.println("Crear nuevo préstamo:");
                    System.out.print("ID préstamo: ");
                    String id = sc.nextLine();
                    System.out.print("Monto: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Cliente: ");
                    String customer = sc.nextLine();
                    Loan loan = new Loan(id, amount, customer);
                    loansService.save(loan);
                    System.out.println("✅ Préstamo registrado.");
                }
                case "2" -> {
                    System.out.print("ID préstamo: ");
                    String id = sc.nextLine();
                    loansService.findById(id).ifPresentOrElse(
                        l -> System.out.println("✅ Encontrado: " + l),
                        () -> System.out.println("⚠️ No se encontró el préstamo.")
                    );
                }
                case "3" -> loansService.findAll().forEach(System.out::println);
                case "4" -> {
                    System.out.print("ID préstamo a actualizar: ");
                    String id = sc.nextLine();
                    System.out.print("Nuevo monto: ");
                    double newAmount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Nuevo cliente: ");
                    String customer = sc.nextLine();
                    Loan updated = new Loan(id, newAmount, customer);
                    loansService.save(updated);
                    System.out.println("✅ Préstamo actualizado.");
                }
                case "5" -> {
                    System.out.print("ID préstamo a eliminar: ");
                    String id = sc.nextLine();
                    if (loansService.deleteById(id))
                        System.out.println("🗑️ Préstamo eliminado.");
                    else
                        System.out.println("⚠️ No se encontró el préstamo.");
                }
                case "0" -> back = true;
                default -> System.out.println("❌ Opción inválida.");
            }
        }
    }

    private static void runCardMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Card");
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1" -> {
                    System.out.println("Crear nueva tarjeta:");
                    System.out.print("Número de tarjeta: ");
                    String number = sc.nextLine();
                    System.out.print("Titular: ");
                    String holder = sc.nextLine();
                    System.out.print("Límite de crédito: ");
                    double limit = sc.nextDouble();
                    sc.nextLine();
                    Card card = new Card(number, holder, limit);
                    cardsService.save(card);
                    System.out.println("✅ Tarjeta registrada.");
                }
                case "2" -> {
                    System.out.print("Número de tarjeta: ");
                    String number = sc.nextLine();
                    cardsService.findById(number).ifPresentOrElse(
                        c -> System.out.println("✅ Encontrada: " + c),
                        () -> System.out.println("⚠️ No se encontró la tarjeta.")
                    );
                }
                case "3" -> cardsService.findAll().forEach(System.out::println);
                case "4" -> {
                    System.out.print("Número de tarjeta a actualizar: ");
                    String number = sc.nextLine();
                    System.out.print("Nuevo titular: ");
                    String holder = sc.nextLine();
                    System.out.print("Nuevo límite: ");
                    double newLimit = sc.nextDouble();
                    sc.nextLine();
                    Card updated = new Card(number, holder, newLimit);
                    cardsService.save(updated);
                    System.out.println("✅ Tarjeta actualizada.");
                }
                case "5" -> {
                    System.out.print("Número de tarjeta a eliminar: ");
                    String number = sc.nextLine();
                    if (cardsService.deleteById(number))
                        System.out.println("🗑️ Tarjeta eliminada.");
                    else
                        System.out.println("⚠️ No se encontró la tarjeta.");
                }
                case "0" -> back = true;
                default -> System.out.println("❌ Opción inválida.");
            }
        }
    }

    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }
}
