import java.util.Scanner;

public class RecursiveFibonacci {

    static int fibonacci(int n) {
        if (n <= 1)
            return n;

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        long startTime = System.nanoTime();

        System.out.println("Fibonacci Series:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time: "
                + (endTime - startTime) + " nanoseconds");
        System.out.println("Time Complexity: O(2^n)");
        System.out.println("Space Complexity: O(n)");

        sc.close();
    }
}
