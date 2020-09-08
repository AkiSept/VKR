package calculation;

public class InputData {

    //Наибольшее значение
    private double maxValue;
    //Наиболее вероятное значение        
    private double probableValue;
    //Объекм выборки
    private int sample;
    //Вероятность
    private double gamma;
    //Время эксплуатации
    private double operatingTime;
    //Количество интервалов
    private int numberIntervals;

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getProbableValue() {
        return probableValue;
    }

    public void setProbableValue(double probableValue) {
        this.probableValue = probableValue;
    }

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(double operatingTime) {
        this.operatingTime = operatingTime;
    }

    public int getNumberIntervals() {
        return numberIntervals;
    }

    public void setNumberIntervals(int numberIntervals) {
        this.numberIntervals = numberIntervals;
    }
}
