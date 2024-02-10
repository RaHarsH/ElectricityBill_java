class Bill {
    private static final double FIXED_CHARGE = 20.0;
    private int billNumber;
    private double unitRate;
    private int unitsConsumed;
    private boolean isPaid;

    public Bill(int billNumber, int houseNumber, double unitRate, int unitsConsumed) {
        this.billNumber = billNumber;
        this.unitRate = unitRate;
        this.unitsConsumed = unitsConsumed;
        this.isPaid = false;
    }

    public double generateBill() {
        return (unitsConsumed * unitRate) + FIXED_CHARGE;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void updateBillDetails(double unitRate, int unitsConsumed) {
        this.unitRate = unitRate;
        this.unitsConsumed = unitsConsumed;
    }
}
