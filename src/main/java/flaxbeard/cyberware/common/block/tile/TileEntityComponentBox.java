package flaxbeard.cyberware.common.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;
import flaxbeard.cyberware.api.item.IBlueprint;
import flaxbeard.cyberware.common.CyberwareContent;

public class TileEntityComponentBox extends TileEntity
{
	public static class ItemStackHandlerComponent extends ItemStackHandler
	{		
		public ItemStackHandlerComponent(int i)
		{
			super(i);
		}

		@Override
		public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
		{
			if (!isItemValidForSlot(slot, stack)) return stack;
			
			return super.insertItem(slot, stack, simulate);
		}
		
		public boolean isItemValidForSlot(int slot, ItemStack stack)
		{
			if (!stack.isEmpty() && stack.getItem() == CyberwareContent.component) return true;
			
			return stack.isEmpty();
		}

	}
	
	public ItemStackHandler slots = new ItemStackHandlerComponent(18);
	public String customName = null;
	public boolean doDrop = true;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return (T) slots;
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		slots.deserializeNBT(compound.getCompoundTag("inv"));
		
		if (compound.hasKey("CustomName"))
		{
			customName = compound.getString("CustomName");
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound = super.writeToNBT(compound);
		
		compound.setTag("inv", this.slots.serializeNBT());
		
		if (this.hasCustomName())
		{
			compound.setString("CustomName", customName);
		}
				
		return compound;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		NBTTagCompound data = pkt.getNbtCompound();
		this.readFromNBT(data);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound data = new NBTTagCompound();
		this.writeToNBT(data);
		return new SPacketUpdateTileEntity(pos, 0, data);
	}
	
	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}
	
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public String getName()
	{
		return this.hasCustomName() ? customName : "cyberware.container.component_box";
	}

	public boolean hasCustomName()
	{
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomInventoryName(String p_145951_1_)
	{
		this.customName = p_145951_1_;
	}
	
	public ITextComponent getDisplayName()
	{
		return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return (oldState.getBlock() != newState.getBlock());
	}
}
