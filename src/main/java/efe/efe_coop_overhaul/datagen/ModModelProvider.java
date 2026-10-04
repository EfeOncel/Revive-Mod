package efe.efe_coop_overhaul.datagen;

import efe.efe_coop_overhaul.block.ModBlocks;
import efe.efe_coop_overhaul.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.AZURITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.RAW_AZURITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.AZURITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.AZURITE_DEEPSLATE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.AZURITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_AZURITE, ModelTemplates.FLAT_ITEM);
    }
}
