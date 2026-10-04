package efe.efe_coop_overhaul;

import efe.efe_coop_overhaul.block.ModBlocks;
import efe.efe_coop_overhaul.creativemodetab.ModCreativeModeTabs;
import efe.efe_coop_overhaul.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EfeSCoOpOverhaul implements ModInitializer {
	public static final String MOD_ID = "efe_coop_overhaul";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerCreativeModTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
