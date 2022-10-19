package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class EnchantmentHobbitPlow extends Enchantment {
    public EnchantmentHobbitPlow(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.DIGGER, slots);
    }
    @Override
    public int getMinCost(int enchantmentLevel) {
        return 10 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 10;
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        if(stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE))
        {
            return (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE));
        }

        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if(stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE))
        {
            return (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE));
        }

        return false;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @SubscribeEvent
    public static void hobbitPlow(BlockEvent.BlockToolInteractEvent event) {
        PlayerEntity player = event.getPlayer();
        World world = (World) event.getWorld();
        BlockPos blockPos = event.getPos();
        Block targetBlock = event.getWorld().getBlockState(blockPos).getBlock();
        BlockState state = event.getWorld().getBlockState(blockPos);
        ItemStack holding = event.getHeldItemStack();
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_PLOW.get(), holding);

        if (holding.isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_PLOW.get(), holding) > 0 && !world.isClientSide()) {
            if (targetBlock == Blocks.GRASS_BLOCK || targetBlock == Blocks.DIRT || targetBlock == Blocks.PODZOL) {
                world.playSound(player, blockPos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClientSide() && !player.isCreative()) {
                    player.getItemInHand(Hand.MAIN_HAND).hurtAndBreak((enchantmentLevel * 2) + 1, player, entity -> entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
                }

                BlockPos waterPos = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                world.setBlockAndUpdate(waterPos, Blocks.WATER.defaultBlockState());

                for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                    for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                        BlockPos targetPos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                        if (world.isEmptyBlock(targetPos.above()) && !world.isWaterAt(targetPos.below()) || !world.isEmptyBlock(targetPos.below())) {
                            world.setBlockAndUpdate(targetPos, Blocks.FARMLAND.defaultBlockState());
                        }
                    }
                }
            } else if (targetBlock instanceof FarmlandBlock && holding.isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_PLOW.get(), holding) > 0) {
                for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                    for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                        Random random = new Random();
                        BlockPos targetPos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                        double d0 = targetPos.offset(x, 0, z).getX() + world.getRandom().nextFloat();
                        double d1 = targetPos.offset(x, 0, z).getY() + 1.0D;
                        double d2 = targetPos.offset(x, 0, z).getZ() + world.getRandom().nextFloat();
                        world.playSound(player, blockPos, SoundEvents.WEATHER_RAIN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                        if (world.isEmptyBlock(targetPos.above()) && !world.isWaterAt(targetPos.below())) {
                            LOGGER.info("Watering farmland");
                            world.setBlockAndUpdate(targetPos, state.setValue(FarmlandBlock.MOISTURE, 7));
                            world.addParticle(ParticleTypes.RAIN, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                            state.randomTick((ServerWorld) world, targetPos, random);
                        }
                    }
                }
            }
        }
    }
}