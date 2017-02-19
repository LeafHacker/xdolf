package com.darkcart.xcheat;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;

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
	
}
