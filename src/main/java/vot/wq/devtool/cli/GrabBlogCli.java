package vot.wq.devtool.cli;

import vot.wq.devtool.Config;
import vot.wq.devtool.L;
import vot.wq.devtool.find.SearchInDir;
import vot.wq.devtool.restf.GrabBlog;

import java.util.Scanner;

public class GrabBlogCli {

    public static void grabBlog(){
        Scanner in = new Scanner(System.in);
        L.tiol("Blog Url: ");
        String url = in.nextLine();
        try {
            GrabBlog grabBlog = new GrabBlog(url, Config.baseDir);
            grabBlog.grab();
            grabBlog.save();
        }catch (RuntimeException e){
            L.e("Grab Blog Failed! Please Make sure your input is correct!");
        }
    }
}
