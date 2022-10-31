package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.brandon3055.brandonscore.lib.datamanager.DataFlags;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedByte;
import com.brandon3055.brandonscore.lib.datamanager.ManagedShort;
import com.greatorator.tolkienmobs.container.capability.CamoSpawnerLogic;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class CamoSpawnerTile extends TileBCore implements MenuProvider, IInteractTile {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");
    public final ManagedShort minSpawnDelay = register(new ManagedShort("min_spawn_delay", (short) 200, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedShort maxSpawnDelay = register(new ManagedShort("max_spawn_delay", (short) 800, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedShort activationRange = register(new ManagedShort("activation_range", (short) 16, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedShort spawnRange = register(new ManagedShort("spawn_range", (short) 4, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedByte spawnCount = register(new ManagedByte("spawn_count", 4, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedShort spawnDelay = register(new ManagedShort("spawn_delay", (short) 20, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedByte maxCluster = register(new ManagedByte("max_cluster", 6, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedByte clusterRange = register(new ManagedByte("cluster_range", 5, SAVE_BOTH_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool requirePlayer = register(new ManagedBool("require_player", true, DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool ignoreSpawnReq = register(new ManagedBool("ignore_spawn", false, DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));
    public final ManagedBool spawnerParticles = register(new ManagedBool("spawner_particles", true, DataFlags.SAVE_NBT_SYNC_TILE, CLIENT_CONTROL));

    public List<CompoundTag> entityTags = new ArrayList<>();
    public CamoSpawnerLogic spawnerLogic = new CamoSpawnerLogic(this);

    public CamoSpawnerTile(BlockPos pos, BlockState state) {
        super(TolkienTiles.CAMO_SPAWNER_TILE.get(), pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        spawnerLogic.tick();
    }

    public void onRightClick(BlockState state, Player playerEntity, InteractionHand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);
        if (playerEntity.isCreative() && stack.isEmpty()) {
            if (playerEntity.isCrouching()) {
                entityTags.clear();
                if (level.isClientSide) {
                    playerEntity.sendMessage(new TranslatableComponent(MODID + ".msg.cleared.entities").withStyle(ChatFormatting.DARK_GREEN), Util.NIL_UUID);
                }
            } else {
                openGUI(playerEntity, this, worldPosition);
            }
        }

        if (playerEntity.isCreative()) {
            if (stack.getItem() instanceof SpawnEggItem) {
                CompoundTag entityTag;
                if (stack.hasTag() && stack.getOrCreateTag().contains("EntityTag")) {
                    entityTag = stack.getOrCreateTag().getCompound("EntityTag");
                } else {
                    EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                    String name = type.getRegistryName().toString();
                    entityTag = new CompoundTag();
                    entityTag.putString("id", name);
                }

                if (!entityTags.contains(entityTag)) {
                    entityTags.add(entityTag);
                    playerEntity.sendMessage(new TranslatableComponent(MODID + ".msg.added.entities").append(" " + entityTag).withStyle(ChatFormatting.DARK_GREEN), Util.NIL_UUID);
                }
            }
        }
    }

    @Override
    public void receivePacketFromServer(MCDataInput data, int id) {
        super.receivePacketFromServer(data, id);
        if (id == 0) {
            int c = data.readVarInt();
            entityTags.clear();
            for (int i = 0; i < c; i++) {
                entityTags.add(data.readCompoundNBT());
            }
        }
    }

    @Override
    public void receivePacketFromClient(MCDataInput data, ServerPlayer client, int id) {
        super.receivePacketFromClient(data, client, id);
        if (id == 1) {
            CompoundTag s = data.readCompoundNBT();
            entityTags.remove(s);
            sendPacketToClient(client, output -> {
                output.writeVarInt(entityTags.size());
                entityTags.forEach(output::writeCompoundNBT);
            }, 0);
        }
    }

    public void openGUI(Player player, MenuProvider containerSupplier, BlockPos pos) {
        if (player instanceof ServerPlayer) {
            sendPacketToClient((ServerPlayer) player, output -> {
                output.writeVarInt(entityTags.size());
                entityTags.forEach(output::writeCompoundNBT);
            }, 0);
            NetworkHooks.openGui((ServerPlayer) player, this, worldPosition);
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
        return new ContainerBCTile<>(TolkienContainers.CAMO_SPAWNER_CONTAINER, windowID, playerInventory, this);
    }

    @Override
    public void writeExtraNBT(CompoundTag compound) {
        super.writeExtraNBT(compound);
        ListTag list = new ListTag();
        entityTags.forEach(list::add);
        compound.put("entity_list", list);
    }

    @Override
    public void readExtraNBT(CompoundTag compound) {
        super.readExtraNBT(compound);
        entityTags.clear();
        ListTag list = compound.getList("entity_list", 10);
        list.forEach(inbt -> entityTags.add((CompoundTag) inbt));
    }
}
