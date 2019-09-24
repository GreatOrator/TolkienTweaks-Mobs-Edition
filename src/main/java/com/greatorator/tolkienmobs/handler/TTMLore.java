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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TTMLore extends ItemBCore {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;
    private boolean itemHasUse = true;

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

        if (itemHasUse) {
            RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
            EntityTMMithrilGolem entity = new EntityTMMithrilGolem(worldIn);
            BlockPos blockpos = raytraceresult.getBlockPos();
            if (worldIn.isRemote)
            {
                return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
            }
            else
            {
                if (entity == null)
                {
                    return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
                }
                else {
                    if (canSpawnEntity) {

                        if (!playerIn.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);
                        }

                        entity.setPosition((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 1.0D, (double) blockpos.getZ() + 0.5D);
                        worldIn.spawnEntity(entity);
                    }
                }
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    public TTMLore setSpawnInfo(boolean isSpawnItem) {
        this.canSpawnEntity = isSpawnItem;
        return this;
    }

    public TTMLore setItemHasUse(boolean itemHasUse) {
        this.itemHasUse = itemHasUse;
        return this;
    }
}