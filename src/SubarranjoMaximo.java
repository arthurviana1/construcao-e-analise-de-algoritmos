import java.util.Scanner;

public class SubarranjoMaximo {

    static boolean melhor(long[] a, long[] b) {
        if (a[0] != b[0]) return a[0] > b[0];
        if (a[1] != b[1]) return a[1] < b[1];
        return a[2] < b[2];
    }

    static long[] forcaBruta(int[] v) {
        long[] best = {v[0], 0, 0};
        for (int i = 0; i < v.length; i++) {
            long soma = 0;
            for (int j = i; j < v.length; j++) {
                soma += v[j];
                if (soma > best[0]) {
                    best = new long[]{soma, i, j};
                }
            }
        }
        return best;
    }

    static long[] cruzando(int[] v, int lo, int mid, int hi) {
        long soma = 0;
        long melhorEsq = Long.MIN_VALUE;
        int inicio = mid;
        for (int i = mid; i >= lo; i--) {
            soma += v[i];
            if (soma >= melhorEsq) {
                melhorEsq = soma;
                inicio = i;
            }
        }
        soma = 0;
        long melhorDir = Long.MIN_VALUE;
        int fim = mid + 1;
        for (int j = mid + 1; j <= hi; j++) {
            soma += v[j];
            if (soma > melhorDir) {
                melhorDir = soma;
                fim = j;
            }
        }
        return new long[]{melhorEsq + melhorDir, inicio, fim};
    }

    static long[] dividir(int[] v, int lo, int hi) {
        if (lo == hi) return new long[]{v[lo], lo, lo};
        int mid = (lo + hi) / 2;
        long[] esq = dividir(v, lo, mid);
        long[] dir = dividir(v, mid + 1, hi);
        long[] meio = cruzando(v, lo, mid, hi);
        long[] best = esq;
        if (melhor(meio, best)) best = meio;
        if (melhor(dir, best)) best = dir;
        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++) v[i] = sc.nextInt();
        long[] r;
        if (args.length > 0 && args[0].equals("forca")) {
            r = forcaBruta(v);
        } else {
            r = dividir(v, 0, n - 1);
        }
        System.out.println(r[0] + " " + r[1] + " " + r[2]);
    }
}