package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;

public class AutoWalk extends Module {

	@Override
	public void tick() {
		Wrapper.getMinecraft().gameSettings.keyBindForward.pressed = true;
	}
	
	@Override
	public void disable() {
		Wrapper.getMinecraft().gameSettings.keyBindForward.pressed = 
				Keyboard.isKeyDown(Wrapper.getMinecraft().gameSettings.keyBindForward.getKeyCode());
	}

	@Override
	public String getName() {
		return "AutoWalk";
	}

	@Override
	public String getDescription() {
		return "Automatically walks in the direction you're facing.";
	}


}
