import commands.CommandFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage

    final String command = args[0];


    CommandFactory commandFactory = new CommandFactory();
    commandFactory.setCurrentCommand(command)
                  .execCommand(args);

  }
}
