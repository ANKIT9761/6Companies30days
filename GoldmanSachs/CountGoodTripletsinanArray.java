// link - https://leetcode.com/problems/count-good-triplets-in-an-array/

// taken help from - https://leetcode.com/problems/count-good-triplets-in-an-array/discuss/2889161/Two-steps%3A-Match-Array-and-BITFenwick-oror-Explained

// https://leetcode.com/problems/count-good-triplets-in-an-array/discuss/2698598/Java-or-3-solutions-or-Binary-Index-Tree

class Solution {
    class BIT {
        long[] arr;
        public BIT(int n) {
            this.arr = new long[n + 1];
        }

        public void update(int idx, long x) {
            idx++;
            while (idx < this.arr.length) {
                arr[idx] += x;
                idx += lowbit(idx);
            }
        }
        // get the prefix sum of [0, idx]
        public long query(int idx) {
            // idx++;
            long result = 0L;
            while (idx > 0) {
                result += arr[idx];
                idx -= lowbit(idx);
            }
            return result;
        }


        public int lowbit(int x) {
            return x & (-x);
        }
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        // use fenwick tree, the value is idx,traverse from smaller to larger, so we can find the small ones
        int n = nums1.length;

        int[] valueToIdx1 = new int[n];
        for (int i = 0; i < n; i++) valueToIdx1[nums1[i]] = i;
        int[] idxMapping2to1 = new int[n];
        for (int i = 0; i < n; i++) {
            int v2 = nums2[i];
            int idx1 = valueToIdx1[v2];
            idxMapping2to1[i] = idx1;
        }

        long ret = 0L;
        BIT B1 = new BIT(n); // B1[i] store that value appears or not
        BIT B2 = new BIT(n); // B2[i] store the number of values smaller than i appear

        for (int idx : idxMapping2to1) {
            ret += B2.query(idx);
            B1.update(idx, 1);
            long lesserCnt = B1.query(idx);
            B2.update(idx, lesserCnt);
        }

        return ret;
    }
}
