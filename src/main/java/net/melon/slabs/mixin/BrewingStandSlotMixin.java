package net.melon.slabs.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.melon.slabs.items.MelonSlabsItems;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandSlotMixin extends LockableContainerBlockEntity{

    public BrewingStandSlotMixin(BlockEntityType<?> blockEntityType_1) {
		super(blockEntityType_1);
	}

    @Inject(at = @At("HEAD"), method = "isValid")
    public void isValid(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> info){
        System.out.println("bye");

        if (slot != 3 && slot != 4 && (stack.getItem() == MelonSlabsItems.DEAD_POTION || stack.getItem() == MelonSlabsItems.LIVING_POTION)) {
            System.out.println("hi");
            info.setReturnValue(true);
        }
        System.out.println("bye");
    }
}