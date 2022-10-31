package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.interfaces.IHobbitHarvest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class EnchantmentHobbitHarvest extends Enchantment {

    public EnchantmentHobbitHarvest(Rarity rarityIn, EquipmentSlotType... slots) {
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
        if (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE)) {
            return (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE));
        }

        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE)) {
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

    @SubscribeEvent (priority = EventPriority.HIGHEST)
    public static void hobbitHarvest(PlayerInteractEvent.RightClickBlock event) {

        ItemStack holding = event.getPlayer().getMainHandItem();
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_HARVEST.get(), holding);

        if (enchantmentLevel > 0 && holding.isEnchanted() && EnchantmentHelper.getEnchantments(holding).containsKey(EnchantmentGenerator.HOBBIT_HARVEST.get()) && !holding.isEmpty()) {
            World world = event.getWorld();
            PlayerEntity player = event.getPlayer();
            boolean replant = TTMConfig.replant;
            for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                    BlockPos blockPos = new BlockPos(event.getPos().getX() + x, event.getPos().getY(), event.getPos().getZ() + z);
                    BlockState state = world.getBlockState(blockPos);
                    Block block = state.getBlock();

                    if (block instanceof IHobbitHarvest) {
                        IHobbitHarvest harvestable = (IHobbitHarvest) block;
                        if (harvestable.canHarvest(state)) {
                            harvestable.harvest(world, blockPos, state, player, replant);
                            player.swing(Hand.MAIN_HAND);
                            event.setCanceled(true);
                        }
                    } else if (block instanceof CropsBlock) {
                        CropsBlock crop = (CropsBlock) block;
                        boolean seedDrop = false;
                        BlockPos below = blockPos.below();
                        BlockState belowState = world.getBlockState(below);
                        Block belowBlock = belowState.getBlock();

                        replant &= belowBlock.canSustainPlant(belowState, world, below, Direction.UP, crop);

                        if (crop.isMaxAge(state)) {
                            if (!world.isClientSide) {
                                List<ItemStack> drops = Block.getDrops(state, (ServerWorld) world, blockPos, null, player, player.getMainHandItem());
                                Item seedItem = crop.getCloneItemStack(world, blockPos, state).getItem();
                                for (ItemStack drop : drops) {
                                    if (replant && !seedDrop) {
                                        if (drop.getItem() == seedItem) {
                                            drop.shrink(1);
                                            seedDrop = true;
                                        }
                                    }
                                    if (!drop.isEmpty()) {
                                        InventoryHelper.dropItemStack(world, blockPos.getX() + .5, blockPos.getY() + .5, blockPos.getZ() + .5, drop);
                                    }
                                }
                                world.destroyBlock(blockPos, false, player);
                                if (seedDrop) {
                                    world.setBlock(blockPos, crop.getStateForAge(0), 3);
                                }
                            }
                            player.swing(Hand.MAIN_HAND);
                            event.setCanceled(true);
                        }
                    } else if (block instanceof NetherWartBlock) {
                        NetherWartBlock crop = (NetherWartBlock) block;
                        boolean seedDrop = false;

                        BlockPos below = blockPos.below();
                        BlockState belowState = world.getBlockState(below);
                        Block belowBlock = belowState.getBlock();

                        replant &= belowBlock.canSustainPlant(belowState, world, below, Direction.UP, crop);

                        if (state.getValue(NetherWartBlock.AGE) >= 3) {
                            if (!world.isClientSide) {
                                List<ItemStack> drops = Block.getDrops(state, (ServerWorld) world, blockPos, null, player, player.getMainHandItem());
                                Item seedItem = crop.getCloneItemStack(world, blockPos, state).getItem();
                                for (ItemStack drop : drops) {
                                    if (replant && !seedDrop) {
                                        if (drop.getItem() == seedItem) {
                                            drop.shrink(1);
                                            seedDrop = true;
                                        }
                                    }
                                    if (!drop.isEmpty()) {
                                        InventoryHelper.dropItemStack(world, blockPos.getX() + .5, blockPos.getY() + .5, blockPos.getZ() + .5, drop);
                                    }
                                }
                                world.destroyBlock(blockPos, false, player);
                                if (seedDrop) {
                                    world.setBlock(blockPos, crop.defaultBlockState(), 3);
                                }
                            }
                            player.swing(Hand.MAIN_HAND);
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }
}