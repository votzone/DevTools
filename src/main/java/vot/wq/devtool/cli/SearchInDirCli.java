package vot.wq.devtool.cli;

import vot.wq.devtool.find.SearchInDir;
import vot.wq.devtool.log.Log;

import java.util.Scanner;

public class SearchInDirCli {
    public static void searchInDir(){
        Scanner in = new Scanner(System.in);
        System.out.print("Search In Dir: ");
        String dir = in.nextLine();
        System.out.print("Input Keyword: ");
        String keyword = in.nextLine();
        System.out.print("Input Keyword's Charset: ");
        String charset = in.nextLine();
        try {
            SearchInDir searchInDir = new SearchInDir(dir,keyword,charset);
            searchInDir.search();
            searchInDir.printResult();
        }catch (RuntimeException e){
            Log.e("Search Failed! Please Make sure your inputs is correct!");
        }
    }
}
