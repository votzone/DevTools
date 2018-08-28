package wq.devtool.cli;

import wq.devtool.Config;
import wq.devtool.find.SearchInDir;
import wq.devtool.log.Log;

import java.io.File;
import java.util.Scanner;

public class DevMain {
    // 编码
    private String printUseage(){
        System.out.println("Useage:");
        System.out.println("\t0- Search In Dir");
        System.out.println("\tD- Debug");
        System.out.println("\tE- Exit");
        Scanner in = new Scanner(System.in);
        System.out.print("Please Choose Task:");
        String cmds = in.nextLine();
        System.out.println(cmds);

        if(cmds.contains("D")){
            Config.isDebug = true;
        }

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
                try {
                    SearchInDir searchInDir = new SearchInDir(dir,keyword,charset);
                    searchInDir.search();
                    searchInDir.printResult();
                }catch (RuntimeException e){
                    Log.e("Search Failed! Please Make sure your inputs is correct!");
                }

            }


            if (cmds.contains("E")){
                System.exit(0);
            }
        }
    }
}
