package main;

public class ErrorMessage {

    public String errorMes = "";
    private boolean errorHave = false;

    public boolean errorField(String sMaxValue,
            String sProbableValue,
            String sGamma,
            String sOperatingTime,
            String sSample,
            String sNumberIntervals) {
        if (sMaxValue.equals("")
                || sProbableValue.equals("")
                || sSample.equals("")
                || sGamma.equals("")
                || sOperatingTime.equals("")
                || sNumberIntervals.equals("")) {
            errorMes = "Введите все исходные данные";
        } else if (!sSample.matches("[0-9]+")) {
            errorMes = "Объем выборки может быть только целым числом";
        } else if (!sNumberIntervals.matches("[0-9]+")) {
            errorMes = "Количество интервалом может быть только целым числом";
        } else if (!sMaxValue.matches("\\d+(\\.\\d+)?")) {
            errorMes = "Наибольшее значение может быть целым или вещественным числом";
        } else if (!sProbableValue.matches("\\d+(\\.\\d+)?")) {
            errorMes = "Наиболее вероятное значение может быть целым или вещественным числом";
        } else if (!sOperatingTime.matches("\\d+(\\.\\d+)?")) {
            errorMes = "Время эксплуатации может быть быть целым или вещественным числом";
        } else if (!(Double.parseDouble(sGamma) > 0 && Double.parseDouble(sGamma) <= 1)) {
            errorMes = "Вероятность может быть больше или равно 0 и меньше или равно 1";
        } else if (!sGamma.matches("\\d+(\\.\\d+)")) {
            errorMes = "Вероятность может быть только вещественным числом";
        } else if (!((Double.parseDouble(sMaxValue) >= Double.parseDouble(sProbableValue)))) {
            errorMes = "Наибольшее значение должно быть больше, чем наиболее вероятное значение";
        } else if (!((Double.parseDouble(sOperatingTime) <= Double.parseDouble(sMaxValue)))) {
            errorMes = "Время эксплуатации должно быть меньше, чем наибольшее значение";
        }

        if (!errorMes.equals("")) {
            errorHave = true;
        }
        return errorHave;
    }
}
