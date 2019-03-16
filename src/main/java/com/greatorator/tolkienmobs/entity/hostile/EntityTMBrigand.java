package com.greatorator.tolkienmobs.entity.hostile;

import com.greatorator.tolkienmobs.entity.EntityTMHostiles;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTMBrigand extends EntityTMHostiles {
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;
    private EntityLivingBase mob;

    public EntityTMBrigand(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 2.0F);
        this.setWeaponType(Items.IRON_SWORD);
        this.setLootTable(LootInit.BRIGAND);
        this.setMob(this);
        this.setMobMentality(true, SoundInit.soundAngryGoblin);
        this.setRndMinMax(1,11);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        EntityPlayer player = (EntityPlayer)damageSource.getTrueSource();
        ItemStack weapon = player != null ? player.inventory.getStackInSlot(player.inventory.currentItem) : null;

        if (weapon != null)
        {
            long time = System.currentTimeMillis();
            if (time > nextAbilityUse
                    && damageSource.getTrueSource() != null
                    && !(damageSource instanceof EntityDamageSourceIndirect))
            {
                nextAbilityUse = time+coolDown;
                EntityItem drop = player.dropItem(player.inventory.decrStackSize(player.inventory.currentItem, 1), false);

                if (drop != null)
                {
                    drop.setPickupDelay(50);
                    mob.world.playSound(null, new BlockPos(mob), SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.HOSTILE, 1.0F + mob.getRNG().nextFloat(), mob.getRNG().nextFloat() * 0.7F + 0.3F);
                }
            }
        }
        return super.attackEntityFrom(damageSource, damage);
    }

    @Override
    public double getAttackDamage() {
        return 6;
    }

    @Override
    public double getArmorStrength() {
        return 3;
    }

    @Override
    public double getHealthLevel() {
        return 26;
    }

    public void setMob(EntityLivingBase mob) {
        this.mob = mob;
    }
}