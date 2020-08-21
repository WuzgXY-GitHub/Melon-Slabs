package net.melon.slabs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Final;

import net.melon.slabs.items.MelonSlabsItems;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;

import java.util.List;

//Adds potion recipes

@Mixin(BrewingRecipeRegistry.class)
public class BrewingMixin{
    @Shadow private static void registerItemRecipe(Item input, Item ingredient, Item output){}
    // @Shadow @Final private static List<BrewingMixin.Recipe<Item>> ITEM_RECIPES;

    @SuppressWarnings("all")
    @Inject(at = @At("HEAD"), method = "registerDefaults")
    private static void registerDefaults(final CallbackInfo info){
        // POTION_RECIPES.add(new BrewingMixin.Recipe(Potions.AWKWARD, Ingredient.ofItems(Items.GUNPOWDER), Potions.AWKWARD));
        // registerItemRecipe(Items.POTION, Items.WITHER_SKELETON_SKULL, MelonSlabsItems.DEAD_POTION);
        registerItemRecipe(Items.POTION, Items.WITHER_SKELETON_SKULL, MelonSlabsItems.DEAD_POTION);
        registerItemRecipe(MelonSlabsItems.DEAD_POTION, Items.FERMENTED_SPIDER_EYE, MelonSlabsItems.LIVING_POTION);

        // ITEM_RECIPES.add(new BrewingMixin.Recipe(Items.POTION, Ingredient.ofItems(Items.WITHER_SKELETON_SKULL), MelonSlabsItems.DEAD_POTION));
        // ITEM_RECIPES.add(new BrewingMixin.Recipe(Items.POTION, Ingredient.ofItems(Items.CREEPER_HEAD), MelonSlabsItems.DEAD_POTION));
        // ITEM_RECIPES.add(new BrewingMixin.Recipe(Items.POTION, Ingredient.ofItems(Items.ZOMBIE_HEAD), MelonSlabsItems.DEAD_POTION));
        // ITEM_RECIPES.add(new BrewingMixin.Recipe(Items.POTION, Ingredient.ofItems(Items.PLAYER_HEAD), MelonSlabsItems.DEAD_POTION));
        // ITEM_RECIPES.add(new BrewingMixin.Recipe(Items.POTION, Ingredient.ofItems(Items.DRAGON_HEAD), MelonSlabsItems.DEAD_POTION));
        // ITEM_RECIPES.add(new BrewingMixin.Recipe(Items.POTION, Ingredient.ofItems(Items.SKELETON_SKULL), MelonSlabsItems.DEAD_POTION));
        // ITEM_RECIPES.add(new BrewingMixin.Recipe(MelonSlabsItems.DEAD_POTION, Ingredient.ofItems(Items.FERMENTED_SPIDER_EYE), MelonSlabsItems.LIVING_POTION));
    }

    // static class Recipe<T> {
    //     private final T input;
    //     private final Ingredient ingredient;
    //     private final T output;
  
    //     public Recipe(T object, Ingredient ingredient, T object2) {
    //        this.input = object;
    //        this.ingredient = ingredient;
    //        this.output = object2;
    //     }
    // }
}