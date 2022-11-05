package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

//
//public class FellBeastPhaseType<T extends IFellBeastPhase> {
//   private static FellBeastPhaseType<?>[] phases = new FellBeastPhaseType[0];
//   public static final FellBeastPhaseType<FellBeastHoldingPatternPhase> HOLDING_PATTERN = create(FellBeastHoldingPatternPhase.class, "HoldingPattern");
//   public static final FellBeastPhaseType<FellBeastStrafePlayerPhase> STRAFE_PLAYER = create(FellBeastStrafePlayerPhase.class, "StrafePlayer");
//   public static final FellBeastPhaseType<FellBeastLandingApproachPhase> LANDING_APPROACH = create(FellBeastLandingApproachPhase.class, "LandingApproach");
//   public static final FellBeastPhaseType<FellBeastLandingPhase> LANDING = create(FellBeastLandingPhase.class, "Landing");
//   public static final FellBeastPhaseType<FellBeastTakeoffPhase> TAKEOFF = create(FellBeastTakeoffPhase.class, "Takeoff");
//   public static final FellBeastPhaseType<FellBeastFlamingSittingPhase> SITTING_FLAMING = create(FellBeastFlamingSittingPhase.class, "SittingFlaming");
//   public static final FellBeastPhaseType<FellBeastScanningSittingPhase> SITTING_SCANNING = create(FellBeastScanningSittingPhase.class, "SittingScanning");
//   public static final FellBeastPhaseType<FellBeastAttackingSittingPhase> SITTING_ATTACKING = create(FellBeastAttackingSittingPhase.class, "SittingAttacking");
//   public static final FellBeastPhaseType<FellBeastChargingPlayerPhase> CHARGING_PLAYER = create(FellBeastChargingPlayerPhase.class, "ChargingPlayer");
//   public static final FellBeastPhaseType<FellBeastDyingPhase> DYING = create(FellBeastDyingPhase.class, "Dying");
//   public static final FellBeastPhaseType<FellBeastHoverPhase> HOVERING = create(FellBeastHoverPhase.class, "Hover");
//   private final Class<? extends IFellBeastPhase> instanceClass;
//   private final int id;
//   private final String name;
//
//   private FellBeastPhaseType(int p_i46782_1_, Class<? extends IFellBeastPhase> p_i46782_2_, String p_i46782_3_) {
//      this.id = p_i46782_1_;
//      this.instanceClass = p_i46782_2_;
//      this.name = p_i46782_3_;
//   }
//
//   public IFellBeastPhase createInstance(FellBeastEntity p_188736_1_) {
//      try {
//         Constructor<? extends IFellBeastPhase> constructor = this.getConstructor();
//         return constructor.newInstance(p_188736_1_);
//      } catch (Exception exception) {
//         throw new Error(exception);
//      }
//   }
//
//   protected Constructor<? extends IFellBeastPhase> getConstructor() throws NoSuchMethodException {
//      return this.instanceClass.getConstructor(FellBeastEntity.class);
//   }
//
//   public int getId() {
//      return this.id;
//   }
//
//   public String toString() {
//      return this.name + " (#" + this.id + ")";
//   }
//
//   public static FellBeastPhaseType<?> getById(int p_188738_0_) {
//      return p_188738_0_ >= 0 && p_188738_0_ < phases.length ? phases[p_188738_0_] : HOLDING_PATTERN;
//   }
//
//   public static int getCount() {
//      return phases.length;
//   }
//
//   private static <T extends IFellBeastPhase> FellBeastPhaseType<T> create(Class<T> p_188735_0_, String p_188735_1_) {
//      FellBeastPhaseType<T> phasetype = new FellBeastPhaseType<>(phases.length, p_188735_0_, p_188735_1_);
//      phases = Arrays.copyOf(phases, phases.length + 1);
//      phases[phasetype.getId()] = phasetype;
//      return phasetype;
//   }
//}