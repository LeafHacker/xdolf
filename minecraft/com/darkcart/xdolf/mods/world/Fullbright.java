package com.darkcart.xdolf.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

public class Fullbright extends Module {

	@Override
	public void disable() {
		if(Client.mc.player.isPotionActive(Potion.getPotionById(16)))
			Client.mc.player.removeActivePotionEffect(Potion.getPotionById(16));
	}

	@Override
	public void tick() {
		try {
			if(!Client.mc.player.isPotionActive(Potion.getPotionById(16))) { //if for some reason the effect vanishes
				PotionEffect nightVision = new PotionEffect(Potion.getPotionById(16), Integer.MAX_VALUE, 0);
				nightVision.setPotionDurationMax(true);
				Client.mc.player.addPotionEffect(nightVision);
			}
		}catch(Exception ex){ /* TEMP TO STOP A CRASH UPON LEAVING SERVER */ }
	}
	
	@Override
	public int getKeyCode() {
		return Keyboard.KEY_C;
	}
	
	@Override
	public String getName() {
		return "Fullbright";
	}

	@Override
	public String getDescription() {
		return "Activates NightVision potion effect.";
	}
}
