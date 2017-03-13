package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelIllager extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer arms;
    public ModelRenderer leg0;
    public ModelRenderer leg1;
    public ModelRenderer nose;
    public ModelRenderer rightArm;
    public ModelRenderer leftArm;

    public ModelIllager(float p_i47227_1_, float p_i47227_2_, int p_i47227_3_, int p_i47227_4_)
    {
        this.head = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.head.setRotationPoint(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i47227_1_);
        this.nose = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.nose.setRotationPoint(0.0F, p_i47227_2_ - 2.0F, 0.0F);
        this.nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, p_i47227_1_);
        this.head.addChild(this.nose);
        this.body = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.body.setRotationPoint(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.body.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i47227_1_);
        this.body.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i47227_1_ + 0.5F);
        this.arms = (new ModelRenderer(this)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.arms.setRotationPoint(0.0F, 0.0F + p_i47227_2_ + 2.0F, 0.0F);
        this.arms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
        this.arms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
        this.arms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i47227_1_);
        this.leg0 = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leg0.setRotationPoint(-2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.leg1 = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leg1.mirror = true;
        this.leg1.setRotationPoint(2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.rightArm = (new ModelRenderer(this, 40, 46)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.rightArm.setRotationPoint(-5.0F, 2.0F + p_i47227_2_, 0.0F);
        this.leftArm = (new ModelRenderer(this, 40, 46)).setTextureSize(p_i47227_3_, p_i47227_4_);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
        this.leftArm.setRotationPoint(5.0F, 2.0F + p_i47227_2_, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.head.render(scale);
        this.body.render(scale);
        this.leg0.render(scale);
        this.leg1.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.arms.rotationPointY = 3.0F;
        this.arms.rotationPointZ = -1.0F;
        this.arms.rotateAngleX = -0.75F;
        this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.leg0.rotateAngleY = 0.0F;
        this.leg1.rotateAngleY = 0.0F;
    }

    public ModelRenderer getArm(EnumHandSide p_191216_1_)
    {
        return p_191216_1_ == EnumHandSide.LEFT ? this.leftArm : this.rightArm;
    }
}
