package com.darkcart.xcheat.mods;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

public class Step extends Module {
	
	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void tick() {
		if(Client.mc.world != null && Client.mc.player != null) {
			Client.mc.player.stepHeight = 0.6F;
			boolean check = !Client.mc.player.isOnLadder() && !Client.mc.player.isInWater()
					&& Client.mc.player.isCollidedHorizontally
					&& Client.mc.player.onGround && !Keyboard.isKeyDown(Keyboard.KEY_SPACE);
			if(check) {
				Client.mc.player.boundingBox.offset(0.0D, 1.0628, 0.0D);
				Client.mc.player.motionY = -420;
				Client.mc.player.isCollidedHorizontally = false;
			}
		}
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_G;
	}

	@Override
	public String getName() {
		return "Step";
	}
	
	@Override
	public String getDescription() {
		return "Allows you to step up 1 block.";
	}
}
