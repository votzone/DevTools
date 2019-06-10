package vot.wq.devtool.cli;

import vot.wq.devtool.Config;
import vot.wq.devtool.L;
import vot.wq.devtool.find.SearchInDir;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Unity2Ios {
    public static void unity2Ios(){
        String charset = "utf-8";
        if(Config.needGbk){
            charset = "gbk";
        }
        Scanner in = new Scanner(System.in, charset);
        L.tiol("Target Dir Or File: ");
        String dir = in.nextLine();

        try {
            vot.wq.devtool.unity2ios.Unity2Ios unity2Ios = new vot.wq.devtool.unity2ios.Unity2Ios();
            unity2Ios.dealOneFile(new File(dir));
        }catch (IOException e){
            L.e("Unity2IOS Failed! Please Make sure your inputs is correct!");
        }
    }

    public static void combinAssets(){
        String charset = "utf-8";
        if(Config.needGbk){
            charset = "gbk";
        }
        Scanner in = new Scanner(System.in, charset);
        L.tiol("Target Dir Or File: ");
        String dir = in.nextLine();

        try {
            vot.wq.devtool.unity2ios.CombinAsset unity2Ios = new vot.wq.devtool.unity2ios.CombinAsset();
            unity2Ios.dealOneFile(new File(dir));
        }catch (IOException e){
            L.e("Unity2IOS Failed! Please Make sure your inputs is correct!");
        }
    }
}
