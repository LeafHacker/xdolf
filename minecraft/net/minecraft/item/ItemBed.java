package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item
{
    public ItemBed()
    {
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (facing != EnumFacing.UP)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            boolean flag = block.isReplaceable(worldIn, pos);

            if (!flag)
            {
                pos = pos.up();
            }

            int i = MathHelper.floor((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            EnumFacing enumfacing = EnumFacing.getHorizontal(i);
            BlockPos blockpos = pos.offset(enumfacing);
            ItemStack itemstack = player.getHeldItem(hand);

            if (player.canPlayerEdit(pos, facing, itemstack) && player.canPlayerEdit(blockpos, facing, itemstack))
            {
                IBlockState iblockstate1 = worldIn.getBlockState(blockpos);
                boolean flag1 = iblockstate1.getBlock().isReplaceable(worldIn, blockpos);
                boolean flag2 = flag || worldIn.isAirBlock(pos);
                boolean flag3 = flag1 || worldIn.isAirBlock(blockpos);

                if (flag2 && flag3 && worldIn.getBlockState(pos.down()).isFullyOpaque() && worldIn.getBlockState(blockpos.down()).isFullyOpaque())
                {
                    IBlockState iblockstate2 = Blocks.BED.getDefaultState().withProperty(BlockBed.OCCUPIED, Boolean.valueOf(false)).withProperty(BlockBed.FACING, enumfacing).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);
                    worldIn.setBlockState(pos, iblockstate2, 10);
                    worldIn.setBlockState(blockpos, iblockstate2.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 10);
                    worldIn.notifyNeighborsRespectDebug(pos, block, false);
                    worldIn.notifyNeighborsRespectDebug(blockpos, iblockstate1.getBlock(), false);
                    SoundType soundtype = iblockstate2.getBlock().getSoundType();
                    worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    itemstack.shrink(1);
                    return EnumActionResult.SUCCESS;
                }
                else
                {
                    return EnumActionResult.FAIL;
                }
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
    }
}
