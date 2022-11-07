package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.item.basic.TrinketItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ColorHandler {

    public static void itemColourEvent(ColorHandlerEvent.Item event) {
        event.getItemColors().register(new TrinketColour(), TolkienItems.TRINKET_CHARM.get(), TolkienItems.TRINKET_AMULET.get(), TolkienItems.TRINKET_BELT.get(), TolkienItems.TRINKET_RING.get(), TolkienItems.TRINKET_GLOVE.get(), TolkienItems.TRINKET_HAT.get(), TolkienItems.TRINKET_CLOAK.get());
    }

    private static class TrinketColour implements ItemColor {
        @Override
        public int getColor(ItemStack stack, int layer) {
            List<MobEffectInstance> effects = TrinketItem.getEffects(stack);
            if (!effects.isEmpty() && layer == 1) {
                return effects.get(0).getEffect().getColor();
            }
            return 0xFFFFFF;
        }
    }
}
