package com.darkcart.xdolf.mods.render;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;

public class Chams extends Module {
	
	// Actual code is in another class, RenderPlayer line 75

	@Override
	public String getName() {
		return "Chams";
	}
	
	@Override
	public String getDescription() {
		return "Renders players through walls.";
	}
}