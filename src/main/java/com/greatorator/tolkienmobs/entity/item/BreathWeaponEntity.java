package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.entity.DragonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;

@SuppressWarnings({"removal" })
public class BreathWeaponEntity extends DragonProjectileEntity
{
    public BreathWeaponEntity(EntityType<? extends Projectile> type, Level level)
    {
        super(type, level);
    }

    public BreathWeaponEntity(EntityType<? extends DragonProjectileEntity> type, DragonEntity shooter)
    {
        super(type, shooter, shooter.getApproximateMouthPos(), Vec3.directionFromRotation(shooter.getXRot(), shooter.yHeadRot));
        this.growthRate = 1.025f;
    }

    @Override
    public void onBlockImpact(BlockPos pos, Direction direction)
    {
        if (!level.isClientSide && !noPhysics && !level.getBlockState(pos).getCollisionShape(level, pos).equals(Shapes.empty()))
        {
            setDeltaMovement(acceleration.multiply(-Math.abs(direction.getStepX()) + 1, -Math.abs(direction.getStepY()) + 1, -Math.abs(direction.getStepZ()) + 1));

            if (!hasCollided)
            {
                life = tickCount + 20;
                this.hasCollided = true;
            }
        }
    }

    @Override
    protected float getMotionFactor()
    {
        return 0.7f;
    }

    @Override
    protected EffectType getEffectType()
    {
        return EffectType.COLLIDING;
    }
}
