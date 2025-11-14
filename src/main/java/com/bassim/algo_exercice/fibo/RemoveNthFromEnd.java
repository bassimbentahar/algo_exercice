package com.bassim.algo_exercice.fibo;

public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode2 node5 = new ListNode2(5);
        ListNode2 node4 = new ListNode2(4, node5);
        ListNode2 node3 = new ListNode2(3, node4);
        ListNode2 node2 = new ListNode2(2, node3);
        ListNode2 node1 = new ListNode2(1, node2);
        //1->2->3->4->5
        new RemoveNthFromEnd().removeNthFromEnd(node5, 1);

    }

    public ListNode2 removeNthFromEnd(ListNode2 head, int n) {
        ListNode2 dummy = new ListNode2(0, head);
        ListNode2 fast = dummy;
        ListNode2 slow = dummy;

        int i = 0;
        while (i <= n) {
            fast = fast.next;
            i++;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }
}

class ListNode2 {
    int val;
    ListNode2 next;

    ListNode2() {
    }

    ListNode2(int val) {
        this.val = val;
    }

    ListNode2(int val, ListNode2 next) {
        this.val = val;
        this.next = next;
    }
}
