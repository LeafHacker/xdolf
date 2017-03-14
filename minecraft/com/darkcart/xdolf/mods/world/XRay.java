package com.darkcart.xdolf.mods.world;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.util.Category;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class XRay extends Module {
	
	public XRay()
	{
		super("Xray", "Find ores easier.", Keyboard.KEY_X, 0xFFFFFF, Category.WORLD);
	}
	
	public static ArrayList<Block> xrayBlocks = new ArrayList<Block>();
	
	public void XRay() {
		xrayBlocks.add(Blocks.DIAMOND_ORE);
		xrayBlocks.add(Blocks.GOLD_ORE);
		xrayBlocks.add(Blocks.EMERALD_ORE);
		xrayBlocks.add(Blocks.REDSTONE_ORE);
		xrayBlocks.add(Blocks.IRON_ORE);
		xrayBlocks.add(Blocks.COAL_ORE);
	}

	@Override
	public void onEnable() {
		Client.mc.renderGlobal.loadRenderers();
	}

	@Override
	public void onDisable() {
		Client.mc.renderGlobal.loadRenderers();
	}
}
