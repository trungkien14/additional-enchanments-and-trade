package com.github.trungkien14.additional_enchantments_and_trade.util.spear.whirlwind;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;

import java.util.List;

public class WhirlwindDamage {
    public static void whirlwindDamage(PlayerEntity player) {
        ItemStack spear = player.getMainHandStack();

        if (!spear.isEmpty() && spear.isDamageable()) {
            spear.damage(100,player, EquipmentSlot.MAINHAND);
        }

        Box area = player.getBoundingBox().expand(4.D, 2.0D, 4.0D);
        List<Entity> targets = player.getWorld().getOtherEntities(player, area, entity -> entity instanceof LivingEntity);

        for (Entity target : targets) {
            if(target instanceof LivingEntity livingEntity) {
                livingEntity.damage(player.getDamageSources().playerAttack(player),4.5F);
                livingEntity.takeKnockback(1.5, player.getX() - target.getX(), player.getZ() - target.getZ());
            }
        }
    }
}