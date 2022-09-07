package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BackpackItem extends BlockItem {
    public BackpackItem(Block block, Properties properties) {
        super(block, properties);
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
//                    BlockPos pos = new BlockPos(playerIn.getX(), playerIn.getY(), playerIn.getZ());
//                    TileEntity tileEntity = worldIn.getBlockEntity(pos);
//                    NetworkHooks.openGui((ServerPlayerEntity) playerIn, (TTMBackpackTile) tileEntity, pos);

                }
            }
        }
        return ActionResult.success(itemstack);
    }

    @OnlyIn(Dist.CLIENT)
    private void openGui(ItemStack stack, PlayerEntity player) {
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

                        if(itemstack.getTag() != null && world.getBlockEntity(blockpos) instanceof BackpackTile)
                        {
//                            ((TTMBackpackTile)world.getBlockEntity(blockpos)).loadAllData(itemstack.getTag());

                            if(itemstack.hasCustomHoverName())
                            {
//                                ((TTMBackpackTile) world.getBlockEntity(blockpos)).setCustomName(itemstack.getDisplayName());
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
}
