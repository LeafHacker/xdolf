package com.darkcart.xcheat.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.util.RenderUtil;

import net.minecraft.entity.Entity;

public class Tracers extends Module {

	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_Y;
	}

	@Override
	public void render() {
		for (Entity e: Client.world.loadedEntityList) {
			RenderUtil.tracerLine(e, Color.green);
		}
	}
	
	@Override
	public String getName() {
		return "Tracers";
	}

	@Override
	public String getDescription() {
		return "Draws a line to entities within render distance.";
	}
}
