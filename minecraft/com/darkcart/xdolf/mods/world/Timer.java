package com.darkcart.xdolf.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;

public class Timer extends Module {
	
	public static float speed = 5.0f;

	@Override
	public void enable() {
		net.minecraft.util.Timer.timerSpeed = speed;
	}

	@Override
	public void disable() {
		net.minecraft.util.Timer.timerSpeed = 1;
	}
	
	@Override
	public int getKeyCode() {
		return Keyboard.KEY_GRAVE;
	}

	@Override
	public String getName() {
		return "Timer";
	}

	@Override
	public String getDescription() {
		return "Speeds up game. Doesn't bypass on most servers.";
	}

}
