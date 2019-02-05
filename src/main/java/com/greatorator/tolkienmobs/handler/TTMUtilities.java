package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.handler.interfaces.IModEntity;

import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.UUID;

public class TTMUtilities {
    public static void teleportAllPetsToOwnerIfSuitable(EntityPlayer parPlayer)
    {
        Iterator<UUID> iterator = parPlayer.getCapability(PetListProvider.PET_LIST, null).getPetList().iterator();
        while (iterator.hasNext())
        {
            Entity entity = parPlayer.world.getMinecraftServer().getEntityFromUuid(iterator.next());
            if (entity instanceof EntityTameable)
            {
                EntityTameable tameable = (EntityTameable)entity;
                teleportPetToOwnerIfSuitable(tameable, parPlayer);
            }
        }
    }

    public static void teleportPetToOwnerIfSuitable(EntityTameable tameable, EntityPlayer owner)
    {
        if (tameable.isSitting())
        {
            return;
        }

        if (tameable.getLeashed() || tameable.isRiding())
        {
            return;

        }

        // only teleport if not in following distance
        if (owner.getDistance(tameable) > 144.0D)
        {
            int i = MathHelper.floor(owner.posX) - 2;
            int j = MathHelper.floor(owner.posZ) - 2;
            int k = MathHelper.floor(owner.getEntityBoundingBox().minY);

            for (int l = 0; l <= 4; ++l)
            {
                for (int i1 = 0; i1 <= 4; ++i1)
                {
                    if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && isTeleportFriendlyBlock(tameable, i, j, k, l, i1))
                    {
                        tameable.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, tameable.rotationYaw, tameable.rotationPitch);
                        tameable.getNavigator().clearPath();
                        return;
                    }
                }
            }
        }
    }

    public static boolean isTeleportFriendlyBlock(Entity parEntity, int x, int p_192381_2_, int y, int p_192381_4_, int p_192381_5_)
    {
        BlockPos blockpos = new BlockPos(x + p_192381_4_, y - 1, p_192381_2_ + p_192381_5_);
        IBlockState iblockstate = parEntity.world.getBlockState(blockpos);
        return iblockstate.getBlockFaceShape(parEntity.world, blockpos, EnumFacing.DOWN) == BlockFaceShape.SOLID && iblockstate.canEntitySpawn(parEntity) && parEntity.world.isAirBlock(blockpos.up()) && parEntity.world.isAirBlock(blockpos.up(2));
    }

    public static String stringToRainbow(String parString, boolean parReturnToBlack)
    {
        int stringLength = parString.length();
        if (stringLength < 1)
        {
            return "";
        }
        String outputString = "";
        TextFormatting[] colorChar =
                {
                        TextFormatting.RED,
                        TextFormatting.GOLD,
                        TextFormatting.YELLOW,
                        TextFormatting.GREEN,
                        TextFormatting.AQUA,
                        TextFormatting.BLUE,
                        TextFormatting.LIGHT_PURPLE,
                        TextFormatting.DARK_PURPLE
                };
        for (int i = 0; i < stringLength; i++)
        {
            outputString = outputString+colorChar[i%8]+parString.substring(i, i+1);
        }
        // return color to a common one after (most chat is white, but for other GUI might want black)
        if (parReturnToBlack)
        {
            return outputString+TextFormatting.BLACK;
        }
        return outputString+TextFormatting.WHITE;
    }

    public static String stringToRainbow(String parString)
    {
        return stringToRainbow(parString, false);
    }

    public static String stringToGolden(String parString, int parShineLocation, boolean parReturnToBlack)
    {
        int stringLength = parString.length();
        if (stringLength < 1)
        {
            return "";
        }
        String outputString = "";
        for (int i = 0; i < stringLength; i++)
        {
            if ((i+parShineLocation+Minecraft.getSystemTime()/20)%88==0)
            {
                outputString = outputString+TextFormatting.WHITE+parString.substring(i, i+1);
            }
            else if ((i+parShineLocation+Minecraft.getSystemTime()/20)%88==1)
            {
                outputString = outputString+TextFormatting.YELLOW+parString.substring(i, i+1);
            }
            else if ((i+parShineLocation+Minecraft.getSystemTime()/20)%88==87)
            {
                outputString = outputString+TextFormatting.YELLOW+parString.substring(i, i+1);
            }
            else
            {
                outputString = outputString+TextFormatting.GOLD+parString.substring(i, i+1);
            }
        }
        // return color to a common one after (most chat is white, but for other GUI might want black)
        if (parReturnToBlack)
        {
            return outputString+TextFormatting.BLACK;
        }
        return outputString+TextFormatting.WHITE;
    }

    // by default return to white (for chat formatting).
    public static String stringToGolden(String parString, int parShineLocation)
    {
        return stringToGolden(parString, parShineLocation, false);
    }

    public static Entity getEntityByID(int entityID, World world)
    {
        for(Object o: world.getLoadedEntityList())
        {
            if(((Entity)o).getEntityId() == entityID)
            {
                // DEBUG
                // System.out.println("Found the entity");
                return ((Entity)o);
            }
        }
        return null;
    }

    public static String toPigLatin(String s)
    {
        String latin = "";
        int i = 0;
        while (i<s.length())
        {
            // Take care of punctuation and spaces
            while (i<s.length() && !isLetter(s.charAt(i)))
            {
                latin = latin + s.charAt(i);
                i++;
            }

            // If there aren't any words left, stop.
            if (i>=s.length()) break;

            // Otherwise we're at the beginning of a word.
            int begin = i;
            while (i<s.length() && isLetter(s.charAt(i)))
            {
                i++;
            }

            // Now we're at the end of a word, so translate it.
            int end = i;
            latin = latin + pigWord(s.substring(begin, end));
        }
        return latin;
    }

    private static boolean isLetter(char c)
    {
        return ( (c >='A' && c <='Z') || (c >='a' && c <='z') );
    }

    private static String pigWord(String word)
    {
        int split = firstVowel(word);
        return word.substring(split)+"-"+word.substring(0, split)+"ay";
    }

    private static int firstVowel(String word)
    {
        word = word.toLowerCase();
        for (int i=0; i<word.length(); i++)
        {
            if (word.charAt(i)=='a' || word.charAt(i)=='e' ||
                    word.charAt(i)=='i' || word.charAt(i)=='o' ||
                    word.charAt(i)=='u')
            {
                return i;
            }
        }
        return 0;
    }

    public static void sendEntitySyncPacketToClient(IModEntity parEntity)
    {
        Entity theEntity = (Entity)parEntity;
        if (!theEntity.world.isRemote)
        {
        }
    }

    public static void sendEntitySyncPacketToServer(IModEntity parEntity)
    {
        Entity theEntity = (Entity)parEntity;
        if (theEntity.world.isRemote)
        {
        }
    }

    public static boolean isSuitableTarget(EntityLivingBase theAttackerEntity,
                                           EntityLivingBase parPossibleTargetEntity,
                                           boolean parShouldCheckSight)
    {
        if (parPossibleTargetEntity == null)
        {
            return false;
        }
        else if (parPossibleTargetEntity == theAttackerEntity)
        {
            return false;
        }
        else if (!parPossibleTargetEntity.isEntityAlive())
        {
            return false;
        }
        else if (theAttackerEntity.isOnSameTeam(parPossibleTargetEntity))
        {
            return false;
        }
        else if (theAttackerEntity instanceof EntityLiving && parShouldCheckSight)
        {
            return ((EntityLiving)theAttackerEntity).getEntitySenses().canSee(parPossibleTargetEntity);
        }
        else
        {
            return true;
        }
    }

    public static float getYawFromVec(Vec3d parVec)
    {
        return (float) -Math.toDegrees(Math.atan2(parVec.x, parVec.z));
    }

    public static float getPitchFromVec(Vec3d parVec)
    {
        Vec3d theVec = parVec.normalize();
        return (float) Math.toDegrees(Math.asin(theVec.y));
    }

    public static AxisAlignedBB copyBoundingBox(AxisAlignedBB aabbIn)
    {
        return new AxisAlignedBB(aabbIn.minX, aabbIn.minY, aabbIn.minZ, aabbIn.maxX, aabbIn.maxY, aabbIn.maxZ);
    }

    public static boolean isCourseTraversable(Entity parEntity, double parX, double parY, double parZ)
    {
        double theDistance = MathHelper.sqrt(parX * parX + parY * parY + parZ * parZ);

        double incrementX = (parX - parEntity.posX) / theDistance;
        double incrementY = (parY - parEntity.posY) / theDistance;
        double incrementZ = (parZ - parEntity.posZ) / theDistance;
        AxisAlignedBB entityBoundingBox = copyBoundingBox(parEntity.getEntityBoundingBox());

        for (int i = 1; i < theDistance; ++i)
        {
            entityBoundingBox.offset(incrementX, incrementY, incrementZ);

            if (!parEntity.world.getCollisionBoxes(parEntity, entityBoundingBox).isEmpty())
            {
                return false;
            }
        }

        return true;
    }
}