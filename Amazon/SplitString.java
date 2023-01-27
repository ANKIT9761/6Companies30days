// link- 

class Solution {
    HashSet<String> set = new HashSet<>();
    int count;
    public int maxUniqueSplit(String s) {
        helper(s, 0, "");
        return count;
    }
    
    public void helper(String s,int idx, String str )
    {
        if(idx == s.length())
        {
           count = Math.max(count, set.size());
            return;
        }
          
        
        for(int i =idx;i<s.length();i++)
        {        
            str = s.substring(idx,i+1);
            if(set.contains(str))
                continue;
             set.add(str);
             helper(s,i+1, str);
             set.remove(str);
           
        }
       
      
    }
}
