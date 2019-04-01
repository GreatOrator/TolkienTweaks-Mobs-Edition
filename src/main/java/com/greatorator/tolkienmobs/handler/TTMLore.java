package com.greatorator.tolkienmobs.handler;

import com.brandon3055.brandonscore.items.ItemBCore;
import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
import mezz.jei.util.Translator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TTMLore extends ItemBCore {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;

    public TTMLore(int stackSize) {
        this.setMaxStackSize(stackSize);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, list, flagIn);
        list.add(TextFormatting.GOLD + Translator.translateToLocalFormatted("lore." + getUnlocalizedName()));
    }

    public TTMLore setEffectOverride(boolean hasEffectOverride) {
        this.hasEffectOverride = hasEffectOverride;
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasEffectOverride || super.hasEffect(stack);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }

        if (!worldIn.isRemote)
        {
            if(canSpawnEntity){
                EntityTMMithrilGolem entityType = new EntityTMMithrilGolem(worldIn);
                entityType.setPosition(playerIn.posX+2, playerIn.posY, playerIn.posZ+2);
                worldIn.spawnEntity(entityType);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    public TTMLore setSpawnInfo(boolean isSpawnItem) {
        this.canSpawnEntity = isSpawnItem;
        return this;
    }
}