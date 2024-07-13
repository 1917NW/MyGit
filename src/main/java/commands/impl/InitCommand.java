package commands.impl;

import commands.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class InitCommand implements Command {
    @Override
    public void execCommand(String[] args) {
        final File root = new File(".git");

        // 生成objects目录
        new File(root, "objects").mkdirs();

        // 生成refs目录
        new File(root, "refs").mkdirs();

        // 生成HEAD文件并写入HEAD文件当前内容
        final File head = new File(root, "HEAD");
        try {
            head.createNewFile();
            Files.write(head.toPath(), "ref: refs/heads/main\n".getBytes());
            System.out.println("Initialized git directory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
