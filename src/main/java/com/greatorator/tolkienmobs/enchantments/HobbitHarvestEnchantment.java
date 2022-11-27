package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.CropsBlock;
import com.greatorator.tolkienmobs.handler.interfaces.HobbitHarvest;
import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
public class HobbitHarvestEnchantment extends BaseEnchantment {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public HobbitHarvestEnchantment(Rarity rarityIn, EquipmentSlot... slots) {
        super(rarityIn, EnchantmentCategory.DIGGER, slots);
    }

    @Override
    public boolean isEnabled() {
        return TolkienConfig.disableHobbitHarvest;
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
        return isEnabled() && super.canEnchant(stack) && stack.getItem() instanceof HoeItem;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @SubscribeEvent (priority = EventPriority.HIGHEST)
    public static void hobbitHarvest(PlayerInteractEvent.RightClickBlock event) {

        ItemStack holding = event.getPlayer().getMainHandItem();
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(TolkienEnchants.HOBBIT_HARVEST.get(), holding);

        if (enchantmentLevel > 0 && holding.isEnchanted() && EnchantmentHelper.getEnchantments(holding).containsKey(TolkienEnchants.HOBBIT_HARVEST.get()) && !holding.isEmpty()) {
            Level world = event.getWorld();
            Player player = event.getPlayer();
            boolean replant = TolkienConfig.replant;
            for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                    BlockPos blockPos = new BlockPos(event.getPos().getX() + x, event.getPos().getY(), event.getPos().getZ() + z);
                    BlockState state = world.getBlockState(blockPos);
                    Block block = state.getBlock();

                    if (block instanceof HobbitHarvest) {
                        HobbitHarvest harvestable = (HobbitHarvest) block;
                        if (harvestable.canHarvest(state)) {
                            harvestable.harvest(world, blockPos, state, player, replant);
                            player.swing(InteractionHand.MAIN_HAND);
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
                                List<ItemStack> drops = Block.getDrops(state, (ServerLevel) world, blockPos, null, player, player.getMainHandItem());
                                Item seedItem = crop.getCloneItemStack(world, blockPos, state).getItem();
                                for (ItemStack drop : drops) {
                                    if (replant && !seedDrop) {
                                        if (drop.getItem() == seedItem) {
                                            drop.shrink(1);
                                            seedDrop = true;
                                        }
                                    }
                                    if (!drop.isEmpty()) {
                                        Containers.dropItemStack(world, blockPos.getX() + .5, blockPos.getY() + .5, blockPos.getZ() + .5, drop);
                                    }
                                }
                                world.destroyBlock(blockPos, false, player);
                                if (seedDrop) {
                                    world.setBlock(blockPos, crop.getStateForAge(0), 3);
                                }
                            }
                            player.swing(InteractionHand.MAIN_HAND);
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
                                List<ItemStack> drops = Block.getDrops(state, (ServerLevel) world, blockPos, null, player, player.getMainHandItem());
                                Item seedItem = crop.getCloneItemStack(world, blockPos, state).getItem();
                                for (ItemStack drop : drops) {
                                    if (replant && !seedDrop) {
                                        if (drop.getItem() == seedItem) {
                                            drop.shrink(1);
                                            seedDrop = true;
                                        }
                                    }
                                    if (!drop.isEmpty()) {
                                        Containers.dropItemStack(world, blockPos.getX() + .5, blockPos.getY() + .5, blockPos.getZ() + .5, drop);
                                    }
                                }
                                world.destroyBlock(blockPos, false, player);
                                if (seedDrop) {
                                    world.setBlock(blockPos, crop.defaultBlockState(), 3);
                                }
                            }
                            player.swing(InteractionHand.MAIN_HAND);
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }
}