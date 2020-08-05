package net.melon.slabs;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;

public class PumpkinSlab extends SlabBlock{

    public PumpkinSlab() {
        super(FabricBlockSettings.copy(Blocks.PUMPKIN));
    }
    
}