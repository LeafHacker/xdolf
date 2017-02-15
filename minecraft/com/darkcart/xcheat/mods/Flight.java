package com.darkcart.xcheat.mods;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class Flight extends Module {

	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void tick() {
		if (Client.mc.gameSettings.keyBindForward.isKeyDown()) {
			Client.mc.player.jump();
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
