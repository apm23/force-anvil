package com.anjas.forceanvil.mixin;

import java.util.function.BiConsumer;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHelper.class)
public abstract class ForceEnchantmentEffectMixin {
    @Inject(
        method = "forEachModifier(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void forceanvil$applyEveryEnchantOnActualSlot(
        ItemStack stack,
        EquipmentSlot slot,
        BiConsumer<Holder<Enchantment>, Integer> action,
        CallbackInfo ci
    ) {
        ItemEnchantments enchantments = EnchantmentHelper.getEnchantmentsForCrafting(stack);
        for (Holder<Enchantment> enchantment : enchantments.keySet()) {
            int level = enchantments.getLevel(enchantment);
            if (level > 0) {
                action.accept(enchantment, level);
            }
        }
        ci.cancel();
    }
}
