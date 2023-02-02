package org.encinet.nirvana.mod;

public class Mod {
    private final String name;
    private final String description;
    private final boolean enable;
    public Mod(String name, String description, boolean enable) {
        this.name = name;
        this.description = description;
        this.enable = enable;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isEnable() {
        return enable;
    }
    public void update() {

    }
}
