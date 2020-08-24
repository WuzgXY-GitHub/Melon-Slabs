package net.melon.slabs.blocks;

import org.jetbrains.annotations.Nullable;

import net.melon.slabs.entity.DisplayItemEntity;
import net.melon.slabs.items.MelonSlabsItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class SunPedestalEntity extends BlockEntity implements ImplementedInventory, SidedInventory{
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public SunPedestalEntity() {
        super(MelonSlabsBlocks.SUN_PEDESTAL_ENTITY);
    }

    public void scheduleTick(){
        this.getWorld().getBlockTickScheduler().schedule(this.getPos(), MelonSlabsBlocks.MIRROR, 1);
    }

    public void displayInventory(ServerWorld world, BlockPos pos){
        SunPedestalEntity blockEntity = (SunPedestalEntity)world.getBlockEntity(pos);
        DisplayItemEntity displayItem = new DisplayItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.1D, (double)pos.getZ() + 0.5D, blockEntity.getItems().get(0));
        this.removeDisplay(world, pos);
        world.spawnEntity(displayItem);
    }

    public void removeDisplay(ServerWorld world, BlockPos pos){
        world.getEntitiesByClass(DisplayItemEntity.class, new Box(pos.up()), (x -> true)).forEach(item -> {
            world.removeEntity(item);
            System.out.println("deleted item yo");
        });
    }

    //implementedInventory
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,items);
    }
 
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return(false);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public int[] getAvailableSlots(Direction dir) {
        return new int[0];
    }
    
    //these next functions make sure to update the state whenever an item is added or removed
    @Override
    public ItemStack removeStack(int slot, int count) {
        scheduleTick();

        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }
 
    
    @Override
    public ItemStack removeStack(int slot) {
        scheduleTick();

        return Inventories.removeStack(getItems(), slot);
    }
 
    
    @Override
    public void setStack(int slot, ItemStack stack) {
        scheduleTick();

        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }
 
    /**
     * Clears the inventory.
     */
    @Override
    public void clear() {
        scheduleTick();

        getItems().clear();
    }
}