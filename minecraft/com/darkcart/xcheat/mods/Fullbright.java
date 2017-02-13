package com.darkcart.xcheat.mods;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class Fullbright extends Module {

	@Override
	public void enable() {
		Client.mc.gameSettings.gammaSetting = 1000f;
	}

	@Override
	public void disable() {
		Client.mc.gameSettings.gammaSetting = 0f;
	}

	@Override
	public void tick() {
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_B;
	}

}
