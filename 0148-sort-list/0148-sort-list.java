/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 import java.util.Arrays;
class Solution {
    public ListNode sortList(ListNode head) {
        int count=1;
        ListNode temp=head;
        if(head==null) return null;
        else if(head.next==null) return head;
      
        while(temp.next!=null)
        {
            temp=temp.next;
            count++;
        }
      
        int arr[]=new int[count];
        temp=head;
        int i=0;
        while(temp.next!=null)
        {
            arr[i]=temp.val;
            i++;
            temp=temp.next;
        }
        arr[i]=temp.val;
       
        i=0;
        Arrays.sort(arr);
      
        temp=head;
        while(temp.next!=null)
        {
            temp.val=arr[i];
            i++;
            temp=temp.next;
        }
        temp.val=arr[i];

        return head;
    }
}