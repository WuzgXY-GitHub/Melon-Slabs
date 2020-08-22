package net.melon.slabs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.Cancellable;

import net.melon.slabs.blocks.LightningCollectorEntity;
import net.melon.slabs.blocks.LightningRod;
import net.melon.slabs.blocks.MelonSlabsBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin{
    @Shadow public abstract boolean addEntity(Entity entity);

    @Inject(at = @At("HEAD"), method = "spawnEntity", cancellable = true)
    
    public void spawnEntity (Entity entity, CallbackInfoReturnable<Boolean> info){
        // isRaining()
        // System.out.println("spawnentitymixin activated");

        if (entity instanceof LightningEntity){
            // System.out.println("lightning found");
            ServerWorld world = (ServerWorld)((LightningEntity) entity).world;
            BlockPos base = ((LightningEntity) entity).getBlockPos();

            BlockPos tempPos;
            
            int i;
            int j;
            int k;
            for (i=-32; i<33; i++){
                for (j=-32; j<33; j++){
                    for (k=0; k<21; k++){

                        // tempPos = ((ServerWorldAccessorMixin)world).callGetSurface(base.north(i).east(j));
                        tempPos = base.north(i).east(j).up(k);;

                        if(world.getBlockState(tempPos).isOf(MelonSlabsBlocks.LIGHTNING_ROD)){
                            // System.out.println("lightning rod found");
                            if(((LightningRod)MelonSlabsBlocks.LIGHTNING_ROD).isActive(world, tempPos)){
                                // System.out.println("lightning rodded");
                                entity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(tempPos));
                                ((LightningCollectorEntity)world.getBlockEntity(tempPos.down())).doLightning();
                            }
                        }
                    }
                }
            }
        }
    }
}