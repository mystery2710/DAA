import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node left, Node right) {
        this.ch = '-';
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        if (this.freq != other.freq)
            return this.freq - other.freq;

        return this.ch - other.ch;
    }
}

public class HuffmanEncoding {

    static void printCodes(Node root, String code) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
            return;
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] characters = new char[n];
        int[] frequencies = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter character " + (i + 1) + ": ");
            characters[i] = sc.next().charAt(0);

            System.out.print("Enter frequency of " + characters[i] + ": ");
            frequencies[i] = sc.nextInt();
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < characters.length; i++) {
            pq.add(new Node(characters[i], frequencies[i]));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node merged = new Node(
                    left.freq + right.freq,
                    left,
                    right
            );

            pq.add(merged);
        }

        Node root = pq.poll();

        System.out.println("Huffman Codes:");
        printCodes(root, "");

        sc.close();
    }
}
