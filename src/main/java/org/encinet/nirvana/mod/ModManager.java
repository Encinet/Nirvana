package org.encinet.nirvana.mod;

import org.encinet.nirvana.mod.mods.movement.SprintMod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    private final List<Mod> mods = new ArrayList<>();
    public List<Mod> getMods() {
        return mods;
    }
    public List<Mod> getEnableMods() {
        return mods.stream().filter(Mod::isEnable).collect(Collectors.toList());
    }
    public void load() {
        mods.add(new SprintMod());
    }
}
