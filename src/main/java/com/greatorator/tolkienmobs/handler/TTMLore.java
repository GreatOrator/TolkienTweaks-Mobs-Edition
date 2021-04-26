package com.greatorator.tolkienmobs.handler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class TTMLore extends Item {
    public boolean hasEffectOverride = false;
    private boolean canSpawnEntity = false;
    private boolean itemHasUse = false;
    private boolean hasLore = false;

    public TTMLore(Item.Properties props) {
        super(props);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        if (hasLore) {
            tooltip.add(new TranslationTextComponent(getDescriptionId() + ".lore").withStyle(TextFormatting.GOLD));
        }
    }

//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        ItemStack itemstack = playerIn.getHeldItem(handIn);
//        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
//        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK && itemHasUse) {
//            return ActionResult.resultPass(itemstack);
//        } else if (!(worldIn instanceof ServerWorld)) {
//            return ActionResult.resultSuccess(itemstack);
//        } else {
//            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
//            BlockPos blockpos = blockraytraceresult.getPos();
//            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
//                return ActionResult.resultPass(itemstack);
//            } else if (worldIn.isBlockModifiable(playerIn, blockpos) && canSpawnEntity && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
//                EntityType<?> entitytype = new EntityTMMithrilGolem(worldIn);
//                if (entitytype.spawn((ServerWorld) worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false) == null) {
//                    return ActionResult.resultPass(itemstack);
//                } else {
//                    if (!playerIn.abilities.isCreativeMode) {
//                        itemstack.shrink(1);
//                    }
//
//                    playerIn.addStat(Stats.ITEM_USED.get(this));
//                    return ActionResult.resultConsume(itemstack);
//                }
//            } else {
//                return ActionResult.resultFail(itemstack);
//            }
//        }
//    }

    public TTMLore setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    public TTMLore setHasLore() {
        this.hasLore = true;
        return this;
    }

    public TTMLore setItemHasUse() {
        this.itemHasUse = true;
        return this;
    }

    public TTMLore setSpawnInfo() {
        this.canSpawnEntity = true;
        return this;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasEffectOverride || super.isFoil(stack);
    }
}