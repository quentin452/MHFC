package mhfc.net.common.quests.factory;

import java.util.List;

import mhfc.net.common.quests.api.*;
import mhfc.net.common.quests.descriptions.ForkGoalDescription;
import mhfc.net.common.quests.goals.ForkQuestGoal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import static mhfc.net.common.quests.descriptions.ForkGoalDescription.*;

public class ForkGoalFactory implements IGoalFactory {

	@Override
	public QuestGoal buildQuestGoal(GoalDescription goalDesc) {
		ForkGoalDescription description = (ForkGoalDescription) goalDesc;
		List<GoalDescription> required = description.getRequired();
		List<GoalDescription> optional = description.getOptional();
		ForkQuestGoal fork = new ForkQuestGoal(null);

		for (GoalDescription desc : required) {
			QuestGoal q = QuestFactory.constructGoal(desc);
			if (q == null)
				continue;
			fork.addRequisite(q);
		}

		for (GoalDescription desc : optional) {
			QuestGoal q = QuestFactory.constructGoal(desc);
			if (q == null)
				continue;
			fork.addOptional(q);
		}

		return fork;
	}

	@Override
	public GoalDescription buildGoalDescription(JsonObject json,
			JsonDeserializationContext context) {
		if (!json.has(ID_REQUIRED))
			throw new JsonParseException(
					"A fork does at least need a list of required goals. This could be empty, but is needed");
		GoalReference[] rqs = new GoalReference[0], opt = new GoalReference[0];
		rqs = context.<GoalReference[]> deserialize(json.get(ID_REQUIRED),
				GoalReference[].class);
		if (json.has(ID_OPTIONAL))
			opt = context.<GoalReference[]> deserialize(json.get(ID_OPTIONAL),
					GoalReference[].class);
		return new ForkGoalDescription(rqs, opt);
	}

}