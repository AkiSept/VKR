package calculation;

public class Analytical {

    private final double tm, t0, g, x;
    private final int J;

    public Analytical(InputData data) {
        this.tm = data.getMaxValue();
        this.t0 = data.getProbableValue();

        this.g = data.getGamma();
        this.x = data.getOperatingTime();
        this.J = data.getNumberIntervals();
    }

    //Расчитать показатели надежности
    public Indicator calculateIndicator() {

        Indicator indicator = new Indicator();
        //Средняя наработка до отказа 
        double rageTime = 0;
        //гамма-процентный ресурс до отказа 
        double gammaRejectionRes = 0;
        //Средний остаточный ресурс до отказа 
        double rageRemainsRes = 0;
        //Гамма-процентный остаточный ресурс 
        double gammaRemainsRes = 0;

        //Средняя наработка до отказа 
        rageTime = (tm + t0) / 3;
        //Гамма-процентный ресурс до отказа 
        double t0tm = 1 - (t0 / tm);
        if ((g >= t0tm) && (g
                < 1)) {
            gammaRejectionRes = (float) (Math.sqrt((1 - g) * tm * t0));
        } else if ((g > t0) && (g < t0tm)) {
            gammaRejectionRes = (float) (tm - (Math.sqrt(g * tm * (tm - t0))));
        }

        //Средний остаточный ресурс до отказа
        if ((x > 0) && (x <= t0)) {
            rageRemainsRes = (x * x * x - 3 * tm * t0 * x + tm * t0 * (tm + t0))
                    / (3 * (tm * t0 - x * x));
        } else if ((x > t0) && (x < tm)) {
            rageRemainsRes = ((tm - x) / 3);
        }

        //Гамма-процентный остаточный ресурс
        double gamma;
        if ((x > 0) && (x <= t0)) {
            gamma = (t0 * (tm - t0)) / (tm * t0 - x * x);
            if ((g >= gamma) && (g < 1)) {
                gammaRemainsRes = (Math.sqrt((1 - g) * tm * t0 + g * x * x) - x);
            } else if ((g > 0) && (g < gamma)) {
                gammaRemainsRes = (tm - x - (Math.sqrt((g * (tm * t0 - x * x)
                        * (tm - t0)) / t0)));
            }
        } else if ((x > t0) && (x < tm)) {
            gammaRemainsRes = ((tm - x) * (1 - Math.sqrt(g)));
        }

        double[] t = new double[J + 1];
        for (int i = 0;
                i < J + 1; i++) {
            t[i] = i * tm / J;
        }

        //Вероятность безотказной работы 
        double[] Pt = new double[J + 1];
        for (int i = 0; i < J + 1; i++) {
            if (t[i] <= 0) {
                Pt[i] = 1;
            }
            if ((t[i] > 0) && (t[i] <= t0)) {
                Pt[i] = 1 - ((t[i] * t[i]) / (tm * t0));
            }
            if ((t[i] > t0) && (t[i] < tm)) {
                Pt[i] = (((tm - t[i]) * (tm - t[i])) / (tm * (tm - t0)));
            }
            if (t[i] >= tm) {
                Pt[i] = 0;
            }
        }

        //Вероятность безотказной работы остаточного ресурса
        double k = J / tm * x;
        int ik = (int) k;
        double[] Pxy = new double[J - ik + 1];
        for (int i = 0; i < J - ik + 1; i++) {
            if (t[i] <= 0) {
                Pxy[i] = 1;
            }
            if (t[i] > (tm - x)) {
                Pxy[i] = 0;
            }
            if ((x > 0) && (x <= t0)) {

                if ((t[i] > 0) && (t[i] <= t0 - x)) {
                    Pxy[i] = (tm * t0 - (x + t[i]) * (x + t[i]))
                            / (tm * t0 - x * x);
                }
                if ((t[i] > t0 - x) && (t[i] <= tm - x)) {
                    double numerator = t0 * (tm - x - t[i]) * (tm - x - t[i]);
                    double denominator = (tm - t0) * (tm * t0 - x * x);
                    Pxy[i] = numerator / denominator;
                }
            } else if ((x > t0) && (x < tm)) {
                if ((t[i] > 0) && (t[i] <= tm - x)) {
                    Pxy[i] = ((tm - x - t[i]) * (tm - x - t[i]))
                            / ((tm - x) * (tm - x));
                }
            }
        }

        indicator.setRageTime(rageTime);

        indicator.setGammaRejectionRes(gammaRejectionRes);
        indicator.setRageRemainsRes(rageRemainsRes);
        indicator.setGammaRemainsRes(gammaRemainsRes);

        indicator.setProbabilityOper(Pt);
        indicator.setProOperRemains(Pxy);

        return indicator;
    }
}
