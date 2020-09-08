package gui.panel;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelInputData extends JPanel {

    public static JTextField fMaxValue, fProbableValue;
    public static JTextField fSample, fGamma;
    public static JTextField fOperatingTime, fNumberIntervals;

    public PanelInputData() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        setBounds(30, 10, 420, 200);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Входные данные", TitledBorder.LEFT, TitledBorder.TOP));
        JLabel lProbableValue = new JLabel("<html>Наиболее вероятное "
                + "значение  t<sub>0</sub></html>");
        lProbableValue.setSize(200, 20);
        lProbableValue.setLocation(20, 23);
        add(lProbableValue);
        fProbableValue = new JTextField("4");
        fProbableValue.setSize(50, 20);
        fProbableValue.setLocation(260, 20);
        add(fProbableValue);
        JLabel lMaxValue = new JLabel("<html>Наибольшее "
                + "значение t<sub>m</sub></html>");
        lMaxValue.setSize(270, 20);
        lMaxValue.setLocation(20, 53);
        add(lMaxValue);
        fMaxValue = new JTextField("11", 10);
        fMaxValue.setSize(50, 20);
        fMaxValue.setLocation(260, 50);
        add(fMaxValue);

        JLabel lSample = new JLabel("Объем выборки n");
        lSample.setSize(270, 20);
        lSample.setLocation(20, 83);
        add(lSample);
        fSample = new JTextField("20000", 10);
        fSample.setSize(50, 20);
        fSample.setLocation(260, 80);
        add(fSample);

        JLabel lGamma = new JLabel("Вероятность " + "\u03b3"); //
        lGamma.setSize(270, 20);
        lGamma.setLocation(20, 113);
        add(lGamma);
        fGamma = new JTextField("0.9", 10);
        fGamma.setSize(50, 20);
        fGamma.setLocation(260, 110);
        add(fGamma);

        JLabel lOperatingTime = new JLabel("Время эксплуатации x");
        lOperatingTime.setSize(270, 20);
        lOperatingTime.setLocation(20, 143);
        add(lOperatingTime);
        fOperatingTime = new JTextField("2", 10);
        fOperatingTime.setSize(50, 20);
        fOperatingTime.setLocation(260, 140);
        add(fOperatingTime);

        JLabel lNumberIntervals = new JLabel("Количество интервалов J");
        lNumberIntervals.setSize(270, 20);
        lNumberIntervals.setLocation(20, 173);
        add(lNumberIntervals);
        fNumberIntervals = new JTextField("44", 10);
        fNumberIntervals.setSize(50, 20);
        fNumberIntervals.setLocation(260, 170);
        add(fNumberIntervals);

    }
}
