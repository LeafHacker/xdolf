package com.darkcart.xcheat.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class FastPlace extends Module {

	@Override
	public void enable() {

	}

	@Override
	public void disable() {

	}

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