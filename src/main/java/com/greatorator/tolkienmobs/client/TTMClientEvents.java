package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketRing;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by brandon3055 on 20/02/19.
 */
public class TTMClientEvents {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void itemColourEvent(ColorHandlerEvent.Item event) {

        event.getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex < 1) {
                return -1;
            }
            else {
                Potion potion = ItemTrinketRing.getPotion(stack);
                if (potion != null) {
                    return potion.getLiquidColor();
                }
            }
            return -1;
        }, TTMFeatures.TRINKET_RING, TTMFeatures.TRINKET_CHARM, TTMFeatures.TRINKET_BELT, TTMFeatures.TRINKET_AMULET);
    }
}