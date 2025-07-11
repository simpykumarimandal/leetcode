import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
   
        List<int[]> sortedMeetings = new ArrayList<>();
        for (int[] meeting : meetings) {
            sortedMeetings.add(meeting);
        }
        Collections.sort(sortedMeetings, (a, b) -> Integer.compare(a[0], b[0]));

       
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }

      
        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]); 
        });

        int[] meetingCounts = new int[n];

      
        for (int[] meeting : sortedMeetings) {
            long startTime = meeting[0];
            long endTime = meeting[1];
            long duration = endTime - startTime;

            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= startTime) {
                long[] room = busyRooms.poll();
                availableRooms.offer((int) room[1]);
            }

           
            if (availableRooms.isEmpty()) {
                
                long[] nextFreeRoom = busyRooms.poll();
                long roomBecomesFreeTime = nextFreeRoom[0];
                int roomNumber = (int) nextFreeRoom[1];
                
             
                meetingCounts[roomNumber]++;
                busyRooms.offer(new long[]{roomBecomesFreeTime + duration, roomNumber});
            } else {

                int roomNumber = availableRooms.poll();
                meetingCounts[roomNumber]++;
                busyRooms.offer(new long[]{startTime + duration, roomNumber});
            }
        }

    
        int maxMeetings = 0;
        int mostBookedRoom = -1;

        for (int i = 0; i < n; i++) {
            if (meetingCounts[i] > maxMeetings) {
                maxMeetings = meetingCounts[i];
                mostBookedRoom = i;
            }
        }

        return mostBookedRoom;
    }
}