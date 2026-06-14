package com.github.trungkien14.additional_enchantments_and_trade.mixin;

import com.github.trungkien14.additional_enchantments_and_trade.util.spear.whirlwind.WhirlwindDamage;
import com.github.trungkien14.additional_enchantments_and_trade.util.spear.whirlwind.WhirlwindEffect;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.github.trungkien14.additional_enchantments_and_trade.enchantment.ModEnchantments;

@Mixin(PlayerEntity.class)
public abstract class WhirlwindAttackMixing {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack stack = player.getMainHandStack();

        // Lấy đối tượng enchantment từ Registry thông qua Key
        var enchantmentWhirlwindEntry = player.getWorld().getRegistryManager()
                .get(RegistryKeys.ENCHANTMENT)
                .getEntry(ModEnchantments.WHIRLWIND_KEY);

        // Kiểm tra nếu tìm thấy bùa và người chơi có bùa đó
        if (enchantmentWhirlwindEntry.isPresent() && EnchantmentHelper.getLevel(enchantmentWhirlwindEntry.get(), stack) > 0) {
            if (player.handSwingProgress > 0 && !player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
                WhirlwindEffect.whirlwindAnimation(player, player.getWorld());
                WhirlwindDamage.whirlwindDamage(player);
                player.getItemCooldownManager().set(stack.getItem(), 60);
            }
        }
    }


}