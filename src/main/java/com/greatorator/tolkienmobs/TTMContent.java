package com.greatorator.tolkienmobs;

import codechicken.lib.gui.SimpleItemGroup;
import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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

    public static ItemGroup toolsGroup = new SimpleItemGroup("tolkienmobs.tools", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));
    public static ItemGroup matsGroup = new SimpleItemGroup("tolkienmobs.mats", () -> new ItemStack(TTMContent.INGOT_MITHRIL.get()));
    public static ItemGroup spawnGroup = new SimpleItemGroup("tolkienmobs.spawn", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));
    public static ItemGroup foodGroup = new SimpleItemGroup("tolkienmobs.food", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));
    public static ItemGroup questGroup = new SimpleItemGroup("tolkienmobs.quest", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));
    public static ItemGroup signsGroup = new SimpleItemGroup("tolkienmobs.signs", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //#################################################################
    // Basic Blocks
    //#################################################################

    public static RegistryObject<Block> BLOCK_MITHRIL = BLOCKS.register("block_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));

    //#################################################################
    // Quest Items
    //#################################################################

    //Blocks now require you to register their item separately. The item for a block should have the same registry name.
    public static RegistryObject<Item> BLOCK_MITHRIL_ITEM = ITEMS.register("block_mithril", () -> new ItemBlockBCore(BLOCK_MITHRIL.get(), new Item.Properties().group(matsGroup)));

    //Actual items
    public static RegistryObject<Item> INGOT_MITHRIL = ITEMS.register("ingot_mithril", () -> new Item(new Item.Properties().group(matsGroup)));

    //#################################################################
    // Tile Entity Types
    //#################################################################

    //public static RegistryObject<TileEntityType<ExampleTile>> EXAMPLE_TILE = TILE.register("example_tile", () -> TileEntityType.Builder.create(ExampleTile::new, EXAMPLE_BLOCK.get()).build(null));

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
    //public static class ExampleTile extends TileBCore {
    //    public ExampleTile() {
    //        super(EXAMPLE_TILE.get());
    //    }
    //}

}
