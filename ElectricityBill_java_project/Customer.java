import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Customer {
    private String cus_name;
    private int cus_id;
    private String cus_address;
    private List<Bill> bills;
    private boolean hasPaid;

    public Customer(String cus_name, int cus_id, String cus_address) {
        this.cus_name = cus_name;
        this.cus_id = cus_id;
        this.cus_address = cus_address;
        this.bills = new ArrayList<>();
        this.hasPaid = false;
    }

    public void getCustomerDetails() {
        System.out.println("Customer Name: " + cus_name);
        System.out.println("Customer ID: " + cus_id);
        System.out.println("Customer Address: " + cus_address);
        System.out.println("Payment Status: " + (hasPaid ? "Paid" : "Not Paid"));
    }

    public int getId() {
        return cus_id;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public List<Bill> getBillHistory() {
        return bills;
    }

    public List<Bill> getUnpaidBills() {
        List<Bill> unpaidBills = new ArrayList<>();
        for (Bill bill : bills) {
            if (!bill.isPaid()) {
                unpaidBills.add(bill);
            }
        }
        return unpaidBills;
    }

    public double calculateTotalAmountDue() {
        double totalAmount = 0;
        for (Bill bill : bills) {
            if (!bill.isPaid()) {
                totalAmount += bill.generateBill();
            }
        }
        return totalAmount;
    }

    public void markAsPaid() {
        hasPaid = true;
    }

    public boolean hasPaid() {
        return hasPaid;
    }

    public void updateDetails(String newName, String newAddress) {
        this.cus_name = newName;
        this.cus_address = newAddress;
    }
}