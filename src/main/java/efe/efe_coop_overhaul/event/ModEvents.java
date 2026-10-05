package efe.efe_coop_overhaul.event;

import efe.efe_coop_overhaul.EfeSCoOpOverhaul;
import efe.efe_coop_overhaul.util.IDownedState;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

    private static boolean isPlayerDowned(Player player) {
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

            player.sendSystemMessage(Component.literal("§cCritically wounded! Awaiting revive..."));
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

    }

}
