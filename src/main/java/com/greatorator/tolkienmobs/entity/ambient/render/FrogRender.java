package com.greatorator.tolkienmobs.entity.ambient.render;

//
//public class FrogRender extends MobRenderer<FrogEntity, FrogModel<FrogEntity>> {
//    private static final ResourceLocation FROG_GREEN_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_green.png");
//    private static final ResourceLocation FROG_RED_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_red.png");
//    private static final ResourceLocation FROG_BLACK_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_black.png");
//    private static final ResourceLocation FROG_RAINBOW_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_rainbow.png");
//    private static final ResourceLocation FROG_YELLOW_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_yellow.png");
//    private static final ResourceLocation FROG_WHITE_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/toaddle_white.png");
//    private static final ResourceLocation FROG_MURDER_LOCATION = new ResourceLocation(TolkienMobs.MODID, "textures/entity/toaddle/murderfrog.png");
//
//    public FrogRender(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new FrogModel<>(), 0.4F);
//    }
//
//    @Nullable
//    @Override
//    public ResourceLocation getTextureLocation(FrogEntity entity) {
//        switch(entity.getFrogType()) {
//            case 0:
//            default:
//                return FROG_GREEN_LOCATION;
//            case 1:
//                return FROG_RED_LOCATION;
//            case 2:
//                return FROG_BLACK_LOCATION;
//            case 3:
//                return FROG_RAINBOW_LOCATION;
//            case 4:
//                return FROG_YELLOW_LOCATION;
//            case 5:
//                return FROG_WHITE_LOCATION;
//            case 99:
//                return FROG_MURDER_LOCATION;
//        }
//    }
//}