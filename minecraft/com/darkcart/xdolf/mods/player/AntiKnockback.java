package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;

public class AntiKnockback extends Module {

	// Actual code is in NetHandlerPlayClient

	@Override
	public String getName() {
		return "AntiKnockback";
	}

	@Override
	public String getDescription() {
		return "Keeps you from being knocked back.";
	}


}
