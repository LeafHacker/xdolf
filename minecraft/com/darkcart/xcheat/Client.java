package com.darkcart.xcheat;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.mods.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Client {

	public static ArrayList<Module> modules = new ArrayList<Module>();
	public static Minecraft mc = Minecraft.getMinecraft();
	public static ScaledResolution gameResolution;
	
	public Client() {
		modules.add(new Fullbright());
		modules.add(new Tracers());
		modules.add(new StorageESP());
		modules.add(new EntityESP());
		modules.add(new NoFall());
		modules.add(new NoHurtCam());
		modules.add(new Step());
	}
	
	// ANY CODE BELOW THIS SHOULD NOT CHANGE
	
	public void tick() {
		for (Module m: modules) {
			if (m.isToggled()) {
				m.tick();
			}
		}
		gameResolution = new ScaledResolution(Client.mc);
	}
	
	public static Module findMod(Class<?extends Module> clazz) 
	{
		for(Module m: modules)
		{
			if(m.getClass() == clazz)
			{
				return m;
			}
		}
		
		return null;
	}
	
	public void parseKey(int key) {
		for (Module m: modules) {
			if (Keyboard.isKeyDown(m.getKeyCode())) {
				m.toggle();
			}
		}
	}
	
	public static void render() {
		for (Module m: modules) {
			if (m.isToggled()) {
				m.render();
			}
		}
	}
}
