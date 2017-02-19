package com.darkcart.xcheat.mods;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class AutoRespawn extends Module {

	@Override
	public void enable() {

	}

	@Override
	public void disable() {

	}

	@Override
	public void tick() {
		if(isToggled() && Client.mc.player.getHealth() <= 0)
        {
			Client.mc.player.respawnPlayer();
        }
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEYBOARD_SIZE;
	}

	@Override
	public String getName() {
		return "AutoRespawn";
	}

	@Override
	public String getDescription() {
		return "Respawns the player for you upon death.";
	}

}
