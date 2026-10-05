package efe.efe_coop_overhaul.mixin;

import efe.efe_coop_overhaul.util.IDownedState;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class CoopPlayerMixin implements IDownedState {

    @Unique
    private boolean isDowned = false;

    @Unique
    private int reviveTimer = 0;

    @Override
    public boolean isDowned() {
        return this.isDowned;
    }

    @Override
    public void setDowned(boolean downed) {
        this.isDowned = downed;
    }

    @Override
    public int getReviveTimer() {
        return this.reviveTimer;
    }

    @Override
    public void setReviveTimer(int timer) {
        this.reviveTimer = timer;
    }
}