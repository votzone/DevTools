package vot.wq.androidhacker.dex.module;

public class DexHeader {
    public static final int kSHA1DigestLen = 20;

    byte[] magic = new byte[8];
    int checksum;
    byte[] signature;
    int fileSize;
    int headerSize;
    int endianTag;
    int linkSize;
    int linkOff;
    int mapOff;

    int stringIdsSize;
    int stringIdsOff;
    int typeIdsSize;
    int typeIdsOff;
    int protoIdsSize;
    int protoIdsOff;
    int fieldIdsSize;
    int fieldIdsOff;
    int methodIdsSize;
    int methodIdsOff;
    int classDefsSize;
    int classDefsOff;

    int dataSize;
    int dataOff;

}