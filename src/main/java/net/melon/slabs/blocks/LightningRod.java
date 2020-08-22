package net.melon.slabs.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.melon.slabs.properties.MelonSlabsProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class LightningRod extends Block{
    public static final VoxelShape SHAPE;
    public static final VoxelShape SHAPE1;
    public static final VoxelShape SHAPE2;
    public static final VoxelShape SHAPE3;
    public static final VoxelShape SHAPE4;
    public static final VoxelShape SHAPE5;
    public static final VoxelShape SHAPE6;
    public static final VoxelShape SHAPE7;
    public static final VoxelShape SHAPE8;
    public static final VoxelShape SHAPE9;
    public static final VoxelShape SHAPE10;

    public LightningRod() {
        // super(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
        super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).ticksRandomly());
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }
    
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
  
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    static{
        SHAPE1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
        SHAPE2 = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
        SHAPE3 = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 3.0D, 13.0D);
        SHAPE4 = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 4.0D, 11.0D);
        SHAPE5 = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D);
        SHAPE6 = Block.createCuboidShape(6.5D, 0.0D, 6.5D, 7.5D, 10.0D, 7.5D);
        SHAPE7 = Block.createCuboidShape(6.5D, 0.0D, 8.5D, 7.5D, 10.0D, 9.5D);
        SHAPE8 = Block.createCuboidShape(8.5D, 0.0D, 6.5D, 9.5D, 10.0D, 7.5D);
        SHAPE9 = Block.createCuboidShape(8.5D, 0.0D, 8.5D, 9.5D, 10.0D, 9.5D);
        SHAPE10 = Block.createCuboidShape(7.5D, 0.0D, 7.5D, 8.5D, 16.0D, 8.5D);
        SHAPE = VoxelShapes.union(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6, SHAPE7, SHAPE8, SHAPE9, SHAPE10);
    }
}