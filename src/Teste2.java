public class Teste2 {

    static void testar(double[][] p, double esperado) {
        double fb = ParMaisProximo.forcaBruta(p)[0];
        double dc = ParMaisProximo.dividir(p)[0];
        System.out.println((fb == esperado && dc == esperado ? "OK     " : "FALHOU ") + esperado + " | " + fb + " | " + dc);
    }

    public static void main(String[] args) {
        testar(new double[][]{{0, 0}, {3, 4}}, 25);
        testar(new double[][]{{0, 0}, {5, 5}, {1, 1}, {9, 9}}, 2);
        testar(new double[][]{{2, 3}, {7, 8}, {2, 3}}, 0);
        testar(new double[][]{{1, 0}, {1, 10}, {1, 3}, {1, 20}, {1, 12}}, 4);
        testar(new double[][]{{0, 0}, {3, 9}, {5, 5}, {6, 5}, {10, 10}, {20, 20}}, 1);
    }
}