package vot.wq.devtool.cli;

import vot.wq.devtool.L;
import vot.wq.devtool.androidR.MergePublicXml;
import vot.wq.devtool.androidR.MergeRes;
import vot.wq.devtool.androidR.ResetSmaliIds;
import vot.wq.devtool.img.GlobalUtil;

import java.io.IOException;
import java.util.Scanner;

public class AndroidRCli {
    public static void resetSmaliRids(){
        Scanner in = GlobalUtil.getScanner();
        L.tiol(null,"Smali Dir: ");
        String smaliDir = in.nextLine();
        L.tiol(null,"Dif ids: ");
        String difIdsFilePath = in.nextLine();

        ResetSmaliIds resetSmaliIds = new ResetSmaliIds(smaliDir, difIdsFilePath);
        resetSmaliIds.reset();
    }

    public static void mergePublicXml(){
        Scanner in = GlobalUtil.getScanner();
        L.tiol(null,"need merge xml path : ");
        String mergXmlPath = in.nextLine();
        L.tiol(null,"original xml path : ");
        String originalXmlPath = in.nextLine();

        MergePublicXml mergePublicXml = new MergePublicXml(mergXmlPath, originalXmlPath);
        mergePublicXml.merge();
    }

    public static void mergeAndroidRes(){
        Scanner in = GlobalUtil.getScanner();
        L.tiol(null,"need merge Dir: ");
        String needMerge = in.nextLine();
        L.tiol(null,"original Dir: ");
        String oriDir = in.nextLine();

        MergeRes mergeRes = new MergeRes(needMerge, oriDir);
        try {
            mergeRes.merge();
            mergeRes.resetRIds();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
