public class Teste1 {

    static void testar(int[] v, long soma, long ini, long fim) {
        String esperado = soma + " " + ini + " " + fim;
        long[] a = SubarranjoMaximo.forcaBruta(v);
        long[] b = SubarranjoMaximo.dividir(v, 0, v.length - 1);
        String fb = a[0] + " " + a[1] + " " + a[2];
        String dc = b[0] + " " + b[1] + " " + b[2];
        System.out.println((fb.equals(esperado) && dc.equals(esperado) ? "OK     " : "FALHOU ") + esperado + " | " + fb + " | " + dc);
    }

    public static void main(String[] args) {
        testar(new int[]{-2, 1, -3, 4, -1, 2, 1, -5}, 6, 3, 6);
        testar(new int[]{-5, -3, -8, -1, -4}, -1, 3, 3);
        testar(new int[]{7}, 7, 0, 0);
        testar(new int[]{1, -1, 1}, 1, 0, 0);
        testar(new int[]{5, -5, 5, -5, 5}, 5, 0, 0);
    }
}