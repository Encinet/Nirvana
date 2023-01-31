package org.encinet.nirvana.command;

public abstract class Command {
    // 命令标识符
    // 例子: /tp @s 1 2 3
    //       |      |
    //    key(No/) args
    private final String[] key;
    public Command(String[] key) {
        this.key = key;
    }
    public abstract void run(String[] args);

    public String[] getKey() {
        return key;
    }
}