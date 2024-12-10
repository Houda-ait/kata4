package software.ulpgc.control;

import software.ulpgc.model.Title;

import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;

public class TitleLoader {
    public void loadTitle(File source, File target) throws SQLException {
        TitleReader  reader = new TsvTitleReader(source);
        SQLiteTitleWriter dbWriter = new SQLiteTitleWriter(target);
        Iterator<Title> titles = reader.read();
        while(titles.hasNext()){
            dbWriter.write(titles.next());
        }
    }
}
