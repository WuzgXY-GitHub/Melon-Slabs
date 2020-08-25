package net.melon.slabs.blocks;

import java.util.Random;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.melon.slabs.properties.MelonSlabsProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

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
        super(FabricBlockSettings.copy(Blocks.IRON_BLOCK));
        // super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).ticksRandomly());
    }

    //returns wether this block is ready to channel lightning
    public boolean isActive(ServerWorld world, BlockPos pos){
        return this.multiblockActive(world, pos);
    }

    //sees if the multiblock structure is complete
    private boolean multiblockActive(ServerWorld world, BlockPos pos){
        //go through all the blocks in the multiblock one at a time

        if (!world.isSkyVisible(pos)){ return false;}

        if (!world.getBlockState(pos.down()).isOf(MelonSlabsBlocks.LIGHTNING_COLLECTOR)){ return false;}

        //central pillar
        if (!world.getBlockState(pos.down(2)).isOf(Blocks.QUARTZ_PILLAR)){ return false;}
        if (!world.getBlockState(pos.down(3)).isOf(Blocks.QUARTZ_PILLAR)){ return false;}
        if (!world.getBlockState(pos.down(4)).isOf(Blocks.QUARTZ_PILLAR)){ return false;}
        if (!world.getBlockState(pos.down(5)).isOf(Blocks.QUARTZ_PILLAR)){ return false;}

        //ladder
        String direction = "none";
        if (world.getBlockState(pos.down(3).north()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(4).north()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(5).north()).isOf(Blocks.LADDER)){ direction = "n";}
        if (world.getBlockState(pos.down(3).east()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(4).east()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(5).east()).isOf(Blocks.LADDER)){ direction = "e";}
        if (world.getBlockState(pos.down(3).south()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(4).south()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(5).south()).isOf(Blocks.LADDER)){ direction = "s";}
        if (world.getBlockState(pos.down(3).west()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(4).west()).isOf(Blocks.LADDER) && world.getBlockState(pos.down(5).west()).isOf(Blocks.LADDER)){ direction = "w";}


        if (direction == "none"){ return false;}

        //jack o'slabs
        if (!(world.getBlockState(pos.down(4).north(2).west(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(4).north(2).west(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(5).north(2).west(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).north(2).west(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}
        if (!(world.getBlockState(pos.down(4).north(2).east(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(4).north(2).east(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(5).north(2).east(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).north(2).east(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}
        if (!(world.getBlockState(pos.down(4).south(2).west(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(4).south(2).west(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(5).south(2).west(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).south(2).west(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}
        if (!(world.getBlockState(pos.down(4).south(2).east(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(4).south(2).east(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(5).south(2).east(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).south(2).east(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}

        //stairs
        if (direction != "e" && !world.getBlockState(pos.down(5).east()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(5).east().north()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(5).east().south()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (direction != "w" && !world.getBlockState(pos.down(5).west()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(5).west().north()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(5).west().south()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (direction != "s" && !world.getBlockState(pos.down(5).south()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (direction != "n" && !world.getBlockState(pos.down(5).north()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}

        //platform center
        if (!world.getBlockState(pos.down(6)).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).north()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).north().east()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).north().west()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).east()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).west()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).south()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).south().east()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(6).south().west()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}

        //sideways pillars
        if (!(world.getBlockState(pos.down(6).north(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).north(2)).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(6).north(2).east()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).north(2).east()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(6).north(2).west()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).north(2).west()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(6).south(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).south(2)).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(6).south(2).east()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).south(2).east()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(6).south(2).west()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).south(2).west()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(6).east(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).east(2)).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(6).east(2).north()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).east(2).north()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(6).east(2).south()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).east(2).south()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(6).west(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).west(2)).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(6).west(2).north()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).west(2).north()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(6).west(2).south()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(6).west(2).south()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}

        return true;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.sendMessage(this.multiblockActive((ServerWorld)world, pos) ? new LiteralText("Multiblock complete") : new LiteralText("Multiblock incomplete"), true);
        }
        return ActionResult.SUCCESS;
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