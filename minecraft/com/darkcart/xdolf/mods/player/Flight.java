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
		Wrapper.getPlayer().motionX = 0;
		Wrapper.getPlayer().motionY = 0;
		Wrapper.getPlayer().motionZ = 0;
		Wrapper.getPlayer().landMovementFactor = 1;
		Wrapper.getPlayer().jumpMovementFactor = 1;
		Wrapper.getPlayer().inWater = false;
		if(Wrapper.getMinecraft().inGameHasFocus) {
			if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindJump.keyCode)) {
				Wrapper.getPlayer().motionY += 3 / 2 + 0.2F;
			}
			if(Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
				Wrapper.getPlayer().motionY -= 3 / 2 + 0.2F;
			}
		}
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
		return "GOTTA GO FAST";
	}
}
