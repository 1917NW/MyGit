import commands.CommandFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
  public static void main(String[] args){


    // Uncomment this block to pass the first stage

    final String command = args[0];

    System.out.println("命令为:" + command);
    CommandFactory commandFactory = new CommandFactory();
    commandFactory.setCurrentCommand(command)
                  .execCommand(args);

  }
}
