package net.melon.slabs.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.melon.slabs.properties.MelonSlabsProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class LightningCollector extends Block{
    public static final IntProperty BOTTLE_STATE;

    public static final VoxelShape SHAPE;
    public static final VoxelShape SHAPE1;
    public static final VoxelShape SHAPE2;
    public static final VoxelShape SHAPE3;
    public static final VoxelShape SHAPE4;
    public static final VoxelShape SHAPE5;
    public static final VoxelShape SHAPE6;

    public LightningCollector() {
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BOTTLE_STATE, 1));
    }
    //inventory stuff
    // public BlockEntity createBlockEntity(BlockView world) {
    //     return new LightningCollectorEntity();
    //  }

    //directional stuff
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BOTTLE_STATE);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
  
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    static{
        BOTTLE_STATE = MelonSlabsProperties.BOTTLE_STATE;

        SHAPE1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
        SHAPE2 = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SHAPE3 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 4.0D);
        SHAPE4 = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 4.0D, 16.0D, 16.0D);
        SHAPE5 = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
        SHAPE6 = Block.createCuboidShape(12.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
        SHAPE = VoxelShapes.union(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6);
    }
}   