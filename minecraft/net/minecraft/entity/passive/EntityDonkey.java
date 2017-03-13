package net.minecraft.entity.passive;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityDonkey extends AbstractChestHorse
{
    public EntityDonkey(World worldIn)
    {
        super(worldIn);
    }

    public static void registerFixesDonkey(DataFixer fixer)
    {
        AbstractChestHorse.registerFixesAbstractChestHorse(fixer, EntityDonkey.class);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_DONKEY;
    }

    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return SoundEvents.ENTITY_DONKEY_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return SoundEvents.ENTITY_DONKEY_DEATH;
    }

    protected SoundEvent getHurtSound()
    {
        super.getHurtSound();
        return SoundEvents.ENTITY_DONKEY_HURT;
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        return otherAnimal == this ? false : (!(otherAnimal instanceof EntityDonkey) && !(otherAnimal instanceof EntityHorse) ? false : this.canMate() && ((AbstractHorse)otherAnimal).canMate());
    }

    public EntityAgeable createChild(EntityAgeable ageable)
    {
        AbstractHorse abstracthorse = (AbstractHorse)(ageable instanceof EntityHorse ? new EntityMule(this.world) : new EntityDonkey(this.world));
        this.setOffspringAttributes(ageable, abstracthorse);
        return abstracthorse;
    }
}
