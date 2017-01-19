/**
 *
 */
package mhfc.net.common.ai.entity.boss.deviljho;

import mhfc.net.common.ai.general.AIUtils;
import mhfc.net.common.ai.general.SelectionUtils;
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
import mhfc.net.common.entity.monster.EntityDeviljho;

/**
 * @author WorldSEnder
 *
 */
public class Bite extends DamagingAction<EntityDeviljho> implements IHasAttackProvider {

	private static enum Variant {
		BITE_1("mhfc:models/Deviljho/bite2.mcanm", 35) {
			@Override
			public void onUpdate(Bite attack) {
				if (attack.getCurrentFrame() == 25) {
					attack.getEntity().playSound(MHFCSoundRegistry.getRegistry().deviljhoBiteA, 2.0F, 1.0F);
				}
			}

			@Override
			public boolean isMoveForwardFrame(int frame) {
				return (frame > 20 && frame < 30);
			}
		},
		BITE_2("mhfc:models/Deviljho/bite.mcanm", 40) {
			@Override
			public void onUpdate(Bite attack) {
				if (attack.getCurrentFrame() == 25) {
					attack.getEntity().playSound(MHFCSoundRegistry.getRegistry().deviljhoBiteB, 2.0F, 1.0F);
				}
			}

			@Override
			public boolean isMoveForwardFrame(int frame) {
				return (frame > 20 && frame < 30);
			}
		};

		public final String animation;
		public final int finalFrame;

		private Variant(String animation, int finalFrame) {
			this.animation = animation;
			this.finalFrame = finalFrame;
		}

		public IAnimationProvider provideAnimation(Bite bite) {
			return new AnimationAdapter(bite, animation, finalFrame);
		}

		public void onUpdate(Bite attack) {}

		public boolean isMoveForwardFrame(int frame) {
			return false;
		}
	}

	private static final IDamageCalculator DAMAGE_CALC = AIUtils.defaultDamageCalc(120f, 50F, 9999999f);

	private static final double MAX_DIST = 5f;
	private static final float WEIGHT = 5;
	private static final Variant[] ALL_VARIANTS = Variant.values();

	private Variant variant;
	private IAttackProvider attackProvider;

	public Bite() {}

	private boolean shouldSelect() {
		return SelectionUtils.isInDistance(0, MAX_DIST, getEntity(), target);
	}

	@Override
	protected float computeSelectionWeight() {
		return shouldSelect() ? WEIGHT : DONT_SELECT;
	}

	@Override
	protected void initializeExecutionRandomness() {
		super.initializeExecutionRandomness();
		variant = ALL_VARIANTS[rng().nextInt(ALL_VARIANTS.length)];
	}

	@Override
	protected void beginExecution() {
		IDamageProvider damageCalc = new DamageAdapter(DAMAGE_CALC);
		attackProvider = new AttackAdapter(variant.provideAnimation(this), damageCalc);
		super.beginExecution();
	}

	@Override
	public IAttackProvider getAttackProvider() {
		return attackProvider;
	}

	@Override
	public void onUpdate() {
		EntityDeviljho entity = this.getEntity();
		target = entity.getAttackTarget();
		variant.onUpdate(this);
		if (variant.isMoveForwardFrame(getCurrentFrame())) {
			EntityDeviljho e = getEntity();
			e.moveForward(1, false);
		}
		damageCollidingEntities();
	}
}