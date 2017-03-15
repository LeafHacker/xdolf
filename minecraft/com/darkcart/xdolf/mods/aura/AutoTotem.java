package com.darkcart.xdolf.mods.aura;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AutoTotem extends Module {

	public AutoTotem() {
		super("AutoTotem", "Automagically places totems in offhand", Category.AURA);
	}

	public void onUpdate(EntityPlayerSP player) {
		if (player.getHeldItemOffhand() == ItemStack.EMPTY) {
			for (ItemStack i : player.inventory.mainInventory) {
				if (i.getItem() == Items.TOTEM) {
					int slotNumber = player.inventory.getSlotFor(i);
					//Wrapper.getMinecraft().playerController.windowClick(windowId, slotId, mouseButton, type, player);
				}
			}
		}
	}
}
