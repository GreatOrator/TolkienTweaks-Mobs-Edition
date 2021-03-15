package com.greatorator.tolkienmobs.init;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TTMColor extends ItemColors {
    private final java.util.Map<net.minecraftforge.registries.IRegistryDelegate<Item>, IItemColor> colors = new java.util.HashMap<>();

//    @SubscribeEvent
//    public static void itemColourEvent(ColorHandlerEvent.Item event) {
//        event.getItemColors().register((stack, tintIndex) -> {
//            if (tintIndex < 1) {
//                return -1;
//            }
//            else {
//                Potion potion = TrinketAmulet.getPotion(stack);
//                if (potion != null) {
//                    return PotionUtils.getColor(stack);
//                }
//            }
//            return -1;
//        }, TTMContent.TRINKET_AMULET.get(), TTMContent.TRINKET_BELT.get(), TTMContent.TRINKET_CHARM.get(), TTMContent.TRINKET_RING.get());
//    }

//    public static TTMColor init(BlockColors colors) {
//        TTMColor trinketColors = new TTMColor();
//
//        trinketColors.register((stack, color) -> {
//            return color > 0 ? -1 : PotionUtils.getColor(stack);
//        }, TTMContent.TRINKET_AMULET.get(), TTMContent.TRINKET_BELT.get(), TTMContent.TRINKET_CHARM.get(), TTMContent.TRINKET_RING.get());
//
//        net.minecraftforge.client.ForgeHooksClient.onItemColorsInit(trinketColors, colors);
//        LOGGER.info(trinketColors);
//        return trinketColors;
//    }

    @Override
    public void register(IItemColor itemColor, IItemProvider... itemsIn) {
        for (IItemProvider iitemprovider : itemsIn) {
            this.colors.put(iitemprovider.asItem().delegate, itemColor);
        }
    }
}
