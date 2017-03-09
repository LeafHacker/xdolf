package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;

import net.minecraft.network.play.client.CPacketPlayer;

public class NoFall extends Module {


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
