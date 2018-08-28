package wq.devtool.cli;

import wq.devtool.Config;
import wq.devtool.find.SearchInDir;

import java.io.File;
import java.util.Scanner;

public class DevMain {
    // 编码
    private String printUseage(){
        System.out.println("用法:");
        System.out.println("\t0- 在文件夹中查找");
        System.out.println("\tD- 调试模式");
        System.out.println("\tE- 退出");
        Scanner in = new Scanner(System.in);
        System.out.print("请选择任务:");
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
