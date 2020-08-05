package net.melon.slabs;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class MelonSlabs implements ModInitializer {

    public static final Block MELON_SLAB = new MelonSlab();

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, "melonslabs:melon_slab", MELON_SLAB);
        Registry.register(Registry.ITEM, "melonslabs:melon_slab", new BlockItem(MELON_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
    
}