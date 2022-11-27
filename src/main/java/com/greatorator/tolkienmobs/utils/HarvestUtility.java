package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.handler.PlayerHandler;
import com.greatorator.tolkienmobs.init.TolkienTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class HarvestUtility {
    public static Iterable<BlockPos> getPositionsFromBox(AABB box) {
        return getPositionsFromBox(new BlockPos(box.minX, box.minY, box.minZ), new BlockPos(box.maxX, box.maxY, box.maxZ));
    }

    public static Iterable<BlockPos> getPositionsFromBox(BlockPos corner1, BlockPos corner2) {
        return () -> BlockPos.betweenClosedStream(corner1, corner2).iterator();
    }

    public static void growNearbyRandomly(boolean harvest, Level level, BlockPos pos, Player player, int enchantLevel) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        boolean grewWater = false;
        int chance = harvest ? 16 : 32;
        for (BlockPos currentPos : getPositionsFromBox(pos.offset(-enchantLevel, -3, -enchantLevel), pos.offset(enchantLevel, 3, enchantLevel))) {
            currentPos = currentPos.immutable();
            BlockState state = serverLevel.getBlockState(currentPos);
            Block crop = state.getBlock();

            if (crop instanceof IForgeShearable || crop instanceof FlowerBlock || crop instanceof DoublePlantBlock ||
                    crop instanceof RootsBlock || crop instanceof NetherSproutsBlock || crop instanceof HangingRootsBlock) {
                if (harvest) {
                    harvestBlock(serverLevel, currentPos, (ServerPlayer) player);
                }
            } else if (crop instanceof BonemealableBlock growable) {
                if (!growable.isValidBonemealTarget(serverLevel, currentPos, state, false)) {
                    if (harvest && !state.is(TolkienTags.tagkeys.BLACKLIST_HARVEST)) {
                        if (!leaveBottomBlock(crop) || serverLevel.getBlockState(currentPos.below()).is(crop)) {
                            harvestBlock(serverLevel, currentPos, (ServerPlayer) player);
                        }
                    }
                } else if (TolkienConfig.disableHobbitGrowth || !isGrassLikeBlock(crop)) {
                    if (serverLevel.random.nextInt(chance) == 0) {
                        growable.performBonemeal(serverLevel, serverLevel.random, currentPos, state);
                        level.levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, currentPos, 0);
                    }
                }
            } else if (crop instanceof IPlantable) {
                if (serverLevel.random.nextInt(chance / 4) == 0) {
                    for (int i = 0; i < (harvest ? 8 : 4); i++) {
                        state.randomTick(serverLevel, currentPos, serverLevel.random);
                    }
                }
                if (harvest) {
                    if (crop == Blocks.SUGAR_CANE || crop == Blocks.CACTUS) {
                        if (serverLevel.getBlockState(currentPos.above()).is(crop) && serverLevel.getBlockState(currentPos.above(2)).is(crop)) {
                            for (int i = crop == Blocks.SUGAR_CANE ? 1 : 0; i < 3; i++) {
                                harvestBlock(serverLevel, currentPos.above(i), (ServerPlayer) player);
                            }
                        }
                    } else if (crop == Blocks.NETHER_WART) {
                        if (state.getValue(NetherWartBlock.AGE) == 3) {
                            harvestBlock(serverLevel, currentPos, (ServerPlayer) player);
                        }
                    }
                }
            }

            else if (!grewWater && serverLevel.random.nextInt(512) == 0 && growWaterPlant(serverLevel, currentPos, state, null)) {
                level.levelEvent(LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH, currentPos, 0);
                grewWater = true;
            }
        }
    }

    private static void harvestBlock(Level level, BlockPos pos, @Nullable ServerPlayer player) {
        if (player == null || PlayerHandler.hasBreakPermission(player, pos)) {
            level.destroyBlock(pos, true, player);
        }
    }

    private static boolean leaveBottomBlock(Block crop) {
        return crop == Blocks.KELP_PLANT || crop == Blocks.BAMBOO;
    }

    private static boolean isGrassLikeBlock(Block crop) {
        return crop instanceof GrassBlock || crop instanceof NyliumBlock || crop instanceof NetherrackBlock || crop instanceof MossBlock;
    }

    public static boolean growWaterPlant(ServerLevel level, BlockPos pos, BlockState state, @Nullable Direction side) {
        boolean success = false;
        if (state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8) {
            Random random = level.getRandom();
            label76:
            for (int i = 0; i < 128; ++i) {
                BlockPos blockpos = pos;
                for (int j = 0; j < i / 16; ++j) {
                    blockpos = blockpos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                            random.nextInt(3) - 1);
                    if (level.getBlockState(blockpos).isCollisionShapeFullBlock(level, blockpos)) {
                        continue label76;
                    }
                }
                BlockState newState = Blocks.SEAGRASS.defaultBlockState();
                Holder<Biome> biome = level.getBiome(blockpos);
                if (biome.is(Biomes.WARM_OCEAN)) {
                    if (i == 0 && side != null && side.getAxis().isHorizontal()) {
                        newState = getRandomState(BlockTags.WALL_CORALS, random, newState);
                        if (newState.hasProperty(BaseCoralWallFanBlock.FACING)) {
                            newState = newState.setValue(BaseCoralWallFanBlock.FACING, side);
                        }
                    } else if (random.nextInt(4) == 0) {
                        newState = getRandomState(BlockTags.UNDERWATER_BONEMEALS, random, newState);
                    }
                }
                if (newState.is(BlockTags.WALL_CORALS, s -> s.hasProperty(BaseCoralWallFanBlock.FACING))) {
                    for (int k = 0; !newState.canSurvive(level, blockpos) && k < 4; ++k) {
                        newState = newState.setValue(BaseCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random));
                    }
                }
                if (newState.canSurvive(level, blockpos)) {
                    BlockState stateToReplace = level.getBlockState(blockpos);
                    if (stateToReplace.is(Blocks.WATER) && stateToReplace.getFluidState().getAmount() == 8) {
                        level.setBlockAndUpdate(blockpos, newState);
                        success = true;
                    } else if (stateToReplace.is(Blocks.SEAGRASS) && random.nextInt(10) == 0) {
                        ((BonemealableBlock) Blocks.SEAGRASS).performBonemeal(level, random, blockpos, stateToReplace);
                        success = true;
                    }
                }
            }
        }
        return success;
    }

    private static BlockState getRandomState(TagKey<Block> key, Random random, BlockState fallback) {
        return LazyTagUtility.tagManager(ForgeRegistries.BLOCKS).getTag(key)
                .getRandomElement(random)
                .map(Block::defaultBlockState)
                .orElse(fallback);
    }
}