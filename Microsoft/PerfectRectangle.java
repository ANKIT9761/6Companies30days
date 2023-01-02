// link- https://leetcode.com/problems/perfect-rectangle/

// His Explanation and code is better than mine so posting this one- https://leetcode.com/problems/perfect-rectangle/discuss/2981176/Java-With-Explanation


class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        
        Set<String> set=new HashSet<>();
        int area=0;
        int MAXA=Integer.MIN_VALUE;
        int MAXB=Integer.MIN_VALUE;
        int MINX=Integer.MAX_VALUE;
        int MINY=Integer.MAX_VALUE;
        for(int[] rect:rectangles){
            int x=rect[0];
            int y=rect[1];
            int a=rect[2];
            int b=rect[3];

            MAXA=Math.max(MAXA,a);
            MAXB=Math.max(MAXB,b);
            MINX=Math.min(MINX,x);
            MINY=Math.min(MINY,y);

            area+=(a-x)*(b-y);

            String p1=x+","+y;//bleft
            String p2=x+","+b;//topleft
            String p3=a+","+y;//bright
            String p4=a+","+b;//tright

            check(set,p1);
            check(set,p2);
            check(set,p3);
            check(set,p4);
        }
        String bleft=MINX+","+MINY;
        String bright=MAXA+","+MINY;
        String tleft=MINX+","+MAXB;
        String tright=MAXA+","+MAXB;
        if(set.size()!=4 || !set.contains(bleft) || !set.contains(tleft) || !set.contains(bright) || !set.contains(tright)){
            return false;
        }
        int marea=(MAXA-MINX)*(MAXB-MINY);
        if(area==marea){
            return true;
        }
        return false;
    }
    public static void check(Set<String> set,String p){
        if(set.contains(p)){
            set.remove(p);
        }
        else{
            set.add(p);
        }
    }
}
