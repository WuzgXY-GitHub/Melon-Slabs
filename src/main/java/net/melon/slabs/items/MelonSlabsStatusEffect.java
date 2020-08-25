package net.melon.slabs.items;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class MelonSlabsStatusEffect extends StatusEffect{
    public MelonSlabsStatusEffect(StatusEffectType type, int color) {
		super(type, color);
	}

	public boolean isInstant() {
		return true;
	}
  
}