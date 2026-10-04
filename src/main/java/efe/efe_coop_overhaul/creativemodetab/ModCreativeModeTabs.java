package efe.efe_coop_overhaul.creativemodetab;

import efe.efe_coop_overhaul.EfeSCoOpOverhaul;
import efe.efe_coop_overhaul.block.ModBlocks;
import efe.efe_coop_overhaul.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab AZURITE_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(EfeSCoOpOverhaul.MOD_ID, "azurite_tab"),
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.AZURITE))
                    .title(Component.translatable("creativemodetab.efescoopoverhaul.azurite_tab"))
                    .displayItems(((parameters, output) -> {
                        output.accept(ModItems.AZURITE);
                        output.accept(ModItems.RAW_AZURITE);
                        output.accept(ModBlocks.AZURITE_BLOCK);
                        output.accept(ModBlocks.RAW_AZURITE_BLOCK);
                        output.accept(ModBlocks.AZURITE_ORE);
                        output.accept(ModBlocks.AZURITE_DEEPSLATE_ORE);
                    }))
                    .build());

    public static void registerCreativeModTabs() {
        EfeSCoOpOverhaul.LOGGER.info("Registering Creative Mode Tabs For " + EfeSCoOpOverhaul.MOD_ID);
    }

}
