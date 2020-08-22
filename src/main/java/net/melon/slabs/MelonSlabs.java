package net.melon.slabs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.melon.slabs.blocks.BoxScreenHandler;
import net.melon.slabs.blocks.MelonSlabsBlocks;
import net.melon.slabs.criteria.MelonSlabsCriteria;
import net.melon.slabs.items.MelonSlabsItems;
import net.melon.slabs.sounds.MelonSlabsSounds;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class MelonSlabs implements ModInitializer {
    //mod id
    public static final String MOD_ID = "melonslabs";
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;

    @Override
    public void onInitialize() {
        //advancement criteria
        MelonSlabsCriteria.loadClass();

        //sounds
        MelonSlabsSounds.registerSoundEvents();
        
        //blocks
        MelonSlabsBlocks.registerBlocks();
            //transparent blocks and such
            MelonSlabsBlocks.putRenderLayers();

        //items
        MelonSlabsItems.registerItems();
    }

    static{
        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("melonslabs:lightning_collector"), BoxScreenHandler::new);
    }
}