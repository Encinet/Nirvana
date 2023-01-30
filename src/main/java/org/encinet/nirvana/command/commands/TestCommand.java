package org.encinet.nirvana.command.commands;

import org.encinet.nirvana.command.Command;

public class TestCommand extends Command {
    public TestCommand() {
        // 可以匹配多个
        super(new String[] {"nirvana"});
    }

    @Override
    public void run(String[] args) {

    }
}
