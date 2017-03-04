package com.darkcart.xcheat.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

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
