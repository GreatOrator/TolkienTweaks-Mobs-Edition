package com.greatorator.tolkienmobs.handler.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.init.TolkienLootFunctions;
import com.greatorator.tolkienmobs.item.basic.TrinketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import javax.annotation.Nonnull;


public class TrinketRandomlyFunction extends LootItemConditionalFunction {
    protected TrinketRandomlyFunction(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {
        if (stack.getItem() instanceof TrinketItem) {
            stack = TrinketItem.getRandomTrinketEffect(stack);
            return stack;
        }else {
            return null;
        }
    }

    @Nonnull
    @Override
    public LootItemFunctionType getType() {
        return TolkienLootFunctions.TRINKET_RANDOMLY.get();
    }

    public static LootItemConditionalFunction.Builder<?> builder() {
        return simpleBuilder((conditions) -> {
            return new TrinketRandomlyFunction(conditions);
        });
    }


    public static class Serializer extends LootItemConditionalFunction.Serializer<TrinketRandomlyFunction> {
        @Override
        public TrinketRandomlyFunction deserialize(JsonObject object, JsonDeserializationContext context, LootItemCondition[] conditions) {
            return new TrinketRandomlyFunction(conditions);
        }
    }
}