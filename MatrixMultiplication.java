import java.util.Scanner;

class MatrixMultiplication {

    static void normalMultiplication(int[][] A, int[][] B, int[][] C) {
        int m = A.length;
        int n = B.length;
        int p = B[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                int sum = 0;

                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }

                C[i][j] = sum;
            }
        }
    }

    static class RowThread extends Thread {
        int[][] A, B, C;
        int startRow, endRow;

        RowThread(int[][] A, int[][] B, int[][] C,
                  int startRow, int endRow) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        public void run() {
            int n = B.length;
            int p = B[0].length;

            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < p; j++) {
                    int sum = 0;

                    for (int k = 0; k < n; k++) {
                        sum += A[i][k] * B[k][j];
                    }

                    C[i][j] = sum;
                }
            }
        }
    }

    static void multithreadedMultiplication(int[][] A, int[][] B, int[][] C)
            throws InterruptedException {

        int m = A.length;

        int numberOfThreads =
                Math.min(Runtime.getRuntime().availableProcessors(), m);

        RowThread[] threads = new RowThread[numberOfThreads];

        int rowsPerThread = m / numberOfThreads;
        int extraRows = m % numberOfThreads;

        int startRow = 0;

        for (int i = 0; i < numberOfThreads; i++) {

            int rows = rowsPerThread;

            if (i < extraRows) {
                rows++;
            }

            int endRow = startRow + rows;

            threads[i] = new RowThread(
                    A, B, C, startRow, endRow);

            threads[i].start();

            startRow = endRow;
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }
    }

    static boolean compareMatrices(int[][] A, int[][] B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rows of Matrix A: ");
        int m = sc.nextInt();

        System.out.print("Enter columns of Matrix A / rows of Matrix B: ");
        int n = sc.nextInt();

        System.out.print("Enter columns of Matrix B: ");
        int p = sc.nextInt();

        int[][] A = new int[m][n];
        int[][] B = new int[n][p];

        System.out.println("\nEnter Matrix A:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nEnter Matrix B:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        int[][] C1 = new int[m][p];
        int[][] C2 = new int[m][p];

        // Warm-up
        int[][] warmup = new int[m][p];
        normalMultiplication(A, B, warmup);

        // Normal multiplication
        long start1 = System.nanoTime();

        normalMultiplication(A, B, C1);

        long end1 = System.nanoTime();

        // Multithreaded multiplication
        long start2 = System.nanoTime();

        multithreadedMultiplication(A, B, C2);

        long end2 = System.nanoTime();

        long normalTime = end1 - start1;
        long multithreadedTime = end2 - start2;

        System.out.println("\nPerformance Comparison:");

        System.out.println("Number of CPU cores : "
                + Runtime.getRuntime().availableProcessors());

        System.out.println("Normal execution time        : "
                + normalTime + " nanoseconds");

        System.out.println("Multithreaded execution time : "
                + multithreadedTime + " nanoseconds");

        if (multithreadedTime < normalTime) {
            double improvement =
                    ((double)(normalTime - multithreadedTime)
                    / normalTime) * 100;

            System.out.println("Multithreading improvement   : "
                    + improvement + "%");
        } else {
            double difference =
                    ((double)(multithreadedTime - normalTime)
                    / normalTime) * 100;

            System.out.println("Multithreading overhead       : "
                    + difference + "%");
        }

        System.out.println("Results are same              : "
                + compareMatrices(C1, C2));

        sc.close();
    }
}