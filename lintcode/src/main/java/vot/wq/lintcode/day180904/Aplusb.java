package vot.wq.lintcode.day180904;

public class Aplusb {
    /**
     * 1. A + B 问题
     * 给出两个整数 a 和 b , 求他们的和。
     *
     * 样例
     * 如果 a=1 并且 b=2，返回3。
     *
     * 挑战
     * 显然你可以直接 return a + b，但是你是否可以挑战一下不这样做？（不使用++等算数运算符）
     *
     * 说明
     * a和b都是 32位 整数么？
     *
     * 是的
     * 我可以使用位运算符么？
     *
     * 当然可以
     * 注意事项
     * 你不需要从输入流读入数据，只需要根据aplusb的两个参数a和b，计算他们的和并返回就行。
     *
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        // 158 ms
        while (b!=0){
            int c = a^b;
            b = (a&b)<<1;
            a = c;
        }
        return a;
    }
}
