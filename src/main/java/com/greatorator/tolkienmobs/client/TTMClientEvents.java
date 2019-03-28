package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.init.PotionInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.item.magical.ItemTrinketRing;
import com.greatorator.tolkienmobs.item.potiontypes.PotionElementalDrowning;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Created by brandon3055 on 20/02/19. */
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

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void renderAir(RenderGameOverlayEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        if(event.getType() == RenderGameOverlayEvent.ElementType.AIR) {

            EntityLivingBase player = (EntityLivingBase)mc.getRenderViewEntity();
            String drown = PotionElementalDrowning.NAME;

            if (player != null && drown != null && PotionInit.ELEMENTAL_DROWNING != null && player.isPotionActive(PotionInit.ELEMENTAL_DROWNING)) {
                event.setCanceled(true);

                if(!player.isInsideOfMaterial(Material.WATER)) {
                    int air = player.getEntityData().getInteger(PotionElementalDrowning.TAG_NAME);

                    mc.mcProfiler.startSection("air");
                    GlStateManager.enableBlend();
                    int width = event.getResolution().getScaledWidth();
                    int height = event.getResolution().getScaledHeight();
                    int left = width / 2 + 91;
                    int top = height - GuiIngameForge.right_height;

                    int full = MathHelper.ceil((double)(air - 2) * 10.0D / 300.0D);
                    int partial = MathHelper.ceil((double)air * 10.0D / 300.0D) - full;

                    for (int i = 0; i < full + partial; ++i)
                    {
                        mc.ingameGUI.drawTexturedModalRect(left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9);
                    }
                    GuiIngameForge.right_height += 10;


                    GlStateManager.disableBlend();
                    mc.mcProfiler.endSection();
                }
            }
        }
    }
}