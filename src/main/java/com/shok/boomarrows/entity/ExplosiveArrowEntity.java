package com.shok.boomarrows.entity;

import com.shok.boomarrows.registry.ModEntities;
import com.shok.boomarrows.registry.ModItems;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ExplosiveArrowEntity extends AbstractArrow {

    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> type, Level level) {
        super(type, level);
    }

    public ExplosiveArrowEntity(Level level, LivingEntity owner, ItemStack pickupStack, ItemStack weaponStack) {
        super(ModEntities.EXPLOSIVE_ARROW, owner, level, pickupStack, weaponStack);
    }

    public ExplosiveArrowEntity(Level level, double x, double y, double z, ItemStack pickupStack, ItemStack weaponStack) {
        super(ModEntities.EXPLOSIVE_ARROW, x, y, z, level, pickupStack, weaponStack);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide() && !this.isInGround()) {
            this.level().addParticle(
                ParticleTypes.FLAME,
                this.getX(), this.getY(), this.getZ(),
                0.0, 0.0, 0.0
            );
            this.level().addParticle(
                ParticleTypes.SMOKE,
                this.getX(), this.getY(), this.getZ(),
                0.0, 0.02, 0.0
            );
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (!this.level().isClientSide() && this.level() instanceof ServerLevel serverLevel) {
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();

            this.level().explode(
                this,
                x, y, z,
                3.5F,
                false,
                Level.ExplosionInteraction.TNT
            );

            // Вспышка с белым цветом (ARGB)
            serverLevel.sendParticles(ColorParticleOption.create(ParticleTypes.FLASH, 0xFFFFFFFF), x, y, z, 3, 0.1, 0.1, 0.1, 0.0);
            serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 2, 0.25, 0.25, 0.25, 0.0);
            serverLevel.sendParticles(ParticleTypes.LAVA, x, y, z, 20, 0.4, 0.4, 0.4, 0.2);
            serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 60, 0.3, 0.3, 0.3, 0.35);
            serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 35, 0.9, 0.2, 0.9, 0.09);

            this.level().playSound(null, x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 2.0F, 0.75F);

            this.discard();
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.EXPLOSIVE_ARROW);
    }
}