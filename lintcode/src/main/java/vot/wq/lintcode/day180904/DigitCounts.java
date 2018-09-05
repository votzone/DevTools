package vot.wq.lintcode.day180904;

public class DigitCounts {
    /**
     * 描述
     * 计算数字k在0到n中的出现的次数，k可能是0~9的一个值
     *
     * 您在真实的面试中是否遇到过这个题？  是
     * 样例
     * 例如n=12，k=1，在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]，我们发现1出现了5次 (1, 10, 11, 12)
     *
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        // write your code here
        int rs = 0;
        int jinzhi = 10;// 几位数 Number of digits
        int num = n;
        int debug =0;
        while ((num/jinzhi)>0){
            //TODO 对零的处理有问题
            rs += (num / jinzhi) * (jinzhi/10);
            if(num/jinzhi >10)


            debug = num%jinzhi/(jinzhi/10);
            if((num%jinzhi/(jinzhi/10))==k) {
                rs += num % jinzhi % (jinzhi / 10) + 1;
            }
            debug = num%jinzhi/(jinzhi/10);
            if((num%jinzhi/(jinzhi/10))>k){
                rs += jinzhi/10;
            }


            jinzhi *=10;
        }
        jinzhi /=10;

        if((num/jinzhi) > k){
            rs += (jinzhi/10);
        }
        if((num/jinzhi) == k){
            rs += (num %jinzhi)+1;
        }

        return rs;
    }

    public static void main(String args[]){
        int k =0;
        int n =121;
        System.out.println(new DigitCounts().digitCounts(k,n));
    }
}
