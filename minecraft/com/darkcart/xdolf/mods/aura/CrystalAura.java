package com.darkcart.xdolf.mods.aura;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class CrystalAura extends Module {

	public CrystalAura() {
		super("CrystalAura", "Automatically end crystal bombs people.", Keyboard.KEYBOARD_SIZE, Category.AURA);
	}
	
	@Override
	public void onDisable() {
		Wrapper.getMinecraft().gameSettings.keyBindUseItem.pressed = Keyboard
				.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
	}

	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			for (Entity e : Wrapper.getWorld().loadedEntityList) {
				if (player.getDistanceToEntity(e) > 5 && e != player
						&& player.canEntityBeSeen(e)) {
					placeCrystal();
					if (e instanceof EntityEnderCrystal) { // attack crystal
						Wrapper.getMinecraft().playerController.attackEntity(player, e);
						player.swingArm(EnumHand.MAIN_HAND);
					}
					Wrapper.getMinecraft().gameSettings.keyBindUseItem.pressed = Keyboard
							.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
					;
				}
			}
		}
	}

	private void placeCrystal() {
		for (int slot = 44; slot >= 9; slot--) {
			ItemStack stack = Wrapper.getPlayer().inventoryContainer.getSlot(slot).getStack();

			if (stack != null) {
				if (slot >= 36 && slot <= 44) {
					if (stack.getItem() instanceof ItemBlock && (stack.getItem() instanceof ItemEndCrystal)) {
						Wrapper.getPlayer().inventory.currentItem = slot - 36;
						Wrapper.getMinecraft().gameSettings.keyBindUseItem.pressed = true;
					}
				} else if (stack.getItem() instanceof ItemBlock && (stack.getItem() instanceof ItemEndCrystal)) {
					int itemSlot = slot;
					int currentSlot = Wrapper.getPlayer().inventory.currentItem + 36;
					Wrapper.getMinecraft().playerController.windowClick(0, slot, 0, ClickType.PICKUP_ALL,
							Wrapper.getPlayer());
					Wrapper.getMinecraft().playerController.windowClick(0, currentSlot, 0, ClickType.SWAP,
							Wrapper.getPlayer());
					for (slot = 44; slot >= 9; slot--) {
						if (Wrapper.getPlayer().inventoryContainer.getSlot(slot).getStack() == null) {
							Wrapper.getMinecraft().playerController.windowClick(0, slot, 0, ClickType.SWAP,
									Wrapper.getPlayer());
						}
					}
				}
			}
		}
	}
}
