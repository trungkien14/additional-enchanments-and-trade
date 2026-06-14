package com.github.trungkien14.additional_enchantments_and_trade.util.shield.counter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class CounterLogic {
    public static void counterLogic(LivingEntity defender, Entity attacker, float amount) {
        // min reflected dame is half heart
        float reflectedDamage = Math.max(1.0f, amount * 0.3f);
        DamageSource counterSource = defender.getDamageSources().thorns(defender);

        if (attacker instanceof LivingEntity livingAttacker) {

            boolean success = livingAttacker.damage(counterSource, reflectedDamage);
            if (success) {
                livingAttacker.getWorld().sendEntityStatus(livingAttacker, (byte) 2);
                livingAttacker.takeKnockback(
                        0.4,
                        defender.getX() - livingAttacker.getX(),
                        defender.getZ() - livingAttacker.getZ()
                );

            }
        }
    }
}