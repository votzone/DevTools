package vot.wq.devtool.cli;

import org.apache.http.util.TextUtils;
import vot.wq.devtool.Config;
import vot.wq.devtool.L;
import vot.wq.devtool.img.GlobalUtil;
import vot.wq.devtool.img.ResizeImg;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImageCli {

    public static void main(String []args){
        makeAndroidIcons();
    }

    public static void resizeImage(){
        Scanner in = GlobalUtil.getScanner();
        L.tiol(null,"Source Image: ");
        String input = in.nextLine();
        L.tiol(null,"Output Image: ");
        String output = in.nextLine();

        if(TextUtils.isEmpty(output)){
            int lastDotIndex = input.lastIndexOf(".");
            output = input.substring(0,lastDotIndex)+"-resize"+input.substring(lastDotIndex);
            L.ti(null, output);
        }

        L.tiol(null,"Resize Width: ");
        int width = in.nextInt();
        L.tiol(null,"Resize Height (0 for default): ");
        int height = in.nextInt();
        L.tiol(null,"Width radius rate(17.5 for 17.5%) : ");
        double needRadius = in.nextDouble()/100;

        try {
            ResizeImg resizeImg = new ResizeImg(input, output, width, height);
            if (needRadius > 0.001) {
                resizeImg.resizeWithRadio((int) (width * needRadius));
            } else {
                resizeImg.resize();
            }
        }catch (RuntimeException e){
            L.te(null,"Resize Image Failed! Please Make sure your input is correct!");
        }

    }

    private static Map<String, Integer> iconSize = new HashMap<>();
    static {
        iconSize.put("ldpi",36);
        iconSize.put("hdpi",72);
        iconSize.put("mdpi",48);
        iconSize.put("xhdpi",96);
        iconSize.put("xxhdpi",144);
        iconSize.put("xxxhdpi",192);
    }
    private static String getSilentOutput(String input, String dirname){
//        int lastDotIndex = input.lastIndexOf(".");
//        return input.substring(0,lastDotIndex)+"-resize"+input.substring(lastDotIndex);
        File file = new File(input);
        File out = new File(file.getParentFile(),dirname);
        out.mkdirs();
        out = new File(out,file.getName());
        return out.getAbsolutePath();
    }

    public static void makeAndroidIcons(){
        Scanner in = GlobalUtil.getScanner();
        L.tiol(null,"Source Image: ");
        String input = in.nextLine();
        L.tiol(null,"Output Dir type (0 for mipmap, 1 for drawable): ");
        int outtype = in.nextInt();
        L.tiol(null,"Width radius rate(17.5 for 17.5%) : ");
        double needRadius = in.nextDouble()/100;
        String prxout = "";
        if(outtype ==0){
            prxout = "mipmap";
        }else {
            prxout = "drawable";
        }

        try {
            for (String key : iconSize.keySet()) {
                String output = getSilentOutput(input, prxout + "-" + key);
                int size = iconSize.get(key);
                ResizeImg resizeImg = new ResizeImg(input, output, size, size);
                if (needRadius > 0.001) {
                    resizeImg.resizeWithRadio((int) (size * needRadius));
                } else {
                    resizeImg.resize();
                }
            }
        }catch (RuntimeException e){
            L.te(null,"Make Android Icons Failed! Please Make sure your input is correct!");
        }


    }
}
