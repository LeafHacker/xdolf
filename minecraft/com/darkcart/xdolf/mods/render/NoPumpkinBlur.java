package com.darkcart.xdolf.mods.render;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;

public class NoPumpkinBlur extends Module {
	
	// NOTE: Code is in GuiIngame, line 160.

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
