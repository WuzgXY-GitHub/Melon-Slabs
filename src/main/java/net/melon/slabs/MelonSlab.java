package net.melon.slabs;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;

public class MelonSlab extends SlabBlock{

    public MelonSlab() {
        super(FabricBlockSettings.copy(Blocks.MELON));
    }
    
}