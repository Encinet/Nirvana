package org.encinet.nirvana;

import net.minecraftforge.fml.client.SplashProgress;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.encinet.nirvana.event.EventManager;
import org.encinet.nirvana.until.KeyManager;
import org.lwjgl.opengl.Display;

import java.util.concurrent.ThreadLocalRandom;

@SideOnly(Side.CLIENT)
@Mod(modid = Nirvana.MODID, name = Nirvana.NAME, version = Nirvana.VERSION, acceptedMinecraftVersions = "1.8.9")
public class Nirvana
{
    public static final String MODID = "nirvana";
    public static final String NAME = "Nirvana";
    public static final String VERSION = "1.0.0-alpha";
    public static KeyManager keyManager;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // title
        String[] titles = {"本客户端处于初期开发阶段，"};
        Display.setTitle(NAME + " " + VERSION + " | " + titles[ThreadLocalRandom.current().nextInt(titles.length)]);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // event
        EventManager.init();
		// key bind
        keyManager = new KeyManager();
    }
}
