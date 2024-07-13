package objects;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Blob;

@Data
public class BlobObject {
    int header;
    String content;

    public final static String BLOB_FILE_PREFIX = "blob";

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






}
