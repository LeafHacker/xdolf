package com.darkcart.xdolf.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;

public class FastPlace extends Module {

	@Override
	public void tick() {
		if(isToggled())
        {
			Wrapper.getMinecraft().rightClickDelayTimer = 0;
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