package net.melon.slabs.items;

import java.util.Collections;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.text.Text;


public class MelonSlabsPotion extends Item{
	public MelonSlabsPotion(Settings settings)
	{
		super(settings);
	}

	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.NEUTRAL, 10.0F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
  
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.abilities.creativeMode) {
            itemStack.decrement(1);
        
            user.inventory.insertStack(new ItemStack(Items.GLASS_BOTTLE));
        }
        return TypedActionResult.method_29237(itemStack, world.isClient());
        // return TypedActionResult.consume(itemStack);
    }
}