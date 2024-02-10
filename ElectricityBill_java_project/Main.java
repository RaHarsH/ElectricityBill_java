import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Customer> customerList = new ArrayList<>();
    private static int customerIdCounter = 0; 
    private static int billNumberCounter = 1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Add Customer");
            System.out.println("2. View Customer List");
            System.out.println("3. Enter Bill Details");
            System.out.println("4. View Customer Details");
            System.out.println("5. View Unpaid Bills");
            System.out.println("6. Calculate Total Amount Due");
            System.out.println("7. Pay Bill");
            System.out.println("8. Update Customer Details");
            System.out.println("9. Update Bill Details");
            System.out.println("10. Delete Bill");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addCustomer();
                    break; 
                case 2:
                    viewCustomerList();
                    break;
                case 3:
                    enterBillDetails();
                    break;
                case 4:
                    viewCustomerDetails();
                    break;
                case 5:
                    viewUnpaidBills();
                    break;
                case 6:
                    calculateTotalAmountDue();
                    break;
                case 7:
                    payBill();
                    break;
                case 8:
                    updateCustomerDetails();
                    break;
                case 9:
                    updateBillDetails();
                    calculateTotalAmountDue();
                    break;
                case 10:
                    deleteBill();
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 11.");
            }
        }

     sc.close();
    }

    private static void addCustomer() {
        System.out.print("Enter customer name: ");
        String cusName = sc.nextLine();

        System.out.print("Enter customer address: ");
        String cusAddress = sc.nextLine();

        Customer customer = new Customer(cusName, customerIdCounter++, cusAddress);
        customerList.add(customer);
        System.out.println("Customer added successfully.");
    }

    private static void viewCustomerList() {
        System.out.println("\nCustomer List:");
        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customerList) {
                System.out.println("Customer ID: " + customer.getId());
            }
        }
    }

    private static void enterBillDetails() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter house number: ");
        int houseNumber = sc.nextInt();

        System.out.print("Enter unit rate: ");
        double unitRate = sc.nextDouble();

        System.out.print("Enter units consumed: ");
        int unitsConsumed = sc.nextInt();

        Bill bill = new Bill(billNumberCounter++, houseNumber, unitRate, unitsConsumed);
        customer.addBill(bill);
        System.out.println("Bill added successfully.");
    }

    private static void viewCustomerDetails() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            System.out.println("\nCustomer Details:");
            customer.getCustomerDetails();
        }
    }

    private static void viewUnpaidBills() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        List<Bill> unpaidBills = customer.getUnpaidBills();
        if (unpaidBills.isEmpty()) {
            System.out.println("No unpaid bills found for this customer.");
        } else {
            System.out.println("Unpaid Bills:");
            for (Bill bill : unpaidBills) {
                System.out.println("Bill Number: " + bill.getBillNumber());
            }
        }
    }

    private static void calculateTotalAmountDue() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        double totalAmountDue = customer.calculateTotalAmountDue();
        System.out.println("Total Amount Due for Customer " + customer.getId() + ": Rs" + totalAmountDue);
    }

    private static void payBill() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter bill number to pay: ");
        int billNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        Bill bill = findBillByNumber(customer, billNumber);
        if (bill == null) {
            System.out.println("Bill not found.");
        } else if (bill.isPaid()) {
            System.out.println("Bill is already paid.");
        } else {
            bill.markAsPaid(); // Mark bill as paid
            customer.markAsPaid(); // Mark customer as paid
            System.out.println("Bill paid successfully.");
        }
    }

    private static void updateCustomerDetails() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        System.out.print("Enter new address: ");
        String newAddress = sc.nextLine();

        customer.updateDetails(newName, newAddress);
        System.out.println("Customer details updated successfully.");
    }

    private static void updateBillDetails() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter bill number to update: ");
        int billNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        Bill bill = findBillByNumber(customer, billNumber);
        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        System.out.print("Enter new unit rate: ");
        double newUnitRate = sc.nextDouble();

        System.out.print("Enter new units consumed: ");
        int newUnitsConsumed = sc.nextInt();

        bill.updateBillDetails(newUnitRate, newUnitsConsumed);
        System.out.println("Bill details updated successfully.");
    }

    private static void deleteBill() {
        System.out.print("Enter customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter bill number to delete: ");
        int billNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        Bill bill = findBillByNumber(customer, billNumber);
        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        customer.getBillHistory().remove(bill);
        System.out.println("Bill deleted successfully.");
    }

    private static Customer findCustomerById(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getId() == customerId) { // Correct comparison
                return customer;
            }
        }
        return null;
    }

    private static Bill findBillByNumber(Customer customer, int billNumber) {
        for (Bill bill : customer.getBillHistory()) {
            if (bill.getBillNumber() == billNumber) {
                return bill;
            }
        }
        return null;
    }
}
