package efe.efe_coop_overhaul.item;

import efe.efe_coop_overhaul.EfeSCoOpOverhaul;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class ModItems{

    public static final Item AZURITE = registerItem("azurite", Item::new);
    public static final Item RAW_AZURITE = registerItem("raw_azurite", Item::new);







    public static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(EfeSCoOpOverhaul.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EfeSCoOpOverhaul.MOD_ID, name)))));
    }

    public static void registerModItems() {
        EfeSCoOpOverhaul.LOGGER.info("Registering Mod Items For " + EfeSCoOpOverhaul.MOD_ID);
    }

}
