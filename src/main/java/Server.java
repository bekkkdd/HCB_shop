import java.sql.SQLException;

public class Server {
    public static Customer getCustomerByFace(Long face) {
        try {
            return Database.getCustomer(face);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    public static boolean pay(Customer customer, Double money) {
        if (customer.getBankAccount() - money >= 0) {
            customer.setBankAccount(customer.getBankAccount() - money);
            Database.updateCustomerPayed(customer);
            return true;
        }
        return false;
    }

    public static boolean makeCustomerBoxActive(Customer customer, boolean active) {
        return Database.makeBoxActive(customer, active);
    }


}
