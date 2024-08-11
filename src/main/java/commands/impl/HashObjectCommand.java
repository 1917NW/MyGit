package commands.impl;

import commands.Command;
import objects.BlobObject;
import utils.FileUtil;
import utils.SHAUtil;
import utils.ZipUtil;

import java.io.File;
import java.nio.file.Files;

public class HashObjectCommand implements Command {


    @Override
    public void execCommand(String[] args) {

        if(args.length < 2)
            return;

        // 解析并保存参数
        boolean needToWrite = false;
        String filename;

        String idtifierW = args[1];
        if(idtifierW.equals("-w")){
            filename = args[2];
            needToWrite = true;
        }else
            filename = idtifierW;

        // 读取的文件内容
        File file = new File(filename);
        byte[] bytes = FileUtil.readFileToBytes(file);

        // 读取文件中的内容并映射为blob对象模式
        String blobContentFromContent = BlobObject.getBlobContentFromContent(new String(bytes));

        // 通过SHA算法计算40位的HASH值
        String shaHash = SHAUtil.encodeTo40Characters(blobContentFromContent.getBytes());

        // 判断是否需要保存
        if(needToWrite){
            try {
                final File root = new File(".git/objects");
                String hashDir = shaHash.substring(0, 2);
                String hashFile = shaHash.substring(2);

                // 创建Hash目录
                File hashDirFile = new File(root,  hashDir);
                hashDirFile.mkdirs();

                // 创建文件
                File blobFile = new File(hashDirFile, hashFile);
                System.out.println(blobFile.getAbsolutePath());
                blobFile.createNewFile();

                // 写入zip压缩后的字节流
                ZipUtil.zipStrContent(blobFile, blobContentFromContent);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("创建Blob对象失败！");
            }


        }




    }
}
