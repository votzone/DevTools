import org.junit.Test;
import wq.devtool.Config;
import wq.devtool.find.SearchInDir;

public class TestFind {

    @Test
    public void findInDir(){
//        Config.isDebug = true;
        String dir = "D:\\Workspace\\DevtoolsTestFile\\Data";
        String keyword = "荒川";
        String charSet = "utf-8";
        SearchInDir searchInDir = new SearchInDir(dir,keyword,charSet);
        searchInDir.search();
        searchInDir.printResult();
    }
}
