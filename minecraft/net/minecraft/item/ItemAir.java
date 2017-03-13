package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;

public class ItemAir extends Item
{
    private final Block block;

    public ItemAir(Block blockIn)
    {
        this.block = blockIn;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.block.getUnlocalizedName();
    }

    /**
     * Returns the unlocalized name of this item.
     */
    public String getUnlocalizedName()
    {
        return this.block.getUnlocalizedName();
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        super.addInformation(stack, playerIn, tooltip, advanced);
        this.block.addInformation(stack, playerIn, tooltip, advanced);
    }
}
