package com.greatorator.tolkienmobs.integration;

import com.brandon3055.brandonscore.capability.MultiCapabilityProvider;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.lib.FlamingBalrog;
import net.minecraft.data.TagsProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
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
public class TTMCurios extends TTMEquipMgr {

    public static final Tags.IOptionalNamedTag<Item> CURIO_TAG = ItemTags.createOptional(new ResourceLocation("curios", "curio"));
    public static final Tags.IOptionalNamedTag<Item> NECK_TAG = ItemTags.createOptional(new ResourceLocation("curios", "necklace"));
    public static final Tags.IOptionalNamedTag<Item> BELT_TAG = ItemTags.createOptional(new ResourceLocation("curios", "belt"));
    public static final Tags.IOptionalNamedTag<Item> CHARM_TAG = ItemTags.createOptional(new ResourceLocation("curios", "charm"));
    public static final Tags.IOptionalNamedTag<Item> RING_TAG = ItemTags.createOptional(new ResourceLocation("curios", "ring"));
    public static final Tags.IOptionalNamedTag<Item> HAND_TAG = ItemTags.createOptional(new ResourceLocation("curios", "hands"));
    public static final Tags.IOptionalNamedTag<Item> HEAD_TAG = ItemTags.createOptional(new ResourceLocation("curios", "head"));
    public static final Tags.IOptionalNamedTag<Item> BODY_TAG = ItemTags.createOptional(new ResourceLocation("curios", "body"));

    @CapabilityInject(ICurio.class)
    public static Capability<ICurio> CURIO_CAP = null;

    public static void sendIMC(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BODY.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BRACELET.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HANDS.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CURIO.getMessageBuilder().build());
    }

    @Override
    public void addEquipCaps(ItemStack stack, MultiCapabilityProvider provider) {
        provider.addUnsavedCap(CURIO_CAP, new TTMCurioWrapper(stack));
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
            ICuriosItemHandler handler = optional.orElseThrow(FlamingBalrog::new);
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

    /**
     * Data Gen
     */
    public static void generateTags(Function<Tags.IOptionalNamedTag<Item>, TagsProvider.Builder<Item>> builder) {
        builder.apply(NECK_TAG).add(
                TTMContent.TRINKET_AMULET.get());
        builder.apply(BELT_TAG).add(
                TTMContent.TRINKET_BELT.get());
        builder.apply(CHARM_TAG).add(
                TTMContent.TRINKET_CHARM.get());
        builder.apply(RING_TAG).add(
                TTMContent.TRINKET_RING.get());
        builder.apply(HAND_TAG).add(
                TTMContent.TRINKET_GLOVE.get());
        builder.apply(HEAD_TAG).add(
                TTMContent.TRINKET_HAT.get());
    }
}
