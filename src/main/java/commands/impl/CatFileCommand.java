package commands.impl;

import commands.Command;
import objects.BlobObject;
import utils.ZipUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CatFileCommand implements Command {
    @Override
    public void execCommand(String[] args) {
        String idtifierP = args[1];

        if(!idtifierP.equals("-p")){
            System.out.println("cat-file 命令格式不正确");
        }

        String blobSha = args[2];
        String firstDir = blobSha.substring(0, 2);
        String secondFile = blobSha.substring(2);

        final File root = new File(".git");
        File bloShaFile = new File(root, "objects/" + firstDir + "/" + secondFile);

        // 解压文件
        String blobFileContent = ZipUtil.unZipFile(bloShaFile);


        // 解析Blob文件格式
        BlobObject blobObjectFromBlobContent = BlobObject.getBlobObjectFromBlobContent(blobFileContent);

        System.out.print(blobObjectFromBlobContent.getContent());


    }




}
