package efe.efe_coop_overhaul.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record RevivePayload() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RevivePayload> PACKET_TYPE = new CustomPacketPayload.Type<>(ModNetworking.REVIVE_PACKET_ID);

    public static final StreamCodec<FriendlyByteBuf, RevivePayload> CODEC = StreamCodec.unit(new RevivePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }



}//end record
