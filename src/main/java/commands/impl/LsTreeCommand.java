package commands.impl;

import commands.Command;
import objects.TreeObject;
import utils.ZipUtil;

import java.io.File;

public class LsTreeCommand implements Command {
    @Override
    public void execCommand(String[] args) {
        String hashPath = args[1];

        String hashDirStr = hashPath.substring(0, 2);
        String hashFileStr = hashPath.substring(2);

        final File root = new File(".git/objects");

        File treeFile = new File(root, hashDirStr + "/" + hashFileStr);

        // 解压文件
        String treeFileContent = ZipUtil.unZipFile(treeFile);

        // 解析TreeObject
        TreeObject treeObjectFromTreeContent = TreeObject.getTreeObjectFromTreeContent(treeFileContent);

        // 展示TreeObject
        treeObjectFromTreeContent.show();

    }
}
