package calculation;

import gui.panel.PanelOutputData;
import java.util.Locale;

public class Modeling {

    private final double tm, t0, g, x;
    private final int n, J;

    private final double zY = 1.96;

    public Modeling(InputData data) {
        this.tm = data.getMaxValue();
        this.t0 = data.getProbableValue();

        this.g = data.getGamma();
        this.x = data.getOperatingTime();
        this.n = data.getSample();
        this.J = data.getNumberIntervals();
    }

    //Расчитать чтсловые показатели
    public Indicator calculateNumberIndicator() {
        Indicator indicator = new Indicator();
        //Средняя наработка до отказа 
        double rageTime = 0;
        //гамма-процентный ресурс до отказа 
        double gammaRejectionRes = 0;
        //Средний остаточный ресурс до отказа 
        double rageRemainsRes = 0;
        //Гамма-процентный остаточный ресурс 
        double gammaRemainsRes = 0;

        //Случайное число на интервале (0, 1) 
        double r;
        //Алгоритм моделирования времени наработки
        double model[] = new double[n];
        double t0tm = t0 / tm;
        for (int i = 0; i < n; i++) {
            r = Math.random();
            if ((r > 0) && (t0tm >= r)) {
                model[i] = Math.sqrt(tm * t0 * r);
            }
            if ((r < 1) && (t0tm < r)) {
                model[i] = tm - Math.sqrt(tm * (tm - t0) * (1 - r));
            }
        }
        //Доверительный интервал
        double t = 0;
        double sumT = 0;
        double squT = 0;
        for (int q = 0; q < n; q++) {
            sumT += model[q];
            squT += model[q] * model[q];
        }
        t = sumT / n;

        //Оценка среднеквадратического отклонения
        double s = Math.sqrt((squT - n * t * t) / (n - 1));
        double bet = (zY * s) / Math.sqrt(n);
        double t1 = t - bet;
        double t2 = t + bet;

        String str = "<html>Доверительный интервал: ";
        String minInterval = String.valueOf(String.format(Locale.US, "%.3f", t1));
        String maxInterval = String.valueOf(String.format(Locale.US, "%.3f", t2));
        PanelOutputData.lInterval.setText(str + " (" + minInterval + "; "
                + maxInterval + ")</html>");

        //Длина интервала 
        double dt = (double) tm / J;
        //Узлы
        double[] tj = new double[J + 1];
        tj[0] = 0;
        tj[J] = tm;
        for (int j = 1; j < J; j++) {
            tj[j] = j * dt;
        }

        //Частоты или число выборочных значений наработок
        int[] nj = new int[J];
        int countInterval = 0;
        for (int j = 0; j < J; j++) {
            for (int i = 0; i < n; i++) {
                if (model[i] >= tj[j] && model[i] < tj[j + 1]) {
                    countInterval++;
                }
            }
            nj[j] = countInterval;
            countInterval = 0;
        }

        //Накопленные относительные частоты
        double[] mj = new double[J + 1];
        mj[J] = 1;
        double z = 0;
        for (int j = 0; j < J; j++) {
            for (int i = 0; i < j; i++) {
                z += nj[i];
            }
            mj[j] = z / n;
            z = 0;
        }

        //Обратная величина относительных частот
        double[] kj = new double[J + 1];
        kj[0] = 1;
        for (int j = 1; j < J + 1; j++) {
            kj[j] = 1 - mj[j];
        }
        //Сумма
        double kjS = 0;
        for (int j = 1; j < J + 1; j++) {
            kjS += kj[j];
        }
        //Средняя наработка до отказа 
        rageTime = (tm / J) * (0.5 + kjS);

        //Численный  гамма-процентный ресурс до отказа
        for (int j = 1; j < J; j++) {
            if (kj[j - 1] >= g && kj[j] < g) {
                gammaRejectionRes += tj[j - 1] + (((g - kj[j - 1])
                        / (kj[j] - kj[j - 1])) * (tm / J));
            }
        }

        //Статистическая вероятность безотказной работы для остаточного ресурса
        double[] pji = new double[J + 1];
        pji[0] = 1.0;
        double k = J / tm * x;
        int ik = (int) k;
        for (int i = 0; i < J - ik + 1; i++) {
            pji[i] = kj[ik + i] / kj[ik];
        }

        //Численный средний остаточный ресурс до отказа
        for (int i = 1; i < J - ik; i++) {
            rageRemainsRes += pji[i];
        }
        rageRemainsRes = (tm / J) * (0.5 + rageRemainsRes);

        double[] ti = new double[J - ik];
        for (int i = 1; i < J - ik; i++) {
            ti[i] = i * tm / J;
        }
        double rea;
        for (int i = 1; i < J - ik; i++) {
            if (pji[i - 1] >= g && pji[i] < g) {
                rea = (g - pji[i - 1]) / (pji[i] - pji[i - 1]);
                gammaRemainsRes += ti[i - 1] + rea * tm / J;
            }
        }

        //Численная вероятности безотказной работы
        double[] Prt = new double[J + 1];
        for (int i = 1; i < J + 1; i++) {
            if (tj[i - 1] >= tm) {
                Prt[i - 1] = 0;
            }
            if ((tj[i - 1] > 0) && (tj[i - 1] < tm)) {
                double sd = ((tj[i] - tj[i - 1]) * (kj[i] - kj[i - 1]));
                Prt[i - 1] = kj[i - 1] + sd * (tm / J);
            }
            if (tj[i - 1] <= 0) {
                Prt[i - 1] = 1;
            }
        }

        //Численная вероятность безотказной работы для остаточного ресурса
        double[] Pry = new double[J - ik + 1];
        for (int i = 1; i < J - ik + 1; i++) {
            if (tj[i - 1] >= (tm - x)) {
                Pry[i - 1] = 0;
            }
            if ((tj[i - 1] >= 0) && (tj[i - 1] < tm)) {
                double sd = ((tj[i] - tj[i - 1]) * (pji[i] - pji[i - 1]));
                Pry[i - 1] = pji[i - 1] + sd * (tm / J);
            }
            if (tj[i - 1] < 0) {
                Pry[i - 1] = 1;
            }
        }

        indicator.setRageTime(rageTime);
        indicator.setGammaRejectionRes(gammaRejectionRes);
        indicator.setRageRemainsRes(rageRemainsRes);
        indicator.setGammaRemainsRes(gammaRemainsRes);

        indicator.setProbabilityOper(Prt);
        indicator.setProOperRemains(Pry);
        return indicator;
    }
}
