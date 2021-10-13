package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.item.trinket.Trinket;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TTMColor {

    public static void itemColourEvent(ColorHandlerEvent.Item event) {
        event.getItemColors().register(new TrinketColour(), TTMContent.TRINKET_CHARM.get(), TTMContent.TRINKET_AMULET.get(), TTMContent.TRINKET_BELT.get(), TTMContent.TRINKET_RING.get());
    }

    private static class TrinketColour implements IItemColor {
        @Override
        public int getColor(ItemStack stack, int layer) {
            List<EffectInstance> effects = Trinket.getEffects(stack);
            if (!effects.isEmpty() && layer == 1) {
                return effects.get(0).getEffect().getColor();
            }
            return 0xFFFFFF;
        }
    }
}
