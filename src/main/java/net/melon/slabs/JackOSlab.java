package net.melon.slabs;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Tickable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

import com.ibm.icu.impl.number.AffixPatternProvider.Flags;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.fluid.Fluids;
import net.fabricmc.api.EnvType;



public class JackOSlab extends SlabBlock{
    public static final DirectionProperty FACING;
    private static final ParticleEffect PARTICLE;
    public boolean scheduled = false;

    public JackOSlab() {
        super(FabricBlockSettings.copy(Blocks.JACK_O_LANTERN));
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FACING, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return (BlockState)((BlockState)blockState.with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false).with(FACING, ctx.getPlayerFacing().getOpposite());
        } else {
            BlockState blockState2 = (BlockState)((BlockState)this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, false).with(FACING, ctx.getPlayerFacing().getOpposite());
            Direction direction = ctx.getSide();
            return direction != Direction.DOWN && (direction == Direction.UP || ctx.getHitPos().y - (double)blockPos.getY() <= 0.5D) ? blockState2 : (BlockState)blockState2.with(TYPE, SlabType.TOP).with(FACING, ctx.getPlayerFacing().getOpposite());
        }
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return false;
    }
    
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == MelonSlabs.CARVED_PUMPKIN_SLAB) {

            SlabType slabType = (SlabType)state.get(TYPE);
            Boolean replacing;

            Direction direction = hit.getSide();
            if (slabType == SlabType.BOTTOM) {
                replacing = direction == Direction.UP;
            } else {
                replacing =  direction == Direction.DOWN;
            }

            if (replacing){
                world.setBlockState(pos, (BlockState)((BlockState)state.with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false));
                return ActionResult.success(world.isClient);
            } else {
                return super.onUse(state, world, pos, player, hand, hit);
            }
  
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!scheduled){
            world.getBlockTickScheduler().schedule(pos, this, 1);
            scheduled = false;
        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        this.update(state, world, pos);
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
        if (!scheduled){
            scheduled = true;
            world.getBlockTickScheduler().schedule(pos, this, 1);
        }
    }

    public void update (BlockState state, World world, BlockPos pos){
        if (state.get(WATERLOGGED)){
            world.setBlockState(pos, MelonSlabs.CARVED_PUMPKIN_SLAB_BLOCK.getDefaultState().with(FACING, state.get(FACING)).with(WATERLOGGED, true).with(TYPE,state.get(TYPE)));

            Direction direction2 = state.get(TYPE) == SlabType.BOTTOM ? Direction.UP : Direction.DOWN;

            ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D + (double)direction2.getOffsetX() * 0.65D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D + (double)direction2.getOffsetZ() * 0.65D, new ItemStack(Items.TORCH, 1));
            itemEntity.setVelocity(0.05D * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02D);
            world.spawnEntity(itemEntity);
        }
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(TYPE) != SlabType.DOUBLE){
            double d = (double)pos.getX() + 0.5D;
            double e = (double)pos.getY() + (state.get(TYPE) == SlabType.BOTTOM ? 0.8D : 0.5D);
            double f = (double)pos.getZ() + 0.5D;
            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0D, 0.0D, 0.0D);
            world.addParticle(PARTICLE, d, e, f, 0.0D, 0.0D, 0.0D);
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(TYPE) != SlabType.DOUBLE){
            world.getBlockTickScheduler().schedule(pos, this, 1);
        } else{
            scheduled = false;
        }
        update(state, world, pos);
    }

    static{
        FACING = HorizontalFacingBlock.FACING;
        PARTICLE = ParticleTypes.FLAME;
    }
}