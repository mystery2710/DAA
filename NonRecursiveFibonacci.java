import java.util.Scanner;

public class NonRecursiveFibonacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        long startTime = System.nanoTime();

        int a = 0, b = 1, c;

        System.out.println("Fibonacci Series:");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time: "
                + (endTime - startTime) + " nanoseconds");
        System.out.println("Time Complexity: O(n)");
        System.out.println("Space Complexity: O(1)");

        sc.close();
    }
}
