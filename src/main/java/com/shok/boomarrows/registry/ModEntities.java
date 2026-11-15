package com.shok.boomarrows.registry;

import com.shok.boomarrows.BoomArrows;
import com.shok.boomarrows.entity.ExplosiveArrowEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> EXPLOSIVE_ARROW_KEY = ResourceKey.create(
        Registries.ENTITY_TYPE,
        Identifier.fromNamespaceAndPath(BoomArrows.MOD_ID, "explosive_arrow")
    );

    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        EXPLOSIVE_ARROW_KEY,
        EntityType.Builder.<ExplosiveArrowEntity>of(ExplosiveArrowEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(EXPLOSIVE_ARROW_KEY)
    );

    public static void register() {
    }
}