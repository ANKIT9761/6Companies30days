// link- https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Dfs(root1, list1);
        Dfs(root2, list2);
        List<Integer> res = merge(list1, list2);
        return res;
    }
    void Dfs(TreeNode root, List<Integer> list) {
        if(root == null) return;
        Dfs(root.left, list);
        list.add(root.val);
        Dfs(root.right, list);
    }
    List<Integer> merge(List<Integer> l1, List<Integer> l2) {
        List<Integer> ans = new ArrayList<>();
        int m = 0; 
        int n = 0;
        while(m < l1.size() && n < l2.size()) {
            if(l1.get(m) < l2.get(n))
                ans.add(l1.get(m++));
            else ans.add(l2.get(n++));    
        }
        while(m < l1.size())
            ans.add(l1.get(m++));
        while(n < l2.size())
            ans.add(l2.get(n++));    
        return ans;    
    }
}
