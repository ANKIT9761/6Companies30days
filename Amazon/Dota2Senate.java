// link- 

class Solution {
    public String predictPartyVictory(String senates) {
        char[] senate = senates.toCharArray();
        int bansForR = 0;
        int bansForD = 0;
        int totalBannedR = 0;
        int totalBannedD = 0;
        int totalR = 0;
        int totalD = 0;
        for (int i=0; i<senate.length; i++) {
            char memberParty = senate[i];
            if (memberParty == 'R') {
                totalR++;
            } else {
                totalD++;
            }
        }
        int totalSenators = senate.length;
        //X senators are "banned" who can't participate anymore
        for (int i=0; true; i++) {
            if (i==senate.length) {
                i=0;
            }
            char memberParty = senate[i];
            if (memberParty == 'R') {
                if (bansForR>0) {                
                    //R Banned, make him disappear and discount one ban
                    bansForR--;
                    senate[i] = 'X';
                } else {
                    //if not banned, he bans
                    bansForD++;
                    totalBannedD++;
                    if (totalBannedD >= totalD) {
                        return "Radiant";
                    }
                }
            } else if (memberParty == 'D') {
                //same for D
                if (bansForD>0) {
                    bansForD--;
                    senate[i] = 'X';
                } else {
                    bansForR++;
                    totalBannedR++;
                    if (totalBannedR >= totalR) {
                        return "Dire";
                    }
                }
            }
        }
    }
}

// 2nd queue Approach

public String predictPartyVictory(String senate) {
        Queue<Integer> qr = new LinkedList<>();
        Queue<Integer> qd = new LinkedList<>();
        int n = senate.length();
        for(int i = 0;i < n;i++){
            if(senate.charAt(i) == 'R') qr.add(i);
            else qd.add(i);
        }
        for(;!qr.isEmpty() && !qd.isEmpty();){
            int r_i = qr.poll();
            int d_i = qd.poll();
            if(r_i < d_i) qr.add(r_i + n);
            else qd.add(d_i + n);
        }
        return qr.size() > qd.size() ? "Radiant" : "Dire";
    }
