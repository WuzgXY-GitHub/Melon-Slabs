package net.melon.slabs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.melon.slabs.items.MelonSlabsPotions;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;

//Adds potion recipes

@Mixin(BrewingRecipeRegistry.class)
public class BrewingMixin{
    @Shadow private static void registerPotionRecipe(Potion input, Item ingredient, Potion output){}
    // @Shadow @Final private static List<BrewingMixin.Recipe<Item>> ITEM_RECIPES;

    @SuppressWarnings("all")
    @Inject(at = @At("HEAD"), method = "registerDefaults")
    private static void registerDefaults(final CallbackInfo info){
        registerPotionRecipe(Potions.AWKWARD, Items.WITHER_SKELETON_SKULL, MelonSlabsPotions.DEAD_POTION);
        registerPotionRecipe(Potions.AWKWARD, Items.SKELETON_SKULL, MelonSlabsPotions.DEAD_POTION);
        registerPotionRecipe(Potions.AWKWARD, Items.CREEPER_HEAD, MelonSlabsPotions.DEAD_POTION);
        registerPotionRecipe(Potions.AWKWARD, Items.PLAYER_HEAD, MelonSlabsPotions.DEAD_POTION);
        registerPotionRecipe(Potions.AWKWARD, Items.ZOMBIE_HEAD, MelonSlabsPotions.DEAD_POTION);
        registerPotionRecipe(Potions.AWKWARD, Items.DRAGON_HEAD, MelonSlabsPotions.DEAD_POTION);
        registerPotionRecipe(MelonSlabsPotions.DEAD_POTION, Items.FERMENTED_SPIDER_EYE, MelonSlabsPotions.LIVING_POTION);
    }
}