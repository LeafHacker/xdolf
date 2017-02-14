package com.darkcart.xcheat.mods;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.util.RenderUtil;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

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
		try
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glDepthMask(false);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glLineWidth(1.5F);
	    	for(Object entities: Client.mc.world.loadedEntityList) {
	    		if (entities != Client.mc.player && entities != null) {
	    			if (entities instanceof EntityPlayer) {
	    				EntityPlayer entity = (EntityPlayer)entities;
	    				float distance = Client.mc.renderViewEntity.getDistanceToEntity(entity);
	    				double posX = ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - RenderManager.renderPosX));
	    				double posY = ((entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - RenderManager.renderPosY));
	    				double posZ = ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - RenderManager.renderPosZ));

	    				if (distance <= 6F) {
	    					GL11.glColor3f(1.0F, 0.0F, 0.0F);
	    				}else if (distance <= 96F) {
	    					GL11.glColor3f(1.0F, (distance / 100F), 0.0F);
	    				}else if (distance > 96F) {
	    					GL11.glColor3f(0.1F, 0.6F, 255.0F);
	    				}

	    				GL11.glBegin(GL11.GL_LINE_LOOP);
	    				GL11.glVertex3d(0, 0, 0);
	    				GL11.glVertex3d(posX, posY + 1, posZ);
	    				GL11.glEnd();
	    			}
	    		}
	    	}
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glDepthMask(true);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glDisable(GL11.GL_LINE_SMOOTH);
	        GL11.glPopMatrix();
		}catch(Exception e) {}
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
