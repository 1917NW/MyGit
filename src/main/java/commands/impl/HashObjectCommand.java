package commands.impl;

import commands.Command;
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

        // 通过SHA算法计算40位的HASH值
        String shaHash = SHAUtil.encodeTo40Bits(bytes);

        // 判断是否需要保存
        if(needToWrite){
            try {
                final File root = new File(".git");
                String hashDir = shaHash.substring(0, 2);
                String hashFile = shaHash.substring(2);
                File blobFile = new File(root, "objects/" + hashDir + "/" + hashFile);
                blobFile.createNewFile();

                // 写入zip压缩后的字节流
                ZipUtil.zipStrContent(blobFile, bytes);
            }catch (Exception e){
                System.out.println("创建Blob对象失败！");
            }


        }




    }
}
