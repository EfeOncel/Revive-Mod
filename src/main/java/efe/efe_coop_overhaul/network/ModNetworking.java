package efe.efe_coop_overhaul.network;

import efe.efe_coop_overhaul.EfeSCoOpOverhaul;
import efe.efe_coop_overhaul.event.ModEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;

public class ModNetworking {

    public static final Identifier REVIVE_PACKET_ID = Identifier.parse("efe_coop_overhaul:revive_packet");

    public static void registerC2SPackets() {
        PayloadTypeRegistry.serverboundPlay().register(RevivePayload.PACKET_TYPE, RevivePayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(RevivePayload.PACKET_TYPE, ((payload, context) -> {
            ServerPlayer sender = context.player();

            Vec3 eyePos = sender.getEyePosition();
            Vec3 lookVec = sender.getViewVector(1.0f);
            Vec3 endPos = eyePos.add(lookVec.scale(3.0));

            AABB searchBox =  sender.getBoundingBox().expandTowards(lookVec.scale(3.0));
            List<Entity> targets =  sender.level().getEntities(sender, searchBox, e -> e instanceof ServerPlayer && !e.isSpectator());

            for (Entity targetEntity : targets){
                Optional<Vec3> hitResult =  targetEntity.getBoundingBox().clip(eyePos, endPos);

                if(hitResult.isPresent()) {
                    ServerPlayer target = (ServerPlayer) targetEntity;
                    EfeSCoOpOverhaul.LOGGER.info("Target found {}", target.getName().getString());

                    if (ModEvents.isPlayerDowned(target)) {
                        ModEvents.executeRevive(sender, target);
                    }
                    break;
                }
            }//end for


        }));
    }


}