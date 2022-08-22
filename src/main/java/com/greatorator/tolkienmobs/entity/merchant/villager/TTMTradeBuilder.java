package com.greatorator.tolkienmobs.entity.merchant.villager;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.item.trinket.Trinket;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.potion.Effect;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class TTMTradeBuilder {
    private static final ArrayList<ArrayList<TTMTradeBuilder>> TRADES_LIST = new ArrayList<>();

    static
    {
        for(int i = 0; i <= 6; ++i)
        {
            TTMTradeBuilder.TRADES_LIST.add(new ArrayList<TTMTradeBuilder>());
        }
    }

    private static ArrayList<TTMTradeBuilder> getList(int i)
    {
        return TTMTradeBuilder.TRADES_LIST.get(i);
    }

    private static void register(int i, TTMTradeBuilder tradeBuilder)
    {
        TTMTradeBuilder.getList(i).add(tradeBuilder);
    }

    public static void forEachLevel(BiConsumer<Integer, TTMTradeBuilder> consumer)
    {
        ArrayList<TTMTradeBuilder> list;

        for(int i = 1; i <= 5; ++i)
        {
            list = TTMTradeBuilder.TRADES_LIST.get(i - 1);

            for(TTMTradeBuilder tradeBuilder : list)
            {
                consumer.accept(i, tradeBuilder);
            }
        }
    }

    public static void forEachLevel(Consumer<TTMTradeBuilder> consumer)
    {
        TTMTradeBuilder.forEachLevel((level, tradeBuilder) -> consumer.accept(tradeBuilder));
    }

    public static void forEachWanderer(Consumer<TTMTradeBuilder> consumer)
    {
        ArrayList<TTMTradeBuilder> list = TTMTradeBuilder.TRADES_LIST.get(5);

        for(TTMTradeBuilder tradeBuilder : list)
        {
            consumer.accept(tradeBuilder);
        }
    }

    public static void forEachWandererRare(Consumer<TTMTradeBuilder> consumer)
    {
        ArrayList<TTMTradeBuilder> list = TTMTradeBuilder.TRADES_LIST.get(6);

        for(TTMTradeBuilder tradeBuilder : list)
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

    public TTMTradeBuilder(int maxTrades, int xp, float priceMult)
    {
        this.price = null;
        this.price2 = (random) -> ItemStack.EMPTY;
        this.forSale = null;
        this.maxTrades = maxTrades;
        this.xp = xp;
        this.priceMult = priceMult;
        this.rare = false;
    }

    public TTMTradeBuilder setPrice(Function<Random, ItemStack> price)
    {
        this.price = price;
        return this;
    }

    public TTMTradeBuilder setPrice(Item item, int min, int max)
    {
        return this.setPrice(TTMTradeBuilder.createFunction(item, min, max));
    }

    public TTMTradeBuilder setPrice2(Function<Random, ItemStack> price2)
    {
        this.price2 = price2;
        return this;
    }

    public TTMTradeBuilder setPrice2(Item item, int min, int max)
    {
        return this.setPrice2(TTMTradeBuilder.createFunction(item, min, max));
    }

    public TTMTradeBuilder setForSale(Function<Random, ItemStack> forSale)
    {
        this.forSale = forSale;
        return this;
    }

    public TTMTradeBuilder setForSale(Item item, int min, int max)
    {
        return this.setForSale(TTMTradeBuilder.createFunction(item, min, max));
    }

    public TTMTradeBuilder setForSaleWithPotion(Function<Random, ItemStack> forSale)
    {
        this.forSale = forSale;
        return this;
    }

    public TTMTradeBuilder setTrinketForSaleWithPotion(Item item)
    {
        Function<Random, ItemStack> generator = (random) -> {
            Effect randomEffect = TTMConfig.potionArray[random.nextInt(TTMConfig.potionArray.length)];
            return  ((Trinket) item).getTrinketForEffect(randomEffect);
        };
        return this.setForSaleWithPotion(generator);
    }

    public TTMTradeBuilder setEmeraldPrice(int emeralds)
    {
        return this.setPrice((random) -> new ItemStack(Items.EMERALD, emeralds));
    }

    public TTMTradeBuilder setEmeraldPriceFor(int emeralds, Item item, int amt)
    {
        this.setEmeraldPrice(emeralds);
        return this.setForSale((random) -> new ItemStack(item, amt));
    }

    public TTMTradeBuilder setEmeraldPriceFor(int emeralds, Item item)
    {
        return this.setEmeraldPriceFor(emeralds, item, 1);
    }

    public TTMTradeBuilder setEmeraldPrice(int min, int max)
    {
        return this.setPrice(Items.EMERALD, min, max);
    }

    public TTMTradeBuilder setEmeraldPriceFor(int min, int max, Item item, int amt)
    {
        this.setEmeraldPrice(min, max);
        return this.setForSale((random) -> new ItemStack(item, amt));
    }

    public TTMTradeBuilder setEmeraldPriceFor(int min, int max, Item item)
    {
        return this.setEmeraldPriceFor(min, max, item, 1);
    }

    public TTMTradeBuilder setRare()
    {
        this.rare = true;
        return this;
    }

    public boolean canBuild()
    {
        return this.price != null && this.forSale != null;
    }

    public VillagerTrades.ITrade build()
    {
        return (entity, random) -> !this.canBuild() ? null : new MerchantOffer(this.price.apply(random), this.price2.apply(random), this.forSale.apply(random), this.maxTrades, this.xp, this.priceMult);
    }

    public static Function<Random, ItemStack> createFunction(Item item, int min, int max)
    {
        return (random) -> new ItemStack(item, random.nextInt(max) + min);
    }

    // --- registering stuff ---

    protected TTMTradeBuilder register(int index)
    {
        TTMTradeBuilder.register(index, this);
        return this;
    }

    /**
     * @param level 1-5
     */
    public TTMTradeBuilder registerLevel(int level)
    {
        return this.register(level - 1);
    }

    public TTMTradeBuilder registerWanderer(boolean rare)
    {
        return this.register(rare ? 6 : 5);
    }
}
