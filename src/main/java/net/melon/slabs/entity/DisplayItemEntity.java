package net.melon.slabs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DisplayItemEntity extends ItemEntity{
    public DisplayItemEntity(ServerWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
        this.noClip = false;
        this.setPickupDelayInfinite();
        this.setVelocity(0.0D, 0.0D, 0.0D);
        this.setInvulnerable(true);
        //this.age = -32768;
    }

    @Override
    public void tick() {
        if (this.getStack().isEmpty()) {
            this.remove();
         } else {
            if (!this.world.isClient) {
                this.setFlag(6, this.isGlowing());
            }
       
            this.baseTick();
   
            this.prevX = this.getX();
            this.prevY = this.getY();
            this.prevZ = this.getZ();
            Vec3d vec3d = this.getVelocity();
            float f = this.getStandingEyeHeight() - 0.11111111F;
            if (!this.hasNoGravity()) {
               this.setVelocity(this.getVelocity().add(0.0D, -0.04D, 0.0D));
            }
   
            if (!this.onGround || squaredHorizontalLength(this.getVelocity()) > 9.999999747378752E-6D || (this.getAge() + this.getEntityId()) % 4 == 0) {
               this.move(MovementType.SELF, this.getVelocity());
               float g = 0.98F;
               if (this.onGround) {
                  g = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock().getSlipperiness() * 0.98F;
               }
   
               this.setVelocity(this.getVelocity().multiply((double)g, 0.98D, (double)g));
               if (this.onGround) {
                  Vec3d vec3d2 = this.getVelocity();
                  if (vec3d2.y < 0.0D) {
                     this.setVelocity(vec3d2.multiply(1.0D, -0.5D, 1.0D));
                  }
               }
            }   
            this.velocityDirty |= this.updateWaterState();
            if (!this.world.isClient) {
               double d = this.getVelocity().subtract(vec3d).lengthSquared();
               if (d > 0.01D) {
                  this.velocityDirty = true;
               }
            }
   
         }
    }
}