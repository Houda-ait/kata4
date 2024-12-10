package software.ulpgc.control;

import software.ulpgc.model.Title;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public interface TitleReader {
    Iterator<Title> read() throws SQLException;
}
