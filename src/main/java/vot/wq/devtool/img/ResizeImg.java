package vot.wq.devtool.img;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImg {
    String input,output;
    int outWidth;
    int outHeight;
    boolean forceSize;

    public ResizeImg(String input,String output, int outWidth, int outHeight){
        this.input = input;
        this.output = output;
        this.outWidth = outWidth;
        if(outHeight <=0){
            forceSize = false;
            this.outHeight = Integer.MAX_VALUE;
        }else {
            forceSize = true;
            this.outHeight = outHeight;
        }
    }

    public void resize(){
        try {
            if(forceSize){
                Thumbnails.of(input).forceSize(outWidth, outHeight).toFile(output);
            }else {
                Thumbnails.of(input).size(outWidth, outHeight).toFile(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resizeWithRadio(int radius){
        try {
            BufferedImage bufferedImage = null;
            if(forceSize){
                bufferedImage= Thumbnails.of(input).forceSize(outWidth,outHeight).asBufferedImage();
            }else {
                bufferedImage= Thumbnails.of(input).size(outWidth,outHeight).asBufferedImage();
            }
            bufferedImage = makeRoundedCorner(bufferedImage,radius);
            ImageIO.write(bufferedImage,"png",new File(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成圆角图标
     * @author Master.Pan
     * @date 2016年5月11日 上午9:56:37
     * @param image
     * @param cornerRadius 圆角半径
     * @return
     */
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }


}
