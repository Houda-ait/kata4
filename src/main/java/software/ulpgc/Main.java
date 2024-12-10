package software.ulpgc;



import software.ulpgc.view.MainFrame;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        File tsvFile = new File("title.basics.tsv");
        File dbFile = new File("titles.db");
        new TitleLoader().loadTitle(tsvFile, dbFile);
        MainFrame mainFrame = new MainFrame();
        SQLiteTitleReader titleReader = new SQLiteTitleReader(dbFile);
        mainFrame.setTitleReader(titleReader);
        mainFrame.setVisible(true);
    }

}
