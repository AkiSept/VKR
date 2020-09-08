package gui.panel;

import calculation.InputData;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PanelGraphProbOper {

    public JPanel addGrafik(double[] Pt, double[] Prt, InputData data) {

        double j = data.getMaxValue() / data.getNumberIntervals();
        double[] t = new double[Pt.length];
        for (int i = 0; i < t.length; i++) {
            t[i] = i * j;
        }

        XYSeries seriesPt = new XYSeries("P(t)");

        for (int i = 0; i < Pt.length; i++) {
            seriesPt.add(t[i], Pt[i]);
        }

        XYSeries seriesPrt = new XYSeries("Pr(t)");
        for (int i = 0; i < Prt.length; i++) {
            seriesPrt.add(t[i], Prt[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(seriesPt);
        dataset.addSeries(seriesPrt);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Вероятность безотказной работы",
                "t", "",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        chart.getTitle().setFont(new Font("TimesRoman", Font.PLAIN, 24));
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();

        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        LegendItemCollection chartLegend = new LegendItemCollection();
        Shape shape = new Rectangle(10, 10);
        chartLegend.add(new LegendItem("P(t) - аналитическая", null, null, null, shape, Color.RED));
        chartLegend.add(new LegendItem("Pr(t) - численная", null, null, null, shape, Color.BLUE));
        plot.setFixedLegendItems(chartLegend);

        //TextTitle subtitle1 = new TextTitle("P(t) - аналитическая модель");
        /*TextTitle subtitle1 = new TextTitle("tm = " + data.getMaxValue());
        //subtitle1.setPaint(Color.RED);
        chart.addSubtitle(subtitle1);
        subtitle1.setFont(new Font("TimesRoman", Font.PLAIN, 13));

        TextTitle subtitle2 = new TextTitle("Pr(t) - численная модель");
        subtitle2.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        chart.addSubtitle(subtitle2);*/
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        AttributedString as = new AttributedString("P(t), Pr(t)");
        as.addAttribute(TextAttribute.SIZE, 14, 0, 11);
        as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 7, 8);
        rangeAxis.setAttributedLabel(as);

        ChartPanel Chp = new ChartPanel(chart);
        return Chp;
    }
}
