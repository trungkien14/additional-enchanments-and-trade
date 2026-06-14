package com.github.trungkien14.additional_enchantments_and_trade.mixin;

import com.github.trungkien14.additional_enchantments_and_trade.enchantment.ModEnchantments;
import com.github.trungkien14.additional_enchantments_and_trade.util.shield.counter.CounterLogic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class CounterMixing {
    @Inject(
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damageShield(F)V")
    )
    private void onBlockedByShield(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity defender = (LivingEntity) (Object) this;

        if (defender.getWorld().isClient()) {
            return;
        }

        Entity attacker = source.getAttacker();

        if (attacker instanceof LivingEntity) {
            ItemStack stack = defender.getActiveItem();
            if (stack.isEmpty()) {
                stack = defender.getOffHandStack();
            }

            var enchantmentCounterEntry = defender.getWorld().getRegistryManager()
                    .get(RegistryKeys.ENCHANTMENT)
                    .getEntry(ModEnchantments.COUNTER_KEY);

            if (enchantmentCounterEntry.isPresent()) {
                int enchantLevel = EnchantmentHelper.getLevel(enchantmentCounterEntry.get(), stack);

                if (enchantLevel > 0) {
                    CounterLogic.counterLogic(defender, attacker, amount);
                }
            }
        }
    }
}