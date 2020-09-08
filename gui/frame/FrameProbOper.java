package gui.frame;

import calculation.InputData;
import gui.panel.PanelGraphProbOper;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameProbOper extends JFrame {

    private boolean open = true;
    JPanel currentPanel;

    FrameProbOper() {
        setTitle("График вероятность безотказной работы");
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

        //currentPanel = new JPanel();
    }

    public boolean getGAgain() {
        return open;
    }

    public void drawFrame(double[] Pt,
            double[] Prt,
            InputData data,
            boolean bAgain) {

        PanelGraphProbOper panelGraph = new PanelGraphProbOper();
        System.out.println(bAgain);
        if ((bAgain == true)) { //&& (open == true)
            //Если открыть окно, то построить график
            //this.getContentPane().remove(currentPanel);
            JPanel graph = panelGraph.addGrafik(Pt, Prt, data);
            this.getContentPane().add(graph);
            //open = false;
            currentPanel = graph;
        }

        /*if ((bAgain == true)) { //&& (open == true)
            //Если открыть окно, то построить график
            this.getContentPane().remove(currentPanel);
            JPanel graph = panelGraph.addGrafik(Pt, Prt, data);
            this.getContentPane().add(graph);
            open = false;
            currentPanel = graph;
        } /*else if ((bAgain == true) ) {//&& (open == false)
            //Если окно открыто, то перерисовать график
            this.getContentPane().remove(currentPanel);
            JPanel graph = panelGraph.addGrafik(Pt, Prt, data);
            this.getContentPane().add(graph);
            currentPanel = graph;
            this.toFront();
        }*/ /*else if ((bAgain == false)) {// && (open == true)
            //Если окно закрыли и расчет не произвели, то открыть заново
            /*this.getContentPane().remove(currentPanel);
            JPanel graph = panelGraph.addGrafik(Pt, Prt, data);
            this.getContentPane().add(graph);
            open = false;
            currentPanel = graph;*/
 /*}
        currentPanel.revalidate();
        this.repaint();*/
    }
}
