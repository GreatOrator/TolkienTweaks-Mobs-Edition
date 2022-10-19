package com.greatorator.tolkienmobs.enchantments;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
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
    public static void hobbitHarvest(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        ItemStack holding = event.getPlayer().getMainHandItem();
        Minecraft mc = Minecraft.getInstance();
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_HARVEST.get(), holding);

        if(state.getBlock() instanceof CropsBlock)
        {
            CropsBlock crop = (CropsBlock) event.getState().getBlock();
            if(crop.isMaxAge(state))
            {
                PlayerEntity player = event.getPlayer();
                ItemStack heldItem = player.getItemBySlot(EquipmentSlotType.MAINHAND);
                if(!heldItem.isEmpty())
                {
                    if(EnchantmentHelper.getEnchantments(heldItem).containsKey(EnchantmentGenerator.HOBBIT_HARVEST.get()))
                    {
                        World world = (World) event.getWorld();
                        BlockPos thisPos = event.getPos().offset(-1, 0, -1);

                        if(enchantmentLevel > 0)
                        {
                            for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                                for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                                    thisPos = new BlockPos(thisPos.getX() + x, thisPos.getY(), thisPos.getZ() + z);
                                    state = world.getBlockState(thisPos).getBlockState();
                                    replantCrop(state, world, thisPos, player, event.getPos(), enchantmentLevel);
                                }
                            }
                        }
                        else
                        {
                            for (int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
                                for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
                                    BlockRayTraceResult playerLookVec = (BlockRayTraceResult) mc.hitResult;
                                    ItemUseContext context = new ItemUseContext(player, player.getUsedItemHand(), playerLookVec);
                                    thisPos = new BlockPos(thisPos.getX() + x, thisPos.getY(), thisPos.getZ() + z);
                                    state = world.getBlockState(thisPos).getBlockState();
                                    harvestCrop(world, state, thisPos, context, enchantmentLevel);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void replantCrop(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockPos originalPos, int level) {
        if(state.getBlock() instanceof CropsBlock)
        {
            CropsBlock crop = (CropsBlock) state.getBlock();
            if(state.getValue(crop.getAgeProperty()) == crop.getMaxAge())
            {
                ItemStack stack = crop.getCloneItemStack(world, pos, state);
                if(stack.getItem() instanceof BlockItem)
                {
                    BlockItem blockItem = (BlockItem) stack.getItem();
                    if(blockItem.getBlock() instanceof CropsBlock)
                    {
                        // Search for seeds in the crop block drop
                        ItemStack seeds = ItemStack.EMPTY;
                        List<ItemStack> drops = Block.getDrops(state, (ServerWorld) world, pos, null);
                        for(ItemStack drop : drops)
                        {
                            if(drop.getItem() == stack.getItem())
                            {
                                seeds = drop.split(1);
                                break;
                            }
                        }

                        // If the crop block dropped no seeds, search the player's inventory
                        if(seeds.isEmpty())
                        {
                            for(int i = 0; i < player.inventory.getContainerSize(); i++)
                            {
                                ItemStack inventoryStack = player.inventory.getItem(i);
                                if(inventoryStack.getItem() == stack.getItem())
                                {
                                    seeds = inventoryStack;
                                    break;
                                }
                            }
                        }

                        world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        if(!player.isCreative())
                        {
                            ServerWorld serverworld = (ServerWorld)((ServerWorld) world).getLevel();

                            drops.forEach(drop -> Block.popResource(world, pos, drop));
                            state.spawnAfterBreak(serverworld, pos, ItemStack.EMPTY);
                        }
                        if(!pos.equals(originalPos))
                        {
                            world.blockEvent(pos, state.getBlock(), 2001, Block.getId(state));
                        }

                        BlockState seedState = blockItem.getBlock().defaultBlockState();
                        if(!seeds.isEmpty() & blockItem.getBlock().isAir(seedState, world, pos))
                        {
                            int age = 0;
                            double growthChance = Math.random();
                            if(growthChance >= 1.0 - ((level - 1) * 0.05)) // Level 1: 0%, Level 2: 5%, Level 3: 10%
                            {
                                age = 2;
                            }
                            else if(growthChance >= 1.0 - (level * 0.15)) // Level 1: 15%, Level 2: 25%, Level 3: 35%
                            {
                                age = 1;
                            }

                            final int newAge = age;

                            MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
                            server.submit(new TickDelayedTask(0, () -> world.setBlockAndUpdate(pos, seedState.setValue(BlockStateProperties.AGE_7, newAge))));

                            seeds.shrink(1);
                        }
                    }
                }
            }
        }
    }

    private static void harvestCrop(World world, BlockState state, BlockPos pos, ItemUseContext context, int bonus)
    {
        LootContext.Builder lootContext = new LootContext.Builder((ServerWorld) world).withParameter(LootParameters.ORIGIN, new Vector3d(pos.getX(), pos.getY(), pos.getZ())).withParameter(LootParameters.BLOCK_STATE, state).withParameter(LootParameters.THIS_ENTITY, context.getPlayer()).withParameter(LootParameters.TOOL, context.getItemInHand());
        for (ItemStack stacks : state.getDrops(lootContext)) {
            InventoryHelper.dropItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), stacks);
            stacks.setCount(TTMRand.getRandomInteger(8, 1));
            if (bonus > 0) {
                InventoryHelper.dropItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), stacks);
            }
        }
        BlockState newState = state.setValue(CropsBlock.AGE, 0);
        world.setBlockAndUpdate(pos, newState);
    }
}
