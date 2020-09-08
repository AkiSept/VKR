package main;

import gui.frame.FrameWindow;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {

        JFrame myWindows = new FrameWindow();
        myWindows.setTitle("Оценки ПНРС");
        myWindows.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myWindows.setLayout(null);
        myWindows.setResizable(false);
        myWindows.setMinimumSize(new Dimension(910, 590));
        myWindows.setLocationRelativeTo(null);
        myWindows.setVisible(true);

    }
}
