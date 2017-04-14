package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.util.Category;

public class AutoFish extends Module {

	public AutoFish() {
		super("AutoFish", "Automatically catches fish", Keyboard.KEY_NONE, 0xffffff, Category.Player);
	}

}
