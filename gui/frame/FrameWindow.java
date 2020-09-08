package gui.frame;

import calculation.Analytical;
import calculation.Indicator;
import calculation.InputData;
import calculation.Modeling;
import gui.panel.PanelGraphProbOper;
import gui.panel.PanelGraphProbOperRem;
import gui.panel.PanelInputData;
import gui.panel.PanelOutputData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import main.ErrorMessage;

public class FrameWindow extends JFrame {

    //Панель - главная панель
    JPanel main = new JPanel();
    //Панель - входные/выходные данные
    JPanel data = new JPanel();
    //Панель - изображение
    JPanel picture = new JPanel();
    JButton buildGraph = new JButton("Построить график");
    JButton calculate = new JButton("Расcчитать");
    private boolean bCalculate = true;
    private boolean bAgainOper = true;
    private boolean bAgainOperRem = true;
    private Indicator numInd;
    private Indicator analytInd;
    JRadioButton probOperButton, probRemButton;
    //Окна с графиками
    //FrameProbOper fGraphOper;
    //FrameProbOperRem fGraphRem;
    InputData input;

    public FrameWindow() {
        this.getContentPane().add(main);

        main.setBackground(Color.WHITE);
        main.setLayout(null);
        main.setBounds(0, 0, 1030, 600);

        data.setLayout(null);
        data.setBounds(410, 10, 480, 540);
        data.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        picture.setLayout(null);
        picture.setBounds(10, 10, 388, 540);
        picture.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        main.add(picture);
        picture.setBackground(Color.WHITE);
        main.add(data);
        data.setBackground(Color.WHITE);
        try {
            BufferedImage pic = ImageIO.read(new File("img/Rails.png"));//.png
            JLabel wPic = new JLabel(new ImageIcon(pic));
            wPic.setSize(385, 540);
            wPic.setLocation(1, 0);
            picture.add(wPic);
        } catch (IOException ex) {

        }

        //Панель входных данных
        JPanel inputData = new PanelInputData();
        data.add(inputData);
        //Панель выходных данных
        JPanel outputData = new PanelOutputData();
        data.add(outputData);
        //Кнопка - Расчитать
        calculate.setSize(125, 25);
        calculate.setLocation(325, 420);
        ImageIcon iconCal = new ImageIcon("img/calculator.png");
        calculate.setIcon(iconCal);
        data.add(calculate);

        JLabel nameGroup = new JLabel("Выберите график:");
        nameGroup.setSize(150, 20);
        nameGroup.setLocation(50, 440);
        data.add(nameGroup);
        ButtonGroup groupGragh = new ButtonGroup();
        probOperButton = new JRadioButton("Вероятность безотказной работы", true);
        groupGragh.add(probOperButton);
        probRemButton = new JRadioButton("Вероятность безотказной работы для "
                + "остаточного ресурса", false);
        probOperButton.setBackground(Color.WHITE);
        probOperButton.setSize(400, 25);
        probOperButton.setLocation(50, 460);
        probRemButton.setBackground(Color.WHITE);
        probRemButton.setSize(400, 25);
        probRemButton.setLocation(50, 480);

        groupGragh.add(probRemButton);

        data.add(probOperButton);
        data.add(probRemButton);

        //Кнопка - Построить график
        buildGraph.setSize(175, 25);
        buildGraph.setLocation(50, 510);
        ImageIcon iconGraph = new ImageIcon("img/down.png");
        buildGraph.setIcon(iconGraph);
        data.add(buildGraph);

        buildGraph.addActionListener(this::buttonClick);
        calculate.addActionListener(this::buttonClick);

        input = new InputData();

        //Окно - Вероятность безотказной работы
        //Окно - Вероятность безотказной работы для остаточного ресурса
        //fGraphRem = new FrameProbOperRem();
        ///fGraphOper = new FrameProbOper();
    }

    private void buttonClick(ActionEvent e) {

        if (e.getSource().equals(calculate)) {
            //Класс - Ошибки
            ErrorMessage er = new ErrorMessage();
            String sMaxValue = PanelInputData.fMaxValue.getText().replace(',', '.');
            String sProbableValue = PanelInputData.fProbableValue.getText().replace(',', '.');
            String sGamma = PanelInputData.fGamma.getText().replace(',', '.');
            String sOperatingTime = PanelInputData.fOperatingTime.getText().replace(',', '.');
            String sSample = PanelInputData.fSample.getText();
            String sNumberIntervals = PanelInputData.fNumberIntervals.getText();

            boolean error = er.errorField(sMaxValue, sProbableValue,
                    sGamma, sOperatingTime, sSample, sNumberIntervals);
            if (error) {
                JOptionPane.showMessageDialog(null, er.errorMes);
            } else {
                input.setMaxValue(Double.parseDouble(sMaxValue));
                input.setProbableValue(Double.parseDouble(sProbableValue));
                input.setSample(Integer.parseInt(sSample));
                input.setGamma(Double.parseDouble(sGamma));
                input.setOperatingTime(Double.parseDouble(sOperatingTime));
                input.setNumberIntervals(Integer.parseInt(sNumberIntervals));
                //Аналитический подход
                Analytical comp = new Analytical(input);
                analytInd = comp.calculateIndicator();
                PanelOutputData.aAverageTime.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", analytInd.getRageTime())));
                PanelOutputData.aGammaRes.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", analytInd.getGammaRejectionRes())));
                PanelOutputData.aRemainsRes.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", analytInd.getRageRemainsRes())));
                PanelOutputData.aGammaRemainsRes.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", analytInd.getGammaRemainsRes())));

                //Численный подход
                Modeling model = new Modeling(input);
                numInd = model.calculateNumberIndicator();
                PanelOutputData.nAverageTime.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", numInd.getRageTime())));
                PanelOutputData.nGammaRes.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", numInd.getGammaRejectionRes())));
                PanelOutputData.nRemainsRes.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", numInd.getRageRemainsRes())));
                PanelOutputData.nGammaRemainsRes.setText(String.valueOf(
                        String.format(Locale.US, "%.3f", numInd.getGammaRemainsRes())));
                bCalculate = false;
                bAgainOper = true;
                bAgainOperRem = true;
            }
        }
        if (e.getSource().equals(buildGraph)) {

            if (bCalculate == false) {
                if (probOperButton.isSelected()) {

                    if (bAgainOper == true) {
                        JFrame frame = new JFrame();
                        frame.setTitle("График вероятности безотказной работы");
                        frame.setSize(new Dimension(550, 390));
                        frame.setMinimumSize(new Dimension(550, 390));
                        frame.setLocation(200, 220);
                        frame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                bAgainOper = true;
                            }
                        });
                        PanelGraphProbOper graph = new PanelGraphProbOper();
                        JPanel panel = graph.addGrafik(analytInd.getProbabilityOper(),
                                numInd.getProbabilityOper(), input);
                        frame.getContentPane().add(panel);
                        frame.setVisible(true);
                        bAgainOper = false;
                    }
                } else if (probRemButton.isSelected()) {
                    if (bAgainOperRem == true) {
                        JFrame frame = new JFrame();
                        frame.setTitle("График вероятности безотказной работы для остаточного ресурса");
                        frame.setSize(new Dimension(550, 390));
                        frame.setMinimumSize(new Dimension(550, 390));
                        frame.setLocation(200, 500);
                        frame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                bAgainOperRem = true;
                            }
                        });
                        PanelGraphProbOperRem graph = new PanelGraphProbOperRem();
                        JPanel panel = graph.addGrafik(analytInd.getProOperRemains(),
                                numInd.getProOperRemains(), input);
                        frame.getContentPane().add(panel);
                        frame.setVisible(true);
                        bAgainOperRem = false;
                    }
                    /*fGraphRem.drawFrame(analytInd.getProOperRemains(),
                            numInd.getProOperRemains(), input, bAgain);
                    fGraphRem.setVisible(true);*/
                }

            } else {
                JOptionPane.showMessageDialog(null, "Для начало необходимо "
                        + "произвести расчеты");
            }
        }
    }
}
