package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.EnchantmentsInit;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid= TolkienMobs.MODID)
public class TTMEnchantHandler {
    public static UUID resolveUUID = UUID.fromString("838FDDBA-2270-4EE2-82FB-29883B38E330");
    public static UUID longevityUUID = UUID.fromString("E08A038A-BF0D-425A-8897-97A40F7E402F");

    /* Balrog Step Enchant */
    @SubscribeEvent
    public void burningGround(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.BALROG_MARK, living);
        BlockPos pos = living.getPosition();
        World worldIn = event.getEntity().world;

        if (living.onGround && living instanceof EntityPlayer && level !=0)
        {
            float f = (float)Math.min(16, 2 + level);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);

            for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f)))
            {
                if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double)(f * f))
                {
                    blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);

                    if (iblockstate.getMaterial() == Material.AIR)
                    {
                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1);

                        if (iblockstate1.getBlock() == Blocks.GRASS || iblockstate1.getBlock() == Blocks.DIRT && worldIn.mayPlace(Blocks.MAGMA, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null))
                        {
                            worldIn.setBlockState(blockpos$mutableblockpos1, Blocks.MAGMA.getDefaultState());
                            worldIn.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), Blocks.MAGMA, MathHelper.getInt(living.getRNG(), 60, 120));
                        }
                    }
                }
            }
        }
    }

    /* Gondorian Resolve Enchant */
    @SubscribeEvent
    public void gondorianResolve(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.GONDOR_RESOLVE, living);

        if(living instanceof EntityPlayer && level > 0){
            AddResolve(living, level);
        }
        else{
            RemoveResolve(living, level);
        }
    }

    private void AddResolve(EntityLivingBase entity, int level)
    {
        IAttributeInstance battleResolve = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);

        if(battleResolve.getModifier(resolveUUID) != null)
            return;

        AttributeModifier modResolve = new AttributeModifier(resolveUUID, "Resolve", 0.20D, level);

        // Creatures and Players use 2 different values here
        battleResolve.removeModifier(modResolve);
        battleResolve.applyModifier(modResolve);
    }

    private void RemoveResolve(EntityLivingBase entity, int level)
    {
        IAttributeInstance battleResolve = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);

        if(battleResolve.getModifier(resolveUUID) == null)
            return;

        AttributeModifier modResolve = new AttributeModifier(resolveUUID, "Resolve", 0.20D, level);
        battleResolve.removeModifier(modResolve);
    }

    /* Hobbit Harvest Enchant */
    @SubscribeEvent
    public void hobbitHarvest(BlockEvent.HarvestDropsEvent event)
    {
        if (event.getHarvester() == null){
            return;
        }

        ItemStack harvestingItem = event.getHarvester().inventory.getStackInSlot(event.getHarvester().inventory.currentItem);
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentsInit.HOBBIT_HARVEST, harvestingItem);

        List<ItemStack> dropList = new ArrayList();
        //LogHelperTTM.info("DropListB:"+ event.getDrops().size());
        Iterator<ItemStack> it = event.getDrops().iterator();
        ItemStack item;

        if(harvestingItem.getItem() instanceof ItemHoe && event.getHarvester() != null) {
            if (event.getState().getBlock() instanceof BlockCrops && level > 0) {
                while(it.hasNext())
                {
                    item = it.next();
                    //LogHelperTTM.info("increase items:"+ item.getDisplayName());
                    for (int i = 0; i <= level; i++)
                    {
                        dropList.add(item.copy());
                    }
                }
                event.getDrops().addAll(dropList);
                //LogHelperTTM.info("DropListA:"+ event.getDrops().size());
            }
        }
    }

    /* Elven Longevity Enchant */
    @SubscribeEvent
    public void elvenLongevity(LivingEvent.LivingUpdateEvent event)
    {
        EntityLivingBase living = event.getEntityLiving();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.ELVEN_LONGEVITY, living);

        if(living instanceof EntityPlayer && level > 0){
            AddLongevity(living, level);
        }
        else{
            RemoveLongevity(living, level);
        }
    }

    private void AddLongevity(EntityLivingBase entity, int level)
    {
        IAttributeInstance elvenLongevity = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

        if(elvenLongevity.getModifier(longevityUUID) != null)
            return;

        AttributeModifier modLongevity = new AttributeModifier(longevityUUID, "Longevity", level * 0.2, 1);

        elvenLongevity.applyModifier(modLongevity);
    }

    private void RemoveLongevity(EntityLivingBase entity, int level)
    {
        IAttributeInstance elvenLongevity = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

        if(elvenLongevity.getModifier(longevityUUID) == null)
            return;

        AttributeModifier modLongevity = new AttributeModifier(longevityUUID, "Longevity", level * 0.2, 1);

        if(entity.getMaxHealth() < entity.getHealth()) entity.setHealth(entity.getMaxHealth());
        elvenLongevity.removeModifier(modLongevity);
    }

    /* Dwarven Mining Enchant */
}