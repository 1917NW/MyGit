package commands;

import lombok.Getter;

import java.util.Arrays;


public enum CommandEnum {

    INIT("init"),
    CAT_FILE("cat-file"),
    HASH_OBJECT("hash-object"),
    LS_TREE("ls-tree"),
    WRITE_TREE("write-tree"),

    COMMIT_TREE("commit-tree");
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
