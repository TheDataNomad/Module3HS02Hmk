public class Pascal {
    public static int[][] generatePascal(int n) {
        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println();
        int n = 15;
        int i;
        int j;

        System.out.println("Pascal Triangle: n = " + String.valueOf(n));

        int[][] a = generatePascal(n);

        for (i = 0; i < n; i++) {
            for (j = 0; j <= (n - i); j++) {
                System.out.print("   ");
            }

            for (j = 0; j <= i; j++) {
                if (a[i][j] < 10) {
                    System.out.print(a[i][j] + "      ");
                } else if (a[i][j] < 100) {
                    System.out.print(a[i][j] + "     ");
                } else if (a[i][j] < 1_000) {
                    System.out.print(a[i][j] + "    ");
                } else if (a[i][j] < 10_000) {
                    System.out.print(a[i][j] + "   ");
                } else {
                    System.out.print(a[i][j] + " ");
                }
            }

            System.out.println();
        }
    }
}