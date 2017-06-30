package mhfc.net.common.ai.entity.boss.lagiacrus;

import mhfc.net.common.ai.general.AIUtils;
import mhfc.net.common.ai.general.actions.DamagingAction;
import mhfc.net.common.ai.general.provider.adapters.AnimationAdapter;
import mhfc.net.common.ai.general.provider.adapters.AttackAdapter;
import mhfc.net.common.ai.general.provider.adapters.DamageAdapter;
import mhfc.net.common.ai.general.provider.composite.IAnimationProvider;
import mhfc.net.common.ai.general.provider.composite.IAttackProvider;
import mhfc.net.common.ai.general.provider.impl.IHasAttackProvider;
import mhfc.net.common.ai.general.provider.simple.IDamageCalculator;
import mhfc.net.common.ai.general.provider.simple.IDamageProvider;
import mhfc.net.common.core.registry.MHFCSoundRegistry;
import mhfc.net.common.entity.monster.EntityLagiacrus;
import mhfc.net.common.util.world.WorldHelper;
import net.minecraft.util.math.Vec3d;

public class Bite extends DamagingAction<EntityLagiacrus> implements IHasAttackProvider {

	private static final int ANIM_FRAME = 60;
	private static final String ANIM_LOCATION = "mhfc:models/Lagiacrus/LagiacrusBite.mcanm";

	private static final IDamageCalculator DAMAGE_CALC = AIUtils.defaultDamageCalc(105F, 125F, 99999999F);

	private static double TARGET_DISTANCE = 35F;

	private static float WEIGHT = 15;

	private final IAttackProvider ATTACK;
	{
		final IAnimationProvider ANIMATION = new AnimationAdapter(this, ANIM_LOCATION, ANIM_FRAME);
		final IDamageProvider DAMAGE = new DamageAdapter(DAMAGE_CALC);
		ATTACK = new AttackAdapter(ANIMATION, DAMAGE);
	}

	public Bite() {}

	@Override
	protected float computeSelectionWeight() {
		EntityLagiacrus entity = this.getEntity();
		target = entity.getAttackTarget();
		if (target == null) {
			return DONT_SELECT;
		}
		Vec3d toTarget = WorldHelper.getVectorToTarget(entity, target);
		double dist = toTarget.lengthVector();
		if (dist > TARGET_DISTANCE) {
			return DONT_SELECT;
		}
		return WEIGHT;
	}

	@Override
	public IAttackProvider getAttackProvider() {
		return ATTACK;
	}

	@Override
	public void onUpdate() {
		if (getEntity().getAttackTarget() != null && this.getCurrentFrame() == 38) {
			getEntity().playSound(MHFCSoundRegistry.getRegistry().lagiacrusBite, 2.0F, 1.0F);
		}
		if (isMoveForwardFrame(getCurrentFrame())) {
			EntityLagiacrus entity = getEntity();
			entity.moveForward(0.2, false);
		}
		damageCollidingEntities();

	}

	private static boolean isMoveForwardFrame(int frame) {
		return (frame > 10 && frame < 25);
	}

}
