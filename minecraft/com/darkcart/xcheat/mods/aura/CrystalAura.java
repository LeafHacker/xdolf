package com.darkcart.xcheat.mods.aura;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.Wrapper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.EnumHand;

public class CrystalAura extends Module {

	@Override
	public void enable() {

	}

	@Override
	public void disable() {

	}

	@Override
	public void tick() {
		for (Entity e : Wrapper.getWorld().loadedEntityList) {
			if (Wrapper.getPlayer().getDistanceToEntity(e) > 5 && e != Wrapper.getPlayer()) {
				Wrapper.getMinecraft().gameSettings.keyBindUseItem.pressed = true;
				if (e instanceof EntityEnderCrystal) {
					Client.mc.playerController.attackEntity(Client.mc.player, e);
				}
				Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
			}
		}
	}

	@Override
	public void beforeUpdate() {

	}

	@Override
	public void afterUpdate() {

	}

	@Override
	public int getKeyCode() {
		return 0;
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
