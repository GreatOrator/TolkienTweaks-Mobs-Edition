package com.greatorator.tolkienmobs.enchantments;

//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.CropsBlock;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.enchantment.EnchantmentType;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.inventory.EquipmentSlotType;
//import net.minecraft.item.BlockItem;
//import net.minecraft.item.HoeItem;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.util.concurrent.TickDelayedTask;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.server.ServerWorld;
//import net.minecraftforge.event.world.BlockEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.LogicalSide;
//import net.minecraftforge.fml.LogicalSidedProvider;
//import net.minecraftforge.fml.common.Mod;
//
//import java.util.List;
//
//@Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
//public class EnchantmentHobbitHarvest extends Enchantment {
//
//    public EnchantmentHobbitHarvest(Rarity rarityIn, EquipmentSlotType... slots) {
//        super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack)
//    {
//        return stack.getItem() instanceof HoeItem;
//    }
//
//    @SubscribeEvent
//    public static void onPlayerHarvestBlock(BlockEvent.BreakEvent event)
//    {
//        if(event.getState().getBlock() instanceof CropsBlock)
//        {
//            ItemStack heldItem = event.getPlayer().getMainHandItem();
//            int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentGenerator.HOBBIT_PLOW.get(), heldItem);
//
//            if(heldItem.isEmpty())
//                return;
//
//            if(!EnchantmentHelper.getEnchantments(heldItem).containsKey(EnchantmentGenerator.HOBBIT_HARVEST.get()))
//                return;
//
//            World world = event.getPlayer().level;
//            if(EnchantmentHelper.getEnchantments(heldItem).containsKey(EnchantmentGenerator.HOBBIT_PLOW.get()))
//            {
//                BlockPos pos = event.getPos().offset(-1, 0, -1);
//                for(int x = -enchantmentLevel; x <= enchantmentLevel; x++) {
//                    for (int z = -enchantmentLevel; z <= enchantmentLevel; z++) {
//                        BlockPos targetPos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
//                        BlockState state = world.getBlockState(targetPos);
//                        EnchantmentHobbitHarvest.replantCrop(state, world, targetPos, event.getPlayer(), event.getPos());
//                    }
//                }
//            }
//            else
//            {
//                EnchantmentHobbitHarvest.replantCrop(event.getState(), world, event.getPos(), event.getPlayer(), event.getPos());
//            }
//        }
//    }
//
//    private static void replantCrop(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockPos originalPos)
//    {
//        if(state.getBlock() instanceof CropsBlock)
//        {
//            CropsBlock crop = (CropsBlock) state.getBlock();
//            if(state.equals(crop.getMaxAge()))
//                return;
//
//            ItemStack stack = crop.getCloneItemStack(world, pos, state);
//            if(stack.getItem() instanceof BlockItem)
//            {
//                BlockItem blockItem = (BlockItem) stack.getItem();
//                if(blockItem.getBlock() instanceof CropsBlock)
//                {
//                    ItemStack seeds = ItemStack.EMPTY;
//                    List<ItemStack> drops = Block.getDrops(state, (ServerWorld) world, pos, null);
//                    for(ItemStack drop : drops)
//                    {
//                        if(drop.getItem() == stack.getItem())
//                        {
//                            seeds = drop.split(1);
//                            break;
//                        }
//                    }
//                    if(seeds.isEmpty())
//                    {
//                        seeds = findSeeds(player, stack.getItem());
//                    }
//
//                    drops.forEach(drop -> Block.popResource(world, pos, drop));
//                    state.spawnAdditionalDrops((ServerWorld) world, pos, ItemStack.EMPTY);
//                    world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
//
//                    if(!pos.equals(originalPos))
//                    {
//                        world.levelEvent(2001, pos, Block.getId(state));
//                    }
//
//                    BlockState seedState = blockItem.getBlock().defaultBlockState();
//                    if(!seeds.isEmpty() & blockItem.getBlock().is(Blocks.FARMLAND))
//                    {
//                        MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
//                        server.enqueue(new TickDelayedTask(0, () -> world.setBlock(pos, seedState, 3)));
//                        seeds.shrink(1);
//                    }
//                }
//            }
//        }
//    }
//
//    private static ItemStack findSeeds(PlayerEntity player, Item item)
//    {
//        for(int i = 0; i < player.inventory.getContainerSize(); i++)
//        {
//            ItemStack stack = player.inventory.getItem(i);
//            if(stack.getItem() == item)
//            {
//                return stack;
//            }
//        }
//        return ItemStack.EMPTY;
//    }
//    @Override
//    public int getMinCost(int enchantmentLevel) {
//        return 10 * (enchantmentLevel - 1);
//    }
//
//    @Override
//    public int getMaxCost(int enchantmentLevel) {
//        return super.getMinCost(enchantmentLevel) + 10;
//    }
//
//    @Override
//    public boolean isTreasureOnly() {
//        return false;
//    }
//
//    @Override
//    public int getMaxLevel() {
//        return 1;
//    }
//
//    @Override
//    public boolean isTradeable() {
//        return true;
//    }
//
//    @Override
//    public boolean isAllowedOnBooks() {
//        return true;
//    }
//}
