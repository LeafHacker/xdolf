package com.darkcart.xcheat;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.mods.AntiKnockback;
import com.darkcart.xcheat.mods.BHop;
import com.darkcart.xcheat.mods.EntityESP;
import com.darkcart.xcheat.mods.Flight;
import com.darkcart.xcheat.mods.Fullbright;
import com.darkcart.xcheat.mods.NoFall;
import com.darkcart.xcheat.mods.NoHurtCam;
import com.darkcart.xcheat.mods.Step;
import com.darkcart.xcheat.mods.StorageESP;
import com.darkcart.xcheat.mods.Timer;
import com.darkcart.xcheat.mods.Tracers;

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
		modules.add(new AntiKnockback());
		modules.add(new Step());
		modules.add(new Flight());
		modules.add(new BHop());
		modules.add(new Timer());
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
