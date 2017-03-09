package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;

public class AutoRespawn extends Module {

	@Override
	public void tick() {
		if(Client.mc.player.getHealth() <= 0)
        {
			Client.mc.player.respawnPlayer();
        }
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
