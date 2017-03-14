package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.util.Category;

public class AntiKnockback extends Module {
	
	public AntiKnockback() {
		super("AntiKnockback", "Blocks velocity.", Keyboard.KEY_L, 0xCC66FF, Category.AURA);
	}
}
