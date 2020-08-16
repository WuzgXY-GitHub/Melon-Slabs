package net.melon.slabs;

import net.melon.slabs.mixin.CriteriaMixin;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.util.Identifier;

public class MelonSlabsCriteria {
	public static final CreatedFrankenmelonCriterion CREATED_FRANKENMELON = register(new CreatedFrankenmelonCriterion(new Identifier(MelonSlabs.MOD_ID,"created_frankenmelon")));

    // public static final Advancement ROOT_ADVANCEMENT = Advancement.Task.create().display((ItemConvertible)Items.MAP, new TranslatableText("advancements.melonslabs.frankenmelon.title"), new TranslatableText("advancements.melonslabs.frankenmelon.description"), new Identifier("textures/gui/advancements/backgrounds/adventure.png"), AdvancementFrame.TASK, false, false, false).criterion("created_frankenmelon", (CriterionConditions)CreatedFrankenmelonCriterion.Conditions.create(new Identifier("melonslabs:created_frankenmelon")));


	private static <T extends Criterion<?>> T register(T criterion) {
		return CriteriaMixin.callRegister(criterion);
	}

	public static void loadClass() {

	}
}