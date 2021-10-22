package com.greatorator.tolkienmobs.server;

import com.brandon3055.brandonscore.api.TimeKeeper;
import com.greatorator.tolkienmobs.datagen.BiomeGenerator;
import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.greatorator.tolkienmobs.datagen.StructureGenerator;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMStructureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class TTMServerEvents {

    public static void livingUpdate (LivingEvent.LivingUpdateEvent living){
        LivingEntity entity = living.getEntityLiving();
        BlockPos pos = entity.blockPosition();
        World worldIn = living.getEntity().level;

        //region/*---------------- Balrog's Mark -----------------*/
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.BALROG_MARK.get(), entity);

        if (entity.isOnGround() && level > 0) {
            BlockState blockstate = Blocks.MAGMA_BLOCK.defaultBlockState();
            float f = (float)Math.min(16, 2 + level);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset((double)(-f), -1.0D, (double)(-f)), pos.offset((double)f, -1.0D, (double)f))) {
                if (blockpos.closerThan(entity.position(), (double)f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = worldIn.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir(worldIn, blockpos$mutable)) {
                        BlockState blockstate2 = worldIn.getBlockState(blockpos);
                        if (blockstate2.getBlock() == Blocks.GRASS_BLOCK || blockstate2.getBlock() == Blocks.DIRT && worldIn.isUnobstructed(blockstate, blockpos, ISelectionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(entity, net.minecraftforge.common.util.BlockSnapshot.create(worldIn.dimension(), worldIn, blockpos), Direction.DOWN)) {
                            worldIn.setBlockAndUpdate(blockpos, blockstate);
                            worldIn.getBlockTicks().scheduleTick(blockpos, Blocks.MAGMA_BLOCK, MathHelper.nextInt(entity.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }
        //endregion
        //region/*---------------- Hobbit Plow -----------------*/

        //endregion
    }

    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        Biome biome = player.level.getBiome(player.blockPosition());

        if(!(player instanceof ServerPlayerEntity) || !player.isAlive()) return;

        if(player.isInWater() && TimeKeeper.getServerTick() % 20 == 0) {

            if(biome.getRegistryName().equals(BiomeGenerator.BIOME_MIRKWOOD.get().getRegistryName())) {
                player.addEffect(new EffectInstance(PotionGenerator.SLEEPNESIA.get(), 1200, 8));
            }
        }
    }

    private static Method GETCODEC_METHOD;
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(StructureGenerator.TTMHOUSE_ELVEN.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMHOUSE_ELVEN.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }

    public static void biomeModification(final BiomeLoadingEvent event) {
        if (event.getName().equals(BiomeGenerator.BIOME_LORINAND.getId())) {
        event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_ELVEN);
        }
    }
}
