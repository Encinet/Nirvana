package org.encinet.nirvana.event;

import net.minecraftforge.common.MinecraftForge;

public class EventManager {
    public static void init() {
        // register event listeners
        MinecraftForge.EVENT_BUS.register(new Basic());
    }
}
