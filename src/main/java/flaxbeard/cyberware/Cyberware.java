package flaxbeard.cyberware;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import flaxbeard.cyberware.common.CommonProxy;
import flaxbeard.cyberware.common.CyberwareConfig;
import flaxbeard.cyberware.common.misc.CommandClearCyberware;
import flaxbeard.cyberware.common.misc.TabCyberware;

@Mod(name = Cyberware.MODNAME, modid = Cyberware.MODID, version = Cyberware.VERSION, certificateFingerprint = "@MOD_SIGNATURE@")
public class Cyberware
{
	public static final String MODNAME = "Cyberware";
	public static final String MODID = "cyberware";
	public static final String VERSION = "@MOD_VERSION@";
	
	@Instance(MODID)
	public static Cyberware INSTANCE;
		
	@SidedProxy(clientSide = "flaxbeard.cyberware.client.ClientProxy", serverSide = "flaxbeard.cyberware.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		CyberwareConfig.preInit(event);
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}
	
	public static CreativeTabs creativeTab = new TabCyberware(MODID);

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandClearCyberware());
	}

	@Mod.EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event)
	{
		// TODO: add a proper logger?
		System.out.println("[Cyberware] Invalid fingerprint detected");
	}
}
