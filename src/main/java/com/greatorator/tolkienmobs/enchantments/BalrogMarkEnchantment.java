package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class BalrogMarkEnchantment extends BaseEnchantment {
    public BalrogMarkEnchantment(Enchantment.Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.ARMOR_FEET, slots);
    }

    @Override
    public boolean isEnabled() {
        return TolkienConfig.disableBalrogMark;
    }

    @Override
    public boolean isTradeable() {
        return isEnabled() && super.isTradeable();
    }

    @Override
    public boolean isDiscoverable() {
        return isEnabled() && super.isDiscoverable();
    }

    @Override
    public boolean isAllowedOnBooks() {
        return isEnabled() && super.isAllowedOnBooks();
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return isEnabled() && (stack.getItem() instanceof ArmorItem) && ((ArmorItem) stack.getItem()).getSlot() == EquipmentSlot.FEET;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench) && ench != Enchantments.DEPTH_STRIDER && ench != Enchantments.FROST_WALKER;
    }

    @SubscribeEvent
    public static void balrogMark (LivingEvent.LivingUpdateEvent living){
        LivingEntity entity = living.getEntityLiving();
        BlockPos pos = entity.blockPosition();
        Level worldIn = living.getEntity().level;

        int level = EnchantmentHelper.getEnchantmentLevel(TolkienEnchants.BALROG_MARK.get(), entity);

        if (entity.isOnGround() && level > 0) {
            BlockState blockstate = Blocks.MAGMA_BLOCK.defaultBlockState();
            float f = (float)Math.min(16, 2 + level);
            BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset((double)(-f), -1.0D, (double)(-f)), pos.offset((double)f, -1.0D, (double)f))) {
                if (blockpos.closerToCenterThan(entity.position(), (double)f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = worldIn.getBlockState(blockpos);
                        if (blockstate2.getBlock() == Blocks.GRASS_BLOCK || blockstate2.getBlock() == Blocks.DIRT && worldIn.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(entity, net.minecraftforge.common.util.BlockSnapshot.create(worldIn.dimension(), worldIn, blockpos), Direction.DOWN)) {
                            worldIn.setBlockAndUpdate(blockpos, blockstate);
                            worldIn.scheduleTick(blockpos, Blocks.MAGMA_BLOCK, Mth.nextInt(entity.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }
    }
}