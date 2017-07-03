package mhfc.net.common.ai.entity.boss.nargacuga;

import mhfc.net.common.ai.general.actions.WanderAction;
import mhfc.net.common.ai.general.provider.adapters.AnimationAdapter;
import mhfc.net.common.ai.general.provider.adapters.CountLoopAdvancer;
import mhfc.net.common.ai.general.provider.adapters.MoveParameterAdapter;
import mhfc.net.common.ai.general.provider.composite.IAnimationProvider;
import mhfc.net.common.ai.general.provider.impl.IHasAnimationProvider;
import mhfc.net.common.ai.general.provider.simple.IMoveParameterProvider;
import mhfc.net.common.entity.monster.EntityNargacuga;

public class Wander extends WanderAction<EntityNargacuga> implements IHasAnimationProvider {

	private static final int LAST_FRAME = 70;
	private static final String ANIMATION_LOCATION = "mhfc:models/Nargacuga/Wander.mcanm";

	private static final float WEIGHT = 3F;

	private static final IMoveParameterProvider MOVEMENT_PARAMS = new MoveParameterAdapter(3f, 0.6f);
	private final IAnimationProvider ANIMATION;

	public Wander() {
		ANIMATION = AnimationAdapter.builder().setAnimation(ANIMATION_LOCATION).setAnimationLength(LAST_FRAME)
				.setFrameAdvancer(new CountLoopAdvancer(0, 70, -1)).build(this);
	}

	@Override
	protected float computeWanderWeight() {
		return WEIGHT;
	}

	@Override
	public IAnimationProvider getAnimProvider() {
		return ANIMATION;
	}

	@Override
	public IMoveParameterProvider provideMoveParameters() {
		return MOVEMENT_PARAMS;
	}

}