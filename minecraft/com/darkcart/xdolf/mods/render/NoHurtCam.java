package com.darkcart.xdolf.mods.render;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;

public class NoHurtCam extends Module {

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_Z;
	}

	@Override
	public String getName() {
		return "NoHurtCam";
	}
	
	@Override
	public String getDescription() {
		return "Disables the HurtCam animation when damage is taken.";
	}
}
