package org.encinet.nirvana.command.commands;

import org.encinet.nirvana.command.Command;

public class TestCommand extends Command {
    public TestCommand() {
        // 可以匹配多个
        super(new String[] {"nirvana"});
    }

    @Override
    public void run(String[] args) {
        if (args.length == 0) {
            System.out.println("执行了`nirvana");
        } else if (args.length == 1) {
            if (args[0].equals("test")) { // 这里我不太清楚是不是args[0]
                System.out.println("输入了test子指令");
            }

        }
    }
}
