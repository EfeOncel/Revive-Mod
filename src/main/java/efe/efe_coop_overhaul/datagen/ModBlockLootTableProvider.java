package efe.efe_coop_overhaul.datagen;

import efe.efe_coop_overhaul.block.ModBlocks;
import efe.efe_coop_overhaul.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        dropSelf(ModBlocks.AZURITE_BLOCK);
        dropSelf(ModBlocks.RAW_AZURITE_BLOCK);

        add(ModBlocks.AZURITE_ORE, createOreDrop(ModBlocks.AZURITE_ORE, ModItems.RAW_AZURITE));
        add(ModBlocks.AZURITE_ORE, createOreDrop(ModBlocks.AZURITE_DEEPSLATE_ORE, ModItems.RAW_AZURITE));

    }
}
