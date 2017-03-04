package com.darkcart.xcheat.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class AntiKnockback extends Module {

	// Actual code is in NetHandlerPlayClient
	
	@Override
	public int getKeyCode() {
		return Keyboard.KEY_C;
	}

	@Override
	public String getName() {
		return "AntiKnockback";
	}

	@Override
	public String getDescription() {
		return "Keeps you from being knocked back.";
	}


}
