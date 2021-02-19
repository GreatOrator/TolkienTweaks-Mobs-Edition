//package com.greatorator.tolkienmobs.entity.entityai.phase;
//
//import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
//
//import java.lang.reflect.Constructor;
//import java.util.Arrays;
//
//public class TTMPhaseList<T extends ITTMPhase>
//{
//    private static TTMPhaseList<?>[] phases = new TTMPhaseList[0];
//    public static final TTMPhaseList<TTMPhaseHoldingPattern> HOLDING_PATTERN = create(TTMPhaseHoldingPattern.class, "HoldingPattern");
//    public static final TTMPhaseList<TTMPhaseStrafePlayer> STRAFE_PLAYER = create(TTMPhaseStrafePlayer.class, "StrafePlayer");
//    public static final TTMPhaseList<TTMPhaseLandingApproach> LANDING_APPROACH = create(TTMPhaseLandingApproach.class, "LandingApproach");
//    public static final TTMPhaseList<TTMPhaseLanding> LANDING = create(TTMPhaseLanding.class, "Landing");
//    public static final TTMPhaseList<TTMPhaseTakeoff> TAKEOFF = create(TTMPhaseTakeoff.class, "Takeoff");
//    public static final TTMPhaseList<TTMPhaseSittingFlaming> SITTING_FLAMING = create(TTMPhaseSittingFlaming.class, "SittingFlaming");
//    public static final TTMPhaseList<TTMPhaseSittingScanning> SITTING_SCANNING = create(TTMPhaseSittingScanning.class, "SittingScanning");
//    public static final TTMPhaseList<TTMPhaseSittingAttacking> SITTING_ATTACKING = create(TTMPhaseSittingAttacking.class, "SittingAttacking");
//    public static final TTMPhaseList<TTMPhaseChargingPlayer> CHARGING_PLAYER = create(TTMPhaseChargingPlayer.class, "ChargingPlayer");
//    public static final TTMPhaseList<TTMPhaseDying> DYING = create(TTMPhaseDying.class, "Dying");
//    public static final TTMPhaseList<TTMPhaseHover> HOVER = create(TTMPhaseHover.class, "Hover");
//    private final Class <? extends ITTMPhase> clazz;
//    private final int id;
//    private final String name;
//
//    private TTMPhaseList(int idIn, Class <? extends ITTMPhase> clazzIn, String nameIn)
//    {
//        this.id = idIn;
//        this.clazz = clazzIn;
//        this.name = nameIn;
//    }
//
//    public ITTMPhase createPhase(EntityTMFellBeast fellbeast)
//    {
//        try
//        {
//            Constructor <? extends ITTMPhase> constructor = this.getConstructor();
//            return constructor.newInstance(fellbeast);
//        }
//        catch (Exception exception)
//        {
//            throw new Error(exception);
//        }
//    }
//
//    protected Constructor <? extends ITTMPhase> getConstructor() throws NoSuchMethodException
//    {
//        return this.clazz.getConstructor(EntityTMFellBeast.class);
//    }
//
//    public int getId()
//    {
//        return this.id;
//    }
//
//    public String toString()
//    {
//        return this.name + " (#" + this.id + ")";
//    }
//
//    /**
//     * Gets a phase by its ID. If the phase is out of bounds (negative or beyond the end of the phase array), returns
//     * {@link #HOLDING_PATTERN}.
//     */
//    public static TTMPhaseList<?> getById(int idIn)
//    {
//        return idIn >= 0 && idIn < phases.length ? phases[idIn] : HOLDING_PATTERN;
//    }
//
//    public static int getTotalPhases()
//    {
//        return phases.length;
//    }
//
//    private static <T extends ITTMPhase> TTMPhaseList<T> create(Class<T> phaseIn, String nameIn)
//    {
//        TTMPhaseList<T> phaselist = new TTMPhaseList<T>(phases.length, phaseIn, nameIn);
//        phases = (TTMPhaseList[])Arrays.copyOf(phases, phases.length + 1);
//        phases[phaselist.getId()] = phaselist;
//        return phaselist;
//    }
//}