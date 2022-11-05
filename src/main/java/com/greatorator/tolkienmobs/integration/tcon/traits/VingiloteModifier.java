package com.greatorator.tolkienmobs.integration.tcon.traits;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

import javax.annotation.Nullable;
import java.util.List;

public class VingiloteModifier extends NoLevelsModifier {
    private static final Component MINING_SPEED = TConstruct.makeTranslation("modifier", "dwarven.mining_speed");
    private static final float BOOST_DISTANCE = 64f;
    private static final float DEBUFF_RANGE = 128f;
    private static final float BONUS = 6;

    private static float getBoost(Level world, int y, int level, float baseSpeed, float modifier) {
        if (y < BOOST_DISTANCE) {
            float scale = Mth.clamp((BOOST_DISTANCE - y) / BOOST_DISTANCE, 0, 2);
            return baseSpeed + (level * scale * BONUS * modifier);
        }

        float baselineDebuff = Math.max(world.getMaxBuildHeight() - (DEBUFF_RANGE + BOOST_DISTANCE), 96);
        if (y > baselineDebuff) {
            if (y >= baselineDebuff + DEBUFF_RANGE) {
                return baseSpeed * 0.25f;
            }
            return baseSpeed * (1 - ((y - baselineDebuff) / DEBUFF_RANGE * 0.75f));
        }
        return baseSpeed;
    }

    @Override
    public void onBreakSpeed(IToolStackView tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (!isEffective) {
            return;
        }
        event.setNewSpeed(getBoost(event.getPlayer().level, event.getPos().getY(), level, event.getNewSpeed(), miningSpeedModifier * tool.getMultiplier(ToolStats.MINING_SPEED)));
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        if (tool.hasTag(TinkerTags.Items.HARVEST)) {
            double boost;
            if (player != null && key == TooltipKey.SHIFT) {
                boost = getBoost(player.level, (int)player.getY(), level, 1, 1f) - 1;
                if (boost < 0) {
                    if (boost <= -0.01) {
                        addPercentTooltip(MINING_SPEED, boost, tooltip);
                    }
                    return;
                }
            } else {
                boost = BONUS * level;
            }
            if (boost >= 0.01) {
                addFlatBoost(MINING_SPEED, boost * tool.getMultiplier(ToolStats.MINING_SPEED), tooltip);
            }
        }
    }

    @Override
    public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
        return 0;
    }

    @Override
    public int getPriority() {
        return 125;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, int level) {
        return 0xFFFFFF;
    }
}
