package com.darkcart.xcheat.mods.aura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.Wrapper;
import com.darkcart.xcheat.util.EntityUtils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

public class KillAura extends Module {
	
	private float yaw, pitch, yawHead;

	private long currentMS = 0L;
	private long lastMS = -1L;
	
	@Override
	public void beforeUpdate() {
		this.yaw = Wrapper.getPlayer().rotationYaw;
		this.pitch = Wrapper.getPlayer().rotationPitch;
		this.yawHead = Wrapper.getPlayer().rotationYawHead;
	}
	
	@Override
	public void tick() {
		currentMS = System.nanoTime() / 1000000;
		if(hasDelayRun((long)(1000 / 8)))
		{
			for(Object o: Wrapper.getWorld().loadedEntityList)
			{
				try
				{
					Entity e = (Entity) o;
					boolean checks = !(e instanceof EntityPlayerSP) && (e instanceof EntityLivingBase) && Wrapper.getPlayer().getDistanceToEntity(e) <= 4 && Wrapper.getPlayer().canEntityBeSeen(e) && !e.isDead;
	
					if(e instanceof EntityPlayer) 
					{
						EntityPlayer ep = (EntityPlayer) o;
					}
				
					if(checks) 
					{
						Wrapper.getPlayer().setSprinting(false);
						faceEntity(e);
						Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
						Wrapper.getMinecraft().playerController.attackEntity(Wrapper.getPlayer(), e);
						lastMS = System.nanoTime() / 1000000;
						break;
					}
				}catch(Exception e) {}
			}
		}
	}
	
	@Override
	public void afterUpdate() {
		Wrapper.getPlayer().rotationYaw = this.yaw;
		Wrapper.getPlayer().rotationPitch = this.pitch;
		Wrapper.getPlayer().rotationYawHead = this.yawHead;
	}
	
	public boolean hasDelayRun(long time) {
		return (currentMS - lastMS) >= time;
	}
	
	public void faceEntity(Entity entity)
    {
		double x = entity.posX - Wrapper.getPlayer().posX;
		double z = entity.posZ - Wrapper.getPlayer().posZ;
		double y = entity.posY + (entity.getEyeHeight()/1.4D) - Wrapper.getPlayer().posY + (Wrapper.getPlayer().getEyeHeight()/1.4D);
		double helper = MathHelper.sqrt(x * x + z * z);

		float newYaw = (float)((Math.toDegrees(-Math.atan(x / z))));
		float newPitch = (float)-Math.toDegrees(Math.atan(y / helper));

		if(z < 0 && x < 0) { newYaw = (float)(90D + Math.toDegrees(Math.atan(z / x))); }
		else if(z < 0 && x > 0) { newYaw = (float)(-90D + Math.toDegrees(Math.atan(z / x))); }

		Wrapper.getPlayer().rotationYaw = newYaw; 
		Wrapper.getPlayer().rotationPitch = newPitch;
		Wrapper.getPlayer().rotationYawHead = newPitch;
    }


	@Override
	public int getKeyCode() {
		return Keyboard.KEY_K;
	}

	@Override
	public String getName() {
		return "KillAura";
	}

	@Override
	public String getDescription() {
		return "what do you fucking think?";
	}
}
