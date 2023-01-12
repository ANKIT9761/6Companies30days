// link- https://leetcode.com/problems/magical-string/

// helper link- https://leetcode.com/problems/magical-string/discuss/2560327/Very-easy-java-solution


class Solution {
    public int magicalString(int n) {
        StringBuilder st=new StringBuilder("122");
        int p1=2,p2=st.length(),count=0;
        while(st.length()<n){
            if(st.charAt(p1)=='1'){
                if(st.charAt(p2-1)=='1')
                    st.append(2);
                else
                    st.append(1);
                p2++;
            }
            else{
                if(st.charAt(p2-1)=='1')
                    st.append(22);
                else
                    st.append(11);
                p2+=2;
            }
            
            //p2++;
            p1++;
        }
        for(int i=0;i<n;i++){
            if(st.charAt(i)=='1')
                count++;
        }
        return count;
    }
}
