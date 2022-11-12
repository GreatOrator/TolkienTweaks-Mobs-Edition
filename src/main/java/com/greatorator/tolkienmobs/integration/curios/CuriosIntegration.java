package com.greatorator.tolkienmobs.integration.curios;

import com.brandon3055.brandonscore.capability.MultiCapabilityProvider;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/** Borrowed from Draconic Evolution */
public class CuriosIntegration extends EquipmentManager {

    public static final TagKey<Item> CURIO_TAG = ItemTags.create(new ResourceLocation("curios", "curio"));
    public static final TagKey<Item> HEAD_TAG = ItemTags.create(new ResourceLocation("curios", "head"));
    public static final TagKey<Item> NECK_TAG = ItemTags.create(new ResourceLocation("curios", "necklace"));
    public static final TagKey<Item> BACK_TAG = ItemTags.create(new ResourceLocation("curios", "back"));
    public static final TagKey<Item> BODY_TAG = ItemTags.create(new ResourceLocation("curios", "body"));
    public static final TagKey<Item> HAND_TAG = ItemTags.create(new ResourceLocation("curios", "hands"));
    public static final TagKey<Item> RING_TAG = ItemTags.create(new ResourceLocation("curios", "ring"));
    public static final TagKey<Item> BELT_TAG = ItemTags.create(new ResourceLocation("curios", "belt"));
    public static final TagKey<Item> CHARM_TAG = ItemTags.create(new ResourceLocation("curios", "charm"));

    public static Capability<ICurio> CURIO_CAP = CapabilityManager.get(new CapabilityToken<>() {});

    public static void sendIMC(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CURIO.getMessageBuilder().size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BODY.getMessageBuilder().size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HANDS.getMessageBuilder().size(2).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().size(3).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().size(1).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().size(1).build());
    }

    @Override
    public void addEquipCaps(ItemStack stack, MultiCapabilityProvider provider) {
        provider.addUnsavedCap(CURIO_CAP, new CurioWrapper(stack));
    }

    @Override
    public LazyOptional<IItemHandlerModifiable> getInventory(LivingEntity entity) {
        return CuriosApi.getCuriosHelper().getEquippedCurios(entity);
    }

    @Override
    public ItemStack findMatchingItem(Item item, LivingEntity entity) {
        return CuriosApi.getCuriosHelper()
                .findEquippedCurio(item, entity)
                .map(ImmutableTriple::getRight)
                .orElse(ItemStack.EMPTY);
    }

    @Override
    public ItemStack findMatchingItem(Predicate<ItemStack> predicate, LivingEntity entity) {
        return CuriosApi.getCuriosHelper()
                .findEquippedCurio(predicate, entity)
                .map(ImmutableTriple::getRight)
                .orElse(ItemStack.EMPTY);
    }

    @Override
    public List<ResourceLocation> getSlotIcons(LivingEntity entity) {
        LazyOptional<ICuriosItemHandler> optional = CuriosApi.getCuriosHelper().getCuriosHandler(entity);
        if (optional.isPresent()) {
            ICuriosItemHandler handler = optional.orElseThrow(TolkienExceptions::new);
            List<ResourceLocation> icons = new ArrayList<>();
            handler.getCurios().forEach((s, h) -> {
                for (int i = 0; i < h.getSlots(); i++) {
                    icons.add(CuriosApi.getIconHelper().getIcon(s));
                }
            });
            return icons;
        }
        return Collections.emptyList();
    }

//    public static ICurio createBackpackProvider()
//    {
//        return new CurioWrapper(ItemStack.EMPTY);
//    }
//
//    public static Optional<ImmutableTriple<String, Integer, ItemStack>> getCurioBackpack(LivingEntity livingEntity)
//    {
//        Predicate<ItemStack> backpack = stack -> stack.getItem() instanceof BackpackItem;
//        return CuriosApi.getCuriosHelper().findEquippedCurio(backpack, livingEntity);
//    }
//
//    public static ItemStack getCurioBackpackStack(Player player)
//    {
//        if(getCurioBackpack(player).isPresent())
//        {
//            return getCurioBackpack(player).get().getRight();
//        }
//        return ItemStack.EMPTY;
//    }

    /**
     * Data Gen
     */
    public static void generateTags(Function<TagKey<Item>, TagsProvider.TagAppender<Item>> builder) {
        builder.apply(NECK_TAG).add(
                TolkienItems.TRINKET_AMULET.get());
        builder.apply(BELT_TAG).add(
                TolkienItems.TRINKET_BELT.get());
        builder.apply(CHARM_TAG).add(
                TolkienItems.TRINKET_CHARM.get());
        builder.apply(RING_TAG).add(
                TolkienItems.TRINKET_RING.get());
        builder.apply(HAND_TAG).add(
                TolkienItems.TRINKET_GLOVE.get());
        builder.apply(HEAD_TAG).add(
                TolkienItems.TRINKET_HAT.get());
        builder.apply(BACK_TAG).add(
                TolkienItems.ITEM_BACKPACK.get());
        builder.apply(BODY_TAG).add(
                TolkienItems.TRINKET_CLOAK.get());
    }
}
