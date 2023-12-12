import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class billDAO {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // Default constructor
    public billDAO() {
        }

    // Establish a database connection
    private void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Update the connection URL, username, and password based on your database configuration
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john123");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found.", e);
            }
        }
    }

    // Close the database connection
    private void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            connect.close();
        }
    }

    // Get a list of all bills
    public List<bill> listAllBills() throws SQLException {
        List<bill> billList = new ArrayList<>();

        // SQL query to select all bills
        String sql = "SELECT * FROM Bills";

        connect_func();
        preparedStatement = connect.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int orderID = resultSet.getInt("orderid");
            double price = resultSet.getDouble("price");
            double discount = resultSet.getDouble("discount");
            double balance = resultSet.getDouble("balance");
            String status = resultSet.getString("status");
            String note = resultSet.getString("note");
            
            bill bill = new bill(id, status, orderID, price, discount, balance, note);
            billList.add(bill);
        }

        resultSet.close();
        disconnect();

        return billList;
    }

    // Insert a new bill
    public void insert(bill bill) throws SQLException {
        connect_func();
        String sql = "INSERT INTO Bills (orderid, price, discount, balance, status) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connect.prepareStatement(sql);

        preparedStatement.setInt(1, bill.getOrderID());
        preparedStatement.setDouble(2, bill.getPrice());
        preparedStatement.setDouble(3, bill.getDiscount());
        preparedStatement.setDouble(4, bill.getBalance());
        preparedStatement.setString(5, bill.getStatus());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }

    // Update a bill
    public boolean update(bill bill) throws SQLException {
        connect_func();
        String sql = "UPDATE Bills SET orderid = ?, price = ?, discount = ?, balance = ?, status = ? WHERE id = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, bill.getOrderID());
        preparedStatement.setDouble(2, bill.getPrice());
        preparedStatement.setDouble(3, bill.getDiscount());
        preparedStatement.setDouble(4, bill.getBalance());
        preparedStatement.setString(5, bill.getStatus());
        preparedStatement.setInt(6, bill.getID());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }

    // Delete a bill
    public boolean delete(int billID) throws SQLException {
        connect_func();
        String sql = "DELETE FROM Bills WHERE id = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

    // Get a bill by ID
    public bill getBillById(int billID) throws SQLException {
        connect_func();
        String sql = "SELECT * FROM Bills WHERE id = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);

        ResultSet resultSet = preparedStatement.executeQuery();

        bill bill = null;
        if (resultSet.next()) {
            int orderID = resultSet.getInt("orderid");
            double price = resultSet.getDouble("price");
            double discount = resultSet.getDouble("discount");
            double balance = resultSet.getDouble("balance");
            String status = resultSet.getString("status");
            String note = resultSet.getString("note");

            bill = new bill(billID, status, orderID, price, discount, balance, note);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return bill;
    }

    // Check the validity of a billID
    public boolean isValid(int billID) throws SQLException {
        connect_func();
        String sql = "SELECT * FROM Bills WHERE id = ?";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean valid = !resultSet.next(); // If resultSet is empty, the ID is valid

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return valid;
    }

    // Get overdue bills
    public List<bill> getOverdueBills() {
        List<bill> overdueBills = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT * FROM Bills WHERE DATEDIFF(NOW(), scheduleend) > 7 AND status = 'Unpaid'";
            preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate overdueBills from the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int orderID = resultSet.getInt("orderid");
                double price = resultSet.getDouble("price");
                double discount = resultSet.getDouble("discount");
                double balance = resultSet.getDouble("balance");
                String status = resultSet.getString("status");
                String note = resultSet.getString("note");

                bill bill = new bill(id, status, orderID, price, discount, balance, note);
                overdueBills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return overdueBills;
    }
}
