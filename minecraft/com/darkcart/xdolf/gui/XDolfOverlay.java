package com.darkcart.xdolf.gui;

import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.google.common.collect.Ordering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class XDolfOverlay extends GuiIngame {

	public XDolfOverlay(Minecraft mcIn) {
		super(mcIn);
	}

	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		renderXDolfOverlay();
	}

	// Color format: argb: 0x[aarrggbb]
	// 00 = completely transparent, ff = opaque

	public void renderXDolfOverlay() {

		int width = Client.gameResolution.getScaledWidth();
		int height = Client.gameResolution.getScaledHeight();

		this.drawRect(0, 0, Client.mc.fontRendererObj.getStringWidth("XClient") + 4, 12, 0x77222222);
		Client.mc.fontRendererObj.drawString("XCheat", 2, 2, 0x55FF55);
		Wrapper.getMinecraft().fontRendererObj.drawString("FPS: " + Wrapper.getMinecraft().getDebugFPS(), 2, 14, 0xffffff);

		int count = 0;
		try {
			for (String s : Client.enabledModuleNames) {
				int x2 = width - (Client.mc.fontRendererObj.getStringWidth(s));
				int y = (10 * count);
				Client.mc.fontRendererObj.drawStringWithShadow(s, x2 - 2, y + 2, 0xffffff);
				count++;
			}
		} catch (Exception ex) {
		}

		if (Client.mc.player != null) {
			if (Client.mc.player.inventory.armorInventory.get(3) != null) {
				Client.mc.fontRendererObj.drawStringWithShadow(
						"h: " + (Client.mc.player.inventory.armorInventory.get(3).getItemDamage()), 1, height - 40,
						0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(2) != null) {
				Client.mc.fontRendererObj.drawStringWithShadow(
						"c: " + (Client.mc.player.inventory.armorInventory.get(2).getItemDamage()), 1, height - 30,
						0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(1) != null) {
				Client.mc.fontRendererObj.drawStringWithShadow(
						"l: " + (Client.mc.player.inventory.armorInventory.get(1).getItemDamage()), 1, height - 20,
						0xffffff);
			}

			if (Client.mc.player.inventory.armorInventory.get(0) != null) {
				Client.mc.fontRendererObj.drawStringWithShadow(
						"b: " + (Client.mc.player.inventory.armorInventory.get(0).getItemDamage()), 1, height - 10,
						0xffffff);
			}

			Collection<PotionEffect> var4 = Wrapper.getPlayer().getActivePotionEffects();

			if (!var4.isEmpty()) {
				final ResourceLocation var5 = new ResourceLocation("textures/gui/container/inventory.png");
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				int var6 = -23;
				int count2 = 0;

				for (PotionEffect potioneffect : Ordering.natural().sortedCopy(var4)) {
					count2++;
					Potion var9 = potioneffect.getPotion();
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					Wrapper.getMinecraft().renderEngine.bindTexture(var5);

					String s1 = I18n.format(var9.getName(), new Object[0]);

					if (potioneffect.getAmplifier() == 1) {
						s1 = s1 + " II";
					} else if (potioneffect.getAmplifier() == 2) {
						s1 = s1 + " III";
					} else if (potioneffect.getAmplifier() == 3) {
						s1 = s1 + " IV";
					}

					String var11 = Potion.getPotionDurationString(potioneffect, 1.0F);
					int var14 = width - Wrapper.getMinecraft().fontRendererObj.getStringWidth(s1) - 2 - 20;
					int var16 = width - (Wrapper.getMinecraft().fontRendererObj.getStringWidth(var11) / 2) - 4 - 20;

					if (var9.hasStatusIcon()) {
						int var10 = var9.getStatusIconIndex();
						this.drawTexturedModalRect(width - 20, height - (count2 * 20), 0 + var10 % 8 * 18,
								198 + var10 / 8 * 18, 18, 18);
					}

					Wrapper.getMinecraft().fontRendererObj.drawStringWithShadow(s1, var14, height - (count2 * 20),
							16777215);
					var14 = width - Wrapper.getMinecraft().fontRendererObj.getStringWidth(var11) - 2;
					Wrapper.getMinecraft().fontRendererObj.drawStringWithShadow(var11, (var16) - 8,
							height + 10 - (count2 * 20), 8355711);
				}
			}
		}
	}
}
