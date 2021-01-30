//package com.greatorator.tolkienmobs.handler;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.init.EnchantmentsInit;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockCrops;
//import net.minecraft.block.BlockDirt;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.attributes.AttributeModifier;
//import net.minecraft.entity.ai.attributes.IAttributeInstance;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemHoe;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.World;
//import net.minecraftforge.event.entity.living.LivingEvent;
//import net.minecraftforge.event.entity.player.UseHoeEvent;
//import net.minecraftforge.event.world.BlockEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.EventPriority;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.UUID;
//
//@SuppressWarnings("unused")
//@Mod.EventBusSubscriber(modid= TolkienMobs.MODID)
//public class TTMEnchantHandler {
//    public static UUID resolveUUID = UUID.fromString("838FDDBA-2270-4EE2-82FB-29883B38E330");
//    public static UUID longevityUUID = UUID.fromString("E08A038A-BF0D-425A-8897-97A40F7E402F");
//
//    private int radius;
//
//    /* Balrog Step Enchant */
//    @SubscribeEvent
//    public void burningGround(LivingEvent.LivingUpdateEvent event)
//    {
//        LivingEntity living = event.getEntityLiving();
//        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.BALROG_MARK, living);
//        BlockPos pos = living.getPosition();
//        World worldIn = event.getEntity().world;
//
//        if (living.onGround && living instanceof PlayerEntity && level !=0)
//        {
//            float f = (float)Math.min(16, 2 + level);
//            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(0, 0, 0);
//
//            for (BlockPos.MutableBlockPos blockpos$mutableblockpos1 : BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f)))
//            {
//                if (blockpos$mutableblockpos1.distanceSqToCenter(living.posX, living.posY, living.posZ) <= (double)(f * f))
//                {
//                    blockpos$mutableblockpos.setPos(blockpos$mutableblockpos1.getX(), blockpos$mutableblockpos1.getY() + 1, blockpos$mutableblockpos1.getZ());
//                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);
//
//                    if (iblockstate.getMaterial() == Material.AIR)
//                    {
//                        IBlockState iblockstate1 = worldIn.getBlockState(blockpos$mutableblockpos1);
//
//                        if (iblockstate1.getBlock() == Blocks.GRASS || iblockstate1.getBlock() == Blocks.DIRT && worldIn.mayPlace(Blocks.MAGMA, blockpos$mutableblockpos1, false, EnumFacing.DOWN, (Entity)null))
//                        {
//                            worldIn.setBlockState(blockpos$mutableblockpos1, Blocks.MAGMA.getDefaultState());
//                            worldIn.scheduleUpdate(blockpos$mutableblockpos1.toImmutable(), Blocks.MAGMA, MathHelper.getInt(living.getRNG(), 60, 120));
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /* Gondorian Resolve Enchant */
//    @SubscribeEvent
//    public void gondorianResolve(LivingEvent.LivingUpdateEvent event)
//    {
//        LivingEntity living = event.getEntityLiving();
//        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.GONDOR_RESOLVE, living);
//
//        if(living instanceof PlayerEntity && level > 0 && event.getEntityLiving() != null){
//            AddResolve(living, level);
//        }
//        else{
//            RemoveResolve(living, level);
//        }
//    }
//
//    private void AddResolve(LivingEntity entity, int level)
//    {
//        IAttributeInstance battleResolve = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
//
//        if(battleResolve.getModifier(resolveUUID) != null)
//            return;
//
//        AttributeModifier modResolve = new AttributeModifier(resolveUUID, "Resolve", 0.20D, level);
//
//        // Creatures and Players use 2 different values here
//        battleResolve.removeModifier(modResolve);
//        battleResolve.applyModifier(modResolve);
//    }
//
//    private void RemoveResolve(LivingEntity entity, int level)
//    {
//        IAttributeInstance battleResolve = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
//
//        if(battleResolve.getModifier(resolveUUID) == null)
//            return;
//
//        AttributeModifier modResolve = new AttributeModifier(resolveUUID, "Resolve", 0.20D, level);
//        battleResolve.removeModifier(modResolve);
//    }
//
//    /* Hobbit Harvest Enchant */
//    @SubscribeEvent
//    public void hobbitHarvest(BlockEvent.HarvestDropsEvent event)
//    {
//        if (event.getHarvester() == null){
//            return;
//        }
//
//        ItemStack harvestingItem = event.getHarvester().inventory.getStackInSlot(event.getHarvester().inventory.currentItem);
//        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentsInit.HOBBIT_HARVEST, harvestingItem);
//
//        List<ItemStack> dropList = new ArrayList();
//        //LogHelperTTM.info("DropListB:"+ event.getDrops().size());
//        Iterator<ItemStack> it = event.getDrops().iterator();
//        ItemStack item;
//
//        if(harvestingItem.getItem() instanceof ItemHoe && event.getHarvester() != null) {
//            if (event.getState().getBlock() instanceof BlockCrops && level > 0) {
//                while(it.hasNext())
//                {
//                    item = it.next();
//                    //LogHelperTTM.info("increase items:"+ item.getDisplayName());
//                    for (int i = 0; i <= level; i++)
//                    {
//                        dropList.add(item.copy());
//                    }
//                }
//                event.getDrops().addAll(dropList);
//                //LogHelperTTM.info("DropListA:"+ event.getDrops().size());
//            }
//        }
//    }
//
//    /* Hobbit Plow Event*/
//    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
//    public void onEvent(UseHoeEvent event)
//    {
//        if (!event.getWorld().isRemote && !event.getEntityPlayer().isSneaking()) {
//            ItemStack harvestingItem = event.getEntityPlayer().getHeldItemMainhand();
//            radius = EnchantmentHelper.getEnchantmentLevel(EnchantmentsInit.HOBBIT_PLOW, harvestingItem);
//
//            if (harvestingItem.getItem() instanceof ItemHoe && event.getEntityPlayer() != null) {
//                if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockCrops)
//                    rightClickCrop(event);
//                else
//                    rightClickDirt(event);
//                event.setCanceled(true);
//            }
//        }
//    }
//
//    private void rightClickDirt(UseHoeEvent event)
//    {
//        for (int x=-radius; x<=radius; x++)
//        {
//            for (int z=-radius; z<=radius; z++)
//            {
//                if (onItemUse(event, x, z))
//                    event.getWorld().scheduleBlockUpdate(event.getPos().add(x, 0, z), event.getWorld().getBlockState(event.getPos().add(x, 0, z)).getBlock(), 0, 1);//event.getWorld().markBlockForUpdate(event.getPos().add(x, 0, z));
//            }
//        }
//    }
//
//    @SuppressWarnings("deprecation")
//    private boolean onItemUse(UseHoeEvent event, int x, int z)
//    {
//        if(event.getCurrent().getItemDamage() == 0 && !(x == -radius && z == -radius) && !event.getEntityPlayer().capabilities.isCreativeMode)
//            return false;
//        int[] TYPE_LOOKUP = new int[BlockDirt.DirtType.values().length];
//        TYPE_LOOKUP[BlockDirt.DirtType.DIRT.ordinal()] = 1;
//        TYPE_LOOKUP[BlockDirt.DirtType.COARSE_DIRT.ordinal()] = 2;
//
//        if (!event.getEntityPlayer().canPlayerEdit(event.getPos().add(x, 0, z).offset(EnumFacing.UP), EnumFacing.UP, event.getCurrent()))
//            return false;
//        else
//        {
//            IBlockState iblockstate = event.getWorld().getBlockState(event.getPos().add(x, 0, z));
//            Block block = iblockstate.getBlock();
//            if (EnumFacing.UP != EnumFacing.DOWN && event.getWorld().isAirBlock(event.getPos().add(x, 0, z).up()))
//            {
//                if (block == Blocks.GRASS)
//                    return useHoe(event.getCurrent(), event.getEntityPlayer(), event.getWorld(), event.getPos().add(x, 0, z), Blocks.FARMLAND.getStateFromMeta(1));
//                else if (block == Blocks.DIRT)
//                {
//                    switch (TYPE_LOOKUP[((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT)).ordinal()])
//                    {
//                        case 1:
//                            return useHoe(event.getCurrent(), event.getEntityPlayer(), event.getWorld(), event.getPos().add(x, 0, z), Blocks.FARMLAND.getStateFromMeta(1));
//                        case 2:
//                            return useHoe(event.getCurrent(), event.getEntityPlayer(), event.getWorld(), event.getPos().add(x, 0, z), Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
//                    }
//                }
//            }
//            return false;
//        }
//    }
//
//    private boolean useHoe(ItemStack stack, PlayerEntity player, World worldIn, BlockPos pos, IBlockState state)
//    {
//        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
//
//        if (!worldIn.isRemote)
//        {
//            worldIn.setBlockState(pos, state, 4);
//            stack.damageItem(1, player);
//        }
//        return true;
//    }
//
//    private void rightClickCrop(UseHoeEvent event)
//    {
//        for (int x=-radius; x<=radius; x++)
//        {
//            for (int z=-radius; z<=radius; z++)
//            {
//                if (event.getWorld().getBlockState(event.getPos().add(x, 0, z)).getBlock() instanceof BlockCrops && !((BlockCrops) event.getWorld().getBlockState(event.getPos().add(x, 0, z)).getBlock()).canGrow(event.getWorld(), event.getPos().add(x, 0, z), event.getWorld().getBlockState(event.getPos().add(x, 0, z)), false))
//                {
//                    ItemStack hoe = event.getCurrent();
//                    BlockCrops crop = (BlockCrops) event.getWorld().getBlockState(event.getPos().add(x, 0, z)).getBlock();
//                    try
//                    {
//                        Method method = crop.getClass().getDeclaredMethod("getSeed");
//                        method.setAccessible(true);
//                        Item seed = (Item) method.invoke(crop);
//                        crop.harvestBlock(event.getWorld(), event.getEntityPlayer(), event.getPos().add(x, 0, z), event.getWorld().getBlockState(event.getPos().add(x, 0, z)), null, hoe);
//                        if (event.getEntityPlayer().capabilities.isCreativeMode || event.getEntityPlayer().inventory.clearMatchingItems(seed, -1, 1, null) == 1/*event.getEntityPlayer().inventory.consumeInventoryItem(seed)*/)
//                        {
//                            event.getEntityPlayer().inventoryContainer.detectAndSendChanges();
//                            event.getWorld().notifyBlockUpdate(event.getPos().add(x, 0, z), event.getWorld().getBlockState(event.getPos().add(x, 0, z)), crop.getStateFromMeta(0), 0);
//                            event.getWorld().setBlockState(event.getPos().add(x, 0, z), crop.getStateFromMeta(0), 0);
//                        }
//                        else
//                            event.getWorld().setBlockToAir(event.getPos().add(x, 0, z));
//                    }
//                    catch(Exception e)
//                    {
//                        try
//                        {
//                            Method method = crop.getClass().getDeclaredMethod("func_149866_i");
//                            method.setAccessible(true);
//                            Item seed = (Item) method.invoke(crop);
//                            crop.harvestBlock(event.getWorld(), event.getEntityPlayer(), event.getPos().add(x, 0, z), event.getWorld().getBlockState(event.getPos().add(x, 0, z)), null, hoe);
//                            if (event.getEntityPlayer().capabilities.isCreativeMode || event.getEntityPlayer().inventory.clearMatchingItems(seed, -1, 1, null) == 1/*consumeInventoryItem(seed)*/)
//                            {
//                                event.getEntityPlayer().inventoryContainer.detectAndSendChanges();
//                                event.getWorld().notifyBlockUpdate(event.getPos().add(x, 0, z), event.getWorld().getBlockState(event.getPos().add(x, 0, z)), crop.getStateFromMeta(0), 0);
//                                event.getWorld().setBlockState(event.getPos().add(x, 0, z), crop.getStateFromMeta(0), 0);
//                            }
//                            else
//                                event.getWorld().setBlockToAir(event.getPos().add(x, 0, z));
//                        }
//                        catch(Exception ex)
//                        {
//                            ex.printStackTrace();
//                            System.out.println("[Improved Hoes] Error during onPlayerInteractEvent(). Please report this to the mod maker.");
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /* Hobbit Gardening Event*/
//
//    /* Elven Longevity Enchant */
//    @SubscribeEvent
//    public void elvenLongevity(LivingEvent.LivingUpdateEvent event)
//    {
//        LivingEntity living = event.getEntityLiving();
//        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.ELVEN_LONGEVITY, living);
//
//        if(living instanceof PlayerEntity && level > 0){
//            AddLongevity(living, level);
//        }
//        else{
//            RemoveLongevity(living, level);
//        }
//    }
//
//    private void AddLongevity(LivingEntity entity, int level)
//    {
//        IAttributeInstance elvenLongevity = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
//
//        if(elvenLongevity.getModifier(longevityUUID) != null)
//            return;
//
//        AttributeModifier modLongevity = new AttributeModifier(longevityUUID, "Longevity", level * 0.2, 1);
//
//        elvenLongevity.applyModifier(modLongevity);
//    }
//
//    private void RemoveLongevity(LivingEntity entity, int level)
//    {
//        IAttributeInstance elvenLongevity = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
//
//        if(elvenLongevity.getModifier(longevityUUID) == null)
//            return;
//
//        AttributeModifier modLongevity = new AttributeModifier(longevityUUID, "Longevity", level * 0.2, 1);
//
//        if(entity.getMaxHealth() < entity.getHealth()) entity.setHealth(entity.getMaxHealth());
//        elvenLongevity.removeModifier(modLongevity);
//    }
//
//    /* Dwarven Endurance Enchant */
//    @SubscribeEvent
//    public void dwarvenEndurance(LivingEvent.LivingUpdateEvent event)
//    {
//        LivingEntity living = event.getEntityLiving();
//        int level = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsInit.DWARF_ENDURANCE, living);
//
//        if (living instanceof PlayerEntity && level !=0)
//        {
//            ((PlayerEntity)living).getFoodStats().addStats(level + 1, 1.0F);
//        }
//    }
//}