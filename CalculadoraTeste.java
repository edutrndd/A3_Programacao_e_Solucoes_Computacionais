public class CalculadoraTeste {
    public static void main(String[] args){
    Calculadora samydu = new Calculadora(8);
        double resultadoSoma = samydu.somar(2,2);
    
        System.out.println(resultadoSoma);  

        System.out.println(samydu.somarComPreferido(2));
    }
}
