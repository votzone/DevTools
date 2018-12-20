import org.junit.Test;
import vot.wq.devtool.androidR.ManifestSimplify;
import vot.wq.devtool.androidR.MergeRes;
import vot.wq.devtool.androidR.MergeValues;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class TestAndroiValueMerge {

    @Test
    public void testValueMerge() throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String megerDir = "D:\\Workspace\\DevtoolsTestFile\\testvalue\\mergeDir\\";
        String oriDir = "D:\\Workspace\\DevtoolsTestFile\\testvalue\\oriDir\\";
        MergeValues mergeValues = new MergeValues(megerDir,oriDir);
        mergeValues.merge();
    }

    @Test
    public void testResMerge() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String megerDir = "C:\\Users\\Administrator\\Desktop\\dif\\zyag\\res\\";
        String oriDir = "D:\\mykiller\\projects\\不要碰我（HD）-meizu-3\\Project\\res";
        MergeRes mergeRes = new MergeRes(megerDir,oriDir);
        mergeRes.merge();
    }


    @Test
    public void testSimplifyManifest() throws IOException{
        String manifestPath = "D:\\mykiller\\projects\\不要碰我（HD）-meizu-3\\Project\\AndroidManifest.xml";
        ManifestSimplify manifestSimplify = new ManifestSimplify(new File(manifestPath));
        manifestSimplify.simply();
    }
}
