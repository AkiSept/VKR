package gui.frame;

import calculation.InputData;
import gui.panel.PanelGraphProbOperRem;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameProbOperRem extends JFrame {

    private boolean open = true;
    JPanel currentPanel;

    FrameProbOperRem() {
        setTitle("График вероятность безотказной работы "
                + "для остаточного ресурса");
        setSize(new Dimension(550, 390));
        setMinimumSize(new Dimension(550, 390));
        setLocation(200, 220);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                open = true;
            }
        });
        currentPanel = new JPanel();
    }

    public void drawFrame(double[] Pxy,
            double[] Prx,
            InputData data,
            boolean bAgain) {

        double j = data.getMaxValue() / data.getNumberIntervals();
        double operTime = data.getOperatingTime();
        /*PanelGraphProbOperRem panelGraph = new PanelGraphProbOperRem();
        if ((bAgain == true) && (open == true)) {
            //Если открыть окно, то построить график
            this.getContentPane().remove(currentPanel);
            JPanel graph = panelGraph.addGrafik(Pxy, Prx, operTime, j);
            this.getContentPane().add(graph);
            open = false;
            currentPanel = graph;
        } else if ((bAgain == true) && (open == false)) {
            this.getContentPane().remove(currentPanel);
            JPanel graph = panelGraph.addGrafik(Pxy, Prx, operTime, j);
            this.getContentPane().add(graph);
            currentPanel = graph;
            this.toFront();
        } else if ((bAgain == false) && (open == true)) {
            //Если окно закрыли и расчет не произвели, то открыть заново
            this.getContentPane().remove(currentPanel);
            JPanel graph = panelGraph.addGrafik(Pxy, Prx, operTime, j);
            this.getContentPane().add(graph);
            open = false;
            currentPanel = graph;
        }
        currentPanel.revalidate();
        this.repaint();*/
    }
}
