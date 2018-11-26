package vot.wq.androidhacker.dex.module;

import vot.wq.androidhacker.dex.DexFormatExcption;
import vot.wq.androidhacker.util.DexUtil;

public class DexHeader extends BaseDexModule{
    public static final int kSHA1DigestLen = 20;

    byte[] magic = "dex\n035\u0000".getBytes();
    int checksum;
    byte[] signature = new byte[20];
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

    public DexHeader(byte[] buf, int off){
        super(buf,off);
        checkMagic();
        checksum = readU4();
        for (int i=0;i< signature.length;i++){
            signature[i]=buf[crtOff++];
        }
        fileSize = readU4();
        headerSize = readU4();
        endianTag = readU4();
        linkSize = readU4();
        linkOff = readU4();
        mapOff = readU4();
        stringIdsSize = readU4();
        stringIdsOff = readU4();
        typeIdsSize = readU4();
        typeIdsOff = readU4();
        protoIdsSize = readU4();
        protoIdsOff = readU4();
        fieldIdsSize = readU4();
        fieldIdsOff = readU4();
        methodIdsSize = readU4();
        methodIdsOff = readU4();
        classDefsSize = readU4();
        classDefsOff = readU4();
        dataSize = readU4();
        dataOff = readU4();
    }

    private void checkMagic(){
        for(byte b :magic){
            if(b != buf[crtOff++]){
                throw new DexFormatExcption("Wrong magic in DexHeader");
            }
        }
    }

    @Override
    public String toString(){
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append("magic : ").append("dex\\n035\\u0000").append('\n')
                .append("checkSum : " ).append(Integer.toHexString(checksum)).append('\n')
                .append("signature : " ).append(DexUtil.byte2String(signature)).append('\n')
                .append("fileSize : " ).append(fileSize).append('\n')
                .append("headerSize : " ).append(headerSize).append('\n')
                .append("endianTag : " ).append(endianTag).append('\n')
                .append("linkSize : " ).append(linkSize).append('\n')
                .append("linkOff : " ).append(linkOff).append('\n')
                .append("mapOff : " ).append(mapOff).append('\n')
                .append("stringIdsSize : " ).append(stringIdsSize).append('\n')
                .append("stringIdsOff : " ).append(stringIdsOff).append('\n')
                .append("typeIdsSize : " ).append(typeIdsSize).append('\n')
                .append("typeIdsOff : " ).append(typeIdsOff).append('\n')
                .append("protoIdsSize : " ).append(protoIdsSize).append('\n')
                .append("protoIdsOff : " ).append(protoIdsOff).append('\n')
                .append("fieldIdsSize : " ).append(fieldIdsSize).append('\n')
                .append("fieldIdsOff : " ).append(fieldIdsOff).append('\n')
                .append("methodIdsSize : " ).append(methodIdsSize).append('\n')
                .append("methodIdsOff : " ).append(methodIdsOff).append('\n')
                .append("classDefsSize : " ).append(classDefsSize).append('\n')
                .append("classDefsOff : " ).append(classDefsOff).append('\n')
                .append("dataSize : " ).append(dataSize).append('\n')
                .append("dataOff : " ).append(dataOff);

        return sbuffer.toString();
    }

    public static int getkSHA1DigestLen() {
        return kSHA1DigestLen;
    }

    public byte[] getMagic() {
        return magic;
    }

    public void setMagic(byte[] magic) {
        this.magic = magic;
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getHeaderSize() {
        return headerSize;
    }

    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    public int getEndianTag() {
        return endianTag;
    }

    public void setEndianTag(int endianTag) {
        this.endianTag = endianTag;
    }

    public int getLinkSize() {
        return linkSize;
    }

    public void setLinkSize(int linkSize) {
        this.linkSize = linkSize;
    }

    public int getLinkOff() {
        return linkOff;
    }

    public void setLinkOff(int linkOff) {
        this.linkOff = linkOff;
    }

    public int getMapOff() {
        return mapOff;
    }

    public void setMapOff(int mapOff) {
        this.mapOff = mapOff;
    }

    public int getStringIdsSize() {
        return stringIdsSize;
    }

    public void setStringIdsSize(int stringIdsSize) {
        this.stringIdsSize = stringIdsSize;
    }

    public int getStringIdsOff() {
        return stringIdsOff;
    }

    public void setStringIdsOff(int stringIdsOff) {
        this.stringIdsOff = stringIdsOff;
    }

    public int getTypeIdsSize() {
        return typeIdsSize;
    }

    public void setTypeIdsSize(int typeIdsSize) {
        this.typeIdsSize = typeIdsSize;
    }

    public int getTypeIdsOff() {
        return typeIdsOff;
    }

    public void setTypeIdsOff(int typeIdsOff) {
        this.typeIdsOff = typeIdsOff;
    }

    public int getProtoIdsSize() {
        return protoIdsSize;
    }

    public void setProtoIdsSize(int protoIdsSize) {
        this.protoIdsSize = protoIdsSize;
    }

    public int getProtoIdsOff() {
        return protoIdsOff;
    }

    public void setProtoIdsOff(int protoIdsOff) {
        this.protoIdsOff = protoIdsOff;
    }

    public int getFieldIdsSize() {
        return fieldIdsSize;
    }

    public void setFieldIdsSize(int fieldIdsSize) {
        this.fieldIdsSize = fieldIdsSize;
    }

    public int getFieldIdsOff() {
        return fieldIdsOff;
    }

    public void setFieldIdsOff(int fieldIdsOff) {
        this.fieldIdsOff = fieldIdsOff;
    }

    public int getMethodIdsSize() {
        return methodIdsSize;
    }

    public void setMethodIdsSize(int methodIdsSize) {
        this.methodIdsSize = methodIdsSize;
    }

    public int getMethodIdsOff() {
        return methodIdsOff;
    }

    public void setMethodIdsOff(int methodIdsOff) {
        this.methodIdsOff = methodIdsOff;
    }

    public int getClassDefsSize() {
        return classDefsSize;
    }

    public void setClassDefsSize(int classDefsSize) {
        this.classDefsSize = classDefsSize;
    }

    public int getClassDefsOff() {
        return classDefsOff;
    }

    public void setClassDefsOff(int classDefsOff) {
        this.classDefsOff = classDefsOff;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public int getDataOff() {
        return dataOff;
    }

    public void setDataOff(int dataOff) {
        this.dataOff = dataOff;
    }
}