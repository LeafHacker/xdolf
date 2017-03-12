package com.darkcart.xdolf.util;

import com.darkcart.xdolf.Wrapper;
import com.mojang.authlib.GameProfile;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.MoverType;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;

public class FreecamEntity extends EntityOtherPlayerMP {
	
    private MovementInput movementInput;
    
    public FreecamEntity(final World worldObj, GameProfile par2Str){
        super(worldObj, par2Str);
        this.setPositionAndRotation(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY, Wrapper.getPlayer().posZ, Wrapper.getPlayer().rotationYaw, Wrapper.getPlayer().rotationPitch);
        this.stepHeight = Wrapper.getPlayer().stepHeight;
        this.noClip = true;
        this.capabilities.isFlying = true;
        Wrapper.getWorld().spawnEntityInWorld(this);
    }
   
    @Override
    public void moveEntity(MoverType par1, final double x, final double y, final double z){
        this.onGround = true;
        super.moveEntity(MoverType.SELF, x, y, z);
        this.onGround = true;
    }
   
    public void setMovementInput(final MovementInput par1MovementInput){
        this.movementInput = par1MovementInput;
        this.moveEntityWithHeading(par1MovementInput.moveStrafe, par1MovementInput.moveForward);
    }
}
