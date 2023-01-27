package org.encinet.nirvana;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Nirvana.MODID, name = Nirvana.NAME, version = Nirvana.VERSION, acceptedMinecraftVersions = "1.8.9")
public class Nirvana
{
    public static final String MODID = "nirvana";
    public static final String NAME = "Nirvana";
    public static final String VERSION = "1.0.0-alpha";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // TODO
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
