package vot.wq.devtool.img;

import vot.wq.devtool.Config;

import java.util.Scanner;

public class GlobalUtil {
    public static Scanner getScanner(){
        String charset = "utf-8";
        if(Config.needGbk){
            charset = "gbk";
        }
        Scanner in = new Scanner(System.in,charset);
        return in;
    }
}
