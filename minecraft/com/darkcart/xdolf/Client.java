package com.darkcart.xdolf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.mods.aura.AutoArmor;
import com.darkcart.xdolf.mods.aura.CrystalAura;
import com.darkcart.xdolf.mods.aura.KillAura;
import com.darkcart.xdolf.mods.player.AntiKnockback;
import com.darkcart.xdolf.mods.player.AutoRespawn;
import com.darkcart.xdolf.mods.player.AutoWalk;
import com.darkcart.xdolf.mods.player.Flight;
import com.darkcart.xdolf.mods.player.NoFall;
import com.darkcart.xdolf.mods.player.Spammer;
import com.darkcart.xdolf.mods.player.Step;
import com.darkcart.xdolf.mods.render.EntityESP;
import com.darkcart.xdolf.mods.render.NoHurtCam;
import com.darkcart.xdolf.mods.render.NoPumpkinBlur;
import com.darkcart.xdolf.mods.render.StorageESP;
import com.darkcart.xdolf.mods.render.Tracers;
import com.darkcart.xdolf.mods.world.Fullbright;
import com.darkcart.xdolf.mods.world.Timer;
import com.darkcart.xdolf.mods.world.XRay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Client {

	public static ArrayList<Module> modules = new ArrayList<Module>();
	public static ArrayList<String> enabledModuleNames = new ArrayList<String>();
	public static Minecraft mc = Minecraft.getMinecraft();
	public static ScaledResolution gameResolution;
	public static ArrayList<String> friends = new ArrayList<String>();

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
		modules.add(new Spammer());
		modules.add(new Timer());
		modules.add(new NoPumpkinBlur());
		modules.add(new XRay());
		modules.add(new KillAura());
		modules.add(new AutoRespawn());
		modules.add(new AutoArmor());
		modules.add(new CrystalAura());
		modules.add(new AutoWalk());
	}

	// ANY CODE BELOW THIS SHOULD NOT CHANGE

	public void tick() {
		for (Module m : modules) {
			if (m.isToggled()) {
				m.beforeUpdate();
				m.tick();
				m.afterUpdate();
			}
		}
		gameResolution = new ScaledResolution(Client.mc);
	}

	public static Module findMod(Class<? extends Module> clazz) {
		for (Module m : modules) {
			if (m.getClass() == clazz) {
				return m;
			}
		}

		return null;
	}

	public void parseKey(int key) {
		for (Module m : modules) {
			try {
				if (Keyboard.isKeyDown(m.getKeyCode())) {
					m.toggle();
					if(Client.enabledModuleNames.contains(m.getName())) {
						Client.enabledModuleNames.remove(m.getName());
					}else{
						Client.enabledModuleNames.add(m.getName());
					}
					Collections.sort(enabledModuleNames);
				}
			} catch (Exception ex) {
				/* NEED TO FIX AS CRASH HERE */}
		}
	}

	public static void render() {
		for (Module m : modules) {
			if (m.isToggled()) {
				m.render();
			}
		}
	}

	public static String downloadString(String uri) {
		try {
			URL url = new URL(uri);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			String text = "";

			String line = "";
			while ((line = reader.readLine()) != null) {
				String curLine = line;
				text += curLine;
			}
			return text;
		} catch (Exception e) {
			return "Failed to retrieve string.";
		}
	}

	public static String wrap(String in, int len) {
		in = in.trim();
		if (in.length() < len)
			return in;
		if (in.substring(0, len).contains("\n"))
			return in.substring(0, in.indexOf("\n")).trim() + "\n\n" + wrap(in.substring(in.indexOf("\n") + 1), len);
		int place = Math.max(Math.max(in.lastIndexOf(" ", len), in.lastIndexOf("\t", len)), in.lastIndexOf("-", len));
		return in.substring(0, place).trim() + "\n" + wrap(in.substring(place), len);
	}

	public static void shutdown() {
		
	}
}
