package com.darkcart.xcheat.mods;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Module;

public class NoPumpkinBlur extends Module {

	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_PERIOD;
	}

	@Override
	public String getName() {
		return "NoPumpkinBlur";
	}

	@Override
	public String getDescription() {
		return "Removes that god forsaken pumpkin blur";
	}

}
