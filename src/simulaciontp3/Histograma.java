/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulaciontp3;

import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;

import java.awt.Dimension;

public class Histograma extends javax.swing.JFrame {

    private int intervalos;

    public Histograma(String title, double[] serie, int inter) {

        HistogramDataset dataset;
        dataset = new HistogramDataset();
        dataset.addSeries("Frecuencias de Numeros Aleatorios: ", serie, inter);
        JFreeChart chart = ChartFactory.createHistogram("Conteo de Frecuentcia: ", "Intervalos", "Cantidad de Números por Intervalo", dataset, PlotOrientation.VERTICAL, true, true, false);

        ChartFrame frame = new ChartFrame("Histograma", chart);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        serie = new double[intervalos];

        for (int i = 0; i < intervalos; i++) {
            serie[i] = dataset.getY(0, i).intValue();
        }

    }

    private HistogramDataset crearDataset(double[] serie) {

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Histograma", serie, intervalos);

        return dataset;
    }

    public static JFreeChart crearChart(HistogramDataset datos) {
        JFreeChart chart = ChartFactory.createHistogram(
                "Distribucion de frecuencia del generador", //Nombre de la gráfica
                "Intervalos", //Nombre del eje Horizontal
                "Ocurrencias", //Nombre del eje vertical
                datos, //Data
                PlotOrientation.VERTICAL, //Orientación
                true, //Incluir leyenda
                true, //Información al pasar el mouse
                true);                      //URls

        return chart;
    }
}
