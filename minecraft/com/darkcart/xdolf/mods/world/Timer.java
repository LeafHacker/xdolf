package com.darkcart.xdolf.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.util.Category;

public class Timer extends Module {
	
	public Timer()
	{
		super("Timer", "Speeds up the game. Doesn't bypass on most servers.", Keyboard.KEYBOARD_SIZE, Category.WORLD);
	}
	
	public static float speed = 5.0f;

	@Override
	public void onEnable() {
		net.minecraft.util.Timer.timerSpeed = speed;
	}

	@Override
	public void onDisable() {
		net.minecraft.util.Timer.timerSpeed = 1;
	}
}
