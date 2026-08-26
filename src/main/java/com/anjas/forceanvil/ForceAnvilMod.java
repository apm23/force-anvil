package com.anjas.forceanvil;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ForceAnvilMod implements ModInitializer {
    public static final String MOD_ID = "forceanvil";
    public static final ResourceLocation FORCE_ANVIL_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "force_anvil");
    public static final ResourceKey<Block> FORCE_ANVIL_BLOCK_KEY = ResourceKey.create(Registries.BLOCK, FORCE_ANVIL_ID);
    public static final ResourceKey<Item> FORCE_ANVIL_ITEM_KEY = ResourceKey.create(Registries.ITEM, FORCE_ANVIL_ID);

    public static final Block FORCE_ANVIL = new ForceAnvilBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL).setId(FORCE_ANVIL_BLOCK_KEY)
    );
    public static final Item FORCE_ANVIL_ITEM = new BlockItem(
        FORCE_ANVIL,
        new Item.Properties().setId(FORCE_ANVIL_ITEM_KEY).useBlockDescriptionPrefix()
    );

    @Override
    public void onInitialize() {
        BuiltInRegistries.register(BuiltInRegistries.BLOCK, FORCE_ANVIL_ID, FORCE_ANVIL);
        BuiltInRegistries.register(BuiltInRegistries.ITEM, FORCE_ANVIL_ID, FORCE_ANVIL_ITEM);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
            .register(entries -> entries.accept(FORCE_ANVIL_ITEM));
    }
}
