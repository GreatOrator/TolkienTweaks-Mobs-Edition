package com.greatorator.tolkienmobs.world.gen.generators;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.ITTMStructure;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
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
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Map;
import java.util.PrimitiveIterator;
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
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            flag = true;
            template.addBlocksToWorldChunk(world, pos, settings);

            if (check) {

                for (int i = 0; i < template.getSize().getX(); i++) {
                    for (int j = 0; j < template.getSize().getZ(); j++) {
                        BlockPos down = pos.add(i, -1, j);
                        Block b = world.getBlockState(down).getBlock();
                        if (!b.equals(Blocks.SAND)) {
                            flag = false;
                        }
                    }
                }
            }

            if (flag) {
                PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
                        .setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
                        .setReplacedBlock((Block) null).setIgnoreStructureBlock(true);

                template.addBlocksToWorldChunk(world, pos.down(), placementsettings);
                template.getDataBlocks(pos, placementsettings);

                Map<BlockPos, String> map = template.getDataBlocks(pos, placementsettings);

                for (Map.Entry<BlockPos, String> entry : template.getDataBlocks(pos, placementsettings).entrySet()) {
                    if ("chest".equals(entry.getValue())) {
                        BlockPos blockpos2 = entry.getKey();
                        world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
                        TileEntity tileentity = world.getTileEntity(blockpos2);

                        if (tileentity instanceof TileEntityChest) {
                            ((TileEntityChest)tileentity).setLootTable(LootInit.BARROW_GRAVE, world.rand.nextLong());
                        }
                    }
                    if ("barrow_chest".equals(entry.getValue())) {
                        BlockPos blockpos2 = entry.getKey();
                        world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
                        TileEntity tileentity = world.getTileEntity(blockpos2);

                        if (tileentity instanceof TileEntityChest) {
                            ((TileEntityChest)tileentity).setLootTable(LootInit.BARROW_CHEST, world.rand.nextLong());
                        }
                    }
                }
            }
        }
    }
}
