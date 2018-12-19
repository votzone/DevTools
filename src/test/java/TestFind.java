import org.junit.Test;
import vot.wq.devtool.find.SearchInDir;
import vot.wq.devtool.img.ResizeImg;
import vot.wq.devtool.restf.GrabBlog;

public class TestFind {

    @Test
    public void findInDir(){
//        Config.isDebug = true;
        String dir = "D:\\Workspace\\DevtoolsTestFile\\Data";
        String keyword = "0";
        String charSet = "utf-8";
        SearchInDir searchInDir = new SearchInDir(dir,keyword,charSet);
        searchInDir.search();
        searchInDir.printResult();
    }

    @Test
    public void grabBlog(){
        String dir = "D:\\Workspace\\DevtoolsTestFile";
        String url = "https://blog.csdn.net/liu857279611/article/details/71244224";
        GrabBlog grabBlog = new GrabBlog(url, dir);
        grabBlog.grab();
        grabBlog.save();
    }

    @Test
    public void resizeImage(){
        String input = "D:\\Workspace\\DevtoolsTestFile\\clipimg01.png";
        String output = "D:\\Workspace\\DevtoolsTestFile\\clipimg01-1.png";
        ResizeImg resizeImg = new ResizeImg(input, output, 100,100);
        resizeImg.resizeWithRadio(17);

    }

}
