package net.melon.slabs.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.melon.slabs.MelonSlabs;
// import net.minecraft.block.Block;
// import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
// import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
// import net.minecraft.item.ItemStack;
// import net.minecraft.state.property.IntProperty;
// import net.minecraft.util.ActionResult;
// import net.minecraft.util.Hand;
// import net.minecraft.util.hit.BlockHitResult;
// import net.minecraft.util.math.BlockPos;
// import net.minecraft.world.World;

@Mixin(ComposterBlock.class)
public class ComposterMixin{

    @Shadow @Final public static Object2FloatMap<ItemConvertible> ITEM_TO_LEVEL_INCREASE_CHANCE;

    @Inject(at = @At("HEAD"), method = "registerDefaultCompostableItems")
    private static void registerDefaultCompostableITems(CallbackInfo info) {
        System.out.println("hi");
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(MelonSlabs.MELON_SLAB.asItem(), 0.5f);
        System.out.println("hi");
    }

    // @Inject(at = @At("TAIL"), method = "onUse")
    // public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    //     int i = (Integer)state.get(LEVEL);
    //     ItemStack itemStack = player.getStackInHand(hand);
    //     if (i < 8 && ITEM_TO_LEVEL_INCREASE_CHANCE.containsKey(itemStack.getItem())) {
    //         if (i < 7 && !world.isClient) {
    //         BlockState blockState = addToComposter(state, world, pos, itemStack);
    //         world.syncWorldEvent(1500, pos, state != blockState ? 1 : 0);
    //         if (!player.abilities.creativeMode) {
    //             itemStack.decrement(1);
    //         }
    //         }

    //         return ActionResult.success(world.isClient);
    //     } else if (i == 8) {
    //         emptyFullComposter(state, world, pos);
    //         return ActionResult.success(world.isClient);
    //     } else {
    //         return ActionResult.PASS;
    //     }
    // }
}