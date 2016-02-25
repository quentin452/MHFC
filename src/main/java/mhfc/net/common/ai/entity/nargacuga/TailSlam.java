package mhfc.net.common.ai.entity.nargacuga;

import mhfc.net.common.ai.general.AIUtils;
import mhfc.net.common.ai.general.AIUtils.IDamageCalculator;
import mhfc.net.common.ai.general.actions.AIGeneralJumpAttack;
import mhfc.net.common.ai.general.provider.IAnimationProvider;
import mhfc.net.common.ai.general.provider.IDamageProvider;
import mhfc.net.common.ai.general.provider.IJumpParamterProvider;
import mhfc.net.common.ai.general.provider.IJumpParamterProvider.ConstantAirTimeAdapter.ITargetResolver;
import mhfc.net.common.ai.general.provider.ISelectionPredicate;
import mhfc.net.common.ai.general.provider.IWeightProvider;
import mhfc.net.common.entity.monster.EntityNargacuga;
import net.minecraft.util.Vec3;

public class TailSlam extends AIGeneralJumpAttack<EntityNargacuga> {

	private static final int MAX_ANGLE = 20;
	private static final float MAX_DISTANCE = 6;
	private static final float WEIGHT = 13;
	private static final int JUMP_FRAME = 19;
	private static final int JUMP_TIME = 12;
	private static final int ANIM_LENGTH = 100;

	private static final IAnimationProvider animation = new IAnimationProvider.AnimationAdapter(
			"mhfc:models/Nargacuga/TailSlam.mcanm",
			ANIM_LENGTH);
	private static final ISelectionPredicate<EntityNargacuga> select = new ISelectionPredicate.SelectionAdapter<>(
			-MAX_ANGLE,
			MAX_ANGLE,
			0,
			MAX_DISTANCE);
	private static final IWeightProvider<EntityNargacuga> weight = new IWeightProvider.SimpleWeightAdapter<>(WEIGHT);
	private static final IDamageCalculator damageCalculator = AIUtils.defaultDamageCalc(50, 100, 80);

	public TailSlam() {
		super(generateProvider());
	}

	private static IJumpProvider<EntityNargacuga> generateProvider() {
		IDamageProvider damage = new IDamageProvider.DamageAdapter(damageCalculator);
		IJumpParamterProvider<EntityNargacuga> jumpParams = new IJumpParamterProvider.ConstantAirTimeAdapter<>(
				JUMP_TIME,
				new ITargetResolver<EntityNargacuga>() {
					@Override
					public Vec3 getJumpTarget(EntityNargacuga entity) {
						return entity.getLook(1.0f).addVector(entity.posX, entity.posY, entity.posZ);
					}
				});
		IJumpTimingProvider<EntityNargacuga> timing = new IJumpTimingProvider.JumpTimingAdapter<EntityNargacuga>(
				JUMP_FRAME,
				0,
				0);
		JumpAdapter<EntityNargacuga> adapter = new JumpAdapter<EntityNargacuga>(
				animation,
				select,
				weight,
				damage,
				jumpParams,
				timing);
		return adapter;
	}

	@Override
	protected void finishExecution() {
		super.finishExecution();
		EntityNargacuga nargacuga = getEntity();
		nargacuga.rotationYaw = AIUtils.normalizeAngle(nargacuga.rotationYaw + 180);
		nargacuga.addVelocity(10e-4, 0, 10e-4);
	}
}
