package net.melon.slabs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MelonSlabs implements ModInitializer {
    //Blocks
    private static final Block MELON_SLAB_BLOCK = new MelonSlab();
    private static final Block PUMPKIN_SLAB_BLOCK = new PumpkinSlab();

    //Item group
    private static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("melonslabs", "group"), () -> new ItemStack(MelonSlabs.MELON_SLAB));

    //Items
    public static final Item MELON_SLAB = new BlockItem(MELON_SLAB_BLOCK, new Item.Settings().group(GROUP));
    public static final Item PUMPKIN_SLAB = new BlockItem(PUMPKIN_SLAB_BLOCK, new Item.Settings().group(GROUP));
    public static final Item PUMPKIN_SLICE = new Item(new Item.Settings().group(GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.3f).snack().build()));
    public static final Item COOKED_PUMPKIN_SLICE = new Item(new Item.Settings().group(GROUP).food(new FoodComponent.Builder().hunger(4).saturationModifier(2.4f).build()));

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, "melonslabs:melon_slab", MELON_SLAB_BLOCK);
        Registry.register(Registry.BLOCK, "melonslabs:pumpkin_slab", PUMPKIN_SLAB_BLOCK);
        Registry.register(Registry.ITEM, "melonslabs:melon_slab", MELON_SLAB);
        Registry.register(Registry.ITEM, "melonslabs:pumpkin_slab", PUMPKIN_SLAB);
        Registry.register(Registry.ITEM, "melonslabs:pumpkin_slice", PUMPKIN_SLICE);
        Registry.register(Registry.ITEM, "melonslabs:cooked_pumpkin_slice", COOKED_PUMPKIN_SLICE);
    }
    
}