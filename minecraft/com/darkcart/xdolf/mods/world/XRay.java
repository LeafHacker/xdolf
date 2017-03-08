package com.darkcart.xdolf.mods.world;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class XRay extends Module {
	
	public static ArrayList<Block> xrayBlocks = new ArrayList<Block>();
	
	public XRay() {
		xrayBlocks.add(Blocks.DIAMOND_ORE);
		xrayBlocks.add(Blocks.GOLD_ORE);
		xrayBlocks.add(Blocks.EMERALD_ORE);
		xrayBlocks.add(Blocks.REDSTONE_ORE);
		xrayBlocks.add(Blocks.IRON_ORE);
		xrayBlocks.add(Blocks.COAL_ORE);
	}

	@Override
	public void enable() {
		Client.mc.renderGlobal.loadRenderers();
	}

	@Override
	public void disable() {
		Client.mc.renderGlobal.loadRenderers();
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_X;
	}

	@Override
	public String getName() {
		return "XRay";
	}

	@Override
	public String getDescription() {
		return "Find ores easier";
	}

}
