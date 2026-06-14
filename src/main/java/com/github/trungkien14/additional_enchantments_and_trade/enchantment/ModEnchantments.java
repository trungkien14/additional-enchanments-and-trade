package com.github.trungkien14.additional_enchantments_and_trade.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    // Khai báo RegistryKey
    public static final RegistryKey<Enchantment> WHIRLWIND_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of("additional_enchantments_and_trade", "whirwind"));
    public static final RegistryKey<Enchantment> COUNTER_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of("additional_enchantments_and_trade", "counter"));
    // Không cần khai báo biến Enchantment tĩnh (static) nữa để tránh lỗi
    public static void registerModEnchantments() {
        // Chỉ cần gọi hàm này trong ModInitializer
    }
}