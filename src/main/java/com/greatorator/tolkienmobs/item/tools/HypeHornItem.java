package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class HypeHornItem extends Item {
    public HypeHornItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, @Nonnull InteractionHand hand) {
        if (!worldIn.isClientSide){
            worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), TolkienSounds.hype_horn.get(), SoundSource.PLAYERS, 5F, 0.95F + worldIn.random.nextFloat() * 0.1F);
        }
        return super.use(worldIn, player, hand);
    }
}
