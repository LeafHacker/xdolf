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
		super("CrystalAura", "Automatically hits nearby end crystals.", Keyboard.KEYBOARD_SIZE, 0xFFFFFF, Category.Combat);
	}
	
	@Override
	public void onDisable() {

	}

	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			for (Entity e : Wrapper.getWorld().loadedEntityList) {
				if (player.getDistanceToEntity(e) > 4.5) {
					if (e instanceof EntityEnderCrystal) { // attack crystal
						Wrapper.getMinecraft().playerController.attackEntity(player, e);
						player.swingArm(EnumHand.MAIN_HAND);
					}
				}
			}
		}
	}
}
