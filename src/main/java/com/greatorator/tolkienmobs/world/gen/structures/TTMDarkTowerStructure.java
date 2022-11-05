package com.greatorator.tolkienmobs.world.gen.structures;

//
//public class TTMDarkTowerStructure extends Structure<NoFeatureConfig> {
//    public TTMDarkTowerStructure(Codec<NoFeatureConfig> codec) {
//        super(codec);
//    }
//
//    @Override
//    public IStartFactory<NoFeatureConfig> getStartFactory() {
//        return TTMDarkTowerStructure.Start::new;
//    }
//
//    @Override
//    public GenerationStage.Decoration step() {
//        return GenerationStage.Decoration.SURFACE_STRUCTURES;
//    }
//
//    private static final List<MobSpawnInfo.Spawners> STRUCTURE_MONSTERS = ImmutableList.of(
//            new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_WITCHKING.get(), 10, 1, 1),
//            new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_NAZGUL.get(), 80, 1, 1),
//            new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_NAZGULSTEED.get(), 100, 1, 1)
//    );
//
//    @Override
//    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
//        return STRUCTURE_MONSTERS;
//    }
//
//    private static final List<MobSpawnInfo.Spawners> STRUCTURE_CREATURES = ImmutableList.of(
//    );
//
//    @Override
//    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
//        return STRUCTURE_CREATURES;
//    }
//
//    @Override
//    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
//        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);
//
//        int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
//
//        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
//
//        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
//
//        return topBlock.getFluidState().isEmpty(); //landHeight > 100;
//    }
//
//    /**
//     * Handles calling up the structure's pieces class and height that structure will spawn at.
//     */
//    public static class Start extends StructureStart<NoFeatureConfig> {
//        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
//            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
//        }
//
//        @Override
//        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
//
//            int x = chunkX * 16;
//            int z = chunkZ * 16;
//            int landHeight = chunkGenerator.getFirstFreeHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
//            BlockPos centerPos = new BlockPos(x, landHeight + 1, z);
//
//            JigsawManager.addPieces(
//                    dynamicRegistryManager,
//                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(new ResourceLocation(MODID, "ttmdark_tower")), 10),
//                    AbstractVillagePiece::new,
//                    chunkGenerator,
//                    templateManagerIn,
//                    centerPos,
//                    pieces,
//                    random,
//                    false,
//                    false
//            );
//
//            Vector3i structureCenter = pieces.get(0).getBoundingBox().getCenter();
//            int xOffset = centerPos.getX() - structureCenter.getX();
//            int zOffset = centerPos.getZ() - structureCenter.getZ();
//            pieces.forEach(piece -> piece.move(xOffset, 0, zOffset));
//
//            calculateBoundingBox();
//        }
//
//    }
//}
