package objects;

import constants.StrConstant;
import lombok.Data;

import java.util.List;

@Data
public class TreeObject implements GitObject{

    int header;

    List<TreeObjectEntry> gitObjects;


    public final static String TREE_FILE_PREFIX = "tree";

    public static TreeObject getTreeObjectFromTreeContent(String treeContent){

        if(!treeContent.startsWith(TREE_FILE_PREFIX))
            return null;

        TreeObject treeObject = new TreeObject();
        String content = treeContent.substring(4).trim();

        int nullIndex = content.indexOf(StrConstant.OBJECT_NULL);
        treeObject.setHeader(Integer.parseInt(content.substring(0, nullIndex)));
        String children = content.substring(nullIndex + 1);

        while ((nullIndex = children.indexOf(StrConstant.OBJECT_NULL))!= -1){
            String head = children.substring(0, nullIndex);
            String[] split = head.split(StrConstant.SPACE);
            String mode = split[0];
            String name= split[1];

            String tail = children.substring(nullIndex + 1, nullIndex + 21);

            TreeObjectEntry treeObjectEntry = TreeObjectEntry.builder()
                    .mode(mode)
                    .name(name)
                    .shaBytes(tail.getBytes())
                    .build();

            treeObject.getGitObjects().add(treeObjectEntry);
            children = children.substring(nullIndex + 21);
        }

        return treeObject;
    }

    public static String getTreeObjectContentFromGitObjects(List<TreeObjectEntry> treeObjectEntryList){
        StringBuilder treeContent = new StringBuilder();

        for(TreeObjectEntry treeObjectEntry : treeObjectEntryList){
            treeContent.append(treeObjectEntry.getMode() + StrConstant.SPACE + treeObjectEntry.getName() + StrConstant.OBJECT_NULL + treeObjectEntry.getShaBytes().toString());
        }
        StringBuilder res = new StringBuilder();
        res.append(TREE_FILE_PREFIX)
                .append(StrConstant.SPACE)
                .append(treeContent.toString().length())
                .append(StrConstant.OBJECT_NULL)
                .append(treeContent);
        return res.toString();
    }



    public void show(){
        for(TreeObjectEntry treeObjectEntry : gitObjects){
            System.out.println(treeObjectEntry.getMode() + StrConstant.SPACE + treeObjectEntry.getName() + StrConstant.SPACE + treeObjectEntry.getShaBytes().toString());
        }
    }


}


