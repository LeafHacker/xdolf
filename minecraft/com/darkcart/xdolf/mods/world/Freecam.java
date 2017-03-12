package com.darkcart.xdolf.mods.world;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.mods.player.Flight;
import com.darkcart.xdolf.util.FreecamEntity;

import net.minecraft.client.entity.EntityPlayerSP;

public class Freecam extends Module {
	
	public FreecamEntity freecamEnt = null;

	@Override
	public void enable() {
		if(Wrapper.getPlayer() != null && Wrapper.getWorld() != null)
		{
			freecamEnt = new FreecamEntity(Wrapper.getWorld(), Wrapper.getPlayer().getGameProfile());
			freecamEnt.noClip = false;
			freecamEnt.setPosition(Wrapper.getPlayer().posX, Wrapper.getPlayer().boundingBox.minY, Wrapper.getPlayer().posZ);
			//freecamEnt.boundingBox.setBB(AdolfWrapper.getPlayer().boundingBox);
			//AdolfWrapper.getWorld().spawnEntityInWorld(freecamEnt);
		}
		
		Wrapper.getMinecraft().renderViewEntity = freecamEnt;
	}

	@Override
	public void disable() {
		if(freecamEnt != null && Wrapper.getWorld() != null)
		{
			freecamEnt.setHealth(0);
			freecamEnt.setDead();
			Wrapper.getWorld().removeEntity(freecamEnt);
			freecamEnt = null;
		}
	
		Wrapper.getMinecraft().renderViewEntity = Wrapper.getPlayer();
	}

	@Override
	public void tick() {
		try {
			if(freecamEnt != null)
			{
				EntityPlayerSP player = Wrapper.getPlayer();
				freecamEnt.inventory = player.inventory;
				freecamEnt.renderOffsetY = player.renderOffsetY;
				freecamEnt.renderOffsetY = player.renderOffsetY;
				freecamEnt.setMovementInput(player.movementInput);
				freecamEnt.rotationPitch = player.rotationPitch;
				freecamEnt.rotationYaw = player.rotationYaw;
				freecamEnt.rotationYawHead = player.rotationYawHead;
				freecamEnt.setSprinting(player.isSprinting());
				if(Client.findMod(Flight.class).isToggled()) 
				{
					freecamEnt.inWater = false;
				}
				if(Wrapper.getMinecraft().renderViewEntity != freecamEnt)
				{
					Wrapper.getMinecraft().renderViewEntity = freecamEnt;
				}
			}else{
				this.toggle();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getKeyCode() {
		return Keyboard.KEY_B;
	}

	@Override
	public String getName() {
		return "Freecam";
	}

	@Override
	public String getDescription() {
		return "Frees the player's camera to move freely.";
	}
}
