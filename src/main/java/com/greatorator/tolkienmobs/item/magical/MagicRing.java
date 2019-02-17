package com.greatorator.tolkienmobs.item.magical;

import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
public class MagicRing extends ItemBCore {
    private int potionColorType;

    public MagicRing() {
    this.setMaxStackSize(1);
    this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return isEnabled(stack);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean hotbar) {
        updateRing(stack, entity);
    }

    private void updateRing(ItemStack stack, Entity entity) {
        if(isEnabled(stack)){
            World world = entity.getEntityWorld();
            boolean flag = false;
            LogHelperTTM.info("Ring has been enabled");
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking()) {
            toggleEnabled(stack);
        }
        return super.onItemRightClick(world, player, hand);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "IsActive", false);
    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "IsActive", !isEnabled(stack));
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        return potionColorType;
    }

    public boolean hasColor(ItemStack stack)
    {
        if (this.material != ItemArmor.ArmorMaterial.LEATHER)
        {
            return false;
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();
            return nbttagcompound != null && nbttagcompound.hasKey("potionColorType", 10) ? nbttagcompound.getCompoundTag("potionColorType").hasKey("color", potionColorType) : false;
        }
    }

    public int getColor(ItemStack stack)
    {
        if (this.material != ItemArmor.ArmorMaterial.LEATHER)
        {
            return 16777215;
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();

            if (nbttagcompound != null)
            {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("potionColorType");

                if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3))
                {
                    return nbttagcompound1.getInteger("color");
                }
            }

            return 10511680;
        }
    }

    public void setColor(ItemStack stack, int color)
    {
        if (this.material != ItemArmor.ArmorMaterial.LEATHER)
        {
            throw new UnsupportedOperationException("Can't dye non-leather!");
        }
        else
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();

            if (nbttagcompound == null)
            {
                nbttagcompound = new NBTTagCompound();
                stack.setTagCompound(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("potionColorType");

            if (!nbttagcompound.hasKey("potionColorType", 10))
            {
                nbttagcompound.setTag("potionColorType", nbttagcompound1);
            }

            nbttagcompound1.setInteger("color", color);
        }
    }

//    @Optional.Method(modid = "baubles")
//    @Override
//    public BaubleType getBaubleType(ItemStack itemstack) {
//        return BaubleType.RING;
//    }

//    @Override
//    @Optional.Method(modid = "baubles")
//    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
//        if (!(player instanceof EntityPlayer)) return;
//        updateRing(itemstack, player);
//    }
}
