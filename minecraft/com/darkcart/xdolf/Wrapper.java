package com.darkcart.xdolf;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.text.TextComponentString;

public class Wrapper {
	
	public static final Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}
	
	public static final EntityPlayerSP getPlayer() {
		return getMinecraft().player;
	}
	
	public static final WorldClient getWorld() {
		return getMinecraft().world;
	}

	public static final File getMinecraftDir() {
		return getMinecraft().mcDataDir;
	}
	
	public static void addChatMessage(String s) {
		getPlayer().addChatMessage(new TextComponentString(Client.wrap(String.format("[%s%s%s] %s", "\247e", "Xdolf", "\247f", s), 100)));
	}
	
}
