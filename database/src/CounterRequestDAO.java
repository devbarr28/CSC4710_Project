import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CounterRequestDAO {
    private Connection connection;

    public CounterRequestDAO(Connection connection) {
        this.connection = connection;
    }
    public CounterRequestDAO() {
    	
    }
    public void insertCounterRequest(CounterRequest counterRequest) throws SQLException {
        String query = "INSERT INTO CounterRequest (quoteId, counterNotes, counterPrice) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, counterRequest.getQuoteId());
            preparedStatement.setString(2, counterRequest.getCounterNotes());
            preparedStatement.setDouble(3, counterRequest.getCounterPrice());
            preparedStatement.executeUpdate();
        }
    }

    public List<CounterRequest> getAllCounterRequests() throws SQLException {
        List<CounterRequest> counterRequests = new ArrayList<>();
        String query = "SELECT * FROM CounterRequest";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CounterRequest counterRequest = extractCounterRequestFromResultSet(resultSet);
                counterRequests.add(counterRequest);
            }
        }
        return counterRequests;
    }

    private CounterRequest extractCounterRequestFromResultSet(ResultSet resultSet) throws SQLException {
        CounterRequest counterRequest = new CounterRequest();
        counterRequest.setQuoteId(resultSet.getInt("quoteId"));
        counterRequest.setCounterNotes(resultSet.getString("counterNotes"));
        counterRequest.setCounterPrice(resultSet.getDouble("counterPrice"));
        return counterRequest;
    }

   public boolean delete(int quoteId) throws SQLException {
        String sql = "DELETE FROM CounterRequest WHERE quoteId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, quoteId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    public boolean update(CounterRequest counterRequest) throws SQLException {
        String sql = "UPDATE CounterRequest SET counterNotes = ?, counterPrice = ? WHERE quoteId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, counterRequest.getCounterNotes());
            preparedStatement.setDouble(2, counterRequest.getCounterPrice());
            preparedStatement.setInt(3, counterRequest.getQuoteId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}
