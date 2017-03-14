package com.darkcart.xdolf.mods.player;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;

import net.minecraft.client.entity.EntityPlayerSP;

public class Step extends Module {
	
	public Step() {
		super("Step", "Step up more than half of a block.", Keyboard.KEYBOARD_SIZE, Category.PLAYER);
	}

	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(Wrapper.getWorld() != null && player != null) {
			player.stepHeight = 0.6F;
			boolean check = !player.isOnLadder() && !player.isInWater()
					&& player.isCollidedHorizontally
					&& player.onGround && !Keyboard.isKeyDown(Keyboard.KEY_SPACE);
			if(check) {
				player.boundingBox.offset(0.0D, 1.0628, 0.0D);
				player.motionY = -420;
				player.isCollidedHorizontally = false;
			}
		}
	}
}
