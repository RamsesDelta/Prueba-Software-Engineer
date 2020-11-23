import java.util.Scanner;

public class Ejercicio1 {

    static void spiralPrint(int m, int n, int a[][])
    {
        int i, k = 0, l = 0;

        while (k < m && l < n) {

            for (i = l; i < n; ++i) {
                System.out.print(a[k][i] + " ");
            }
            k++;

            for (i = k; i < m; ++i) {
                System.out.print(a[i][n - 1] + " ");
            }
            n--;

            if (k < m) {
                for (i = n - 1; i >= l; --i) {
                    System.out.print(a[m - 1][i] + " ");
                }
                m--;
            }

            if (l < n) {
                for (i = m - 1; i >= k; --i) {
                    System.out.print(a[i][l] + " ");
                }
                l++;
            }
        }
    }

    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        System.out.println("ingresa numero de columnas");
        int c = sc.nextInt();
        System.out.println("ingresa numero de filas");
        int r = sc.nextInt();

        int matriz[][] = new int[c][r];

        for (int x=0; x < matriz.length; x++) {
            for (int y=0; y < matriz[x].length; y++) {
                System.out.println("Introduzca el elemento [" + x + "," + y + "]");
                matriz[x][y] = sc.nextInt();
            }
        }

        spiralPrint(r, c, matriz);

    }
}
