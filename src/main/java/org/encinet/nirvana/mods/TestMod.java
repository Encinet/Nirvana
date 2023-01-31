package org.encinet.nirvana.mods;

import org.encinet.nirvana.until.Mod;

public class TestMod extends Mod {
    public TestMod() {
        super("TestMod", "a test mod", false);
    }

    @Override
    public void tick() {
        System.out.println("测试Mod加载成功");
    }
}
