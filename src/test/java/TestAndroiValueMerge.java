import org.junit.Test;
import vot.wq.devtool.androidR.MergeValues;

import java.io.IOException;

public class TestAndroiValueMerge {

    @Test
    public void testBoolMerge() throws IOException {
        String megerDir = "D:\\Workspace\\DevtoolsTestFile\\testvalue\\mergeDir\\";
        String oriDir = "D:\\Workspace\\DevtoolsTestFile\\testvalue\\oriDir\\";
        MergeValues mergeValues = new MergeValues(megerDir,oriDir);
        mergeValues.merge();
    }
}
