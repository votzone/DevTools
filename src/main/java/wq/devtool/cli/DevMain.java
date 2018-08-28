package wq.devtool.cli;

import wq.devtool.find.SearchInDir;

import java.io.File;
import java.util.Scanner;

public class DevMain {
    // 编码
    private String printUseage(){
        System.out.println("Useage:");
        System.out.println("0- Search In Dir");
        System.out.println("D- Debug");
        System.out.println("E- Exit");
        Scanner in = new Scanner(System.in);
        System.out.print("Place Choose Task:");
        String cmds = in.nextLine();
        System.out.println(cmds);
        return cmds.trim().replace(","," ").replace("，"," ");

    }

    private static String cmds = "";
    public static void main(String [] args){
        while (true) {
            cmds = new DevMain().printUseage();

            if(cmds.contains("0")){
                Scanner in = new Scanner(System.in);
                System.out.print("Search In Dir: ");
                String dir = in.nextLine();
                System.out.print("Input Keyword: ");
                String keyword = in.nextLine();
                System.out.print("Input Keyword's Charset: ");
                String charset = in.nextLine();
                SearchInDir searchInDir = new SearchInDir(dir,keyword,charset);
                searchInDir.search();
                searchInDir.printResult();
            }


            if (cmds.contains("E")){
                System.exit(0);
            }
        }
    }
}
