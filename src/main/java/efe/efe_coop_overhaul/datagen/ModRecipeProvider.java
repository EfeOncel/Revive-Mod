package efe.efe_coop_overhaul.datagen;

import efe.efe_coop_overhaul.block.ModBlocks;
import efe.efe_coop_overhaul.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> AZURITE_SMELTABLES = List.of(ModItems.RAW_AZURITE,
                        ModBlocks.AZURITE_ORE, ModBlocks.AZURITE_DEEPSLATE_ORE);

                oreSmelting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.AZURITE, 0.25f, 200, "azurite");
                oreBlasting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.AZURITE, 0.25f, 100, "azurite");





                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.AZURITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AZURITE_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_AZURITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_AZURITE_BLOCK);


            }
        };
    }

    @Override
    public String getName() {
        return "Efe's Co-op Overhaul Recipes";
    }
}
