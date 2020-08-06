// package net.melon.slabs;

// import java.util.Iterator;
// import java.util.Random;

// import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

// import net.minecraft.block.Block;
// import net.minecraft.block.BlockState;
// import net.minecraft.block.Blocks;
// import net.minecraft.block.Material;
// import net.minecraft.block.ShapeContext;
// import net.minecraft.block.SlabBlock;
// import net.minecraft.block.enums.SlabType;
// import net.minecraft.entity.Entity;
// import net.minecraft.entity.ai.pathing.NavigationType;
// import net.minecraft.entity.damage.DamageSource;
// import net.minecraft.fluid.FluidState;
// import net.minecraft.item.ItemPlacementContext;
// import net.minecraft.server.world.ServerWorld;
// import net.minecraft.state.StateManager;
// import net.minecraft.state.property.EnumProperty;
// import net.minecraft.state.property.IntProperty;
// import net.minecraft.util.math.BlockPos;
// import net.minecraft.util.shape.VoxelShape;
// import net.minecraft.world.BlockView;
// import net.minecraft.world.World;
// import net.minecraft.world.WorldView;
// import net.minecraft.state.property.Properties;
// import net.minecraft.util.math.Direction;
// import net.minecraft.fluid.Fluids;

// public class CactusSlab extends SlabBlock{
//     public static final IntProperty AGE;

//     public static final EnumProperty<SlabType> TYPE;

//     protected static final VoxelShape BOTTOM_SHAPE;
//     protected static final VoxelShape TOP_SHAPE;
//     protected static final VoxelShape DOUBLE_SHAPE;

//     protected static final VoxelShape BOTTOM_COLLISION_SHAPE;
//     protected static final VoxelShape TOP_COLLISION_SHAPE;
//     protected static final VoxelShape DOUBLE_COLLISION_SHAPE;

//     public CactusSlab() {
//         super(FabricBlockSettings.copy(Blocks.CACTUS));
//     }

//     @Override
//     public BlockState getPlacementState(ItemPlacementContext ctx) {
//         BlockPos blockPos = ctx.getBlockPos();
//         BlockState blockState = ctx.getWorld().getBlockState(blockPos);
//         if (blockState.isOf(this)) {
//            return (BlockState)((BlockState)blockState.with(TYPE, SlabType.DOUBLE)).with(WATERLOGGED, false);
//         } else {
//            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
//            BlockState blockState2 = (BlockState)((BlockState)this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
//            Direction direction = ctx.getSide();
//            return blockState2;
//         }
//     }

//     @Override
//     public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//         if (!state.canPlaceAt(world, pos)) {
//            world.breakBlock(pos, true);
//         }
  
//     }
    
//     @Override
// 	public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
// 	{
// 		return true;
// 	}

//     @Override
//     public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//         SlabType slabType = (SlabType)state.get(TYPE);
//         switch(slabType) {
//         case DOUBLE:
//             return DOUBLE_SHAPE;
//         case TOP:
//             return TOP_SHAPE;
//         default:
//             return BOTTOM_SHAPE;
//         }
//     }
    
//     @Override
//     public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) { 
//         BlockPos blockPos = pos.down();
//         return world.getBlockState(blockPos).getMaterial().isSolid();
//         //return false;   
//      }

//     @Override
//     public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
//         entity.damage(DamageSource.CACTUS, 1.0F);
//      }
    
//     @Override
//     protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//         builder.add(AGE);
//         builder.add(TYPE, WATERLOGGED);
//     }

//     @Override
//     public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
//         return false;
//     }

//     static {
//         AGE = Properties.AGE_15;

//         TYPE = Properties.SLAB_TYPE;

//         DOUBLE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
//         BOTTOM_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
//         TOP_SHAPE = Block.createCuboidShape(1.0D, 8.0D, 1.0D, 15.0D, 16.0D, 15.0D);

//         DOUBLE_COLLISION_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
//         BOTTOM_COLLISION_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
//         TOP_COLLISION_SHAPE = Block.createCuboidShape(1.0D, 8.0D, 1.0D, 15.0D, 15.0D, 15.0D);
//     }
// }