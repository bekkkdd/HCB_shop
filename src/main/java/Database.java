import java.sql.*;
import java.util.ArrayList;

public class Database {

    public static final String Database_name = "hcb_shop";
    public static final String Database_user = "root";
    public static final String Database_pass = "";

    public static Connection con;

    public static boolean init() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database_name + "?useUnicode=true&serverTimezone=UTC", Database_user,
                        Database_pass);
            } catch (SQLException e) {

                System.out.println("Error: Database Connection Failed ! Please check the connection Setting");

                return false;

            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static boolean makeBoxActive(Customer customer, boolean isBoxActive){
        try
        {
            String query = "update customers set is_active_box = ? where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setBoolean(1, isBoxActive);
            preparedStmt.setLong(2, customer.getId());
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean updateCustomerPayed(Customer customer){
        try
        {
            String query = "update customers set bank_account = ? where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setDouble(1, customer.getBankAccount());
            preparedStmt.setLong(2, customer.getId());
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static Customer getCustomer(long id) throws SQLException {

        Customer customer = new Customer();

        try {
            String sql = "select * from customers where face_code=" + id + " limit 1";

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setAge(rs.getInt("age"));
                customer.setBankAccount(rs.getDouble("bank_account"));
                customer.setFaceCode(rs.getLong("face_code"));
                customer.setCrimeCoef(rs.getDouble("crime_coef"));
                customer.setActiveBox(rs.getBoolean("is_active_box"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return customer;
    }

    public static ArrayList<Good> getAllGoods() throws SQLException {

        ArrayList<Good> goods = new ArrayList<Good>();

        try {
            String sql = "select * from goods";

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Good good = new Good();

                good.setId(rs.getLong("id"));
                good.setName(rs.getString("name"));
                good.setPrice(rs.getDouble("price"));
                good.setAgeLimit(rs.getInt("age_limit"));
                good.setWeight(rs.getDouble("weight"));

                goods.add(good);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return goods;
    }
}
