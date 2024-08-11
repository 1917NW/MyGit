package commands.impl;

import commands.Command;
import objects.BlobObject;
import objects.TreeObject;
import objects.TreeObjectEntry;
import utils.FileUtil;
import utils.GitFileUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class WriteTreeCommand implements Command {
    FilenameFilter  ignoreDotGitFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            if(!dir.isDirectory() || ! name.equals(".git"))
                return true;
            return false;
        }
    };

    File root = new File(".git/objects");

    @Override
    public void execCommand(String[] args) {
        // 假定当前的工作目录为暂存区
        File stageAreaDir = new File("/");

        //
        File stageArea = new File(".git/objects");

        writeTree(stageArea);

    }


    public String writeTree(File file){
        if(file.isFile()){
            byte[] bytes = FileUtil.readFileToBytes(file);

            // 读取文件中的内容并映射为blob对象模式
            String blobContentFromContent = BlobObject.getBlobContentFromContent(new String(bytes));

            // 通过SHA算法计算40位的HASH值
            return GitFileUtil.writeGitObjectToFile(blobContentFromContent);
        } else {
            TreeObject treeObject = new TreeObject();
            List<TreeObjectEntry> treeObjectEntryList = new ArrayList<>();

            File[] childFiles = file.listFiles(ignoreDotGitFilter);

            for(File child: childFiles){
                // 求mode
                String mode = "";
                if(child.isDirectory())
                    mode = TreeObjectEntry.DIRECTORIES;
                else {
                    if(child.canExecute())
                        mode = TreeObjectEntry.EXECUTABLE_FILE;
                    else
                        mode = TreeObjectEntry.REGULAR_FILE;
                }

                treeObjectEntryList.add(TreeObjectEntry.builder()
                                .mode(mode)
                                .name(child.getName())
                                .shaBytes(writeTree(child).getBytes())
                                .build());
                treeObjectEntryList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                String treeObjectContentFromGitObjects = TreeObject.getTreeObjectContentFromGitObjects(treeObjectEntryList);
                return GitFileUtil.writeGitObjectToFile(treeObjectContentFromGitObjects);
            }

        }
        return "";
    }
}
