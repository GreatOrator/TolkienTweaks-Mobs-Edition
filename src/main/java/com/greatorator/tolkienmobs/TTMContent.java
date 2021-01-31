package com.greatorator.tolkienmobs;

import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerBCore;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.task.CreateBabyVillagerTask;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * Created by brandon3055 on 31/1/21
 */
public class TTMContent {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //#################################################################
    // Blocks
    //#################################################################

    public static RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK)));

    //#################################################################
    // Items
    //#################################################################

    //Blocks now require you to register their item separately. The item for a block should have the same registry name.
    public static RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new ItemBlockBCore(EXAMPLE_BLOCK.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));

    //Actual items
    public static RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));

    //#################################################################
    // Tile Entity Types
    //#################################################################

    public static RegistryObject<TileEntityType<ExampleTile>> EXAMPLE_TILE = TILE.register("example_tile", () -> TileEntityType.Builder.create(ExampleTile::new, EXAMPLE_BLOCK.get()).build(null));

    //#################################################################
    // Containers
    //#################################################################

    //TODO Will get back to this when its needed. I need to figure out a better way to do this.
//    public static RegistryObject<ContainerType<ContainerBCTile<ExampleTile>>> EXAMPLE_CONTAINER = CONTAINER.register("example_container", (windowId, inv, data) -> new ContainerBCTile<ExampleTile>(EXAMPLE_CONTAINER.get(), windowId, inv, data, TRANSFUSER_LAYOUT));

    //#################################################################
    // Entities
    //#################################################################

    public static RegistryObject<EntityType<PigEntity>> EXAMPLE_ENTITY = ENTITY.register("example_entity", () -> EntityType.Builder.create(PigEntity::new, EntityClassification.CREATURE).size(0.9F, 0.9F).trackingRange(10).build("example_entity"));



    //For demonstration purposes only
    public static class ExampleTile extends TileBCore {
        public ExampleTile() {
            super(EXAMPLE_TILE.get());
        }
    }

}
