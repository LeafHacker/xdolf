package com.darkcart.xcheat.mods.aura;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.Wrapper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class CrystalAura extends Module {

	@Override
	public void disable() {
		Wrapper.getMinecraft().gameSettings.keyBindUseItem.pressed = Keyboard
				.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
	}

	@Override
	public void tick() {
		for (Entity e : Wrapper.getWorld().loadedEntityList) {
			if (Wrapper.getPlayer().getDistanceToEntity(e) > 5 && e != Wrapper.getPlayer()
					&& Wrapper.getPlayer().canEntityBeSeen(e)) {
				placeCrystal();
				if (e instanceof EntityEnderCrystal) { // attack crystal
					Client.mc.playerController.attackEntity(Client.mc.player, e);
					Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
				}
				Wrapper.getMinecraft().gameSettings.keyBindUseItem.pressed = Keyboard
						.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
				;
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

	@Override
	public String getName() {
		return "CrystalAura";
	}

	@Override
	public String getDescription() {
		return "Automatically end crystal bombs people";
	}

}
