package com.greatorator.tolkienmobs.world.gen.generators;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.gen.ITTMStructure;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenStructure extends WorldGenerator implements ITTMStructure {
    public static String structureName;

    public WorldGenStructure(String name) {
        this.structureName = name;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.generateStructure(worldIn, position, true);
        return true;
    }

    public static void generateStructure(World world, BlockPos pos, boolean check) {
        boolean flag = false;

        MinecraftServer mcServer = world.getMinecraftServer();
        TemplateManager manager = worldServer.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(TolkienMobs.MODID, structureName);
        Template template = manager.get(mcServer, location);

        if (template != null) {
            BlockPos chestPos = new BlockPos(pos.getX() + 2, pos.getY() + 1, pos.getZ() + 3);
            pos = pos.add(-(template.getSize().getX()) / 2, 0, -(template.getSize().getZ() / 2));
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            flag = true;
            template.addBlocksToWorldChunk(world, pos, settings);

            if (check) {

                for (int i = 0; i < template.getSize().getX(); i++) {
                    for (int j = 0; j < template.getSize().getZ(); j++) {
                        BlockPos down = pos.add(i, -1, j);
                        Block b = world.getBlockState(down).getBlock();
                        if (!b.equals(Blocks.GRASS)) {
                            flag = false;
                        }
                    }
                }
            }

            if (flag) {
                PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
                        .setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
                        .setReplacedBlock((Block) null).setIgnoreStructureBlock(false);

                template.addBlocksToWorldChunk(world, pos.down(), placementsettings);
            }
        }
    }
}