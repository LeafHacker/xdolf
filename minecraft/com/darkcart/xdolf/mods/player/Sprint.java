package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;
import com.darkcart.xdolf.util.Value;

import net.minecraft.client.entity.EntityPlayerSP;

public class Sprint extends Module {

	public Sprint()
	{
	}
	
	@Override
	public void onUpdate(EntityPlayerSP entity) {
		if(isEnabled())
		{
			boolean shouldSprint = entity.movementInput.moveForward > 0.0F && !entity.isSneaking();
			if (shouldSprint) 
			{
				entity.setSprinting(true);
			}
		}
	}
}
