package vot.wq.devtool.cli;

import vot.wq.devtool.Config;
import vot.wq.devtool.L;
import vot.wq.devtool.gui.system.SystemIcon;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class DevMain {
    // 编码
    private String printUseage(){
        L.i("Useage:");
        L.i("\t0- Search In Dir");
        L.i("\t1- Grab Blog");
        L.i("\tD- Debug");
        L.i("\tE- Exit");
        Scanner in = new Scanner(System.in);
        L.i("Please Choose Task:");
        String cmds = in.nextLine();
        L.i(cmds);

        if(cmds.contains("D")){
            Config.isDebug = true;
        }

        if(!Config.isDebug){
            File file = new File(".\\data");
            file.mkdirs();
            Config.baseDir = file.getAbsolutePath();
        }

        return cmds.trim().replace(","," ").replace("，"," ");

    }

    private static String cmds = "";
    public static void main(String [] args){
        Config.isDebug = false;
//        if(args == null || args.length <=0){
//            showTrayIcon();
//        }else {
            while (true) {
                cmds = new DevMain().printUseage();
                if (cmds.contains("0")) {
                    SearchInDirCli.searchInDir();
                }

                if(cmds.contains("1")){
                    GrabBlogCli.grabBlog();
                }

                if (cmds.contains("E")) {
                    System.exit(0);
                }
            }
//        }
    }

    public static void showTrayIcon(){
        try {
            SystemIcon.showSystemIcon();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 这种情况下，直接双击jar包即可打开frame
    public static void main1(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(350, 250);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        jf.setSize(500, 500);
//        jf.setLocationRelativeTo(null);
//        jf.setType(Window.Type.NORMAL);

        // 创建内容面包容器，指定使用 边界布局
        JPanel panel = new JPanel(new BorderLayout());

        // 创建 5 个按钮
        JButton btnN = new JButton("Button_North");
        JButton btnS = new JButton("Button_South");
        JButton btnW = new JButton("Button_West");
        JButton btnE = new JButton("Button_East");
        JButton btnC = new JButton("Button_Center");

        // 把 5 个按钮添加到容器中的 5 个方位
        // test：通过设置BorderLayout，可以实现内容根据window缩放变化
        panel.add(btnN, BorderLayout.NORTH);
        panel.add(btnS, BorderLayout.SOUTH);
        panel.add(btnW, BorderLayout.WEST);
        panel.add(btnE, BorderLayout.EAST);
        panel.add(btnC, BorderLayout.CENTER);

        jf.setContentPane(panel);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
