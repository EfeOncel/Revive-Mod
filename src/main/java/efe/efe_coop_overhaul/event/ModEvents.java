package efe.efe_coop_overhaul.event;

import efe.efe_coop_overhaul.EfeSCoOpOverhaul;
import efe.efe_coop_overhaul.util.IDownedState;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class ModEvents {
    private static final List<MobEffectInstance> ON_DOWN_EFFECTS = List.of(
            new MobEffectInstance(MobEffects.GLOWING, -1, 0, false, false, false),
            new MobEffectInstance(MobEffects.BLINDNESS, -1, 0, false, false, false),
            new MobEffectInstance(MobEffects.MINING_FATIGUE, -1, 255, false, false, false),
            new MobEffectInstance(MobEffects.SLOWNESS, -1, 9, false, false, false)
    );

    public static boolean isPlayerDowned(Player player) {
        return player.hasEffect(MobEffects.SLOWNESS) &&
                player.getEffect(MobEffects.SLOWNESS).getAmplifier() >= 8 &&
                player.hasEffect(MobEffects.BLINDNESS) &&
                player.hasEffect(MobEffects.GLOWING);
    }

    public static void registerEvents() {
        ServerPlayerEvents.ALLOW_DEATH.register((player, damageSource, damageAmount) -> {
            IDownedState state = (IDownedState) player;

            if (state.isDowned()) {
                return true;
            }

            state.setDowned(true);
            player.setHealth(1.0F);

            player.level().getServer().getPlayerList().broadcastSystemMessage(
                        Component.literal(player.getName().getString() + " is critically wounded! Awaiting revive...")
                                .withStyle(ChatFormatting.RED),
                        false
                );

            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.PLAYER_BIG_FALL,  SoundSource.PLAYERS, 0.3F, 1.0F);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.IRON_GOLEM_DAMAGE,  SoundSource.PLAYERS, 0.6f, 1.0f);

            player.level().sendParticles(ParticleTypes.RAID_OMEN,
                    player.getX(), player.getY() + 1, player.getZ(),
                    15, 0.5, 0.5, 0.5, 0.1);
            player.level().sendParticles(ParticleTypes.TRIAL_OMEN,
                    player.getX(), player.getY() + 1, player.getZ(),
                    15, 0.5, 0.5, 0.5, 0.1);


            for (MobEffectInstance effect : ON_DOWN_EFFECTS) {
                player.addEffect(new MobEffectInstance(effect));
            }

            return false;
        });

        UseItemCallback.EVENT.register((player, level, hand) -> {
            if (isPlayerDowned(player)) {
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        });

        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (isPlayerDowned(player)) {
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        });

        AttackEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            if (isPlayerDowned(player)) {
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for(Player player : server.getPlayerList().getPlayers()){
                if(isPlayerDowned(player)){
                    player.setPose(Pose.SWIMMING);
                }
            }
        });

    }//end registerEvents

    public static void executeRevive(ServerPlayer reviver, ServerPlayer target){
        target.removeEffect(MobEffects.SLOWNESS);
        target.removeEffect(MobEffects.MINING_FATIGUE);
        target.removeEffect(MobEffects.GLOWING);
        target.removeEffect(MobEffects.BLINDNESS);

        ((IDownedState) target).setDowned(false);
        target.setHealth(10.0f);

        ServerLevel serverLevel = (ServerLevel) target.level();

        serverLevel.playSound(null, target.getX(), target.getY(), target.getZ(),
                                SoundEvents.ZOMBIE_VILLAGER_CURE,
                                SoundSource.PLAYERS, 0.6F, 1.0F);
        serverLevel.playSound(null, target.getX(), target.getY(), target.getZ(),
                                SoundEvents.BEACON_ACTIVATE,
                                SoundSource.PLAYERS, 0.6F, 1.0F);

        serverLevel.sendParticles(ParticleTypes.HEART,
                target.getX(), target.getY() + 1.0, target.getZ(),
                15, 0.5, 0.5, 0.5, 0.1);
        serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                target.getX(), target.getY() + 1.0, target.getZ(),
                15, 0.5, 0.5, 0.5, 0.1);

        serverLevel.getServer().getPlayerList().broadcastSystemMessage(
                Component.literal(target.getName().getString() + " got revived!")
                        .withStyle(ChatFormatting.GREEN),
                false
        );

        EfeSCoOpOverhaul.LOGGER.info("{} revived {}", reviver.getName().getString(), target.getName().getString());
    }

}
