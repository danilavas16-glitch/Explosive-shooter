package com.shok.boomarrows.registry;

import com.shok.boomarrows.BoomArrows;
import com.shok.boomarrows.item.ExplosiveArrowItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {

    public static final ResourceKey<Item> EXPLOSIVE_ARROW_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(BoomArrows.MOD_ID, "explosive_arrow")
    );

    public static final Item EXPLOSIVE_ARROW = Registry.register(
        BuiltInRegistries.ITEM,
        EXPLOSIVE_ARROW_KEY,
        new ExplosiveArrowItem(new Item.Properties().setId(EXPLOSIVE_ARROW_KEY))
    );

    public static void register() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(EXPLOSIVE_ARROW);
        });
    }
}