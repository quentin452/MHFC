package mhfc.net.common.quests.descriptions;

import java.util.concurrent.CompletionStage;

import mhfc.net.common.core.registry.MHFCQuestBuildRegistry;
import mhfc.net.common.quests.GeneralQuest;
import mhfc.net.common.quests.api.GoalReference;
import mhfc.net.common.quests.api.IVisualInformation;
import mhfc.net.common.quests.api.QuestDefinition;
import mhfc.net.common.quests.api.QuestFactory;
import mhfc.net.common.quests.api.QuestGoal;
import mhfc.net.common.quests.api.VisualDefinition;
import mhfc.net.common.quests.world.GlobalAreaManager;
import mhfc.net.common.quests.world.QuestFlair;
import mhfc.net.common.world.area.IActiveArea;
import mhfc.net.common.world.area.IAreaType;

/**
 * Used by the QuestFactory as well as to display quests.
 */
public class DefaultQuestDescription extends QuestDefinition {

	public static final String KEY_MAX_PARTY_SIZE = "maxPartySize";
	public static final String KEY_QUEST_TYPE = "questType";
	// public static final String KEY_TIME_LIMIT = "timeLimit";
	public static final String KEY_AREA_ID = "areaID";
	public static final String KEY_FLAIR = "flair";
	public static final String KEY_FEE = "fee";
	public static final String KEY_REWARD = "reward";
	public static final String KEY_GOAL = "goal";
	public static final String KEY_VISUAL = "visual";

	protected GoalReference goalReference;
	protected IVisualInformation visual;
	protected QuestType questType;

	protected IAreaType areaType;
	protected QuestFlair questFlair;

	protected int reward;
	protected int fee;
	protected int maxPartySize;

	public DefaultQuestDescription(
			GoalReference goalDescID,
			QuestDefinition.QuestType type,
			IAreaType areaId,
			QuestFlair flair,
			int reward,
			int fee,
			int maxPartySize) {
		super(MHFCQuestBuildRegistry.QUEST_DEFAULT);
		this.goalReference = goalDescID;
		this.questType = type;
		this.areaType = areaId;
		this.questFlair = flair;
		this.reward = reward;
		this.fee = fee;
		this.maxPartySize = maxPartySize;
		this.visual = VisualDefinition.UNKNOWN;
	}

	public void setVisualInformation(IVisualInformation visualInformation) {
		this.visual = visualInformation;
	}

	@Override
	public GoalReference getGoalReference() {
		return goalReference;
	}

	@Override
	public int getReward() {
		return reward;
	}

	@Override
	public int getFee() {
		return fee;
	}

	@Override
	public IAreaType getAreaType() {
		return areaType;
	}

	@Override
	public int getMaxPartySize() {
		return maxPartySize;
	}

	@Override
	public IVisualInformation getVisualInformation() {
		return visual;
	}

	@Override
	public QuestType getQuestType() {
		return questType;
	}

	@Override
	public QuestFlair getQuestFlair() {
		return questFlair;
	}

	@Override
	public GeneralQuest build() {
		QuestGoal goal = QuestFactory.constructGoal(getGoalReference().getReferredDescription());
		if (goal == null) {
			return null;
		}
		IAreaType areaType = getAreaType();

		CompletionStage<IActiveArea> activeArea = GlobalAreaManager.getInstance()
				.getUnusedInstance(areaType, getQuestFlair());
		if (activeArea == null) {
			return null;
		}

		return new GeneralQuest(goal, getMaxPartySize(), getReward(), getFee(), activeArea, this);
	}

}
