package vot.wq.devtool.cli;

import vot.wq.devtool.Config;

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
                SearchInDirCli.searchInDir();
            }

            if (cmds.contains("E")){
                System.exit(0);
            }
        }
    }
}
