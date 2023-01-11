// link- https://leetcode.com/problems/fraction-to-recurring-decimal/

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 0) {
            return "NaN";
        }

        StringBuilder res = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0)) {
            res.append("-");
        }
        
        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        res.append(n / d);

        if (n % d == 0) {
            return res.toString();
        }

        res.append(".");
        n %= d;
        Map<Long, Integer> dic = new HashMap<>();
        int idx = res.length();
        
        while (n != 0) {
            if (dic.containsKey(n)) {
                res.insert(dic.get(n), "(");
                res.append(")");
                break;
            }
            dic.put(n, idx);
            idx++;
            n *= 10;
            res.append(n / d);
            n %= d;
        }
        
        return res.toString();
    }
}
