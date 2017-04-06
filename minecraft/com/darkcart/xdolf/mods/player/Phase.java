package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;

public class Phase extends Module {
	
	public Phase() {
		super("Phase", "Makes the player fall into the wall..", Keyboard.KEYBOARD_SIZE, 0xFF00FF, Category.Player);
	}
	
	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			player.noClip = true;
			player.motionY = 0D;
			player.setSprinting(true);
		}
	} 
	
	@Override
	public void onDisable() {
		try {
			Wrapper.getPlayer().noClip = false;
		}catch(Exception ex){}
	}
}