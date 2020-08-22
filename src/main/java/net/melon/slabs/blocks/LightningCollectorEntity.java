package net.melon.slabs.blocks;

import org.jetbrains.annotations.Nullable;

import net.melon.slabs.items.MelonSlabsItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public class LightningCollectorEntity extends BlockEntity implements ImplementedInventory, SidedInventory{
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public LightningCollectorEntity() {
        super(MelonSlabsBlocks.LIGHTNING_COLLECTOR_ENTITY);
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
        if (stack.hasTag()){
            return(stack.getTag().asString().contains("minecraft:melonslabs_living"));
        }
        return(false);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return stack.getItem() == MelonSlabsItems.LIGHTNING_BOTTLE;
    }

    @Override
    public int[] getAvailableSlots(Direction dir) {
        int[] result = new int[1];
        result[0] = 0;
        return result;
    }
 
    //for when it implemented NamedScreenHandlerFactory
    // @Override
    // public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
    //     return new BoxScreenHandler(syncId, playerInventory, this);
    // }
 
    // @Override
    // public Text getDisplayName() {
    //     return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    // }
}