package com.darkcart.xcheat.mods.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.Wrapper;
import com.darkcart.xcheat.util.RenderUtils;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class Tracers extends Module {


	@Override
	public int getKeyCode() {
		return Keyboard.KEY_Y;
	}

	@Override
	public void render() {
		try {
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glLineWidth(1.5F);
			for (Entity entities : Client.mc.world.loadedEntityList) {
				if (entities != Client.mc.player && entities != null) {
					float distance = Client.mc.renderViewEntity.getDistanceToEntity(entities);
					double posX = ((entities.lastTickPosX + (entities.posX - entities.lastTickPosX)
							- RenderManager.renderPosX));
					double posY = ((entities.lastTickPosY + (entities.posY - entities.lastTickPosY)
							- RenderManager.renderPosY));
					double posZ = ((entities.lastTickPosZ + (entities.posZ - entities.lastTickPosZ)
							- RenderManager.renderPosZ));

					if (distance <= 6F) {
						GL11.glColor3f(1.0F, 0.0F, 0.0F);
					} else if (distance <= 96F) {
						GL11.glColor3f(1.0F, (distance / 100F), 0.0F);
					} else if (distance > 96F) {
						GL11.glColor3f(0.1F, 0.6F, 255.0F);
					}
					Vec3d eyes = new Vec3d(0, 0, 1).rotatePitch(-(float) Math.toRadians(Wrapper.getPlayer().rotationPitch)).rotateYaw(-(float) Math.toRadians(Wrapper.getPlayer().rotationYaw));
					GL11.glBegin(GL11.GL_LINE_LOOP);
					GL11.glVertex3d(eyes.xCoord, Wrapper.getPlayer().getEyeHeight() + eyes.yCoord, eyes.zCoord);

					GL11.glVertex3d(posX, posY, posZ);

					GL11.glEnd();
				}
			}
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_LINE_SMOOTH);
			GL11.glPopMatrix();
		} catch (Exception e) {
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
