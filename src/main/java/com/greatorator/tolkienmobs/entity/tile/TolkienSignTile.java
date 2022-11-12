package com.greatorator.tolkienmobs.entity.tile;

import com.greatorator.tolkienmobs.init.TolkienTiles;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * Created by brandon3055 on 20/08/2022
 */
public class TolkienSignTile extends BlockEntity {
    private static final String[] RAW_TEXT_FIELD_NAMES = new String[]{"Text1", "Text2", "Text3", "Text4"};
    private static final String[] FILTERED_TEXT_FIELD_NAMES = new String[]{"FilteredText1", "FilteredText2", "FilteredText3", "FilteredText4"};
    private final Component[] messages = new Component[]{TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY};
    private final Component[] filteredMessages = new Component[]{TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY};
    private boolean isEditable = true;
    private boolean hasGlowingText;
    private Player playerWhoMayEdit;
    private FormattedCharSequence[] renderMessages;
    private boolean renderMessagedFiltered;
    private DyeColor color = DyeColor.BLACK;

    public TolkienSignTile(BlockPos pos, BlockState state) {
        super(TolkienTiles.SIGN_TILE.get(), pos, state);
    }

    @Override
    public void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        for(int i = 0; i < 4; ++i) {
            Component component = this.messages[i];
            String s = Component.Serializer.toJson(component);
            compoundTag.putString(RAW_TEXT_FIELD_NAMES[i], s);
            Component component1 = this.filteredMessages[i];
            if (!component1.equals(component)) {
                compoundTag.putString(FILTERED_TEXT_FIELD_NAMES[i], Component.Serializer.toJson(component1));
            }
        }
        compoundTag.putString("Color", this.color.getName());
        compoundTag.putBoolean("GlowingText", this.hasGlowingText);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        this.isEditable = false;
        super.load(compoundTag);
        this.color = DyeColor.byName(compoundTag.getString("Color"), DyeColor.BLACK);

        for(int i = 0; i < 4; ++i) {
            String s = compoundTag.getString(RAW_TEXT_FIELD_NAMES[i]);
            Component component = this.loadLine(s);
            this.messages[i] = component;
            String s1 = FILTERED_TEXT_FIELD_NAMES[i];
            if (compoundTag.contains(s1, 8)) {
                this.filteredMessages[i] = this.loadLine(compoundTag.getString(s1));
            } else {
                this.filteredMessages[i] = component;
            }
        }
        this.renderMessages = null;
    }

    private Component loadLine(String p_155712_) {
        Component component = this.deserializeTextSafe(p_155712_);
        if (this.level instanceof ServerLevel) {
            try {
                return ComponentUtils.updateForEntity(this.createCommandSourceStack((ServerPlayer)null), component, (Entity)null, 0);
            } catch (CommandSyntaxException commandsyntaxexception) {
            }
        }

        return component;
    }

    private Component deserializeTextSafe(String p_155721_) {
        try {
            Component component = Component.Serializer.fromJson(p_155721_);
            if (component != null) {
                return component;
            }
        } catch (Exception exception) {
        }

        return TextComponent.EMPTY;
    }

    @OnlyIn(Dist.CLIENT)
    public Component getMessage(int line, boolean b) {
        return this.getMessages(b)[line];
    }

    public void setMessage(int line, Component component) {
        this.setMessage(line, component, component);
    }

    public void setMessage(int line, Component component, Component component1) {
        this.messages[line] = component;
        this.filteredMessages[line] = component1;
        this.renderMessages = null;
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public FormattedCharSequence[] getRenderMessages(boolean p_155718_, Function<Component, FormattedCharSequence> p_155719_) {
        if (this.renderMessages == null || this.renderMessagedFiltered != p_155718_) {
            this.renderMessagedFiltered = p_155718_;
            this.renderMessages = new FormattedCharSequence[4];

            for(int i = 0; i < 4; ++i) {
                this.renderMessages[i] = p_155719_.apply(this.getMessage(i, p_155718_));
            }
        }

        return this.renderMessages;
    }

    private Component[] getMessages(boolean p_155725_) {
        return p_155725_ ? this.filteredMessages : this.messages;
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public boolean isEditable() {
        return this.isEditable;
    }

    @OnlyIn(Dist.CLIENT)
    public void setEditable(boolean p_145913_1_) {
        this.isEditable = p_145913_1_;
        if (!p_145913_1_) {
            this.playerWhoMayEdit = null;
        }

    }

    public void setAllowedPlayerEditor(Player uuid) {
        this.playerWhoMayEdit = uuid;
    }

    @Nullable
    public Player getPlayerWhoMayEdit() {
        return this.playerWhoMayEdit;
    }

    public boolean executeClickCommands(ServerPlayer p_155710_) {
        for(Component component : this.getMessages(p_155710_.isTextFilteringEnabled())) {
            Style style = component.getStyle();
            ClickEvent clickevent = style.getClickEvent();
            if (clickevent != null && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
                p_155710_.getServer().getCommands().performCommand(this.createCommandSourceStack(p_155710_), clickevent.getValue());
            }
        }
        return true;
    }

    public CommandSourceStack createCommandSourceStack(@Nullable ServerPlayer p_59736_) {
        String s = p_59736_ == null ? "Sign" : p_59736_.getName().getString();
        Component component = (Component)(p_59736_ == null ? new TextComponent("Sign") : p_59736_.getDisplayName());
        return new CommandSourceStack(CommandSource.NULL, Vec3.atCenterOf(this.worldPosition), Vec2.ZERO, (ServerLevel)this.level, 2, s, component, this.level.getServer(), p_59736_);
    }

    public DyeColor getColor() {
        return this.color;
    }

    public boolean setColor(DyeColor p_214068_1_) {
        if (p_214068_1_ != this.getColor()) {
            this.color = p_214068_1_;
            this.setChanged();
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
            return true;
        } else {
            return false;
        }
    }

    public boolean hasGlowingText() {
        return this.hasGlowingText;
    }

    public boolean setHasGlowingText(boolean p_155723_) {
        if (this.hasGlowingText != p_155723_) {
            this.hasGlowingText = p_155723_;
            this.markUpdated();
            return true;
        } else {
            return false;
        }
    }

    private void markUpdated() {
        this.setChanged();
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}