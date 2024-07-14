package objects;

import lombok.Data;


@Data
public class BlobObject {
    int header;
    String content;

    public final static String BLOB_FILE_PREFIX = "blob";

    public final static String BLOB_SPACE = " ";

    public final static String BLOB_NULL= "\0";
    /**
     * 将Blob内容转换为Blob对象
     * @param blobContent
     * @return
     */
    public static BlobObject getBlobObjectFromBlobContent(String blobContent){
        // 校验是否为Blob内容，即前缀是否以blob开头
        if(!blobContent.startsWith(BLOB_FILE_PREFIX))
            return null;

        // 解析Blob文件 <size>'/0'<content>
        String restContent = blobContent.substring(4).trim();
        int nullIndex = restContent.indexOf('\0');
        int size = Integer.parseInt(restContent.substring(0, nullIndex));
        String content = restContent.substring(nullIndex + 1);

        BlobObject blobObject = new BlobObject();
        blobObject.setHeader(size);
        blobObject.setContent(content);

        return blobObject;
    }

    /**
     * 将Blob对象转换为Blob文本
     * @param blobObject
     * @return
     */
    public static String getBlobContentFromBlobObject(BlobObject blobObject){
        return BLOB_FILE_PREFIX + BLOB_SPACE + blobObject.getHeader() + BLOB_NULL + blobObject.getContent();
    }

    /**
     * 将内容转换为对应的Blob文本
     * @param content
     * @return
     */
    public static String getBlobContentFromContent(String content){
        return BLOB_FILE_PREFIX + BLOB_SPACE + content.length() + BLOB_NULL + content;
    }

}
