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

public class PanelGraphProbOperRem {

    public JPanel addGrafik(double[] Pxy,
            double[] Prx,
            InputData data) {

        double j = data.getMaxValue() / data.getNumberIntervals();
        double[] t = new double[Pxy.length + 1];
        for (int i = 0; i < t.length; i++) {
            t[i] = i * j;
        }

        XYSeries seriesPt = new XYSeries("Px(y)");

        for (int i = 0; i < Pxy.length; i++) {
            seriesPt.add(t[i], Pxy[i]);
        }
        XYSeries seriesPrx = new XYSeries("Prx(y)");

        for (int i = 0; i < Prx.length; i++) {
            seriesPrx.add(t[i], Prx[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(seriesPt);
        dataset.addSeries(seriesPrx);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Вероятность безотказной работы для остаточного ресурса",
                "y", " ",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, true);
        chart.getTitle().setFont(new Font("TimesRoman", Font.PLAIN, 24));
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        /*TextTitle subtitle1 = new TextTitle("Px(y) - аналитическая модель");
        chart.addSubtitle(subtitle1);
        subtitle1.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        TextTitle subtitle2 = new TextTitle("Pr(y) - численная модель");
        subtitle2.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        chart.addSubtitle(subtitle2);*/

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        LegendItemCollection chartLegend = new LegendItemCollection();
        Shape shape = new Rectangle(10, 10);
        chartLegend.add(new LegendItem("Px(y) - аналитическая", null, null, null, shape, Color.RED));
        chartLegend.add(new LegendItem("Prx(y) - численная ", null, null, null, shape, Color.BLUE));
        plot.setFixedLegendItems(chartLegend);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        AttributedString as = new AttributedString("Px(y), Pxr(y)");//
        as.addAttribute(TextAttribute.SIZE, 14, 0, 12);
        as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 2);
        as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 8, 9);
        as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 9, 10);
        rangeAxis.setAttributedLabel(as);

        ChartPanel Chp = new ChartPanel(chart);
        return Chp;
    }
}
