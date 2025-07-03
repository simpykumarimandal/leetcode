class Solution {
    Map<String, Set<String>> mp;

    public int ladderLength(String sw, String ew, List<String> w) {
        mp = new HashMap<>();

       
        for (String word : w) {
            mp.put(word, new HashSet<>());
        }
        mp.put(sw, new HashSet<>()); 
        for (String w2 : w) {
            if (oneCharDiff(sw, w2)) {
                mp.get(sw).add(w2);
                mp.get(w2).add(sw);
            }
        }

       
        for (int i = 0; i < w.size() - 1; i++) {
            for (int j = 1; j < w.size(); j++) {
                String w1 = w.get(i);
                String w2 = w.get(j);
                if (oneCharDiff(w1, w2)) {
                    mp.get(w1).add(w2);
                    mp.get(w2).add(w1);
                }
            }
        }

        if (!mp.containsKey(ew)) return 0;

  
        Queue<String> q = new LinkedList<>();
        q.add(sw);
        Set<String> vis = new HashSet<>();
        Map<String, Integer> dist = new HashMap<>();

        for (String word : w) {
            dist.put(word, (int) 1e9);
        }
        dist.put(sw, 0);

        while (!q.isEmpty()) {
            String newW = q.poll();
            vis.add(newW);

            for (String word : mp.get(newW)) {
                int currDist = dist.get(word);
                int newDist = dist.get(newW) + 1;

                if (newDist <= currDist) {
                    dist.put(word, newDist);
                    q.add(word);
                }
            }
        }

        return dist.get(ew) != (int) 1e9 ? dist.get(ew) + 1 : 0;
    }

 
    boolean oneCharDiff(String w1, String w2) {
        if (w1.length() != w2.length()) return false;
        int count = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) count++;
        }
        return count == 1;
    }
}