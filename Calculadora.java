public class Calculadora {
    private double meuNumeroPref;

    public Calculadora(double meuNumeroPref) {
        this.meuNumeroPref = meuNumeroPref; 
    }

    double somar(double a, double b){
        return a + b;
    }
    double subtrair(double a, double b){
        return a - b;
    }

    public double somarComPreferido (double a){
        return a + meuNumeroPref;
    }
}