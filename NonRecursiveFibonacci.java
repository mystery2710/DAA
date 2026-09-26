import java.util.Scanner;

public class NonRecursiveFibonacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of terms: ");
        int n = sc.nextInt();

        int a = 0, b = 1, c;

        for (int i = 0; i < n; i++) {
            if (i == 0)
                System.out.print(a + " ");
            else if (i == 1)
                System.out.print(b + " ");
            else {
                c = a + b;
                System.out.print(c + " ");
                a = b;
                b = c;
            }
        }
    }
}