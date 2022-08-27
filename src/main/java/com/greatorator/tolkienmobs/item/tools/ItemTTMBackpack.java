package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.entity.item.render.ISRenderTTMBackpackTileEntity;
import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
import com.greatorator.tolkienmobs.entity.tile.inventory.TTMBackpackInventory;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.integration.curios.TTMCurios;
import com.greatorator.tolkienmobs.utils.TTMReference;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TTMContent.decoGroup;

public class ItemTTMBackpack extends BlockItem {
    public ItemTTMBackpack(Block block)
    {
        super(block, new Item.Properties().tab(decoGroup).stacksTo(1).setISTER(() -> ISRenderTTMBackpackTileEntity::new));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        if(!worldIn.isClientSide)
        {
            if(handIn == Hand.MAIN_HAND)
            {
                if(itemstack.getItem() == this && !playerIn.isCrouching())
                {
                    TTMBackpackInventory.openGUI((ServerPlayerEntity)playerIn, playerIn.inventory.getSelected(), TTMReference.ITEM_SCREEN_ID);
                }
            }
        }
        return ActionResult.pass(itemstack);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context)
    {
        ActionResultType actionresulttype = this.place(new BlockItemUseContext(context));
        return !actionresulttype.consumesAction() ? this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() : actionresulttype;
    }

    @Override
    public ActionResultType place(BlockItemUseContext context)
    {
        if(!context.canPlace() || (context.getHand() == Hand.MAIN_HAND && !context.getPlayer().isCrouching()))
        {
            return ActionResultType.FAIL;
        }
        else
        {
            BlockItemUseContext blockitemusecontext = this.updatePlacementContext(context);

            if(blockitemusecontext == null)
            {
                return ActionResultType.FAIL;
            }
            else
            {
                BlockState blockstate = this.getPlacementState(blockitemusecontext);

                if(blockstate == null)
                {
                    return ActionResultType.FAIL;
                }

                else if(!this.placeBlock(blockitemusecontext, blockstate))
                {
                    return ActionResultType.FAIL;
                }
                else
                {
                    BlockPos blockpos = blockitemusecontext.getClickedPos();
                    World world = blockitemusecontext.getLevel();
                    PlayerEntity player = blockitemusecontext.getPlayer();
                    ItemStack itemstack = blockitemusecontext.getItemInHand();
                    BlockState blockstate1 = world.getBlockState(blockpos);
                    Block block = blockstate1.getBlock();

                    if(block == blockstate.getBlock())
                    {
                        this.updateCustomBlockEntityTag(blockpos, world, player, itemstack, blockstate1);
                        block.setPlacedBy(world, blockpos, blockstate1, player, itemstack);

                        if(itemstack.getTag() != null && world.getBlockEntity(blockpos) instanceof TTMBackpackTile)
                        {
                            ((TTMBackpackTile)world.getBlockEntity(blockpos)).loadAllData(itemstack.getTag());

                            if(itemstack.hasCustomHoverName())
                            {
                                ((TTMBackpackTile) world.getBlockEntity(blockpos)).setCustomName(itemstack.getDisplayName());
                            }
                        }

                        if(player instanceof ServerPlayerEntity)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)player, blockpos, itemstack);
                        }
                    }

                    SoundType soundtype = blockstate1.getSoundType(world, blockpos, context.getPlayer());
                    world.playSound(player, blockpos, this.getPlaceSound(blockstate1, world, blockpos, player), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                    if(player == null || !player.abilities.instabuild)
                    {
                        itemstack.shrink(1);
                    }

                    return ActionResultType.sidedSuccess(world.isClientSide);
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ITextComponent getName(ItemStack stack)
    {
        return new TranslationTextComponent(this.getDescriptionId(stack)).append(" ").append(new TranslationTextComponent("block.travelersbackpack.travelers_backpack"));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        if(TTMHelper.isCuriosInstalled)
        {
            return new ICapabilityProvider()
            {
                final LazyOptional<ICurio> curio = LazyOptional.of(TTMCurios::createBackpackProvider);

                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
                {
                    return CuriosCapability.ITEM.orEmpty(cap, curio);
                }
            };
        }
        return null;
    }
}
