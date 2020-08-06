package net.melon.slabs;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.state.property.Properties;

public class CactusSlab extends SlabBlock{
    public static final EnumProperty<SlabType> TYPE;
    protected static final VoxelShape BOTTOM_SHAPE;
    protected static final VoxelShape TOP_SHAPE;

    public CactusSlab() {
        super(FabricBlockSettings.copy(Blocks.CACTUS));
    }
    
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = (SlabType)state.get(TYPE);
        switch(slabType) {
        case DOUBLE:
            return Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
        case TOP:
            return TOP_SHAPE;
        default:
            return BOTTOM_SHAPE;
        }
    }

    static {
        TYPE = Properties.SLAB_TYPE;
        BOTTOM_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
        TOP_SHAPE = Block.createCuboidShape(1.0D, 8.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    }
}