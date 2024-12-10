package software.ulpgc.control;

import software.ulpgc.model.Title;

import java.io.File;
import java.sql.*;
import java.util.Iterator;

public class SQLiteTitleReader implements TitleReader {
    private final Connection connection;
    private final PreparedStatement selectStatment;
    private final PreparedStatement randomStatment;

    public SQLiteTitleReader(File dbFile) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
        this.selectStatment = connection.prepareStatement("SELECT * FROM titles");
        this.randomStatment = connection.prepareStatement("SELECT * FROM titles ORDER BY RANDOM() LIMIT 1");
        selectStatment.execute();
    }


    @Override
    public Iterator<Title> read() throws SQLException {
        return new Iterator<Title>() {
            final ResultSet resultSet = selectStatment.executeQuery();

            @Override
            public boolean hasNext() {
                try {
                    return resultSet.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Title next() {
                try {
                    return new Title(resultSet.getString(1),
                            Title.TitleType.valueOf(resultSet.getString(2)),
                            resultSet.getString(3));
                } catch (SQLException e) {
                    return null;
                }
            }

            ;
        };
    }

        public String getRandomTitle(){
           try (ResultSet resultSet = randomStatment.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString(3);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
