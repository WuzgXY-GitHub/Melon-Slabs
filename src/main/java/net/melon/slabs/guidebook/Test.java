package net.melon.slabs.guidebook;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Test {
    public static ItemStack getGuideBook() {
		ItemStack stack = new ItemStack(Registry.ITEM.get(new Identifier("patchouli:guide_book")));
		stack.getOrCreateTag().putString("patchouli:book", "melonslabs:handbook");
		return stack;
	}
}