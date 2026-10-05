package efe.efe_coop_overhaul.mixin;

import efe.efe_coop_overhaul.util.IDownedState;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "jumpFromGround", at = @At("HEAD"), cancellable = true)
    public void onJump(CallbackInfo ci) {

        if ((Object) this instanceof Player player) {

            if(player.hasEffect(MobEffects.SLOWNESS)) {

                int amplifier = player.getEffect(MobEffects.SLOWNESS).getAmplifier();
                if(amplifier == 9 && player.hasEffect(MobEffects.GLOWING)){
                    ci.cancel();
                }
            }

        }
    }
}