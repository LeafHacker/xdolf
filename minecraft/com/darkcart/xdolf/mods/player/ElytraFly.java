package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;
import com.darkcart.xdolf.util.Value;

import net.minecraft.client.entity.EntityPlayerSP;

public class ElytraFly extends Module {
	
	public ElytraFly() {
		super("ElytraFlight", "Vanilla flight using Elytra.", Keyboard.KEYBOARD_SIZE, 0xFFFFFF, Category.Player);
	}
	
	public static Value elytraSpeed = new Value("ElytraFlight Speed");
	
	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			if(player.capabilities.getFlySpeed() != elytraSpeed.getValue()) {
				player.capabilities.setFlySpeed(elytraSpeed.getValue() / 10);
			}
			
			if(player.isElytraFlying()) {
				player.capabilities.isFlying = true;
			}else{
				if(player.onGround || player.isInWater()) {
					player.capabilities.isFlying = false;
				}
			}
        }
	}
	
	@Override
	public void onDisable() {
		if(Wrapper.getPlayer() != null) {
			Wrapper.getPlayer().capabilities.isFlying = false;
		}
	}
}
