public class BoxWork implements Runnable {

    private Customer customer;

    BoxWork(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void run() {
        BoxFrame boxFrame = new BoxFrame(customer);
        boxFrame.setVisible(true);
    }
}
