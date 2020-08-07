package net.melon.slabs.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.melon.slabs.MelonSlabs;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;

@Mixin(ComposterBlock.class)
public class ComposterMixin{

    @Shadow @Final public static Object2FloatMap<ItemConvertible> ITEM_TO_LEVEL_INCREASE_CHANCE;

    @Inject(at = @At("HEAD"), method = "registerDefaultCompostableItems")
    private static void registerDefaultCompostableITems(CallbackInfo info) {
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.MELON_SLAB.asItem(), 0.55f);
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.MELON_STAIRS.asItem(), 0.6f);
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.PUMPKIN_SLAB.asItem(), 0.55f);
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.PUMPKIN_STAIRS.asItem(), 0.6f);
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.PUMPKIN_SLICE.asItem(), 0.5f);
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.COOKED_PUMPKIN_SLICE.asItem(), 0.3f);
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.CACTUS_SLAB.asItem(), 0.3f);
    }
}