package com.darkcart.xdolf.mods.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.util.Category;
import com.darkcart.xdolf.util.RenderUtils;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityShulkerBox;

public class StorageESP extends Module {

	public StorageESP() {
		super("StorageESP", "Creates an ESP box around blocks which can be used to store items.", Keyboard.KEYBOARD_SIZE, Category.ESP);
	}
	
	@Override
	public void onRender() {
		if(isEnabled()) {
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
	}
}
