package efe.efe_coop_overhaul.client;

import com.mojang.blaze3d.platform.InputConstants;
import efe.efe_coop_overhaul.event.ModEvents;
import efe.efe_coop_overhaul.network.RevivePayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.Pose;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;

public class EfeCoopOverhaulClient implements ClientModInitializer {

    public static KeyMapping reviveKey;

    @Override
    public void onInitializeClient() {

        reviveKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.efe_coop_overhaul.revive",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KeyMapping.Category.GAMEPLAY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if(client.player != null){
                if(ModEvents.isPlayerDowned(client.player)){
                    client.player.setPose(Pose.SWIMMING);
                    client.options.keyUp.setDown(false);
                    client.options.keyDown.setDown(false);
                    client.options.keyRight.setDown(false);
                    client.options.keyLeft.setDown(false);
                    client.options.keyJump.setDown(false);
                    client.options.keySprint.setDown(false);
                    client.options.keyDrop.setDown(false);
                    client.options.keyAttack.setDown(false);
                }
            }


            if(reviveKey.isDown()){
                ClientPlayNetworking.send(new RevivePayload());
            }

        });
    }//end END_CLIENT_TICK


}//end class
