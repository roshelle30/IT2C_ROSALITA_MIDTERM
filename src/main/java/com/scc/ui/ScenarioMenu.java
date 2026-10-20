package com.scc.ui;

import com.scc.dao.ScenarioDao;
import com.scc.model.Ticket;
import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private ScenarioDao dao = new ScenarioDao();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("================================================");
            System.out.println("           EVENT TICKET SALES SYSTEM            ");
            System.out.println("================================================");
            System.out.println("[1] Add New Ticket");
            System.out.println("[2] View Sold Tickets");
            System.out.println("[3] Exit System");
            System.out.println("------------------------------------------------");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\n--- ISSUE EVENT TICKET ---");
                    System.out.print("Enter Attendee Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Ticket Tier (REGULAR / VIP / VVIP): ");
                    String tier = scanner.nextLine();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume buffer newline

                    System.out.print("Enter Payment Method (CASH / GCASH / CARD): ");
                    String payment = scanner.nextLine();

                    Ticket newTicket = new Ticket(name, tier, price, payment);
                    dao.addRecord(newTicket);

                    System.out.println("\nSUCCESS: Ticket issued to attendee!\n");
                    break;

                case 2:
                    System.out.println("\n================================================");
                    System.out.println("             EVENT TICKET PURCHASES             ");
                    System.out.println("================================================");
                    System.out.println("+----+------------------+-------------+----------+---------+");
                    System.out.println("| ID | ATTENDEE NAME    | TICKET TIER | PRICE    | PAYMENT |");
                    System.out.println("+----+------------------+-------------+----------+---------+");

                    List<Ticket> tickets = dao.getAllRecords();
                    if (tickets.isEmpty()) {
                        System.out.println("| No records found.                                       |");
                    } else {
                        for (Ticket t : tickets) {
                            System.out.printf("| %-2d | %-16s | %-11s | %-8.2f | %-7s |\n",
                                    t.getId(),
                                    t.getAttendeeName(),
                                    t.getTicketTier(),
                                    t.getPrice(),
                                    t.getPaymentMethod());
                        }
                    }

                    System.out.println("+----+------------------+-------------+----------+---------+");
                    System.out.println("Total Records Displayed: " + tickets.size() + "\n");
                    break;

                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
                    break;
            }
        }
    }
}