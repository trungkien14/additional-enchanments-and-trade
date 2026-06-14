package com.github.trungkien14.additional_enchanments_and_trade;

import com.github.trungkien14.additional_enchanments_and_trade.trade.ModTrades;
import net.fabricmc.api.ModInitializer;
import com.github.trungkien14.additional_enchanments_and_trade.enchantment.ModEnchantments;

public class Additional_enchanments_and_trade implements ModInitializer {
    @Override
    public void onInitialize() {
        // DÒNG NÀY LÀ CHÌA KHÓA, call register
        ModEnchantments.registerModEnchantments();
        ModTrades.registerTrades();
    }
}