package commands;

import commands.impl.CatFileCommand;
import commands.impl.InitCommand;

import java.util.HashMap;
import java.util.Map;


public class CommandFactory implements Command{

    private static Map<CommandEnum, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(CommandEnum.INIT, new InitCommand());
        commandMap.put(CommandEnum.CAT_FILE, new CatFileCommand());
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
            System.out.println("命令" + currentCommand + "执行成功！");
            currentCommand = null;
        }
    }
}
