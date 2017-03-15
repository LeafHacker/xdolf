package com.darkcart.xdolf.mods.aura;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AutoTotem extends Module {

	public AutoTotem() {
		super("AutoTotem", "Automagically places totems in offhand", Category.AURA);
	}

	public void runTick() {
		if (Wrapper.getPlayer().getHeldItemOffhand() == ItemStack.EMPTY) {
			for (ItemStack i : Wrapper.getPlayer().inventory.mainInventory) {
				if (i.getItem() == Items.TOTEM) {
					int slotNumber = Wrapper.getPlayer().inventory.getSlotFor(i);
					//Wrapper.getMinecraft().playerController.windowClick(windowId, slotId, mouseButton, type, player);
				}
			}
		}
	}
}
