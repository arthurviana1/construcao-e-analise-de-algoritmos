import java.util.Arrays;
import java.util.Scanner;

public class ParMaisProximo {

    static int LIMIAR = 3;
    static boolean[] esq;

    static double[] par(double[][] p, int i, int j) {
        double dx = p[i][0] - p[j][0];
        double dy = p[i][1] - p[j][1];
        return new double[]{dx * dx + dy * dy, p[i][0], p[i][1], p[j][0], p[j][1]};
    }

    static double[] forcaBruta(double[][] p) {
        double[] best = {Double.MAX_VALUE, 0, 0, 0, 0};
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double[] c = par(p, i, j);
                if (c[0] < best[0]) best = c;
            }
        }
        return best;
    }

    static int[] ordenar(double[][] p, int coord) {
        Integer[] t = new Integer[p.length];
        for (int i = 0; i < p.length; i++) t[i] = i;
        Arrays.sort(t, (a, b) -> Double.compare(p[a][coord], p[b][coord]));
        int[] r = new int[p.length];
        for (int i = 0; i < p.length; i++) r[i] = t[i];
        return r;
    }

    static double[] resolver(double[][] p, int[] ix, int[] iy) {
        int n = ix.length;
        if (n <= LIMIAR) {
            double[][] sub = new double[n][];
            for (int k = 0; k < n; k++) sub[k] = p[ix[k]];
            return forcaBruta(sub);
        }
        int mid = n / 2;
        double linha = p[ix[mid]][0];
        int[] lx = Arrays.copyOfRange(ix, 0, mid);
        int[] rx = Arrays.copyOfRange(ix, mid, n);
        for (int k = 0; k < mid; k++) esq[ix[k]] = true;
        for (int k = mid; k < n; k++) esq[ix[k]] = false;
        int[] ly = new int[mid];
        int[] ry = new int[n - mid];
        int a = 0;
        int b = 0;
        for (int k = 0; k < n; k++) {
            if (esq[iy[k]]) ly[a++] = iy[k];
            else ry[b++] = iy[k];
        }
        double[] e = resolver(p, lx, ly);
        double[] d = resolver(p, rx, ry);
        double[] best = e[0] <= d[0] ? e : d;
        double delta = Math.sqrt(best[0]);
        int[] faixa = new int[n];
        int m = 0;
        for (int k = 0; k < n; k++) {
            if (Math.abs(p[iy[k]][0] - linha) < delta) faixa[m++] = iy[k];
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && j <= i + 7; j++) {
                double[] c = par(p, faixa[i], faixa[j]);
                if (c[0] < best[0]) best = c;
            }
        }
        return best;
    }

    static double[] dividir(double[][] p) {
        esq = new boolean[p.length];
        return resolver(p, ordenar(p, 0), ordenar(p, 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] p = new double[n][2];
        for (int i = 0; i < n; i++) {
            p[i][0] = sc.nextDouble();
            p[i][1] = sc.nextDouble();
        }
        double[] r;
        if (args.length > 0 && args[0].equals("forca")) {
            r = forcaBruta(p);
        } else {
            r = dividir(p);
        }
        System.out.println(Math.sqrt(r[0]) + " " + r[1] + " " + r[2] + " " + r[3] + " " + r[4]);
    }
}