package com.anjas.forceanvil.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class ForceAnvilMenuMixin {
    @Inject(method = "createResult", at = @At("TAIL"))
    private void forceanvil$mergeAllEnchantments(CallbackInfo ci) {
        AnvilMenu menu = (AnvilMenu) (Object) this;
        ItemStack base = menu.getSlot(0).getItem();
        ItemStack addition = menu.getSlot(1).getItem();
        if (base.isEmpty() || addition.isEmpty()) return;

        ItemEnchantments incoming = EnchantmentHelper.getEnchantmentsForCrafting(addition);
        if (incoming.isEmpty()) return;

        ItemStack vanillaResult = menu.getSlot(2).getItem();
        ItemStack result = vanillaResult.isEmpty() ? base.copy() : vanillaResult.copy();

        EnchantmentHelper.updateEnchantments(result, mutable -> {
            for (Holder<Enchantment> enchantment : incoming.keySet()) {
                mutable.upgrade(enchantment, incoming.getLevel(enchantment));
            }
        });

        menu.getSlot(2).set(result);
        menu.setData(0, 1);
        menu.broadcastChanges();
    }
}
