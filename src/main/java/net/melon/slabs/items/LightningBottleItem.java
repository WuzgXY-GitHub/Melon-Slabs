package net.melon.slabs.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LightningBottleItem extends Item{
    public LightningBottleItem (Settings settings){
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack){
        return true;
    }
}