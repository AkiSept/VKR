package gui.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelOutputData extends JPanel {

    //Аналитические 
    public static JTextField aAverageTime, aGammaRes;
    public static JTextField aRemainsRes, aGammaRemainsRes;

    //Числовые показатели
    public static JTextField nAverageTime, nGammaRes;
    public static JTextField nRemainsRes, nGammaRemainsRes;

    //Доверительный интервал
    public static JLabel lInterval;

    public PanelOutputData() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(30, 215, 420, 200);
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Выходные данные", TitledBorder.LEFT, TitledBorder.TOP));

        JLabel system = new JLabel("<html><p align=\"center\">Аналитические"
                + "</p><p align=\"center\">модели</p><html>");
        system.setSize(250, 40);
        system.setLocation(242, 10);
        system.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        add(system);

        JLabel lAverageTime = new JLabel("Средняя наработка до отказа");
        lAverageTime.setSize(280, 20);
        lAverageTime.setLocation(20, 50);
        add(lAverageTime);
        aAverageTime = new JTextField();
        aAverageTime.setSize(50, 20);
        aAverageTime.setLocation(260, 50);
        aAverageTime.setEditable(false);
        add(aAverageTime);

        JLabel lGammaRes = new JLabel("Гамма-процентный ресурс до отказ");
        lGammaRes.setSize(280, 20);
        lGammaRes.setLocation(20, 80);
        add(lGammaRes);
        aGammaRes = new JTextField();
        aGammaRes.setSize(50, 20);
        aGammaRes.setLocation(260, 80);
        aGammaRes.setEditable(false);
        add(aGammaRes);

        JLabel lRemainsRes = new JLabel("Средний остаточный ресурс до отказа");
        lRemainsRes.setSize(280, 20);
        lRemainsRes.setLocation(20, 110);
        add(lRemainsRes);
        aRemainsRes = new JTextField();
        aRemainsRes.setSize(50, 20);
        aRemainsRes.setLocation(260, 110);
        aRemainsRes.setEditable(false);
        add(aRemainsRes);

        JLabel lGammaRemainsRes = new JLabel("Гамма-процентный остаточный ресурс");
        lGammaRemainsRes.setSize(280, 20);
        lGammaRemainsRes.setLocation(20, 140);
        add(lGammaRemainsRes);
        aGammaRemainsRes = new JTextField();
        aGammaRemainsRes.setSize(50, 20);
        aGammaRemainsRes.setLocation(260, 140);
        aGammaRemainsRes.setEditable(false);
        add(aGammaRemainsRes);

        JLabel numberIndicator = new JLabel("<html><p align=\"center\">"
                + "Численные</p> <p align=\"center\">модели</p><html>");
        numberIndicator.setSize(250, 40);
        numberIndicator.setLocation(335, 10);
        numberIndicator.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        add(numberIndicator);

        nAverageTime = new JTextField();
        nAverageTime.setSize(50, 20);
        nAverageTime.setLocation(342, 50);
        nAverageTime.setEditable(false);
        add(nAverageTime);
        nGammaRes = new JTextField();
        nGammaRes.setSize(50, 20);
        nGammaRes.setLocation(342, 80);
        nGammaRes.setEditable(false);
        add(nGammaRes);
        nRemainsRes = new JTextField();
        nRemainsRes.setSize(50, 20);
        nRemainsRes.setLocation(342, 110);
        nRemainsRes.setEditable(false);
        add(nRemainsRes);

        nGammaRemainsRes = new JTextField();
        nGammaRemainsRes.setSize(50, 20);
        nGammaRemainsRes.setLocation(342, 140);
        nGammaRemainsRes.setEditable(false);
        add(nGammaRemainsRes);

        //Доверительный интервал
        lInterval = new JLabel("<html>Доверительный интервал: </html>");
        lInterval.setSize(300, 20);
        lInterval.setLocation(20, 170);
        add(lInterval);
    }
}
