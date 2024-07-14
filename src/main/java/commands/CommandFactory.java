package commands;

import commands.impl.CatFileCommand;
import commands.impl.HashObjectCommand;
import commands.impl.InitCommand;
import commands.impl.LsTreeCommand;

import java.util.HashMap;
import java.util.Map;


public class CommandFactory implements Command{

    private static Map<CommandEnum, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(CommandEnum.INIT, new InitCommand());
        commandMap.put(CommandEnum.CAT_FILE, new CatFileCommand());
        commandMap.put(CommandEnum.HASH_OBJECT, new HashObjectCommand());
        commandMap.put(CommandEnum.LS_TREE, new LsTreeCommand());
    }

    private CommandEnum currentCommand;

    public CommandFactory setCurrentCommand(String commandStr){

        currentCommand = CommandEnum.getByName(commandStr);
        if(currentCommand == null)
            System.out.println("Unknown command: " + commandStr);

        return this;
    }


    @Override
    public void execCommand(String[] args) {
        if(currentCommand != null){
            commandMap.get(currentCommand).execCommand(args);
            currentCommand = null;
        }
    }
}
