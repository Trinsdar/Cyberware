package flaxbeard.cyberware.common.block.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import flaxbeard.cyberware.api.item.ICyberwareTabItem;
import flaxbeard.cyberware.common.block.BlockSurgeryChamber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSurgeryChamber extends Item implements ICyberwareTabItem
{
	private Block block;
	private String[] tt;

	public ItemSurgeryChamber(Block block, String... tooltip)
	{
		this.block = block;
		this.tt = tooltip;
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = playerIn.getHeldItem(hand);
		if (facing != EnumFacing.UP)
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(worldIn, pos))
			{
				pos = pos.offset(facing);
			}

			if (playerIn.canPlayerEdit(pos, facing, stack) && this.block.canPlaceBlockAt(worldIn, pos))
			{
				EnumFacing enumfacing = EnumFacing.fromAngle((double)playerIn.rotationYaw);
				int i = enumfacing.getFrontOffsetX();
				int j = enumfacing.getFrontOffsetZ();
				placeDoor(worldIn, pos, enumfacing, this.block);
				SoundType soundtype = this.block.getSoundType();
				worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				stack.shrink(1);
				return EnumActionResult.SUCCESS;
			}
			else
			{
				return EnumActionResult.FAIL;
			}
		}
	}

	public static void placeDoor(World worldIn, BlockPos pos, EnumFacing facing, Block door)
	{
		BlockPos blockpos = pos.offset(facing.rotateY());
		BlockPos blockpos1 = pos.offset(facing.rotateYCCW());
		int i = (worldIn.getBlockState(blockpos1).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos1.up()).isNormalCube() ? 1 : 0);
		int j = (worldIn.getBlockState(blockpos).isNormalCube() ? 1 : 0) + (worldIn.getBlockState(blockpos.up()).isNormalCube() ? 1 : 0);
		BlockPos blockpos2 = pos.up();
		
		IBlockState iblockstate = door.getDefaultState().withProperty(BlockSurgeryChamber.FACING, facing).withProperty(BlockSurgeryChamber.OPEN, true);
		worldIn.setBlockState(pos, iblockstate.withProperty(BlockSurgeryChamber.HALF, BlockSurgeryChamber.EnumChamberHalf.LOWER), 2);
		worldIn.setBlockState(blockpos2, iblockstate.withProperty(BlockSurgeryChamber.HALF, BlockSurgeryChamber.EnumChamberHalf.UPPER), 2);
		worldIn.notifyNeighborsOfStateChange(pos, door, true);
		worldIn.notifyNeighborsOfStateChange(blockpos2, door, true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced)
	{
		if (this.tt != null)
		{
			for (String str : tt)
			{
				tooltip.add(ChatFormatting.GRAY + I18n.format(str));
			}
		}
	}

	@Override
	public EnumCategory getCategory(ItemStack stack)
	{
		return EnumCategory.BLOCKS;
	}
}