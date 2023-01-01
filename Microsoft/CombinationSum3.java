// link- https://leetcode.com/problems/combination-sum-iii/

// 1st approach subtracting the sum to get the ans

class Solution {
    private static void solve(List<Integer>cur,List<List<Integer>>ans,int k,int n,int elm)
    {
        if(k==0 && n==0)
        {
            ans.add(new ArrayList(cur));
            return ;
        }
        for(int i=elm+1;i<=9;i++)
        {
            cur.add(i);
            solve(cur,ans,k-1,n-i,i);
            cur.remove(cur.size()-1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer>cur=new ArrayList<Integer>();
        List<List<Integer>>ans=new ArrayList<>();
        solve(cur,ans,k,n,0);
        return ans;
    }
}

// 2nd approach adding up numbers to get the trageted sum and possible solution

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res=new ArrayList<>();
        combi(res,new ArrayList<>(),1,0,k,n);
        return res;
    }
    public void combi(List<List<Integer>> res,List<Integer> ds,int num,int sum,int k,int n){
        if(k==0 && n==sum){
            res.add(new ArrayList<>(ds));
            return;
        }
        for(int i=num;i<=9;i++){
            sum+=i;
            ds.add(i);
            combi(res,ds,i+1,sum,k-1,n);
            sum-=i;
            ds.remove(ds.size()-1);
        }
    }
}
