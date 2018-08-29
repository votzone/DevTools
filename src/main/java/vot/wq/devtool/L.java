package vot.wq.devtool;

import org.apache.http.util.TextUtils;
import vot.wq.devtool.Config;

import java.io.PrintStream;

public class L {
    public static void td(String tag,String ...msg){
        if(!Config.isDebug) {
            return;
        }
        print(System.out, tag, msg);
    }

    public static void ti(String tag, String ...msg){
        print(System.out, tag, msg);
    }

    public static void te(String tag, String ...msg){
        print(System.err,tag, msg);
    }


    public static void d(String ...msg){
        String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
        String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
        String tag = String.format("%s-%s",classname,method_name);
        td(tag, msg);
    }

    public static void i(String ...msg){
        String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
        String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
        String tag = String.format("%s-%s",classname,method_name);
        ti(tag,msg);
    }

    public static void e(String ...msg){
        String classname = new Exception().getStackTrace()[1].getClassName(); //获取调用者的类名
        String method_name = new Exception().getStackTrace()[1].getMethodName(); //获取调用者的方法名
        String tag = String.format("%s-%s",classname,method_name);
        te(tag,msg);
    }


    private static void print(PrintStream ps, String tag, String ...msg){
        if(!TextUtils.isEmpty(tag)) {
            ps.print(tag + " : ");
        }
        if(msg!=null){
            for (int i=0;i<msg.length;i++){
                ps.print(msg[i]+" ");
            }
        }
        ps.println();
    }
}
