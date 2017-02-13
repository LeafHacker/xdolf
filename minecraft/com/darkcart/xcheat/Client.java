package com.darkcart.xcheat;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.mods.Fullbright;
import com.darkcart.xcheat.mods.Tracers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Client {

	public static HashMap<String, Module> modules = new HashMap<String, Module>();
	public static Minecraft mc = Minecraft.getMinecraft();
	public static ScaledResolution gameResolution;
	
	public Client() {
		modules.put("Fullbright", new Fullbright());
		modules.put("Tracers", new Tracers());
	}
	
	public void tick() {
		for (Module m: modules.values()) {
			if (m.isToggled()) {
				m.tick();
			}
		}
		gameResolution = new ScaledResolution(Client.mc);
	}
	
	public void parseKey(int key) {
		for (Module m: modules.values()) {
			if (Keyboard.isKeyDown(m.getKeyCode())) {
				m.toggle();
			}
		}
	}
	
	public static void render() {
		for (Module m: modules.values()) {
			if (m.isToggled()) {
				m.render();
			}
		}
	}
}
