package com.darkcart.xcheat.mods.render;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Module;

public class NoHurtCam extends Module {
	
	// Actual code is in another class, EntityRenderer line 581

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
