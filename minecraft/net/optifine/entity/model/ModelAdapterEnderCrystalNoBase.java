package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.src.Config;
import net.minecraft.src.Reflector;

public class ModelAdapterEnderCrystalNoBase extends ModelAdapterEnderCrystal
{
    public ModelAdapterEnderCrystalNoBase()
    {
        super("end_crystal_no_base");
    }

    public ModelBase makeModel()
    {
        return new ModelEnderCrystal(0.0F, false);
    }

    public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize)
    {
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        RenderEnderCrystal renderendercrystal = new RenderEnderCrystal(rendermanager);

        if (!Reflector.RenderEnderCrystal_modelEnderCrystalNoBase.exists())
        {
            Config.warn("Field not found: RenderEnderCrystal.modelEnderCrystalNoBase");
            return null;
        }
        else
        {
            Reflector.setFieldValue(renderendercrystal, Reflector.RenderEnderCrystal_modelEnderCrystalNoBase, modelBase);
            renderendercrystal.shadowSize = shadowSize;
            return renderendercrystal;
        }
    }
}
