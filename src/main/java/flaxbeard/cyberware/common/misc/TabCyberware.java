package flaxbeard.cyberware.common.misc;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import flaxbeard.cyberware.api.CyberwareAPI;
import flaxbeard.cyberware.api.item.ICyberware.Quality;
import flaxbeard.cyberware.api.item.ICyberware;
import flaxbeard.cyberware.api.item.ICyberwareTabItem;
import flaxbeard.cyberware.api.item.ICyberwareTabItem.EnumCategory;
import flaxbeard.cyberware.common.CyberwareContent;
import flaxbeard.cyberware.common.handler.CreativeMenuHandler;

public class TabCyberware extends CreativeTabs
{

	public TabCyberware(String label)
	{
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return ItemStack.EMPTY;
	}
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(CyberwareContent.cybereyes);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllRelevantItems(NonNullList<ItemStack> list)
	{
		Map<EnumCategory, List<ItemStack>> subLists = new EnumMap<EnumCategory, List<ItemStack>>(EnumCategory.class);
		for (EnumCategory category : EnumCategory.values())
		{
			subLists.put(category, new ArrayList<ItemStack>());
		}
		NonNullList<ItemStack> unsorted = NonNullList.create();
		
		Quality q = CreativeMenuHandler.pageSelected == 0 ? CyberwareAPI.QUALITY_SCAVENGED : CyberwareAPI.QUALITY_MANUFACTURED;
		
		for (Item item : Item.REGISTRY)
		{
			if (item == null)
			{
				continue;
			}
			for (CreativeTabs tab : item.getCreativeTabs())
			{
				if (tab == this)
				{
					if (item instanceof ICyberwareTabItem)
					{
						NonNullList<ItemStack> tempList = NonNullList.create();	
						//item.getSubItems(item, this, tempList);
						item.getSubItems(this, tempList);

						for (ItemStack stack : tempList)
						{
							if (!stack.isEmpty())
							{
								if (CyberwareAPI.isCyberware(stack))
								{
									ICyberware ware = CyberwareAPI.getCyberware(stack);
									if (ware.canHoldQuality(stack, q))
									{
										stack = ware.setQuality(stack, q);
									}
								}
								EnumCategory cat = ((ICyberwareTabItem) stack.getItem()).getCategory(stack);
								subLists.get(cat).add(stack);
							}
						}
					}
					else
					{
						//item.getSubItems(item, this, unsorted);
						item.getSubItems(this, unsorted);
					}
				}
			}
		}
		
		for (EnumCategory category : EnumCategory.values())
		{
			List<ItemStack> toAdd = subLists.get(category);
			int blank = 9 - (toAdd.size() % 9);

			list.addAll(toAdd);

			
		}
		
		list.addAll(unsorted);

		if (this.getRelevantEnchantmentTypes() != null)
		{
			//this.addEnchantmentBooksToList(list, this.getRelevantEnchantmentTypes());
			//this.ad(list, this.getRelevantEnchantmentTypes());
		}
	}
}
