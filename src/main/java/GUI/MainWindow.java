package GUI;

import javax.swing.*;

public class MainWindow extends JFrame {


    public MainWindow() {
        super("Lab 7 Kovaliv");
        this.setBounds(40, 40, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Webcam());

        setVisible(true);
    }
}
