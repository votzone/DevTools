import org.junit.Test;
import vot.wq.devtool.androidR.MergeRes;
import vot.wq.devtool.androidR.MergeValues;

import java.io.IOException;

public class TestAndroiValueMerge {

    @Test
    public void testValueMerge() throws IOException {
        String megerDir = "D:\\Workspace\\DevtoolsTestFile\\testvalue\\mergeDir\\";
        String oriDir = "D:\\Workspace\\DevtoolsTestFile\\testvalue\\oriDir\\";
        MergeValues mergeValues = new MergeValues(megerDir,oriDir);
        mergeValues.merge();
    }

    @Test
    public void testResMerge() throws IOException {
        String megerDir = "C:\\Users\\Administrator\\Desktop\\dif\\zyag\\res\\";
        String oriDir = "D:\\mykiller\\projects\\不要碰我（HD）-meizu-3\\Project\\res";
        MergeRes mergeRes = new MergeRes(megerDir,oriDir);
        mergeRes.merge();
    }
}
