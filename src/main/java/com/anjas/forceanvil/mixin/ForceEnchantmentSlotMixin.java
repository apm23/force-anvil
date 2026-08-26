package com.anjas.forceanvil.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class ForceEnchantmentSlotMixin {
    @Inject(method = "slotMatches", at = @At("HEAD"), cancellable = true)
    private void forceanvil$allowEveryEquipmentSlot(EquipmentSlot slot, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
