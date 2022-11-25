package com.greatorator.tolkienmobs.handler.functions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.*;
import com.greatorator.tolkienmobs.init.TolkienLootFunctions;
import com.greatorator.tolkienmobs.item.basic.TrinketItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;


public class TrinketRandomlyFunction extends LootItemConditionalFunction {
    final List<MobEffect> effects;

    protected TrinketRandomlyFunction(LootItemCondition[] conditions, Collection<MobEffect> collection) {
        super(conditions);
        this.effects = ImmutableList.copyOf(collection);
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

    public static LootItemConditionalFunction.Builder<?> randomApplicableEffect() {
        return simpleBuilder((conditions) -> {
            return new TrinketRandomlyFunction(conditions, ImmutableList.of());
        });
    }


    public static class Serializer extends LootItemConditionalFunction.Serializer<TrinketRandomlyFunction> {
        public void serialize(JsonObject object, TrinketRandomlyFunction function, JsonSerializationContext context) {
            super.serialize(object, function, context);
            if (!function.effects.isEmpty()) {
                JsonArray jsonarray = new JsonArray();

                for(MobEffect mobEffects : function.effects) {
                    ResourceLocation resourcelocation = ForgeRegistries.MOB_EFFECTS.getKey(mobEffects);
                    if (resourcelocation == null) {
                        throw new IllegalArgumentException("Don't know how to serialize effect " + mobEffects);
                    }

                    jsonarray.add(new JsonPrimitive(resourcelocation.toString()));
                }

                object.add("mobeffects", jsonarray);
            }

        }

        @Override
        public TrinketRandomlyFunction deserialize(JsonObject object, JsonDeserializationContext context, LootItemCondition[] conditions) {
            List<MobEffect> list = Lists.newArrayList();
            if (object.has("mobeffects")) {
                for(JsonElement jsonelement : GsonHelper.getAsJsonArray(object, "mobeffects")) {
                    ResourceLocation id = ResourceLocation.tryParse(GsonHelper.convertToString(jsonelement, "mobeffect"));
                    MobEffect mobEffects = ForgeRegistries.MOB_EFFECTS.containsKey(id) ? ForgeRegistries.MOB_EFFECTS.getValue(id) : null;
                    list.add(mobEffects);
                }
            }
            return new TrinketRandomlyFunction(conditions, list);
        }
    }
}