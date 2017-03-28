package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;

public class NoFall extends Module
{
	public NoFall()
	{
		super("NoFall", "Prevents fall damage and bypasses NCP.", Keyboard.KEYBOARD_SIZE, 0xFFFFFF, Category.Player);
	}
	
	@Override
	public void beforeUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			
		}
	}
	
	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			
		}
	}
}