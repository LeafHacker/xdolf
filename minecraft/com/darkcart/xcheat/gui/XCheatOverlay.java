package com.darkcart.xcheat.gui;

import com.darkcart.xcheat.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class XCheatOverlay extends GuiIngame {

	public XCheatOverlay(Minecraft mcIn) {
		super(mcIn);
	}

	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		renderXCheatOverlay();
	}

	// Color format: argb: 0x[aarrggbb]
	// 00 = completely transparent, ff = opaque

	public void renderXCheatOverlay() {

		this.drawRect(0, 0, 40, 10, 0x77222222);
		Client.mc.fontRendererObj.drawString("xcheat", 0, 0, 0x55ff55);

		if (Client.mc.player != null) {
			if (Client.mc.player.inventory.armorInventory.get(3) != null) {
				Client.mc.fontRendererObj.drawString(
						"h: " + (Client.mc.player.inventory.armorInventory.get(3).getItemDamage()),
						0, Client.gameResolution.getScaledHeight() - 40, 0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(2) != null) {
				Client.mc.fontRendererObj.drawString(
						"c: " + (Client.mc.player.inventory.armorInventory.get(2).getItemDamage()),
						0, Client.gameResolution.getScaledHeight() - 30, 0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(1) != null) {
				Client.mc.fontRendererObj.drawString(
						"l: " + (Client.mc.player.inventory.armorInventory.get(1).getItemDamage()),
						0, Client.gameResolution.getScaledHeight() - 20, 0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(0) != null) {
				Client.mc.fontRendererObj.drawString(
						"b: " + (Client.mc.player.inventory.armorInventory.get(0).getItemDamage()),
						0, Client.gameResolution.getScaledHeight() - 10, 0xffffff);
			}
		}
	}
}
