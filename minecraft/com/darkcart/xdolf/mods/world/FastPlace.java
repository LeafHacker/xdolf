package com.darkcart.xdolf.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;

public class FastPlace extends Module {

	@Override
	public void tick() {
		if(isToggled())
        {
			Client.mc.rightClickDelayTimer = 0;
        }
	}
	

	@Override
	public int getKeyCode() {
		return Keyboard.KEYBOARD_SIZE;
	}

	@Override
	public String getName() {
		return "FastPlace";
	}

	@Override
	public String getDescription() {
		return "Allows you to quickly place blocks.";
	}

}