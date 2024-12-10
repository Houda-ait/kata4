package software.ulpgc.view;

import software.ulpgc.control.SQLiteTitleReader;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JLabel label;
    SQLiteTitleReader titleReader;
    public MainFrame(){
        setTitle("Button display");
        setLocationRelativeTo(null);
        setSize(500, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton button = createButton();
        label = new JLabel();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(button);
        panel.add(label);

        add(panel, BorderLayout.CENTER);

    }

    private JButton createButton() {
        JButton button = new JButton("suggest a random show!");
        button.addActionListener(e -> getRandomTitle());
        return button;
    }


    private void getRandomTitle() {
        String title = titleReader.getRandomTitle();
        label.setText("SHOW :" + title);

    }


    public void setTitleReader(SQLiteTitleReader titleReader) {
        this.titleReader = titleReader;
    }
}




