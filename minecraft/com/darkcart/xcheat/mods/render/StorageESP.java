package com.darkcart.xcheat.mods.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.util.RenderUtils;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityShulkerBox;

public class StorageESP extends Module {

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
	public void beforeUpdate() {
		
	}

	@Override
	public void afterUpdate() {
		
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_H;
	}

	@Override
	public void render() {
		for (TileEntity e : Client.mc.world.loadedTileEntityList) {
			if (e instanceof TileEntityChest) {
				RenderUtils.blockESP(e.getPos(), Color.green);
			}
			if (e instanceof TileEntityEnderChest) {
				RenderUtils.blockESP(e.getPos(), Color.magenta);
			}
			if (e instanceof TileEntityFurnace || e instanceof TileEntityDispenser || e instanceof TileEntityDropper) {
				RenderUtils.blockESP(e.getPos(), Color.gray);
			}
			if (e instanceof TileEntityShulkerBox) {
				RenderUtils.blockESP(e.getPos(), Color.red);
			}
		}
	}
	
	@Override
	public String getName() {
		return "StorageESP";
	}

	@Override
	public String getDescription() {
		return "Creates an ESP box around blocks which can be used to store items.";
	}
}
