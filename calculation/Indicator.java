package calculation;

public class Indicator {

    //Средняя наработка до отказа 
    private double rageTime;
    //гамма-процентный ресурс до отказа 
    private double gammaRejectionRes;
    //Средний остаточный ресурс до отказа 
    private double rageRemainsRes;
    //Гамма-процентный остаточный ресурс 
    private double gammaRemainsRes;
    //Вероятность безотказной работы
    private double[] probabilityOper;
    //Вероятность безотказной работы для остаточного ресурса
    private double[] probabilityOperRemains;

    public double getRageTime() {
        return rageTime;
    }

    public void setRageTime(double rageTime) {
        this.rageTime = rageTime;
    }

    public double getGammaRejectionRes() {
        return gammaRejectionRes;
    }

    public void setGammaRejectionRes(double gammaRejectionRes) {
        this.gammaRejectionRes = gammaRejectionRes;
    }

    public double getRageRemainsRes() {
        return rageRemainsRes;
    }

    public void setRageRemainsRes(double rageRemainsRes) {
        this.rageRemainsRes = rageRemainsRes;
    }

    public double getGammaRemainsRes() {
        return gammaRemainsRes;
    }

    public void setGammaRemainsRes(double gammaRemainsRes) {
        this.gammaRemainsRes = gammaRemainsRes;
    }

    public double[] getProbabilityOper() {
        return probabilityOper;
    }

    public void setProbabilityOper(double[] probabilityOper) {
        this.probabilityOper = probabilityOper;
    }

    public double[] getProOperRemains() {
        return probabilityOperRemains;
    }

    public void setProOperRemains(double[] probabilityOperRemains) {
        this.probabilityOperRemains = probabilityOperRemains;
    }

}
