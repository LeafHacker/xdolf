package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.util.math.MathHelper;

public class ModelEvoker extends ModelIllager
{
    public ModelEvoker(float p_i47228_1_)
    {
        super(p_i47228_1_, 0.0F, 64, 64);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        EntityEvoker entityevoker = (EntityEvoker)entityIn;

        if (entityevoker.isCastingSpell())
        {
            this.rightArm.render(scale);
            this.leftArm.render(scale);
        }
        else
        {
            this.arms.render(scale);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.rightArm.rotationPointZ = 0.0F;
        this.rightArm.rotationPointX = -5.0F;
        this.leftArm.rotationPointZ = 0.0F;
        this.leftArm.rotationPointX = 5.0F;
        this.rightArm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
        this.leftArm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
        this.rightArm.rotateAngleZ = 2.3561945F;
        this.leftArm.rotateAngleZ = -2.3561945F;
        this.rightArm.rotateAngleY = 0.0F;
        this.leftArm.rotateAngleY = 0.0F;
    }
}
