package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityEvoker extends EntityMob
{
    protected static final DataParameter<Byte> DATA_SPELL_CASTING_ID = EntityDataManager.<Byte>createKey(EntityEvoker.class, DataSerializers.BYTE);
    private int spellCastingTickCount;
    private int spellCastId;
    private EntitySheep wololoTarget;

    public EntityEvoker(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.experienceValue = 10;
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityEvoker.AICastingSpell());
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.6D, 1.0D));
        this.tasks.addTask(4, new EntityEvoker.AISummonSpell());
        this.tasks.addTask(5, new EntityEvoker.AIAttackSpell());
        this.tasks.addTask(6, new EntityEvoker.AIWololoSpell());
        this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityEvoker.class}));
        this.targetTasks.addTask(2, (new EntityAINearestAttackableTarget(this, EntityPlayer.class, true)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, (new EntityAINearestAttackableTarget(this, EntityVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(DATA_SPELL_CASTING_ID, Byte.valueOf((byte)0));
    }

    public static void registerFixesEvoker(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityEvoker.class);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.spellCastingTickCount = compound.getInteger("SpellTicks");
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("SpellTicks", this.spellCastingTickCount);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ILLAGER;
    }

    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_EVOCATION_ILLAGER;
    }

    public boolean isCastingSpell()
    {
        return this.world.isRemote ? ((Byte)this.dataManager.get(DATA_SPELL_CASTING_ID)).byteValue() > 0 : this.spellCastingTickCount > 0;
    }

    public void setIsCastingSpell(int p_190753_1_)
    {
        this.dataManager.set(DATA_SPELL_CASTING_ID, Byte.valueOf((byte)p_190753_1_));
    }

    private int getSpellCastingTime()
    {
        return this.spellCastingTickCount;
    }

    protected void updateAITasks()
    {
        super.updateAITasks();

        if (this.spellCastingTickCount > 0)
        {
            --this.spellCastingTickCount;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.world.isRemote && this.isCastingSpell())
        {
            int i = ((Byte)this.dataManager.get(DATA_SPELL_CASTING_ID)).byteValue();
            double d0 = 0.7D;
            double d1 = 0.5D;
            double d2 = 0.2D;

            if (i == 2)
            {
                d0 = 0.4D;
                d1 = 0.3D;
                d2 = 0.35D;
            }
            else if (i == 1)
            {
                d0 = 0.7D;
                d1 = 0.7D;
                d2 = 0.8D;
            }

            float f = this.renderYawOffset * 0.017453292F + MathHelper.cos((float)this.ticksExisted * 0.6662F) * 0.25F;
            float f1 = MathHelper.cos(f);
            float f2 = MathHelper.sin(f);
            this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (double)f1 * 0.6D, this.posY + 1.8D, this.posZ + (double)f2 * 0.6D, d0, d1, d2, new int[0]);
            this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX - (double)f1 * 0.6D, this.posY + 1.8D, this.posZ - (double)f2 * 0.6D, d0, d1, d2, new int[0]);
        }
    }

    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isOnSameTeam(Entity entityIn)
    {
        return entityIn == null ? false : (entityIn == this ? true : (super.isOnSameTeam(entityIn) ? true : (entityIn instanceof EntityVex ? this.isOnSameTeam(((EntityVex)entityIn).getOwner()) : (entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getCreatureAttribute() == EnumCreatureAttribute.ILLAGER ? this.getTeam() == null && entityIn.getTeam() == null : false))));
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_EVOCATION_ILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.EVOCATION_ILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.ENTITY_EVOCATION_ILLAGER_HURT;
    }

    private void setWololoTarget(@Nullable EntitySheep p_190748_1_)
    {
        this.wololoTarget = p_190748_1_;
    }

    @Nullable
    private EntitySheep getWololoTarget()
    {
        return this.wololoTarget;
    }

    class AIAttackSpell extends EntityEvoker.AIUseSpell
    {
        private AIAttackSpell()
        {
        }

        protected int getCastingTime()
        {
            return 40;
        }

        protected int getCastingInterval()
        {
            return 100;
        }

        protected void castSpell()
        {
            EntityLivingBase entitylivingbase = EntityEvoker.this.getAttackTarget();
            double d0 = Math.min(entitylivingbase.posY, EntityEvoker.this.posY);
            double d1 = Math.max(entitylivingbase.posY, EntityEvoker.this.posY) + 1.0D;
            float f = (float)MathHelper.atan2(entitylivingbase.posZ - EntityEvoker.this.posZ, entitylivingbase.posX - EntityEvoker.this.posX);

            if (EntityEvoker.this.getDistanceSqToEntity(entitylivingbase) < 9.0D)
            {
                for (int i = 0; i < 5; ++i)
                {
                    float f1 = f + (float)i * (float)Math.PI * 0.4F;
                    this.spawnFangs(EntityEvoker.this.posX + (double)MathHelper.cos(f1) * 1.5D, EntityEvoker.this.posZ + (double)MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for (int k = 0; k < 8; ++k)
                {
                    float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + ((float)Math.PI * 2F / 5F);
                    this.spawnFangs(EntityEvoker.this.posX + (double)MathHelper.cos(f2) * 2.5D, EntityEvoker.this.posZ + (double)MathHelper.sin(f2) * 2.5D, d0, d1, f2, 3);
                }
            }
            else
            {
                for (int l = 0; l < 16; ++l)
                {
                    double d2 = 1.25D * (double)(l + 1);
                    int j = 1 * l;
                    this.spawnFangs(EntityEvoker.this.posX + (double)MathHelper.cos(f) * d2, EntityEvoker.this.posZ + (double)MathHelper.sin(f) * d2, d0, d1, f, j);
                }
            }
        }

        private void spawnFangs(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_)
        {
            BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
            boolean flag = false;
            double d0 = 0.0D;

            while (true)
            {
                if (!EntityEvoker.this.world.isBlockNormalCube(blockpos, true) && EntityEvoker.this.world.isBlockNormalCube(blockpos.down(), true))
                {
                    if (!EntityEvoker.this.world.isAirBlock(blockpos))
                    {
                        IBlockState iblockstate = EntityEvoker.this.world.getBlockState(blockpos);
                        AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(EntityEvoker.this.world, blockpos);

                        if (axisalignedbb != null)
                        {
                            d0 = axisalignedbb.maxY;
                        }
                    }

                    flag = true;
                    break;
                }

                blockpos = blockpos.down();

                if (blockpos.getY() < MathHelper.floor(p_190876_5_) - 1)
                {
                    break;
                }
            }

            if (flag)
            {
                EntityEvokerFangs entityevokerfangs = new EntityEvokerFangs(EntityEvoker.this.world, p_190876_1_, (double)blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, EntityEvoker.this);
                EntityEvoker.this.world.spawnEntity(entityevokerfangs);
            }
        }

        protected SoundEvent getSpellPrepareSound()
        {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_ATTACK;
        }

        protected int getSpellId()
        {
            return 2;
        }
    }

    class AICastingSpell extends EntityAIBase
    {
        public AICastingSpell()
        {
            this.setMutexBits(3);
        }

        public boolean shouldExecute()
        {
            return EntityEvoker.this.getSpellCastingTime() > 0;
        }

        public void startExecuting()
        {
            super.startExecuting();
            EntityEvoker.this.setIsCastingSpell(EntityEvoker.this.spellCastId);
            EntityEvoker.this.navigator.clearPathEntity();
        }

        public void resetTask()
        {
            super.resetTask();
            EntityEvoker.this.setIsCastingSpell(0);
        }

        public void updateTask()
        {
            if (EntityEvoker.this.getAttackTarget() != null)
            {
                EntityEvoker.this.getLookHelper().setLookPositionWithEntity(EntityEvoker.this.getAttackTarget(), (float)EntityEvoker.this.getHorizontalFaceSpeed(), (float)EntityEvoker.this.getVerticalFaceSpeed());
            }
            else if (EntityEvoker.this.getWololoTarget() != null)
            {
                EntityEvoker.this.getLookHelper().setLookPositionWithEntity(EntityEvoker.this.getWololoTarget(), (float)EntityEvoker.this.getHorizontalFaceSpeed(), (float)EntityEvoker.this.getVerticalFaceSpeed());
            }
        }
    }

    class AISummonSpell extends EntityEvoker.AIUseSpell
    {
        private AISummonSpell()
        {
        }

        public boolean shouldExecute()
        {
            if (!super.shouldExecute())
            {
                return false;
            }
            else
            {
                int i = EntityEvoker.this.world.getEntitiesWithinAABB(EntityVex.class, EntityEvoker.this.getEntityBoundingBox().expandXyz(16.0D)).size();
                return EntityEvoker.this.rand.nextInt(8) + 1 > i;
            }
        }

        protected int getCastingTime()
        {
            return 100;
        }

        protected int getCastingInterval()
        {
            return 340;
        }

        protected void castSpell()
        {
            for (int i = 0; i < 3; ++i)
            {
                BlockPos blockpos = (new BlockPos(EntityEvoker.this)).add(-2 + EntityEvoker.this.rand.nextInt(5), 1, -2 + EntityEvoker.this.rand.nextInt(5));
                EntityVex entityvex = new EntityVex(EntityEvoker.this.world);
                entityvex.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
                entityvex.onInitialSpawn(EntityEvoker.this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
                entityvex.setOwner(EntityEvoker.this);
                entityvex.setBoundOrigin(blockpos);
                entityvex.setLimitedLife(20 * (30 + EntityEvoker.this.rand.nextInt(90)));
                EntityEvoker.this.world.spawnEntity(entityvex);
            }
        }

        protected SoundEvent getSpellPrepareSound()
        {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_SUMMON;
        }

        protected int getSpellId()
        {
            return 1;
        }
    }

    abstract class AIUseSpell extends EntityAIBase
    {
        protected int spellWarmup;
        protected int nextCastTime;

        private AIUseSpell()
        {
        }

        public boolean shouldExecute()
        {
            return EntityEvoker.this.getAttackTarget() == null ? false : (EntityEvoker.this.isCastingSpell() ? false : EntityEvoker.this.ticksExisted >= this.nextCastTime);
        }

        public boolean continueExecuting()
        {
            return EntityEvoker.this.getAttackTarget() != null && this.spellWarmup > 0;
        }

        public void startExecuting()
        {
            this.spellWarmup = this.getCastWarmupTime();
            EntityEvoker.this.spellCastingTickCount = this.getCastingTime();
            this.nextCastTime = EntityEvoker.this.ticksExisted + this.getCastingInterval();
            EntityEvoker.this.playSound(this.getSpellPrepareSound(), 1.0F, 1.0F);
            EntityEvoker.this.spellCastId = this.getSpellId();
        }

        public void updateTask()
        {
            --this.spellWarmup;

            if (this.spellWarmup == 0)
            {
                this.castSpell();
                EntityEvoker.this.playSound(SoundEvents.EVOCATION_ILLAGER_CAST_SPELL, 1.0F, 1.0F);
            }
        }

        protected abstract void castSpell();

        protected int getCastWarmupTime()
        {
            return 20;
        }

        protected abstract int getCastingTime();

        protected abstract int getCastingInterval();

        protected abstract SoundEvent getSpellPrepareSound();

        protected abstract int getSpellId();
    }

    public class AIWololoSpell extends EntityEvoker.AIUseSpell
    {
        final Predicate<EntitySheep> wololoSelector = new Predicate<EntitySheep>()
        {
            public boolean apply(EntitySheep p_apply_1_)
            {
                return p_apply_1_.getFleeceColor() == EnumDyeColor.BLUE;
            }
        };

        public AIWololoSpell()
        {
        }

        public boolean shouldExecute()
        {
            if (EntityEvoker.this.getAttackTarget() != null)
            {
                return false;
            }
            else if (EntityEvoker.this.isCastingSpell())
            {
                return false;
            }
            else if (EntityEvoker.this.ticksExisted < this.nextCastTime)
            {
                return false;
            }
            else if (!EntityEvoker.this.world.getGameRules().getBoolean("mobGriefing"))
            {
                return false;
            }
            else
            {
                List<EntitySheep> list = EntityEvoker.this.world.<EntitySheep>getEntitiesWithinAABB(EntitySheep.class, EntityEvoker.this.getEntityBoundingBox().expand(16.0D, 4.0D, 16.0D), this.wololoSelector);

                if (list.isEmpty())
                {
                    return false;
                }
                else
                {
                    EntityEvoker.this.setWololoTarget((EntitySheep)list.get(EntityEvoker.this.rand.nextInt(list.size())));
                    return true;
                }
            }
        }

        public boolean continueExecuting()
        {
            return EntityEvoker.this.getWololoTarget() != null && this.spellWarmup > 0;
        }

        public void resetTask()
        {
            super.resetTask();
            EntityEvoker.this.setWololoTarget((EntitySheep)null);
        }

        protected void castSpell()
        {
            EntitySheep entitysheep = EntityEvoker.this.getWololoTarget();

            if (entitysheep != null && entitysheep.isEntityAlive())
            {
                entitysheep.setFleeceColor(EnumDyeColor.RED);
            }
        }

        protected int getCastWarmupTime()
        {
            return 40;
        }

        protected int getCastingTime()
        {
            return 60;
        }

        protected int getCastingInterval()
        {
            return 140;
        }

        protected SoundEvent getSpellPrepareSound()
        {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_WOLOLO;
        }

        protected int getSpellId()
        {
            return 3;
        }
    }
}
