package com.greatorator.tolkienmobs.entity.tile;

import codechicken.lib.data.MCDataInput;
import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.lib.datamanager.DataFlags;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.brandon3055.brandonscore.lib.datamanager.ManagedByte;
import com.brandon3055.brandonscore.lib.datamanager.ManagedShort;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.container.capability.CamoSpawnerLogic;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.CLIENT_CONTROL;
import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class CamoSpawnerTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
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

    public List<CompoundNBT> entityTags = new ArrayList<>();
    public CamoSpawnerLogic spawnerLogic = new CamoSpawnerLogic(this);

    public CamoSpawnerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CamoSpawnerTile() {
        super(TTMContent.CAMO_SPAWNER_TILE.get());
    }

    @Override
    public void tick() {
        super.tick();
        spawnerLogic.tick();
    }

    public void onRightClick(BlockState state, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);
        if (playerEntity.isCreative() && stack.isEmpty()) {
            if (playerEntity.isCrouching()) {
                entityTags.clear();
                if (level.isClientSide) {
                    playerEntity.sendMessage(new TranslationTextComponent(MODID + ".msg.cleared.entities").withStyle(TextFormatting.DARK_GREEN), Util.NIL_UUID);
                }
            } else {
                openGUI(playerEntity, this, worldPosition);
            }
        }

        if (playerEntity.isCreative()) {
            if (stack.getItem() instanceof SpawnEggItem) {
                CompoundNBT entityTag;
                if (stack.hasTag() && stack.getOrCreateTag().contains("EntityTag")) {
                    entityTag = stack.getOrCreateTag().getCompound("EntityTag");
                } else {
                    EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                    String name = type.getRegistryName().toString();
                    entityTag = new CompoundNBT();
                    entityTag.putString("id", name);
                }

                if (!entityTags.contains(entityTag)) {
                    entityTags.add(entityTag);
                    playerEntity.sendMessage(new TranslationTextComponent(MODID + ".msg.added.entities").append(" " + entityTag).withStyle(TextFormatting.DARK_GREEN), Util.NIL_UUID);
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
    public void receivePacketFromClient(MCDataInput data, ServerPlayerEntity client, int id) {
        super.receivePacketFromClient(data, client, id);
        if (id == 1) {
            CompoundNBT s = data.readCompoundNBT();
            entityTags.remove(s);
            sendPacketToClient(client, output -> {
                output.writeVarInt(entityTags.size());
                entityTags.forEach(output::writeCompoundNBT);
            }, 0);
        }
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos) {
        if (player instanceof ServerPlayerEntity) {
            sendPacketToClient((ServerPlayerEntity) player, output -> {
                output.writeVarInt(entityTags.size());
                entityTags.forEach(output::writeCompoundNBT);
            }, 0);
            NetworkHooks.openGui((ServerPlayerEntity) player, containerSupplier, pos);
        }
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerBCTile<>(TTMContent.CAMO_SPAWNER_CONTAINER, windowID, playerInventory, this);
    }

    @Override
    public void writeExtraNBT(CompoundNBT compound) {
        super.writeExtraNBT(compound);
        ListNBT list = new ListNBT();
        entityTags.forEach(list::add);
        compound.put("entity_list", list);
    }

    @Override
    public void readExtraNBT(CompoundNBT compound) {
        super.readExtraNBT(compound);
        entityTags.clear();
        ListNBT list = compound.getList("entity_list", 10);
        list.forEach(inbt -> entityTags.add((CompoundNBT) inbt));
    }
}
