package net.minecraft.client.renderer.debug;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class DebugRendererCollisionBox implements DebugRenderer.IDebugRenderer
{
    private final Minecraft mc;
    private EntityPlayer player;
    private double renderPosX;
    private double renderPosY;
    private double renderPosZ;

    public DebugRendererCollisionBox(Minecraft p_i47215_1_)
    {
        this.mc = p_i47215_1_;
    }

    public void render(float p_190060_1_, long p_190060_2_)
    {
        this.player = this.mc.player;
        this.renderPosX = this.player.lastTickPosX + (this.player.posX - this.player.lastTickPosX) * (double)p_190060_1_;
        this.renderPosY = this.player.lastTickPosY + (this.player.posY - this.player.lastTickPosY) * (double)p_190060_1_;
        this.renderPosZ = this.player.lastTickPosZ + (this.player.posZ - this.player.lastTickPosZ) * (double)p_190060_1_;
        World world = this.mc.player.world;
        List<AxisAlignedBB> list = world.getCollisionBoxes(this.player, this.player.getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(2.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);

        for (AxisAlignedBB axisalignedbb : list)
        {
            RenderGlobal.drawSelectionBoundingBox(axisalignedbb.expandXyz(0.002D).offset(-this.renderPosX, -this.renderPosY, -this.renderPosZ), 1.0F, 1.0F, 1.0F, 1.0F);
        }

        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
