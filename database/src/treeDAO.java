import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class treeDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;


    public treeDAO(Connection connection) {
        this.connect = connection;
    }
    protected void connect_func() throws SQLException {

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
    

   
    public void insertTree(Trees tree) throws SQLException {
        String query = "INSERT INTO Trees (quoteid, size, height, distanceFromHouse) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, tree.getQuote().getQuoteID());
            preparedStatement.setDouble(2, tree.getSize());
            preparedStatement.setDouble(3, tree.getHeight());
            preparedStatement.setDouble(4, tree.getDistanceFromHouse());
            preparedStatement.executeUpdate();
        }
    }

    
    public List<Trees> getAllTrees() throws SQLException {
        List<Trees> treesList = new ArrayList<>();
        String query = "SELECT * FROM Trees";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Trees tree = extractTreeFromResultSet(resultSet);
                treesList.add(tree);
            }
        }
        return treesList;
    }

   
    private Trees extractTreeFromResultSet(ResultSet resultSet) throws SQLException {
        Trees tree = new Trees();
        tree.setId(resultSet.getInt("id"));
        tree.setQuote(new QuoteRequest(resultSet.getInt("quoteID")));
        tree.setSize(resultSet.getDouble("size"));
        tree.setHeight(resultSet.getDouble("height"));
        tree.setDistanceFromHouse(resultSet.getDouble("distanceFromHouse"));
        return tree;
    }

     public boolean delete(int treeId) throws SQLException {
        String sql = "DELETE FROM Trees WHERE id = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setInt(1, treeId);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    public boolean update(Trees tree) throws SQLException {
        String sql = "UPDATE Trees SET size = ?, height = ?, distanceFromHouse = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setDouble(1, tree.getSize());
            preparedStatement.setDouble(2, tree.getHeight());
            preparedStatement.setDouble(3, tree.getDistanceFromHouse());
            preparedStatement.setInt(4, tree.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    public List<Trees> getHighestTrees() {
        List<Trees> highestTrees = new ArrayList<>();
        try {
            connect_func();
            String sql = "SELECT * FROM Trees WHERE height = (SELECT MAX(height) FROM Trees)";
            preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            
            while (resultSet.next()) {
                Trees tree = new Trees();
                tree.setId(resultSet.getInt("id"));
                tree.setQuoteID(resultSet.getInt("quoteid"));
                tree.setSize(resultSet.getDouble("size"));
                tree.setHeight(resultSet.getDouble("height"));
                tree.setDistanceFromHouse(resultSet.getDouble("distanceFromHouse"));
                highestTrees.add(tree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return highestTrees;
    }


}
