package com.shok.boomarrows.client;

import com.shok.boomarrows.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BoomArrowsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_ARROW, ExplosiveArrowRenderer::new);
    }
}