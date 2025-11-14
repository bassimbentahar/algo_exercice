package com.bassim.algo_exercice.fibo;

public class TortueEtLapin_Floyd {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2; // crée un cycle (4 → 2)

        System.out.println(hasCycle(n1)); // true

    }

    private static boolean hasCycle(Node node) {
        if (node == null) return false;

        Node slow = node;
        Node fast = node;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            System.out.println("slow "+slow.value);
            System.out.println("fast " +fast.value);
            System.out.println("---------");
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }
}
