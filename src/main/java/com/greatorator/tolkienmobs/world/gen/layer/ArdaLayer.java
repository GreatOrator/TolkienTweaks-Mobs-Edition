package com.greatorator.tolkienmobs.world.gen.layer;

//
//public class ArdaLayer {
//    private final LazyArea area;
//
//    public ArdaLayer(IAreaFactory<LazyArea> area) {
//        this.area = area.make();
//    }
//
//    public Biome get(Registry<Biome> biomes, int x, int z) {
//        int i = area.get(x, z);
//
//        RegistryKey<Biome> registrykey = ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getKey(i);
//        if (registrykey == null) {
//            throw new IllegalStateException("Unknown biome id emitted by layers: " + i);
//        }
//
//        Biome biome = biomes.get(registrykey);
//        if (biome == null) {
//            if (SharedConstants.IS_RUNNING_IN_IDE) {
//                throw Util.pauseInIde(new IllegalStateException("Unknown biome id: " + i));
//            }
//
//            System.err.println("Unknown biome id: " + i);
//            return biomes.get(((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getKey(0));
//        }
//
//        return biome;
//    }
//}
