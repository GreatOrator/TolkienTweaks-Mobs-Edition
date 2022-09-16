package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class HypeHornItem extends Item {
    public HypeHornItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide){
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundGenerator.hype_horn.get(), SoundCategory.PLAYERS, 5F, 0.95F + world.random.nextFloat() * 0.1F);
        }
        return super.use(world, player, hand);
    }
}
