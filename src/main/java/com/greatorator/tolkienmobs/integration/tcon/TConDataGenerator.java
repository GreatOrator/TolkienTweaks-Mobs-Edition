package com.greatorator.tolkienmobs.integration.tcon;

import com.greatorator.tolkienmobs.handler.enums.TolkienTiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.data.recipe.ICommonRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TConDataGenerator {

    public static class FluidTagGenerator extends FluidTagsProvider {
        public FluidTagGenerator(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
            super(generator, MODID, existingFileHelper);
        }

        @Override
        protected void addTags() {

        }

        private void tagLocal(FluidObject<?> fluid) {
            tag(fluid.getLocalTag())
                    .addOptional(fluid.getStill().getRegistryName())
                    .addOptional(fluid.getFlowing().getRegistryName());
        }

        private void tagAll(FluidObject<?> fluid) {
            tagLocal(fluid);
            tag(fluid.getForgeTag()).addOptionalTag(fluid.getLocalTag().location());
        }
    }


    public static class MaterialDatagen extends AbstractMaterialDataProvider {
        public MaterialDatagen(DataGenerator gen) {
            super(gen);
        }

        @Override
        protected void addMaterials() {
            addMaterial(TConMaterialManager.SHINY, 3, ORDER_WEAPON, false);
            addMaterial(TConMaterialManager.URUK, 3, ORDER_WEAPON, false);
        }

        @Override
        public String getName() {
            return "Tolkien Tweaks - Mobs Edition Materials";
        }

    }

    public static class MaterialRecipes extends BaseRecipeProvider implements IMaterialRecipeHelper {

        public MaterialRecipes(DataGenerator generator) {
            super(generator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
            materialMeltingCasting(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.SHINY, TConMaterialManager.MOLTEN_MITHRIL, true, "tools/materials/");
            materialMeltingCasting(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.URUK, TConMaterialManager.MOLTEN_MORGULIRON, true, "tools/materials/");

            metalMaterialRecipe(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.SHINY, "tools/materials/", "shiny", false);
            metalMaterialRecipe(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.URUK, "tools/materials/", "uruk", false);
        }


        @Override
        public String getName() {
            return "Tolkien Tweaks - Mobs Edition Material Recipes";
        }
    }

    public static class MaterialRenders extends AbstractMaterialRenderInfoProvider {

        public MaterialRenders(DataGenerator gen, @Nullable AbstractMaterialSpriteProvider materialSprites) {
            super(gen, materialSprites);
        }

        @Override
        protected void addMaterialRenderInfo() {
            buildRenderInfo(TConMaterialManager.SHINY).luminosity(15).fallbacks("metal");
            buildRenderInfo(TConMaterialManager.URUK).fallbacks("metal");
        }

        @Override
        public String getName() {
            return "Tolkien Tweaks - Mobs Edition Material Renders";
        }
    }

    public static class MaterialSprites extends AbstractMaterialSpriteProvider {

        @Override
        public String getName() {
            return "Tolkien Tweaks - Mobs Edition Material Sprites";
        }

        @Override
        protected void addAllMaterials() {
            buildMaterial(TConMaterialManager.SHINY)
                    .meleeHarvest()
                    .colorMapper(GreyToColorMapping.builder()
                            .addARGB(63, 0XE9F0FA)
                            .addARGB(102, 0XE3F3FF)
                            .addARGB(140, 0XD9E1F1)
                            .addARGB(178, 0XCBD2E8)
                            .addARGB(216, 0XC7CEDE)
                            .addARGB(255, 0XBFBFCE)
                            .build());

            buildMaterial(TConMaterialManager.URUK)
                    .meleeHarvest()
                    .colorMapper(GreyToColorMapping.builderFromBlack()
                            .addARGB(63, 0X3F5042)
                            .addARGB(102, 0X353D36)
                            .addARGB(140, 0x314239)
                            .addARGB(178, 0x202624)
                            .addARGB(216, 0X232B27)
                            .addARGB(255, 0X0B0D0B)
                            .build());
        }
    }

    public static class MaterialStats extends AbstractMaterialStatsDataProvider {

        public MaterialStats(DataGenerator gen, AbstractMaterialDataProvider materials) {
            super(gen, materials);
        }

        @Override
        public String getName() {
            return "Tolkien Tweaks - Mobs Edition Material Stats";
        }

        @Override
        protected void addMaterialStats() {
            addMaterialStats(TConMaterialManager.SHINY,
                    new HeadMaterialStats(1050, 6.5F, TolkienTiers.MITHRIL, 3.5F),
                    ExtraMaterialStats.DEFAULT,
                    HandleMaterialStats.DEFAULT);

            addMaterialStats(TConMaterialManager.URUK,
                    new HeadMaterialStats(850, 6.0F, TolkienTiers.MORGULIRON, 2.5F),
                    ExtraMaterialStats.DEFAULT,
                    HandleMaterialStats.DEFAULT);
        }
    }

    public static class MaterialTraits extends AbstractMaterialTraitDataProvider {

        public MaterialTraits(DataGenerator gen, AbstractMaterialDataProvider materials) {
            super(gen, materials);
        }

        @Override
        protected void addMaterialTraits() {
            addDefaultTraits(TConMaterialManager.SHINY, TConMaterialManager.VINGILOTE);
            addDefaultTraits(TConMaterialManager.URUK, TConMaterialManager.GULDURIL);
        }

        @Override
        public String getName() {
            return "Tolkien Tweaks - Mobs Edition Traits";
        }
    }

    public static class SmelteryRecipes extends BaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {

        public SmelteryRecipes(DataGenerator generator) {
            super(generator);
        }

        @Override
        protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
            this.metalTagCasting(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.MOLTEN_MITHRIL, "mithril", "smeltery/casting/metal/", true);
            this.metalMelting(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.MOLTEN_MITHRIL.get(), "mithril", false, "smeltery/melting/metal", false);
            this.metalTagCasting(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.MOLTEN_MORGULIRON, "morgulirion", "smeltery/casting/metal/", true);
            this.metalMelting(withCondition(consumer, new ModLoadedCondition(TConIntegration.TCON_ID)), TConMaterialManager.MOLTEN_MORGULIRON.get(), "morgulirion", false, "smeltery/melting/metal", false);
        }

        @Override
        public String getModId() {
            return MODID;
        }

        @Override
        public String getName() {
            return "Tolkien Tweaks - Mobs Edition Smeltery Recipes";
        }
    }
}