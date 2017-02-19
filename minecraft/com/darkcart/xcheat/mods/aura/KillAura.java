package com.darkcart.xcheat.mods.aura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;

import com.darkcart.xcheat.Client;
import com.darkcart.xcheat.Module;
import com.darkcart.xcheat.util.EntityUtils;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

public class KillAura extends Module {

	public static int speed = 1;
	private Timer t;

	public KillAura() {
		t = new Timer(speed, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Iterator<Entity> entities = Client.mc.world.loadedEntityList.iterator(); entities.hasNext();) {
					Object theObject = entities.next();
					if ((theObject instanceof EntityLivingBase)) {
						EntityLivingBase entity = (EntityLivingBase) theObject;
						if (!(entity instanceof EntityPlayerSP)) {
							if ((!(entity instanceof EntityPlayer)) || (!Client.friends.contains(entity.getName()))) {
								if (!entity.isInvisible()) {
									if ((Client.mc.player.getDistanceToEntity(entity) <= 6.2173615F)
											&& (entity.isEntityAlive())) {
										EntityUtils.faceEntityClient(entity);
										Client.mc.playerController.attackEntity(Client.mc.player, entity);
										Client.mc.player.swingArm(EnumHand.MAIN_HAND);
									}
								}
							}
						}
					}
				}
			}
		});
	}

	@Override
	public void enable() {
		t.start();
	}

	@Override
	public void disable() {
		t.stop();
	}
	
	@Override
	public void beforeUpdate() {
		
	}

	@Override
	public void tick() {

	}
	
	@Override
	public void afterUpdate() {
		
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
