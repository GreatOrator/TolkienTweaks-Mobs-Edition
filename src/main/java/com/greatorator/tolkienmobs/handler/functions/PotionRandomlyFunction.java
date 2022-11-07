package com.greatorator.tolkienmobs.handler.functions;

//
//public class PotionRandomlyFunction extends LootItemConditionalFunction {
//   private static final Logger LOGGER = LogUtils.getLogger();
//   final List<Potion> potionEffects;
//
//   public PotionRandomlyFunction(LootItemCondition[] conditions, Collection<Potion> p_80419_) {
//      super(conditions);
//      this.potionEffects = ImmutableList.copyOf(p_80419_);
//   }
//
//   public LootItemFunctionType getType() {
//      return TolkienLootFunctions.POTION_RANDOMLY;
//   }
//
//   public ItemStack run(ItemStack itemStack, LootContext context) {
//      Random random = context.getRandom();
//      Potion potion;
//      if (this.potionEffects.isEmpty()) {
//         boolean flag = itemStack.is(TolkienTags.items.TRINKET);
//         for (MobEffect p : TTMConfig.potionArray) {
//            if (p != null) {
//               this.getTrinketForEffect(p);
//            }
//         }
//
//         List<Potion> list = Registry.POTION.stream().filter(Potion::isDiscoverable).filter((potion1) -> {
//            return flag || potion1.canEnchant(itemStack);
//         }).collect(Collectors.toList());
//         if (list.isEmpty()) {
//            LOGGER.warn("Couldn't find a compatible potion for {}", (Object)itemStack);
//            return itemStack;
//         }
//
//         potion = list.get(random.nextInt(list.size()));
//      } else {
//         potion = this.potionEffects.get(random.nextInt(this.potionEffects.size()));
//      }
//
//      return potionItem(itemStack, potion, random);
//   }
//
//   public ItemStack getTrinketForEffect(MobEffect effect) {
//      ItemStack stack = new ItemStack(this);
//      PotionUtils.setCustomEffects(stack, Collections.singleton(new MobEffectInstance(effect, 60*20, 0, false, false)));
//      return stack;
//   }
//
//   private static ItemStack potionItem(ItemStack itemStack, Potion potion, Random random) {
//      int i = Mth.nextInt(random, potion.getMinLevel(), potion.getMaxLevel());
//      if (itemStack.is(TolkienTags.items.TRINKET)) {
//         itemStack = new ItemStack(Items.ENCHANTED_BOOK);
//         PotionUtils.setCustomEffects(itemStack, new EnchantmentInstance(potion, i));
//      } else {
//         itemStack.enchant(potion, i);
//      }
//
//      return itemStack;
//   }
//
//   public static PotionRandomlyFunction.Builder randomPotion() {
//      return new PotionRandomlyFunction.Builder();
//   }
//
//   public static LootItemConditionalFunction.Builder<?> randomApplicablePotion() {
//      return simpleBuilder((p_80438_) -> {
//         return new PotionRandomlyFunction(p_80438_, ImmutableList.of());
//      });
//   }
//
//   public static class Builder extends LootItemConditionalFunction.Builder<PotionRandomlyFunction.Builder> {
//      private final Set<Potion> potions = Sets.newHashSet();
//
//      protected PotionRandomlyFunction.Builder getThis() {
//         return this;
//      }
//
//      public PotionRandomlyFunction.Builder withEnchantment(Potion p_80445_) {
//         this.potions.add(p_80445_);
//         return this;
//      }
//
//      public LootItemFunction build() {
//         return new PotionRandomlyFunction(this.getConditions(), this.potions);
//      }
//   }
//
//   public static class Serializer extends LootItemConditionalFunction.Serializer<PotionRandomlyFunction> {
//      public void serialize(JsonObject p_80454_, PotionRandomlyFunction p_80455_, JsonSerializationContext p_80456_) {
//         super.serialize(p_80454_, p_80455_, p_80456_);
//         if (!p_80455_.potionEffects.isEmpty()) {
//            JsonArray jsonarray = new JsonArray();
//
//            for(Potion potion : p_80455_.potionEffects) {
//               ResourceLocation resourcelocation = Registry.POTION.getKey(potion);
//               if (resourcelocation == null) {
//                  throw new IllegalArgumentException("Don't know how to serialize potion " + potion);
//               }
//
//               jsonarray.add(new JsonPrimitive(resourcelocation.toString()));
//            }
//
//            p_80454_.add("enchantments", jsonarray);
//         }
//
//      }
//
//      public PotionRandomlyFunction deserialize(JsonObject p_80450_, JsonDeserializationContext p_80451_, LootItemCondition[] p_80452_) {
//         List<Potion> list = Lists.newArrayList();
//         if (p_80450_.has("enchantments")) {
//            for(JsonElement jsonelement : GsonHelper.getAsJsonArray(p_80450_, "enchantments")) {
//               String s = GsonHelper.convertToString(jsonelement, "enchantment");
//               Potion potion = Registry.POTION.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
//                  return new JsonSyntaxException("Unknown potion '" + s + "'");
//               });
//               list.add(potion);
//            }
//         }
//
//         return new PotionRandomlyFunction(p_80452_, list);
//      }
//   }
//}