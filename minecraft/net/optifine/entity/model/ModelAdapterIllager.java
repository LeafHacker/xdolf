package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelAdapterIllager extends ModelAdapter
{
    public ModelAdapterIllager(Class entityClass, String name, float shadowSize)
    {
        super(entityClass, name, shadowSize);
    }

    public ModelRenderer getModelRenderer(ModelBase model, String modelPart)
    {
        if (!(model instanceof ModelIllager))
        {
            return null;
        }
        else
        {
            ModelIllager modelillager = (ModelIllager)model;
            return modelPart.equals("head") ? modelillager.head : (modelPart.equals("body") ? modelillager.body : (modelPart.equals("arms") ? modelillager.arms : (modelPart.equals("left_leg") ? modelillager.leg1 : (modelPart.equals("right_leg") ? modelillager.leg0 : (modelPart.equals("nose") ? modelillager.nose : (modelPart.equals("left_arm") ? modelillager.leftArm : (modelPart.equals("right_arm") ? modelillager.rightArm : null)))))));
        }
    }
}
