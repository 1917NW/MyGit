package commands.impl;

import commands.Command;
import objects.CommitObject;
import utils.GitFileUtil;

import java.util.HashMap;
import java.util.Map;

public class CommitTreeCommand implements Command {

    private static Map<String, CommitTreeArgHandler> commitTreeArgHandlerMap = new HashMap<>();

    static {
        commitTreeArgHandlerMap.put("-m", (message,commitObject ) ->{
            commitObject.setCommitMessage(message);
        });

        commitTreeArgHandlerMap.put("-p", (parentShaStr, commitObject)->{
            commitObject.setParentShaStr(parentShaStr);
        });
    }

    @Override
    public void execCommand(String[] args) {
        if(args.length < 2){
            return;
        }

        CommitObject commitObject = new CommitObject();
        String treeSha = args[1];

        for(int i = 2; i < args.length; i += 2){
            if(i + 1 > args.length - 1){
                System.out.println("wrong arg, no arg content:" + args[i]);
                return;
            }

            CommitTreeArgHandler commitTreeArgHandler = commitTreeArgHandlerMap.get(args[i]);
            if(commitTreeArgHandler == null){
                System.out.println("wrong arg, no handler:" + args[i]);
                return;
            }

            commitTreeArgHandler.handleCommitTreeArg(args[i + 1], commitObject);

        }

        String commitObjectContentFromCommitObject = CommitObject.getCommitObjectContentFromCommitObject(commitObject);
        String commitSha = GitFileUtil.writeGitObjectToFile(commitObjectContentFromCommitObject);
        System.out.println(commitSha);

    }
}

interface CommitTreeArgHandler{
    public void handleCommitTreeArg(String arg, CommitObject commitObject);
}
