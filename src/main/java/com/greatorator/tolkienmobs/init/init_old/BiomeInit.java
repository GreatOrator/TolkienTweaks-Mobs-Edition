//package com.greatorator.tolkienmobs.init;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.utils.LogHelperTTM;
//import com.greatorator.tolkienmobs.world.world_old.biomes.*;
//import net.minecraft.world.biome.Biome;
//import net.minecraftforge.common.BiomeDictionary;
//import net.minecraftforge.common.BiomeManager;
//import net.minecraftforge.common.BiomeDictionary.Type;
//import net.minecraftforge.common.BiomeManager.BiomeEntry;
//import net.minecraftforge.common.BiomeManager.BiomeType;
//import net.minecraftforge.fml.common.registry.ForgeRegistries;
//
//public class BiomeInit
//{
//
//    public static final Biome LORINAND = new BiomeLorinand();
//    public static final Biome MIRKWOOD = new BiomeMirkwood();
//    public static final Biome HITHAEGLIR = new BiomeHithaeglir();
//    public static final Biome BARROW_DOWNS = new BiomeBarrowDowns();
//    public static final Biome HARADWAITH = new BiomeHaradwaith();
//    public static final Biome DAGORLAD = new BiomeDagorlad();
//    public static final Biome FANGORN = new BiomeFangorn();
//    public static final Biome MORDOR = new BiomeMordor();
//    public static final Biome NINDALF = new BiomeMarshes();
//    public static final Biome OLDFOREST = new BiomeOldForest();
//
//    /** Passive Biomes */
//    public static final Biome SHIRE = new BiomeShire();
//    public static final Biome GLADDEN = new BiomeGladden();
//    public static final Biome IRON_HILLS = new BiomeIronHills();
//    public static final Biome FIRIEN = new BiomeFirien();
//
//    public static void registerBiomes()
//    {
//        LogHelperTTM.info("Making new discoveries possible!");
//        if (TTMConfig_Old.enableBarrowDowns) {
//            initBiome(BARROW_DOWNS, "Tyrn Gorthad", BiomeType.COOL, Type.PLAINS, Type.DEAD, Type.SPOOKY);
//        }
//        if (TTMConfig_Old.enableLorinand) {
//            initBiome(LORINAND, "Lorinand", BiomeType.WARM, Type.PLAINS, Type.FOREST, Type.MAGICAL);
//        }
//        if (TTMConfig_Old.enableMirkwood) {
//            initBiome(MIRKWOOD, "Mirkwood", BiomeType.COOL, Type.SWAMP, Type.FOREST, Type.SPOOKY);
//        }
//        if (TTMConfig_Old.enableHithaeglir) {
//            initBiome(HITHAEGLIR, "Hithaeglir", BiomeType.ICY, Type.MOUNTAIN, Type.SPARSE);
//        }
//        if (TTMConfig_Old.enableHaradwaith) {
//            initBiome(HARADWAITH, "Haradwaith", BiomeType.DESERT, Type.HOT, Type.DRY, Type.DEAD, Type.SPARSE);
//        }
//        if (TTMConfig_Old.enableDagorlad) {
//            initBiome(DAGORLAD, "The Brown Lands", BiomeType.WARM, Type.PLAINS, Type.DRY, Type.LUSH);
//        }
//        if (TTMConfig_Old.enableFangornForest) {
//            initBiome(FANGORN, "Fangorn Forest", BiomeType.COOL, Type.FOREST, Type.LUSH);
//        }
//        if (TTMConfig_Old.enableMordor) {
//            initBiome(MORDOR, "Mordor", BiomeType.DESERT, Type.HOT, Type.NETHER, Type.DEAD, Type.WASTELAND);
//        }
//        if (TTMConfig_Old.enableNindalf) {
//            initBiome(NINDALF, "Nindalf Marshes", BiomeType.WARM, Type.SWAMP, Type.DEAD, Type.LUSH);
//        }
//        if (TTMConfig_Old.enableOldForest) {
//            initBiome(OLDFOREST, "Old Forest", BiomeType.COOL, Type.FOREST, Type.MAGICAL, Type.LUSH);
//        }
//
//        /* Passive Biomes */
//        if (TTMConfig_Old.enableShire) {
//            initBiome(SHIRE, "The Shire", BiomeType.WARM, Type.PLAINS, Type.LUSH);
//        }
//        if (TTMConfig_Old.enableGladden) {
//            initBiome(GLADDEN, "Gladden Fields", BiomeType.WARM, Type.PLAINS, Type.LUSH);
//        }
//        if (TTMConfig_Old.enableIronHills) {
//            initBiome(IRON_HILLS, "Iron Hills", BiomeType.COOL, Type.MOUNTAIN, Type.SPARSE);
//        }
//        if (TTMConfig_Old.enableFirien) {
//            initBiome(FIRIEN, "Firien Wood", BiomeType.COOL, Type.FOREST, Type.LUSH, Type.MAGICAL);
//        }
//        LogHelperTTM.info("New lands to explore get!");
//    }
//
//    private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
//    {
//        biome.setRegistryName(name);
//        ForgeRegistries.BIOMES.register(biome);
//        BiomeDictionary.addTypes(biome, types);
//        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
//        BiomeManager.addSpawnBiome(biome);
//        return biome;
//    }
//}