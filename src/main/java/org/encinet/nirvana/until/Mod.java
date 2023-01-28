package org.encinet.nirvana.until;

public abstract class Mod {
    final String NAME;
    final String DESCRIPTION;
    boolean enabled;

    public Mod(String name, String description, boolean enabled) {
        NAME = name;
        DESCRIPTION = description;
        this.enabled = enabled;
    }

    public String getName() {
        return NAME;
    }
    public String getDescription() {
        return DESCRIPTION;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public abstract void tick();
}
