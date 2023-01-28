package org.encinet.nirvana.mods;

import org.encinet.nirvana.until.Mod;

public class TestMod extends Mod {
    public TestMod() {
        super("name", "description", false);
    }

    @Override
    public void tick() {
        System.out.println(666);
    }
}
