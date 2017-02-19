package com.darkcart.xcheat.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class Flight extends Module {

	@Override
	public void enable() {
		Client.mc.player.capabilities.isFlying = true;
	}

	@Override
	public void disable() {
		Client.mc.player.capabilities.isFlying = false;
	}

	@Override
	public void tick() {
		Client.mc.player.capabilities.isFlying = true;
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
