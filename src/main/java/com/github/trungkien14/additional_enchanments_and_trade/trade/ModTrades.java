package com.github.trungkien14.additional_enchanments_and_trade.trade;

import com.github.trungkien14.additional_enchanments_and_trade.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class ModTrades {
    public static void registerTrades() {
        registerEnchantedBooks();
        registerFood();
    }

    private static void registerEnchantedBooks() {
        // Thêm bùa Whirlwind I cho Thủ thư cấp 1 với giá 16 Emerald + 1 Sách
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1, factories -> {
            factories.add(createEnchantedBookTrade(ModEnchantments.WHIRLWIND_KEY, 1, 25,60, true));
        });

        // Nếu bạn có bùa khác (ví dụ Lifesteal), bạn chỉ cần 1 dòng như thế này:
        // TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 2, factories -> {
        //     factories.add(createEnchantedBookTrade(ModEnchantments.LIFESTEAL_KEY, 2, 32, false));
        // });
    }

    private static TradeOffers.Factory createEnchantedBookTrade(RegistryKey<Enchantment> enchantKey, int enchantLevel, int minCost, int maxCost, boolean requireBook) {
        return (Entity entity, Random random) -> {
            var registryManager = entity.getWorld().getRegistryManager();
            var enchantmentRegistry = registryManager.get(RegistryKeys.ENCHANTMENT);
            var entry = enchantmentRegistry.getEntry(enchantKey);

            ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
            if (entry.isPresent()) {
                ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
                builder.add(entry.get(), enchantLevel);
                book.set(DataComponentTypes.STORED_ENCHANTMENTS, builder.build());
            }

            int cost = random.nextBetween(minCost,maxCost);
            Optional<TradedItem> secondItem = requireBook ? Optional.of(new TradedItem(Items.BOOK, 1)) : Optional.empty();

            return new TradeOffer(
                    new TradedItem(Items.EMERALD, cost),
                    secondItem,
                    book,
                    1, 5, 0.05f
            );
        };
    }

    private static void registerFood() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER,1, factories -> {
            factories.add((entity, random) -> new TradeOffer (
                    new TradedItem(Items.WHEAT_SEEDS,16),
                    Optional.empty(),
                    new ItemStack(Items.EMERALD,1),
                    16,2,0.1f
            ));

            factories.add((entity, random) -> new TradeOffer (
                    new TradedItem(Items.MELON_SEEDS,16),
                    Optional.empty(),
                    new ItemStack(Items.EMERALD,1),
                    12,2,0.1f
            ));

            factories.add((entity, random) -> new TradeOffer (
                    new TradedItem(Items.BEETROOT_SEEDS,16),
                    Optional.empty(),
                    new ItemStack(Items.EMERALD,1),
                    12,2,0.1f
            ));

            factories.add((entity, random) -> new TradeOffer (
                    new TradedItem(Items.BEETROOT_SEEDS,16),
                    Optional.empty(),
                    new ItemStack(Items.EMERALD,1),
                    12,2,0.1f
            ));

            factories.add((entity, random) -> new TradeOffer (
                    new TradedItem(Items.EGG,16),
                    Optional.empty(),
                    new ItemStack(Items.EMERALD,1),
                    20,2,0.1f
            ));
        });
    }
}