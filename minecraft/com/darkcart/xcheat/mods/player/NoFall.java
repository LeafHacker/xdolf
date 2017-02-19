package com.darkcart.xcheat.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

import net.minecraft.network.play.client.CPacketPlayer;

public class NoFall extends Module {

	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void tick() {
		Client.mc.player.connection.sendPacket(new CPacketPlayer(true));
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_O;
	}
	
	@Override
	public String getName() {
		return "NoFall";
	}

	@Override
	public String getDescription() {
		return "Prevents fall damage.";
	}
}
