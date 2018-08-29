package vot.wq.devtool.gui.system;

import vot.wq.devtool.Config;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SystemIcon {

    public static void showSystemIcon() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //将本机系统外观设置为窗体当前外观
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //初始化窗体
        JFrame frame=new JFrame("我的");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setType(Window.Type.UTILITY);

        ImageIcon img=new ImageIcon(SystemIcon.class.getClassLoader().getResource("sysIcon.png"));
        frame.setIconImage(img.getImage());

        //定义弹出菜单
        JPopupMenu Jmenu=new JPopupMenu();

        //为JPopupMenu设置UI
        Jmenu.setUI(new BasicPopupMenuUI(){
            @Override
            public void paint(Graphics g, JComponent c){
                super.paint(g, c);

                //画弹出菜单左侧的灰色背景
                g.setColor(new Color(236,237,238));
                g.fillRect(0, 0, 25, c.getHeight());

                //画弹出菜单右侧的白色背景
                g.setColor(new Color(255,255,255));
                g.fillRect(25, 0, c.getWidth()-25, c.getHeight());
            }
        });

        //定义弹出菜单项
//        JMenuItem online = new JMenuItem("我在线上",new ImageIcon(
//                SystemIcon.class.getClassLoader().getResource("demo01.png")));
//        JMenuItem busy = new JMenuItem("忙碌",new ImageIcon(
//                SystemIcon.class.getClassLoader().getResource("demo02.png")));
        JMenuItem search= new JMenuItem("查找文件");
        JMenuItem debug = new JMenuItem("调试模式");
        JMenuItem exit = new JMenuItem("退出应用");

        //添加弹出菜单项到弹出菜单
//        Jmenu.add(online);
//        Jmenu.add(busy);
        Jmenu.add(search);
        Jmenu.addSeparator();//添加分割线
        Jmenu.add(debug);
        Jmenu.add(exit);

        //得到当前系统托盘
        SystemTray systemtray = SystemTray.getSystemTray();

        //创建带指定图像、工具提示和弹出菜单的 MyTrayIcon
        MyTrayIcon trayicon=new MyTrayIcon(img.getImage(), Config.appName,Jmenu);

        //将TrayIcon添加到系统托盘
        try {
            systemtray.add(trayicon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }

        //设置单击击系统托盘图标显示主窗口
        trayicon.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {

                //鼠标左键点击,设置窗体状态，正常显示
//                if(e.getButton()==MouseEvent.BUTTON1){
//                    frame.setExtendedState(JFrame.NORMAL);
//                    frame.setVisible(true);
//                }
            }
        });

        //定义ActionListener监听器
        ActionListener MenuListen = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("退出应用")){

                    systemtray.remove(trayicon);
                    System.exit(0);
                }
                else if(e.getActionCommand().equals("调试模式")){
                    Config.isDebug = !Config.isDebug;
                }else if(e.getActionCommand().equals("查找文件")){

                }

            }};

        //为弹出菜单项添加监听器
        debug.addActionListener(MenuListen);
        exit.addActionListener(MenuListen);

        frame.setVisible(false);
    }
}
