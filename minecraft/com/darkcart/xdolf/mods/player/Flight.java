package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;

public class Flight extends Module {
	
	public Flight() {
		super("Flight", "Flying mode.", Keyboard.KEY_F, 0xFFFFFF, Category.PLAYER);
	}

	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			try {
				player.motionX = 0;
				player.motionY = 0;
				player.motionZ = 0;
				player.landMovementFactor = 2;
				player.jumpMovementFactor = 2;
				player.inWater = false;
				if(Wrapper.getMinecraft().inGameHasFocus) {
					if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindJump.keyCode)) {
						player.motionY += 2 / 2 + 0.2F;
					}
					if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
						player.motionY -= 2 / 2 + 0.2F;
					}
				}
			}catch(Exception ex){}
		}
	}
}
