package net.melon.slabs;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class MelonSlab extends SlabBlock{

    public MelonSlab() {
        super(FabricBlockSettings.copy(Blocks.MELON));
    }
    
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.SHEARS) {
            if (!world.isClient) {
                if (!player.abilities.creativeMode){
                    itemStack.damage(1, (PlayerEntity)player, (Consumer)(PlayerEntity) -> {
                        ((LivingEntity) PlayerEntity).sendToolBreakStatus(hand);
                    });
                }
                
                Boolean waterlogged = state.get(WATERLOGGED);
                SlabType slabType = (SlabType)state.get(TYPE);

                Integer itemNum = slabType == slabType.DOUBLE ? 4 : 3;

                Direction direction = hit.getSide();
                Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, (BlockState)MelonSlabs.CARVED_MELON_SLAB_BLOCK.getDefaultState().with(CarvedMelonSlab.FACING, direction2).with(TYPE, slabType).with(WATERLOGGED, waterlogged), 11);
                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D + (double)direction2.getOffsetX() * 0.65D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D + (double)direction2.getOffsetZ() * 0.65D, new ItemStack(Items.MELON_SEEDS, itemNum));
                itemEntity.setVelocity(0.05D * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02D);
                world.spawnEntity(itemEntity);
            }
  
           return ActionResult.success(world.isClient);
        } else {
           return super.onUse(state, world, pos, player, hand, hit);
        }
    }
}