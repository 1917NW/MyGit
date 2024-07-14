package objects;

import java.util.List;

public class TreeObject implements GitObject{

    int header;

    List<GitObject> gitObjects;


    public final static String TREE_FILE_PREFIX = "tree";

    public TreeObject getTreeObjectFromTreeContent(String treeContent){
        TreeObject treeObject = new TreeObject();
        return treeObject;
    }


}
