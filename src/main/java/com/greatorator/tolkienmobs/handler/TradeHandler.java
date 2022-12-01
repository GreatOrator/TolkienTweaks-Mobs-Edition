package com.greatorator.tolkienmobs.handler;


import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.item.basic.TrinketItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class TradeHandler {
    private static final ArrayList<ArrayList<TradeHandler>> TRADES_LIST = new ArrayList<>();

    static
    {
        for(int i = 0; i <= 6; ++i)
        {
            TradeHandler.TRADES_LIST.add(new ArrayList<TradeHandler>());
        }
    }

    private static ArrayList<TradeHandler> getList(int i)
    {
        return TradeHandler.TRADES_LIST.get(i);
    }

    private static void register(int i, TradeHandler tradeBuilder)
    {
        TradeHandler.getList(i).add(tradeBuilder);
    }

    public static void forEachLevel(BiConsumer<Integer, TradeHandler> consumer)
    {
        ArrayList<TradeHandler> list;

        for(int i = 1; i <= 5; ++i)
        {
            list = TradeHandler.TRADES_LIST.get(i - 1);

            for(TradeHandler tradeBuilder : list)
            {
                consumer.accept(i, tradeBuilder);
            }
        }
    }

    public static void forEachLevel(Consumer<TradeHandler> consumer)
    {
        TradeHandler.forEachLevel((level, tradeBuilder) -> consumer.accept(tradeBuilder));
    }

    public static void forEachWanderer(Consumer<TradeHandler> consumer)
    {
        ArrayList<TradeHandler> list = TradeHandler.TRADES_LIST.get(5);

        for(TradeHandler tradeBuilder : list)
        {
            consumer.accept(tradeBuilder);
        }
    }

    public static void forEachWandererRare(Consumer<TradeHandler> consumer)
    {
        ArrayList<TradeHandler> list = TradeHandler.TRADES_LIST.get(6);

        for(TradeHandler tradeBuilder : list)
        {
            consumer.accept(tradeBuilder);
        }
    }

    protected Function<Random, ItemStack> price;
    protected Function<Random, ItemStack> price2;
    protected Function<Random, ItemStack> forSale;

    protected final int maxTrades;
    protected final int xp;
    protected final float priceMult;

    protected boolean rare;

    public TradeHandler(int maxTrades, int xp, float priceMult)
    {
        this.price = null;
        this.price2 = (random) -> ItemStack.EMPTY;
        this.forSale = null;
        this.maxTrades = maxTrades;
        this.xp = xp;
        this.priceMult = priceMult;
        this.rare = false;
    }

    public TradeHandler setPrice(Function<Random, ItemStack> price)
    {
        this.price = price;
        return this;
    }

    public TradeHandler setPrice(Item item, int min, int max)
    {
        return this.setPrice(TradeHandler.createFunction(item, min, max));
    }

    public TradeHandler setPrice2(Function<Random, ItemStack> price2)
    {
        this.price2 = price2;
        return this;
    }

    public TradeHandler setPrice2(Item item, int min, int max)
    {
        return this.setPrice2(TradeHandler.createFunction(item, min, max));
    }

    public TradeHandler setForSale(Function<Random, ItemStack> forSale)
    {
        this.forSale = forSale;
        return this;
    }

    public TradeHandler setForSale(Item item, int min, int max)
    {
        return this.setForSale(TradeHandler.createFunction(item, min, max));
    }

    public TradeHandler setForSaleWithPotion(Function<Random, ItemStack> forSale)
    {
        this.forSale = forSale;
        return this;
    }

    public TradeHandler setTrinketForSaleWithPotion(Item item)
    {
        Function<Random, ItemStack> generator = (random) -> {
            MobEffect randomEffect = TolkienConfig.potionArray[random.nextInt(TolkienConfig.potionArray.length)];
            return  ((TrinketItem) item).getTrinketForEffect(randomEffect);
        };
        return this.setForSaleWithPotion(generator);
    }

    public TradeHandler setEmeraldPrice(int emeralds)
    {
        return this.setPrice((random) -> new ItemStack(Items.EMERALD, emeralds));
    }

    public TradeHandler setEmeraldPriceFor(int emeralds, Item item, int amt)
    {
        this.setEmeraldPrice(emeralds);
        return this.setForSale((random) -> new ItemStack(item, amt));
    }

    public TradeHandler setEmeraldPriceFor(int emeralds, Item item)
    {
        return this.setEmeraldPriceFor(emeralds, item, 1);
    }

    public TradeHandler setEmeraldPrice(int min, int max)
    {
        return this.setPrice(Items.EMERALD, min, max);
    }

    public TradeHandler setEmeraldPriceFor(int min, int max, Item item, int amt)
    {
        this.setEmeraldPrice(min, max);
        return this.setForSale((random) -> new ItemStack(item, amt));
    }

    public TradeHandler setEmeraldPriceFor(int min, int max, Item item)
    {
        return this.setEmeraldPriceFor(min, max, item, 1);
    }

    public TradeHandler setRare()
    {
        this.rare = true;
        return this;
    }

    public boolean canBuild()
    {
        return this.price != null && this.forSale != null;
    }

    public VillagerTrades.ItemListing build()
    {
        return (entity, random) -> !this.canBuild() ? null : new MerchantOffer(this.price.apply(random), this.price2.apply(random), this.forSale.apply(random), this.maxTrades, this.xp, this.priceMult);
    }

    public static Function<Random, ItemStack> createFunction(Item item, int min, int max)
    {
        return (random) -> new ItemStack(item, random.nextInt(max) + min);
    }

    // --- registering stuff ---

    protected TradeHandler register(int index)
    {
        TradeHandler.register(index, this);
        return this;
    }

    /**
     * @param level 1-5
     */
    public TradeHandler registerLevel(int level)
    {
        return this.register(level - 1);
    }

    public TradeHandler registerWanderer(boolean rare)
    {
        return this.register(rare ? 6 : 5);
    }
}