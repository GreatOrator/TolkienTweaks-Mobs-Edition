package com.greatorator.tolkienmobs.util.handlers;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Deprecated //This is technically the better way to register things but this does not actually matter until 1.13 (BC will support this in 1.13)
@EventBusSubscriber
public class RegistryHandler {
//    @SubscribeEvent
//    public static void onItemRegister(RegistryEvent.Register<Item> event){
//        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
//    }
//
//    @SubscribeEvent
//    public static void onBlockRegister(RegistryEvent.Register<Block> event){
//        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
//    }
//
//    @SubscribeEvent
//    public static void onModelRegister(ModelRegistryEvent event){
//        for(Item item : ItemInit.ITEMS)
//        {
//            if(item instanceof IHasModel)
//            {
//                ((IHasModel)item).registerModels();
//            } else {
//                TolkienMobs.proxy.register(item);
//            }
//        }
//
//        for(Block block : BlockInit.BLOCKS)
//        {
//            if(block instanceof IHasModel)
//            {
//                ((IHasModel)block).registerModels();
//            } else {
//                TolkienMobs.proxy.register(block);
//            }
//        }
//    }
//
//    public static void preInitRegistries(){
//        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
//
//        BiomeInit.registerBiomes();
//    }
//
//    public static void initRegistries(){
//    }
//
//    public static void postInitRegistries(){
//    }
}
