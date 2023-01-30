package org.encinet.nirvana.command;

import org.encinet.nirvana.command.commands.TestCommand;

import java.util.*;

public class CommandManager {
    private final Map<String[], Command> commandMap = new HashMap<>();


    public void load() {
        TestCommand testCommand = new TestCommand();
        commandMap.put(testCommand.getKey(), testCommand);
    }

    // 返回是否使用了命令前缀
    public boolean run(String msg) {
        if ('`' == msg.charAt(0)) {
            // 去除`
            String subString = msg.substring(0);
            // 用空格分割 获取参数
            String[] s = subString.split(" ");
            String key = s[0];
            Command command = getCommand(key);
            if (command != null) {
                // 分割的参数
                List<String> args = new ArrayList<>();
                // 把分割的参数添加到args里面
                Collections.addAll(args, s);
//                例： `nirvana 1 2 3 4
//                此时s里面会有[nirvana,1,2,3,4]
//                删除第一个就变成[1,2,3,4]
                args.remove(0);
                command.run(args.toArray(new String[0]));
            }
            return true;
        } else {
            return false;
        }
    }
    public Command getCommand(String key) {
        for (Map.Entry<String[], Command> commandEntry : commandMap.entrySet()) {
            for (String k : commandEntry.getKey()) {
                if (k.equals(key)) {
                    return commandEntry.getValue();
                }
            }
        }
        return null;
    }
}