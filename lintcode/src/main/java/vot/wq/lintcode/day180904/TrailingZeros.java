package vot.wq.lintcode.day180904;

public class TrailingZeros {

    /**
     * 描述
     * 设计一个算法，计算出n阶乘中尾部零的个数
     *
     * 您在真实的面试中是否遇到过这个题？  是
     * 样例
     * 11! = 39916800，因此应该返回 2
     *
     * 挑战
     * O(logN)的时间复杂度
     *
     * 思路：
     * 包含5的幂的乘积就会有零，5的幂越高，零越多，因此循环 除5 即可。
     * @param n
     * @return
     */
    public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        long result = 0;
        while(n>=5){
            result += n/5;
            n = n/5;
        }
        return result;
    }

    public static void main(String[] args){
        int n = 105;
        System.out.println(new TrailingZeros().trailingZeros(n));
    }
}
