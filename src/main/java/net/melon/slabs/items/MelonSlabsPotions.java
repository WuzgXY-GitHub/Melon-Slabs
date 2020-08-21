package net.melon.slabs.items;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

public class MelonSlabsPotions{
    public static final Potion DEAD_POTION;
    public static final Potion LIVING_POTION;

    public static final StatusEffect DEAD_EFFECT;
    public static final StatusEffect LIVING_EFFECT;
	static{
        DEAD_EFFECT = (StatusEffect)Registry.register(Registry.STATUS_EFFECT, 324, "melonslabs_dead", new MelonSlabsStatusEffect(StatusEffectType.HARMFUL, 0));
        LIVING_EFFECT = (StatusEffect)Registry.register(Registry.STATUS_EFFECT, 324, "melonslabs_living", new MelonSlabsStatusEffect(StatusEffectType.BENEFICIAL, 65535));

        DEAD_POTION = (Potion)Registry.register(Registry.POTION, "melonslabs_dead", new Potion(new StatusEffectInstance(DEAD_EFFECT, 0)));
        LIVING_POTION = (Potion)Registry.register(Registry.POTION, "melonslabs_living", new Potion(new StatusEffectInstance(LIVING_EFFECT, 0)));

    }
}