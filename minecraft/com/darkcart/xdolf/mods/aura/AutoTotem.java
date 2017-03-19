package com.darkcart.xdolf.mods.aura;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class AutoTotem extends Module {

	public AutoTotem() {
		super("AutoTotem", "Automatically replaces totems", Keyboard.KEYBOARD_SIZE, 0xffffff, Category.AURA);
	}

	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
            NonNullList<ItemStack> inv;
            NonNullList<ItemStack> offhand = player.inventory.offHandInventory;

            int inventoryIndex;

            	inv = player.inventory.mainInventory;

            for(inventoryIndex = 0; inventoryIndex < inv.size(); inventoryIndex++) {
               	if (inv.get(inventoryIndex) != ItemStack.EMPTY) {
                	if (inv.get(inventoryIndex).getItem().getUnlocalizedName().equals("item.totem")) {
                      	replaceTotem(inventoryIndex);
                        break;
                	}
                }
            }
		}
	}
	
    public void replaceTotem(int inventoryIndex) {
        if (Wrapper.getPlayer().openContainer instanceof ContainerPlayer) {
            Wrapper.getMinecraft().playerController.windowClick(0, inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex, 0, ClickType.PICKUP, Wrapper.getPlayer());
            Wrapper.getMinecraft().playerController.windowClick(0, 45, 0, ClickType.PICKUP, Wrapper.getPlayer());
            Wrapper.getMinecraft().playerController.windowClick(0, inventoryIndex < 9 ? inventoryIndex + 36 : inventoryIndex, 0, ClickType.PICKUP, Wrapper.getPlayer());
        }
    }

}
