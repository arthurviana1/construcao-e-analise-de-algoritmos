public class Testes {

    static int total = 0;
    static int passou = 0;

    static void etapa1(int[] v, String esperado) {
        long[] a = SubarranjoMaximo.forcaBruta(v);
        long[] b = SubarranjoMaximo.dividir(v, 0, v.length - 1);
        String fb = a[0] + " " + a[1] + " " + a[2];
        String dc = b[0] + " " + b[1] + " " + b[2];
        boolean ok = fb.equals(esperado) && dc.equals(esperado);
        total++;
        if (ok) passou++;
        System.out.println((ok ? "OK     " : "FALHOU ") + esperado + " | " + fb + " | " + dc);
    }

    static String saida(double[] r) {
        return Math.sqrt(r[0]) + " " + r[1] + " " + r[2] + " " + r[3] + " " + r[4];
    }

    static void etapa2(double[][] p, String esperado) {
        String fb = saida(ParMaisProximo.forcaBruta(p));
        String dc = saida(ParMaisProximo.dividir(p));
        boolean ok = fb.equals(esperado) && dc.equals(esperado);
        total++;
        if (ok) passou++;
        System.out.println((ok ? "OK     " : "FALHOU ") + esperado + " | " + fb + " | " + dc);
    }

    public static void main(String[] args) {
        etapa1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5}, "6 3 6");
        etapa1(new int[]{-5, -3, -8, -1, -4}, "-1 3 3");
        etapa1(new int[]{7}, "7 0 0");
        etapa1(new int[]{-7}, "-7 0 0");
        etapa1(new int[]{1, -1, 1}, "1 0 0");
        etapa1(new int[]{5, -5, 5, -5, 5}, "5 0 0");
        etapa1(new int[]{-2, -2, -2}, "-2 0 0");
        etapa1(new int[]{0, 0, 0}, "0 0 0");
        etapa1(new int[]{1, 2, 3, 4}, "10 0 3");
        etapa1(new int[]{-1, 5, -2, 2, -5, 7}, "7 1 5");

        etapa2(new double[][]{{0, 0}, {3, 4}}, "5.0 0.0 0.0 3.0 4.0");
        etapa2(new double[][]{{0, 0}, {6, 8}, {20, 20}, {9, 12}}, "5.0 6.0 8.0 9.0 12.0");
        etapa2(new double[][]{{2, 3}, {7, 8}, {2, 3}}, "0.0 2.0 3.0 2.0 3.0");
        etapa2(new double[][]{{1, 0}, {1, 10}, {1, 3}, {1, 20}, {1, 12}}, "2.0 1.0 10.0 1.0 12.0");
        etapa2(new double[][]{{0, 5}, {10, 5}, {3, 5}, {4, 5}}, "1.0 3.0 5.0 4.0 5.0");
        etapa2(new double[][]{{0, 0}, {3, 9}, {5, 5}, {6, 5}, {10, 10}, {20, 20}}, "1.0 5.0 5.0 6.0 5.0");

        System.out.println(passou + "/" + total + " testes passaram");
    }
}