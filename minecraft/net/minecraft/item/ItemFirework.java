package net.minecraft.item;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemFirework extends Item
{
    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            ItemStack itemstack = player.getHeldItem(hand);
            EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(worldIn, (double)((float)pos.getX() + hitX), (double)((float)pos.getY() + hitY), (double)((float)pos.getZ() + hitZ), itemstack);
            worldIn.spawnEntity(entityfireworkrocket);

            if (!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }
        }

        return EnumActionResult.SUCCESS;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        NBTTagCompound nbttagcompound = stack.getSubCompound("Fireworks");

        if (nbttagcompound != null)
        {
            if (nbttagcompound.hasKey("Flight", 99))
            {
                tooltip.add(I18n.translateToLocal("item.fireworks.flight") + " " + nbttagcompound.getByte("Flight"));
            }

            NBTTagList nbttaglist = nbttagcompound.getTagList("Explosions", 10);

            if (!nbttaglist.hasNoTags())
            {
                for (int i = 0; i < nbttaglist.tagCount(); ++i)
                {
                    NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                    List<String> list = Lists.<String>newArrayList();
                    ItemFireworkCharge.addExplosionInfo(nbttagcompound1, list);

                    if (!list.isEmpty())
                    {
                        for (int j = 1; j < ((List)list).size(); ++j)
                        {
                            list.set(j, "  " + (String)list.get(j));
                        }

                        tooltip.addAll(list);
                    }
                }
            }
        }
    }
}
