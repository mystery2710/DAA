import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}

public class HuffmanEncoding {

    static void printCodes(Node root, String code) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {

        char[] characters = {'A', 'B', 'C', 'D'};
        int[] frequencies = {5, 9, 12, 13};

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < characters.length; i++) {
            pq.add(new Node(characters[i], frequencies[i]));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node merged = new Node('-', left.freq + right.freq);
            merged.left = left;
            merged.right = right;

            pq.add(merged);
        }

        Node root = pq.poll();

        System.out.println("Huffman Codes:");
        printCodes(root, "");
    }
}