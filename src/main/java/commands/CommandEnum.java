package commands;

import lombok.Getter;

import java.util.Arrays;


public enum CommandEnum {

    INIT("init"),
    CAT_FILE("cat-file")
    ;
    private String commandName;

    CommandEnum(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName(){
        return this.commandName;
    }

    static CommandEnum getByName(String name){
        CommandEnum[] values = CommandEnum.values();
        for(CommandEnum value: values){
            if(value.getCommandName().equals(name))
                return value;
        }
        return null;

    }
}
