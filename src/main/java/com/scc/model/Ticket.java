package com.scc.model;

public class Ticket {
    private int id;
    private String attendeeName;
    private String ticketTier;
    private double price;
    private String paymentMethod;

    // Constructor para sa pag-retrieve gikan sa Database (naay ID)
    public Ticket(int id, String attendeeName, String ticketTier, double price, String paymentMethod) {
        this.id = id;
        this.attendeeName = attendeeName;
        this.ticketTier = ticketTier;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }

    // Constructor para sa pag-add og bag-ong Ticket (Auto-increment ang ID)
    public Ticket(String attendeeName, String ticketTier, double price, String paymentMethod) {
        this.attendeeName = attendeeName;
        this.ticketTier = ticketTier;
        this.price = price;
        this.paymentMethod = paymentMethod;
    }

    public int getId() { return id; }
    public String getAttendeeName() { return attendeeName; }
    public String getTicketTier() { return ticketTier; }
    public double getPrice() { return price; }
    public String getPaymentMethod() { return paymentMethod; }
}