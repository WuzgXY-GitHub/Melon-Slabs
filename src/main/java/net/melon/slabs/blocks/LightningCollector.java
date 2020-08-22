package net.melon.slabs.blocks;

import java.util.Random;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.melon.slabs.items.MelonSlabsItems;
import net.melon.slabs.items.MelonSlabsPotions;
import net.melon.slabs.properties.MelonSlabsProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.state.property.Properties;


public class LightningCollector extends BlockWithEntity {
    public static final IntProperty BOTTLE_STATE;
    
    public static final VoxelShape EMPTY_SHAPE;
    public static final VoxelShape BOTTLE_SHAPE;
    public static final VoxelShape BOTTLE;
    public static final VoxelShape SHAPE1;
    public static final VoxelShape SHAPE2;
    public static final VoxelShape SHAPE3;
    public static final VoxelShape SHAPE4;
    public static final VoxelShape SHAPE5;
    public static final VoxelShape SHAPE6;

    public LightningCollector() {
        super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).ticksRandomly());
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BOTTLE_STATE, 0));
    }

    //returns wether this block is ready to channel lightning
    public boolean active(ServerWorld world, BlockPos pos){
        return this.multiblockActive(world, pos) && world.hasRain(pos);
    }

    //sees if the multiblock structure is complete
    private boolean multiblockActive(ServerWorld world, BlockPos pos){
        //go through all the blocks in the multiblock one at a time

        if (!world.getBlockState(pos.up()).isOf(MelonSlabsBlocks.LIGHTNING_ROD)){ return false;}

        //central pillar
        if (!world.getBlockState(pos.down()).isOf(Blocks.QUARTZ_PILLAR)){ return false;}
        if (!world.getBlockState(pos.down(2)).isOf(Blocks.QUARTZ_PILLAR)){ return false;}
        if (!world.getBlockState(pos.down(3)).isOf(Blocks.QUARTZ_PILLAR)){ return false;}
        if (!world.getBlockState(pos.down(4)).isOf(Blocks.QUARTZ_PILLAR)){ return false;}

        //ladder
        if (!world.getBlockState(pos.down(2).north()).isOf(Blocks.LADDER)){ return false;}
        if (!world.getBlockState(pos.down(3).north()).isOf(Blocks.LADDER)){ return false;}
        if (!world.getBlockState(pos.down(4).north()).isOf(Blocks.LADDER)){ return false;}

        //jack o'slabs
        if (!(world.getBlockState(pos.down(3).north(2).west(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(3).north(2).west(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(4).north(2).west(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).north(2).west(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}
        if (!(world.getBlockState(pos.down(3).north(2).east(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(3).north(2).west(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(4).north(2).east(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).north(2).east(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}
        if (!(world.getBlockState(pos.down(3).south(2).west(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(3).north(2).west(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(4).south(2).west(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).south(2).west(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}
        if (!(world.getBlockState(pos.down(3).south(2).east(2)).isOf(MelonSlabsBlocks.JACK_O_SLAB) && world.getBlockState(pos.down(3).north(2).west(2)).get(JackOSlab.TYPE) == SlabType.BOTTOM)){ return false;}
        if (!world.getBlockState(pos.down(4).south(2).east(2)).isOf(Blocks.CHISELED_QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).south(2).east(2)).isOf(Blocks.QUARTZ_BRICKS)){ return false;}

        //stairs
        if (!world.getBlockState(pos.down(4).east()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(4).east().north()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(4).east().south()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(4).west()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(4).west().north()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(4).west().south()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}
        if (!world.getBlockState(pos.down(4).south()).isOf(Blocks.QUARTZ_STAIRS)){ return false;}

        //platform center
        if (!world.getBlockState(pos.down(5)).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).north()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).north().east()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).north().west()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).east()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).west()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).south()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).south().east()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}
        if (!world.getBlockState(pos.down(5).south().west()).isOf(Blocks.QUARTZ_BLOCK)){ return false;}

        //sideways pillars
        if (!(world.getBlockState(pos.down(5).north(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).north(2)).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(5).north(2).east()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).north(2).east()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(5).north(2).west()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).north(2).west()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(5).south(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).south(2)).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(5).south(2).east()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).south(2).east()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(5).south(2).west()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).south(2).west()).get(PillarBlock.AXIS) == Direction.Axis.X)){ return false;}
        if (!(world.getBlockState(pos.down(5).east(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).east(2)).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(5).east(2).north()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).east(2).north()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(5).east(2).south()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).east(2).south()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(5).west(2)).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).west(2)).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(5).west(2).north()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).west(2).north()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}
        if (!(world.getBlockState(pos.down(5).west(2).south()).isOf(Blocks.QUARTZ_PILLAR) && world.getBlockState(pos.down(5).west(2).south()).get(PillarBlock.AXIS) == Direction.Axis.Z)){ return false;}

        return true;
    }

    //for debugging
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        System.out.println(this.multiblockActive(world, pos));
    }

    //inventory stuff
    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new LightningCollectorEntity();
    }
    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    //This drop inventory onto ground when block broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof LightningCollectorEntity) {
                ItemScatterer.spawn(world, pos, (LightningCollectorEntity)blockEntity);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            ItemStack itemStack = player.getStackInHand(hand);

            if (blockEntity instanceof LightningCollectorEntity){
                if (itemStack.isEmpty() && !((LightningCollectorEntity)blockEntity).getStack(0).isEmpty()){
                    //pick up item
                    player.setStackInHand(hand, ((LightningCollectorEntity)blockEntity).removeStack(0));
                    world.setBlockState(pos, state.with(BOTTLE_STATE, 0));
                    return ActionResult.SUCCESS;
                }

                if (itemStack.hasTag()){
                    if(itemStack.getTag().asString().contains("minecraft:melonslabs_living") && ((LightningCollectorEntity)blockEntity).getStack(0).isEmpty()){
                        ((LightningCollectorEntity)blockEntity).setStack(0, itemStack);
                        player.setStackInHand(hand, ItemStack.EMPTY);
                        world.setBlockState(pos, state.with(BOTTLE_STATE, 1));

                        return ActionResult.SUCCESS;
                    }
                }

                if (itemStack.getItem() == MelonSlabsItems.LIGHTNING_BOTTLE && ((LightningCollectorEntity)blockEntity).getStack(0).isEmpty()){
                    ((LightningCollectorEntity)blockEntity).setStack(0, itemStack);
                    player.setStackInHand(hand, ItemStack.EMPTY);
                    world.setBlockState(pos, state.with(BOTTLE_STATE, 2));

                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.FAIL;
        } else{
            return ActionResult.FAIL;

        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BOTTLE_STATE);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(BOTTLE_STATE) == 0 ? EMPTY_SHAPE : BOTTLE_SHAPE;
    }
  
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(BOTTLE_STATE) == 0 ? EMPTY_SHAPE : BOTTLE_SHAPE;
    }

    static{
        BOTTLE_STATE = MelonSlabsProperties.BOTTLE_STATE;

        SHAPE1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
        SHAPE2 = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SHAPE3 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 4.0D);
        SHAPE4 = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 4.0D, 16.0D, 16.0D);
        SHAPE5 = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
        SHAPE6 = Block.createCuboidShape(12.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
        BOTTLE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
        
        EMPTY_SHAPE = VoxelShapes.union(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6);
        BOTTLE_SHAPE = VoxelShapes.union(SHAPE1, SHAPE2, SHAPE3, SHAPE4, SHAPE5, SHAPE6, BOTTLE);
    }
}   