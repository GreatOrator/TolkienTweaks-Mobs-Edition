package com.greatorator.tolkienmobs.item.container;

import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class BackpackItem extends BlockItem {
    public BackpackItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, @Nonnull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if(!worldIn.isClientSide)
        {
            if(hand == InteractionHand.MAIN_HAND)
            {
                if(itemstack.getItem() == this && !player.isCrouching())
                {
//                    NetworkHooks.openGui((ServerPlayerEntity) playerIn, this);
                }
            }
        }
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        InteractionResult actionresulttype = this.place((BlockPlaceContext) context);
        return !actionresulttype.consumesAction() ? this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() : actionresulttype;
    }

    public InteractionResult place(BlockPlaceContext context)
    {
        if(!context.canPlace() || (context.getHand() == InteractionHand.MAIN_HAND && !context.getPlayer().isCrouching()))
        {
            return InteractionResult.FAIL;
        }
        else
        {
            BlockPlaceContext blockitemusecontext = this.updatePlacementContext((BlockPlaceContext) context);

            if(blockitemusecontext == null)
            {
                return InteractionResult.FAIL;
            }
            else
            {
                BlockState blockstate = this.getPlacementState(blockitemusecontext);

                if(blockstate == null)
                {
                    return InteractionResult.FAIL;
                }

                else if(!this.placeBlock(blockitemusecontext, blockstate))
                {
                    return InteractionResult.FAIL;
                }
                else
                {
                    BlockPos blockpos = blockitemusecontext.getClickedPos();
                    Level world = blockitemusecontext.getLevel();
                    Player player = blockitemusecontext.getPlayer();
                    ItemStack itemstack = blockitemusecontext.getItemInHand();
                    BlockState blockstate1 = world.getBlockState(blockpos);
                    Block block = blockstate1.getBlock();

                    if(block == blockstate.getBlock())
                    {
                        this.updateCustomBlockEntityTag(blockpos, world, player, itemstack, blockstate1);
                        block.setPlacedBy(world, blockpos, blockstate1, player, itemstack);

                        if(itemstack.getTag() != null && world.getBlockEntity(blockpos) instanceof BackpackTile)
                        {
//                            ((TTMBackpackTile)world.getBlockEntity(blockpos)).loadAllData(itemstack.getTag());

                            if(itemstack.hasCustomHoverName())
                            {
//                                ((TTMBackpackTile) world.getBlockEntity(blockpos)).setCustomName(itemstack.getDisplayName());
                            }
                        }

                        if(player instanceof ServerPlayer)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
                        }
                    }

                    SoundType soundtype = blockstate1.getSoundType(world, blockpos, context.getPlayer());
                    world.playSound(player, blockpos, this.getPlaceSound(blockstate1, world, blockpos, player), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                    if(player == null || !player.getAbilities().instabuild)
                    {
                        itemstack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(world.isClientSide);
                }
            }
        }
    }
}