package vot.wq.devtool.cli;

import vot.wq.devtool.Config;
import vot.wq.devtool.L;
import vot.wq.devtool.find.SearchInDir;

import java.util.Scanner;

public class SearchInDirCli {
    public static void searchInDir(){
        String charset = "utf-8";
        if(Config.needGbk){
            charset = "gbk";
        }
        Scanner in = new Scanner(System.in, charset);
        L.tiol("Search In Dir: ");
        String dir = in.nextLine();
        L.tiol("Input Keyword: ");
        String keyword = in.nextLine();
        L.tiol("Input Keyword's Charset: ");
        charset = in.nextLine();
        try {
            SearchInDir searchInDir = new SearchInDir(dir,keyword,charset);
            searchInDir.search();
            searchInDir.printResult();
        }catch (RuntimeException e){
            L.e("Search Failed! Please Make sure your inputs is correct!");
        }
    }
}
