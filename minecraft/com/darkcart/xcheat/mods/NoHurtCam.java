package com.darkcart.xcheat.mods;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Module;

public class NoHurtCam extends Module {

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
		return Keyboard.KEY_Z;
	}

	@Override
	public String getDescription() {
		return "Disables the HurtCam animation when damage is taken.";
	}

}
