package com.darkcart.xdolf.mods.aura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;

import com.darkcart.xdolf.Client;
import com.darkcart.xdolf.Module;
import com.darkcart.xdolf.Wrapper;
import com.darkcart.xdolf.util.Category;
import com.darkcart.xdolf.util.EntityUtils;

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
	
	public KillAura() {
		super("KillAura", "What do you fucking think?", Keyboard.KEY_R, 0xFFFFFF, Category.AURA);
	}
	
	private float yaw, pitch, yawHead;

	private long currentMS = 0L;
	private long lastMS = -1L;
	
	@Override
	public void beforeUpdate(EntityPlayerSP player) {
		try {
			this.yaw = player.rotationYaw;
			this.pitch = player.rotationPitch;
			this.yawHead = player.rotationYawHead;
		}catch(Exception ex){ /* stop crash when leaving server with aura on */ }
	}
	
	@Override
	public void onUpdate(EntityPlayerSP player) {
		if(isEnabled()) {
			try {
				currentMS = System.nanoTime() / 1000000;
				if(hasDelayRun((long)(1000 / 8)))
				{
					for(Object o: Wrapper.getWorld().loadedEntityList)
					{
						Entity e = (Entity) o;
						boolean checks = !(e instanceof EntityPlayerSP) && (e instanceof EntityLivingBase) && player.getDistanceToEntity(e) <= 4 && player.canEntityBeSeen(e) && !e.isDead;
			
						if(e instanceof EntityPlayer) 
						{
							EntityPlayer ep = (EntityPlayer) o;
						}
						
						if(checks) 
						{
							player.setSprinting(false);
							faceEntity(e);
							player.swingArm(EnumHand.MAIN_HAND);
							Wrapper.getMinecraft().playerController.attackEntity(Wrapper.getPlayer(), e);
							lastMS = System.nanoTime() / 1000000;
							break;
						}
					}
				}
			}catch(Exception ex){}
		}
	}
	
	@Override
	public void afterUpdate(EntityPlayerSP player) {
		try {
			player.rotationYaw = this.yaw;
			player.rotationPitch = this.pitch;
			player.rotationYawHead = this.yawHead;
		}catch(Exception ex){}
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
}
