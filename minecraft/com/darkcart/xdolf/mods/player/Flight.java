package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;

public class Flight extends Module {

	@Override
	public void enable() {

	}

	@Override
	public void disable() {

	}

	@Override
	public void tick() {
		try {
			Wrapper.getPlayer().motionX = 0;
			Wrapper.getPlayer().motionY = 0;
			Wrapper.getPlayer().motionZ = 0;
			Wrapper.getPlayer().landMovementFactor = 2;
			Wrapper.getPlayer().jumpMovementFactor = 2;
			Wrapper.getPlayer().inWater = false;
			if(Wrapper.getMinecraft().inGameHasFocus) {
				if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindJump.keyCode)) {
					Wrapper.getPlayer().motionY += 2 / 2 + 0.2F;
				}
				if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
					Wrapper.getPlayer().motionY -= 2 / 2 + 0.2F;
				}
			}
		}catch(Exception ex){}
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_F;
	}

	@Override
	public String getName() {
		return "Flight";
	}

	@Override
	public String getDescription() {
		return "Enables the player to fly.";
	}
}
