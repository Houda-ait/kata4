package software.ulpgc.control;

import software.ulpgc.model.Title;

import java.io.File;
import java.sql.*;

public class SQLiteTitleWriter implements TitleWriter{
    private final Connection connection;
    private final String createTable = """
            CREATE TABLE IF NOT EXISTS titles(
            id TEXT PRIMARY KEY,
            type TEXT NOT NULL,
            primaryTitle TEXT NOT NULL)
            """;

    private final String insertSQL = "INSERT OR IGNORE INTO titles(id, type, primaryTitle) VALUES(?,?,?)";
    private PreparedStatement insertStatment;

    public SQLiteTitleWriter(File file) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" +  file.getAbsolutePath());
        prepareDatabase();
    }

    private void prepareDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTable);
            insertStatment = connection.prepareStatement(this.insertSQL);
            connection.setAutoCommit(false);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(Title title) {
        try {
            insertStatment.setString(1, title.id());
            insertStatment.setString(2, title.titleType().name());
            insertStatment.setString(3, title.primaryTitle());
            insertStatment.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
