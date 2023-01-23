// link- https://leetcode.com/problems/k-divisible-elements-subarrays/

// helper Link- https://leetcode.com/problems/k-divisible-elements-subarrays/discuss/3073936/Java-or-Strict-O(n2)-or-Using-Hashing-with-Polynomial-Function

// Hashing Solution

class Solution {
	 
	// <= 40th [173] prime number didn't work well for all test-cases. Ir-respective of what 
	// M = 10^9 + 7 / 10^15 + 7 is.
    private static final int P = 229; // 229 = 50th Prime Number, 541 = 100th prime number
    private static final long M = (long)(1e15 + 7);
    private static final long[] PP;
    private static final int MAX_LENGTH = 2 * 1_00;

    static {
        PP = new long[MAX_LENGTH + 1];
        PP[0] = P;
        for(int i = 1; i <= MAX_LENGTH; i += 1) {
            PP[i] = (PP[i - 1] % M * P % M) % M;
        }
    }

    private void computeHashesForAllSubarrays(int[] nums, long[][] hashes, int n) {
		// hashes[i][j] = is the unique hash value for subarray nums[i:j]
        for(int i = 0; i < n; i += 1) {
            long subHash = 0;
            for(int j = i; j < n; j += 1) {
                int ele = nums[j];
                subHash += (PP[j - i] * ele) % M;
                hashes[i][j] = subHash;
            }
        }
    }
    
    private void buildDivisibleCountForAllSubarrays(int[] nums, int[][] count, int n, int p) {
        // count[i][j] = returns how many elements are there in the subarray
        // nums[i:j], divisible by p. -> Helps to check (count_of_divisibles <= k) in O(1) time
        for(int i = 0; i < n; i += 1) {
            for(int j = i; j < n; j += 1) {
                int rem = nums[j] % p;
                count[i][j] = rem == 0 ? 1 : 0;
                // Computing prefix count array
                if(j > 0) {
                    count[i][j] = rem == 0 ? count[i][j - 1] + 1 : count[i][j - 1];
                }
                // Actual computation where storing(at count[i][j]) how many elements
                // are divisible by p in the sub-array (i, j)
                count[i][j] = (count[i][j] - count[i][i]) + (nums[i] % p == 0 ? 1 : 0);
            }
        }
    }
    
    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length, countSubarrays = 0;
        int[][] count = new int[n][n];
        long[][] hashes = new long[n][n];
        
        buildDivisibleCountForAllSubarrays(nums, count, n, p);
        computeHashesForAllSubarrays(nums, hashes, n);
        
        Set<Long> subarrayHash = new HashSet<>();
        
        for(int i = 0; i < n; i += 1) {
            for(int j = i; j < n; j += 1) {
                if(count[i][j] <= k) {
                    if(!subarrayHash.contains(hashes[i][j])) {
                        countSubarrays += 1;
                        subarrayHash.add(hashes[i][j]);
                    }
                }
            }
        }
        
        return countSubarrays;
    }
}

// Using trie 

class Solution {
    private static class Node {
		private int val;
		private boolean isEnd;
		private Node[] children;

		public Node(int val) {
			this.val = val;
			this.isEnd = false;
			this.children = new Node[201];
		}
	}

	private int totalCount = 0;
	private Node root = new Node(0);
	
	private void insert(int[] nums, int left, int right) {
		Node curr = this.root;
		for (int i = left; i <= right; i++) {
			int childIdx = nums[i];
			if (curr.children[childIdx] == null) {
				curr.children[childIdx] = new Node(childIdx);
			}
			curr = curr.children[childIdx];
			if (!curr.isEnd) {
				totalCount++;
				curr.isEnd = true;
			}
		}
	}

	public int countDistinct(int[] nums, int k, int p) {
		int count = 0;
		int n = nums.length;
		int left = 0;
		int right = 0;
		while (left < n) {
			while (right < n) {
				if (nums[right] % p == 0) {
					count++;
				}
				if (count == k + 1) {
					count--;
					break;
				}
				right++;
			}
			insert(nums, left, right - 1);
			if (nums[left] % p == 0) {
				count--;
			}
			left++;
		}
		return totalCount;
	}
}
