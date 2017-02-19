package com.darkcart.xcheat.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class BHop extends Module {

	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void tick() {
		Client.mc.player.setSprinting(true);
		if (Client.mc.player.onGround && Client.mc.gameSettings.keyBindForward.isKeyDown()) {
			Client.mc.player.jump();
		}
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_V;
	}

	@Override
	public String getName() {
		return "BHop";
	}

	@Override
	public String getDescription() {
		return "gotta go fast...er!";
	}

	@Override
	public void beforeUpdate() {
		
	}

	@Override
	public void afterUpdate() {
		
	}
}
