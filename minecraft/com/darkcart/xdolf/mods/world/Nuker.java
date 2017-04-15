package com.darkcart.xdolf.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

public class Nuker extends Module {

	public Nuker() {
		super("Nuker", "What do you think it does?", Keyboard.KEY_NONE, 0xffffff, Category.WORLD);
	}

	public void onEnable() {
		Wrapper.getWorld().sendQuittingDisconnectingPacket();
	}
}
