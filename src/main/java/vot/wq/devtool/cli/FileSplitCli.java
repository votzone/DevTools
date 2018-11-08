package vot.wq.devtool.cli;

import vot.wq.devtool.L;
import vot.wq.devtool.androidR.MergePublicXml;
import vot.wq.devtool.filespliter.FileSpliter;
import vot.wq.devtool.img.GlobalUtil;

import java.util.Scanner;

public class FileSplitCli {
    public static void splitFile(){
        Scanner in = GlobalUtil.getScanner();
        L.tiol(null,"file need split path : ");
        String inputFile = in.nextLine();

        FileSpliter fileSpliter = new FileSpliter(inputFile);
        fileSpliter.split();
    }
}
