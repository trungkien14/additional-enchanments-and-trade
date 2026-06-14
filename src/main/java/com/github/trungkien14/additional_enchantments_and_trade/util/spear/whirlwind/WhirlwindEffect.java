package com.github.trungkien14.additional_enchantments_and_trade.util.spear.whirlwind;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class WhirlwindEffect {
    public static void whirlwindAnimation(PlayerEntity player, World world) {
        if (world instanceof ServerWorld serverWorld) {
            double originX = player.getX();
            double originY = player.getY() + 1.0; //chest
            double originZ = player.getZ();

            Vec3d lookDir = player.getRotationVec(1.0F); //looking direction

            for (int degree = 0; degree < 360; degree += 10) {
                double radians = Math.toRadians(degree);

                // Calculate a flat circle pattern spread
                double radius = 4; // whirwind wide
                double offsetX = Math.cos(radians) * radius;
                double offsetZ = Math.sin(radians) * radius;

                // Push the particles slightly forward in the direction the spear is pointing
                double finalX = originX + offsetX + (lookDir.x * 0.5);
                double finalY = originY + (Math.sin(radians * 2) * 0.2); // Makes the wind slightly wavy
                double finalZ = originZ + offsetZ + (lookDir.z * 0.5);

                // 3. Spawn the actual visual particles using the built-in system
                // Parameters: (ParticleType, X, Y, Z, Count, velocityX, velocityY, velocityZ, Speed)
                serverWorld.spawnParticles(
                        ParticleTypes.CLOUD,     // The white cloud texture mimics wind beautifully
                        finalX, finalY, finalZ,  // Where to drop the particle
                        1,                       // Spawn exactly 1 per loop position
                        offsetX * 0.1,           // Gives it outward expansion speed (X)
                        0.05,                    // Makes the wind float slowly upwards (Y)
                        offsetZ * 0.1,           // Gives it outward expansion speed (Z)
                        0.2                      // Particle motion speed modifier
                );

                // Add secondary trailing particles for a denser visual punch
                serverWorld.spawnParticles(
                        ParticleTypes.GUST,      // Built-in trials trial smoke
                        finalX, finalY, finalZ,
                        1, 0, 0, 0, 0.0
                );
            }
        }
    }
}