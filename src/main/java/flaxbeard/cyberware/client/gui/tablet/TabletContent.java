package flaxbeard.cyberware.client.gui.tablet;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import flaxbeard.cyberware.api.item.CyberwareTag;
import flaxbeard.cyberware.client.gui.tablet.TabletMainMenu.MenuElement;
import flaxbeard.cyberware.common.CyberwareContent;
import flaxbeard.cyberware.common.integration.tan.ToughAsNailsIntegration;


public class TabletContent
{
	
	public static CyberwareTag MOBILITY = CyberwareTag.MOBILITY;
	public static CyberwareTag BUILDING = CyberwareTag.BUILDING;
	public static CyberwareTag COMBAT = CyberwareTag.COMBAT;
	public static CyberwareTag POWERGEN = CyberwareTag.POWERGEN;
	public static CyberwareTag POWERSTORAGE = CyberwareTag.POWERSTORAGE;
	public static CyberwareTag SURVIVABILITY = CyberwareTag.SURVIVABILITY;
	public static CyberwareTag MINING = CyberwareTag.MINING;
	public static CyberwareTag REPLACMENT = CyberwareTag.REPLACMENT;
	public static CyberwareTag INFO = CyberwareTag.INFO;
	public static CyberwareTag UTILITY = CyberwareTag.UTILITY;

	public static TabletMainMenu mainMenu;
	public static TabletCatalog catalog;
	
	public static TabletSurgeonInstructions instructions;

	public static void init()
	{
		mainMenu = new TabletMainMenu();
		
		catalog = new TabletCatalog();
		instructions = new TabletSurgeonInstructions();
		
		mainMenu.addItem(new MenuElement("cyberware.gui.tablet.test0", instructions, 0));
		mainMenu.addItem(new MenuElement("cyberware.gui.tablet.test1", catalog, 1));
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.eyeUpgrades), "cyberware.gui.tablet.catalog.hudlens", INFO).setDefaultVisible();

		new TabletCatalogItem(new ItemStack(CyberwareContent.cybereyes), "cyberware.corp.neovision", "cyberware.gui.tablet.catalog.cybereyes", REPLACMENT).setDefaultVisible();
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.cybereyeUpgrades, 1, 0), "cyberware.corp.panoptics", "cyberware.gui.tablet.catalog.nightVision", INFO);

		new TabletCatalogItem(new ItemStack(CyberwareContent.cybereyeUpgrades, 1, 1), "cyberware.corp.barracuda", "cyberware.gui.tablet.catalog.underwaterVision", INFO);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.cybereyeUpgrades, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", INFO);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.cybereyeUpgrades, 1, 3), "cyberware.corp.panoptics", "cyberware.gui.tablet.catalog.targeting", INFO);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.cybereyeUpgrades, 1, 4), "cyberware.corp.neovision", "cyberware.gui.tablet.catalog.zoom", INFO);

		new TabletCatalogItem(new ItemStack(CyberwareContent.brainUpgrades, 1, 0), "cyberware.corp.argent", "cyberware.gui.tablet.catalog.corticalStack", SURVIVABILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.brainUpgrades, 1, 1), "cyberware.corp.neovision", "cyberware.gui.tablet.catalog.enderJammer", COMBAT);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.brainUpgrades, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.brainUpgrades, 1, 3), "cyberware.gui.tablet.catalog.cardioCoupler", UTILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.brainUpgrades, 1, 4), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.brainUpgrades, 1, 5), "cyberware.gui.tablet.catalog.cardioCoupler", UTILITY);

		new TabletCatalogItem(new ItemStack(CyberwareContent.cyberheart), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY);

		new TabletCatalogItem(new ItemStack(CyberwareContent.heartUpgrades, 1, 3), "cyberware.corp.touch", "cyberware.gui.tablet.catalog.cardioCoupler", POWERGEN).setDefaultVisible();
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.heartUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY).setPowerUseConstant(false);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.heartUpgrades, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.heartUpgrades, 1, 2), "cyberware.gui.tablet.catalog.stemcell", SURVIVABILITY);

		new TabletCatalogItem(new ItemStack(CyberwareContent.lungsUpgrades, 1, 0), "cyberware.corp.barracuda", "cyberware.gui.tablet.catalog.oxygen", UTILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.lungsUpgrades, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", MOBILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.lowerOrgansUpgrades, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", POWERSTORAGE).setDefaultVisible();
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.lowerOrgansUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", UTILITY, SURVIVABILITY);

		new TabletCatalogItem(new ItemStack(CyberwareContent.lowerOrgansUpgrades, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", POWERGEN);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.lowerOrgansUpgrades, 1, 3), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY).setPowerUseConstant(false);

		new TabletCatalogItem(new ItemStack(CyberwareContent.denseBattery), "cyberware.gui.tablet.catalog.cardioCoupler", POWERSTORAGE);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.skinUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", POWERGEN);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.skinUpgrades, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", COMBAT);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.skinUpgrades, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", UTILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.skinUpgrades, 1, 3), "cyberware.corp.touch", "cyberware.gui.tablet.catalog.immuno", UTILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.muscleUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", COMBAT);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.muscleUpgrades, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", COMBAT);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.boneUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.boneUpgrades, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", SURVIVABILITY);

		new TabletCatalogItem(new ItemStack(CyberwareContent.boneUpgrades, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", POWERSTORAGE);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.cyberlimbs, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", REPLACMENT);
		new TabletCatalogItem(new ItemStack(CyberwareContent.cyberlimbs, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", REPLACMENT);
		new TabletCatalogItem(new ItemStack(CyberwareContent.cyberlimbs, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", REPLACMENT);
		new TabletCatalogItem(new ItemStack(CyberwareContent.cyberlimbs, 1, 3), "cyberware.gui.tablet.catalog.cardioCoupler", REPLACMENT);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.armUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", COMBAT);
		
		new TabletCatalogItem(new ItemStack(CyberwareContent.handUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", UTILITY);

		new TabletCatalogItem(new ItemStack(CyberwareContent.handUpgrades, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler", COMBAT);

		new TabletCatalogItem(new ItemStack(CyberwareContent.handUpgrades, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", MINING);

		new TabletCatalogItem(new ItemStack(CyberwareContent.legUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", MOBILITY);
		new TabletCatalogItem(new ItemStack(CyberwareContent.legUpgrades, 1, 1), "cyberware.corp.olympiads",  "cyberware.gui.tablet.catalog.fallDamage", SURVIVABILITY);

		new TabletCatalogItem(new ItemStack(CyberwareContent.footUpgrades, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler", MOBILITY);
		new TabletCatalogItem(new ItemStack(CyberwareContent.footUpgrades, 1, 1), "cyberware.corp.olympiads", "cyberware.gui.tablet.catalog.aqua", MOBILITY);
		new TabletCatalogItem(new ItemStack(CyberwareContent.footUpgrades, 1, 2), "cyberware.gui.tablet.catalog.cardioCoupler", MOBILITY);
		
		if (Loader.isModLoaded("ToughAsNails"))
		{
			new TabletCatalogItem(new ItemStack(ToughAsNailsIntegration.sweat, 1, 0), "cyberware.gui.tablet.catalog.cardioCoupler");
			new TabletCatalogItem(new ItemStack(ToughAsNailsIntegration.sweat, 1, 1), "cyberware.gui.tablet.catalog.cardioCoupler");
		}
	}
}