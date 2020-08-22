package net.melon.slabs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.melon.slabs.blocks.BoxScreen;

@Environment(EnvType.CLIENT)
public class MelonSlabsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(MelonSlabs.BOX_SCREEN_HANDLER, BoxScreen::new);
    }
}