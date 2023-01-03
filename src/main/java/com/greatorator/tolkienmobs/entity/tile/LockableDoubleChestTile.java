package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedString;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import com.greatorator.tolkienmobs.item.keys.KeyBaseItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static net.minecraft.core.Direction.UP;

@SuppressWarnings({"removal" })
public class LockableDoubleChestTile extends TileBCore implements MenuProvider, IInteractTile, IAnimatable {
    public AnimationFactory factory = new AnimationFactory(this);
    public static final ContainerSlotLayout.LayoutFactory<LockableDoubleChestTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.mainInventory);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(54);
    public final ManagedString keyCode = register(new ManagedString("KeyCode", SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool chestOpen = register(new ManagedBool("ChestOpen", false, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));

    public LockableDoubleChestTile(BlockPos pos, BlockState state) {
        super(TolkienTiles.LOCKABLE_DOUBLE_CHEST_TILE.get(), pos, state);

        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(mainInventory).setInsertCheck((slot, stack) -> slot > 0), UP, null);
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventory).saveBoth().syncTile();
    }

    public void onRightClick(Player playerEntity, InteractionHand hand) {
        if (!playerEntity.level.isClientSide()) {
            if(!playerEntity.isCreative() || (KeyBaseItem.getCode(playerEntity.getItemInHand(hand)).equals(this.keyCode.get()))) {
                this.chestOpen.set(true);
            }
            NetworkHooks.openGui((ServerPlayer) playerEntity, this, worldPosition);
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
        return new ContainerBCTile<>(TolkienContainers.LOCKABLE_DOUBLE_CHEST_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
    }

    /** Animation */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!this.chestOpen.get()) {
            level.playSound((Player) null, worldPosition, TolkienSounds.chest_close.get(), SoundSource.BLOCKS, 0.3F, 0.5F);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("open", false));
        } else {
            level.playSound((Player) null, worldPosition, TolkienSounds.chest_open.get(), SoundSource.BLOCKS, 0.3F, 0.5F);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("close", false));
            this.chestOpen.set(false);
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
