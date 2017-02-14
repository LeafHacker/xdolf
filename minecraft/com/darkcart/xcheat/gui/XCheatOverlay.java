package com.darkcart.xcheat.gui;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;

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
		
		int width  = Client.gameResolution.getScaledWidth();
		int height = Client.gameResolution.getScaledHeight();

		this.drawRect(0, 0, Client.mc.fontRendererObj.getStringWidth("XClient") + 4, 12, 0x77222222);
		Client.mc.fontRendererObj.drawString("XCheat", 2, 2, 0x55ff55);
		for(Module m: Client.modules)
		{
			if(m.isToggled()) {
				Client.mc.fontRendererObj.drawString(m.getName(), 20, 2, 0xFFFFFF);
			}
		}
		
		int count = 0;
		try {
			for(Module m: Client.modules) {
				int x2 = width - (Client.mc.fontRendererObj.getStringWidth(m.getName()));
				int y = (10 * count);
				Client.mc.fontRendererObj.drawString(m.getName(), x2 - 2, y + 2, 0xffffff);
				count++;
			}
		}catch(Exception ex){}

		if (Client.mc.player != null) {
			if (Client.mc.player.inventory.armorInventory.get(3) != null) {
				Client.mc.fontRendererObj.drawString(
						"h: " + (Client.mc.player.inventory.armorInventory.get(3).getItemDamage()),
						1, height - 40, 0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(2) != null) {
				Client.mc.fontRendererObj.drawString(
						"c: " + (Client.mc.player.inventory.armorInventory.get(2).getItemDamage()),
						1, height - 30, 0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(1) != null) {
				Client.mc.fontRendererObj.drawString(
						"l: " + (Client.mc.player.inventory.armorInventory.get(1).getItemDamage()),
						1, height - 20, 0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(0) != null) {
				Client.mc.fontRendererObj.drawString(
						"b: " + (Client.mc.player.inventory.armorInventory.get(0).getItemDamage()),
						1, height - 10, 0xffffff);
			}
		}
	}
}
