import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDAO {
    private Connection connection;

    public StatisticsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<statistics> getStatistics() throws SQLException {
        List<statistics> statisticsList = new ArrayList<>();
        String sql = "SELECT clientName, COUNT(treeId) AS totalTrees, " +
                     "SUM(price) AS totalDueAmount, SUM(paidAmount) AS totalPaidAmount, " +
                     "MAX(scheduleEnd) AS lastWorkDate " +
                     "FROM YourTableName JOIN ... (Include necessary JOIN conditions) " +
                     "GROUP BY clientName";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	statistics statistics = new statistics();
                statistics.setClientName(resultSet.getString("clientName"));
                statistics.setTotalTrees(resultSet.getInt("totalTrees"));
                statistics.setTotalDueAmount(resultSet.getDouble("totalDueAmount"));
                statistics.setTotalPaidAmount(resultSet.getDouble("totalPaidAmount"));
                statistics.setLastWorkDate(resultSet.getString("lastWorkDate"));

                statisticsList.add(statistics);
            }
        }

        return statisticsList;
    }
}
