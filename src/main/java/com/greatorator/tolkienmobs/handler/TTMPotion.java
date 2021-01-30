//package com.greatorator.tolkienmobs.handler;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class TTMPotion extends Potion {
//    private static final ResourceLocation resource = new ResourceLocation(TolkienMobs.MODID + ":textures/gui/potion_effects.png");
//    private final int iconIndex;
//
//    public TTMPotion(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
//        super(isBadEffectIn, liquidColorIn);
//        setRegistryName(new ResourceLocation(TolkienMobs.MODID + ":" + name));
//        setPotionName(TolkienMobs.MODID + ".effect." + name);
//        this.iconIndex = iconIndex;
//    }
//
//    public boolean hasEffect(LivingEntity entity) {
//        return hasEffect(entity, this);
//    }
//
//    public boolean hasEffect(LivingEntity entity, Potion potion) {
//        return entity.getActivePotionEffect(potion) != null;
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
//        render(x + 6, y + 7, 1);
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
//        render(x + 3, y + 3, alpha);
//    }
//
//    @Override
//    public boolean isReady(int duration, int amplifier)
//    {
//        return true;
//    }
//
//    public boolean canAmplify() {
//        return true;
//    }
//
//    public void affectEntity(Entity thrownPotion, Entity thrower, LivingEntity entity, int amplifier, double potency) {
//        this.performEffect(entity, amplifier);
//    }
//
//    @SideOnly(Side.CLIENT)
//    private void render(int x, int y, float alpha) {
//        Minecraft.getMinecraft().renderEngine.bindTexture(resource);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buf = tessellator.getBuffer();
//        buf.begin(7, DefaultVertexFormats.POSITION_TEX);
//        GlStateManager.color(1, 1, 1, alpha);
//
//        int textureX = iconIndex % 8 * 18;
//        int textureY = 198 + iconIndex / 8 * 18;
//
//        buf.pos(x, y + 18, 0).tex(textureX * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
//        buf.pos(x + 18, y + 18, 0).tex((textureX + 18) * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
//        buf.pos(x + 18, y, 0).tex((textureX + 18) * 0.00390625, textureY * 0.00390625).endVertex();
//        buf.pos(x, y, 0).tex(textureX * 0.00390625, textureY * 0.00390625).endVertex();
//
//        tessellator.draw();
//    }
//}