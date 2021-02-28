package com.greatorator.tolkienmobs.handler;

import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import static com.greatorator.tolkienmobs.TTMContent.spawnGroup;

/** Borrowed from Majrusz */
public class TTMSpawnEgg {
    private static final SpawnEggBehavior SPAWN_EGG_BEHAVIOR = new SpawnEggBehavior();
    private static ItemGroup ITEM_GROUP = spawnGroup;

    /**
     Adds dispense behavior to given spawn egg item. (entity will be spawned from using dispenser)
     @param spawnEgg Spawn egg to add new behavior.
     */
    public static void addDispenseBehaviorTo( SpawnEggItem spawnEgg ) {
        DispenserBlock.registerDispenseBehavior( spawnEgg, SPAWN_EGG_BEHAVIOR );
    }

    /**
     Creates spawn egg item and registers all standard minecraft behavior.
     (does not register that item, to register you need to use createRegistrySpawnEgg)
     @param entityType     Entity type spawn egg should spawn.
     @param primaryColor   Primary color used by the egg. (spots on the egg)
     @param secondaryColor Secondary color used by the egg. (egg background)
     */
    public static SpawnEggItem createSpawnEgg(EntityType< ? > entityType, int primaryColor, int secondaryColor ) {
        Item.Properties itemProperties = new Item.Properties().group( ITEM_GROUP );
        SpawnEggItem spawnEggItem = new SpawnEggItem( entityType, primaryColor, secondaryColor, itemProperties );
        addDispenseBehaviorTo( spawnEggItem );

        return spawnEggItem;
    }

    /**
     Creates and registers spawn egg item.
     @param deferredRegister Deferred register to which the spawn egg will be registered.
     @param registryName     Registry name of the egg.
     @param entityType       Entity type spawn egg should spawn.
     @param primaryColor     Primary color used by the egg. (spots on the egg)
     @param secondaryColor   Secondary color used by the egg. (egg background)
     */
    public static RegistryObject< SpawnEggItem > createRegistrySpawnEgg(DeferredRegister< Item > deferredRegister, String registryName,
                                                                        EntityType< ? > entityType, int primaryColor, int secondaryColor
    ) {
        return deferredRegister.register( registryName, ()->createSpawnEgg( entityType, primaryColor, secondaryColor ) );
    }

    /**
     Creates and registers spawn egg item.
     @param deferredRegister Deferred register to which the spawn egg will be registered.
     @param registryName     Registry name of the egg.
     @param spawnEgg         Pre-created spawn egg item.
     */
    public static RegistryObject< SpawnEggItem > createRegistrySpawnEgg( DeferredRegister< Item > deferredRegister, String registryName,
                                                                         SpawnEggItem spawnEgg
    ) {
        return deferredRegister.register( registryName, ()->spawnEgg );
    }

    public static void setDefaultItemGroup() {
        ITEM_GROUP = spawnGroup;
    }

    private static final class SpawnEggBehavior extends DefaultDispenseItemBehavior {
        @Override
        public ItemStack dispenseStack(IBlockSource source, ItemStack stack ) {
            BlockPos blockPosition = source.getBlockPos();
            BlockState blockState = source.getBlockState();
            Direction direction = blockState.get( DispenserBlock.FACING );
            EntityType< ? > entityType = ( ( SpawnEggItem )stack.getItem() ).getType( stack.getTag() );
            entityType.spawn( source.getWorld(), stack, null, blockPosition.offset( direction ), SpawnReason.DISPENSER, direction != Direction.UP,
                    false
            );
            stack.shrink( 1 );

            return stack;
        }
    }
}
