package com.bassim.algo_exercice.fibo;

import java.sql.DriverManager;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class MergeTwoSortedLists {


    public static void main(String[] args) {

        // Création de list1 = [1,2,4]
        ListNode3 list1 = new ListNode3(1, new ListNode3(2, new ListNode3(4)));

        // Création de list2 = [1,3,4]
        ListNode3 list2 = new ListNode3(1, new ListNode3(3, new ListNode3(4)));

        // Appel de la fonction de fusion
        ListNode3 merged = mergeTwoLists(list1, list2);

        // Affichage du résultat
        printList(merged);  // Résultat attendu : 1 -> 1 -> 2 -> 3 -> 4 -> 4
    }

    public static class ListNode3 {
        int val;
        ListNode3 next;

        ListNode3() {
        }

        ListNode3(int val) {
            this.val = val;
        }

        ListNode3(int val, ListNode3 next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode3 mergeTwoLists(ListNode3 list1, ListNode3 list2) {
        ListNode3 dummy = new ListNode3();
        ListNode3 result = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                dummy.next = list1;
                list1 = list1.next;
            } else {
                dummy.next = list2;
                list2 = list2.next;
            }
            dummy = dummy.next;
        }
        if (list1 != null) dummy.next = list1;
        if (list2 != null) dummy.next = list2;
        return result.next;
    }

    private static void printList(ListNode3 head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}
