import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDAO {
    private Connection connection;
    private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;


    public StatisticsDAO(Connection connection) {
        this.connection = connection;
    }
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john123");
            System.out.println(connect);
        }
    }
    public List<statistics> getStatistics() throws SQLException {
        List<statistics> statisticsList = new ArrayList<>();
        String sql = "SELECT clientName, COUNT(qr.treeId) AS totalTrees, " +
                     "SUM(qr.price) AS totalDueAmount, " +
                     "SUM(CASE WHEN b.status = 'paid' THEN b.balance ELSE 0 END) AS totalPaidAmount, " +
                     "MAX(qr.scheduleEnd) AS lastWorkDate " +
                     "FROM QuoteRequests qr " +
                     "LEFT JOIN Bills b ON qr.quoteId = b.quoteId " +
                     "GROUP BY clientName";

        connect_func();
        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String clientName = resultSet.getString("clientName");
                int totalTrees = resultSet.getInt("totalTrees");
                double totalDueAmount = resultSet.getDouble("totalDueAmount");
                double totalPaidAmount = resultSet.getDouble("totalPaidAmount");
                String lastWorkDate = resultSet.getString("lastWorkDate");

                statistics statistics = new statistics(clientName, totalTrees, totalDueAmount, totalPaidAmount, lastWorkDate);
                statisticsList.add(statistics);
            }
        }

        return statisticsList;
    }

}
