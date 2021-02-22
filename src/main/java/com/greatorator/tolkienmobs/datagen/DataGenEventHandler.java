package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.integration.CuriosTTM;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 26/2/20.
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEventHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        if (event.includeClient()) {
            gen.addProvider(new LangGenerator(gen));
            gen.addProvider(new BlockStateGenerator(gen, event.getExistingFileHelper()));
            gen.addProvider(new ItemModelGenerator(gen, event.getExistingFileHelper()));
        }

        if (event.includeServer()) {
            gen.addProvider(new RecipeGenerator(gen));
            gen.addProvider(new LootTableGenerator(gen));

            //TODO
            BlockTagGenerator blockGenerator = new BlockTagGenerator(gen, TolkienMobs.MODID, event.getExistingFileHelper());
            gen.addProvider(blockGenerator);
            gen.addProvider(new ItemTagGenerator(gen, blockGenerator, TolkienMobs.MODID, event.getExistingFileHelper()));
        }
    }

    private static class ItemTagGenerator extends ItemTagsProvider {
        public ItemTagGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(dataGenerator, blockTagProvider, modId, existingFileHelper);
        }

        @Override
        protected void registerTags() {
            if (ModList.get().isLoaded("curios")) {
                CuriosTTM.generateTags(this::getOrCreateBuilder);
            }
        }
    }

    private static class BlockTagGenerator extends BlockTagsProvider {
        public BlockTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(generatorIn, modId, existingFileHelper);
        }

        @Override
        protected void registerTags() {
//            getOrCreateBuilder(DETags.Blocks.STORAGE_BLOCKS_DRACONIUM).add(DEContent.block_draconium);
//            getOrCreateBuilder(DETags.Blocks.STORAGE_BLOCKS_DRACONIUM_AWAKENED).add(DEContent.block_draconium_awakened);
//            getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).add(DEContent.block_draconium, DEContent.block_draconium_awakened);
//
//           getOrCreateBuilder(DETags.Blocks.ORES_DRACONIUM).add(DEContent.ore_draconium_end, DEContent.ore_draconium_nether, DEContent.ore_draconium_overworld);
//            getOrCreateBuilder(Tags.Blocks.ORES).add(DEContent.ore_draconium_end, DEContent.ore_draconium_nether, DEContent.ore_draconium_overworld);
        }
    }
}
