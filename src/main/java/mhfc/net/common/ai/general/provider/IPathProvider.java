package mhfc.net.common.ai.general.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.Vec3;

public interface IPathProvider<EntityT extends EntityLiving> {

	/**
	 * Resets the path provider and initializes it with the information about
	 * the actor
	 */
	public void initialize(EntityT actor, Entity target);

	public Vec3 getCurrentWaypoint(EntityT actor, Entity target);

	public boolean hasWaypointReached(EntityT actor, Entity target);

	public void onWaypointReached(EntityT actor, Entity target);

	public static class PathListAdapter<EntityT extends EntityLiving>
		implements
			IPathProvider<EntityT> {

		public static final double DEFAULT_MAX_DISTANCE = 0.25f;

		protected List<Vec3> path;
		protected int currentIndex;
		protected double maxDistance;

		public PathListAdapter(List<Vec3> path, double allowedDistance) {
			Objects.requireNonNull(path);
			this.path = new ArrayList<>(path.size());
			this.path.addAll(path);
			this.currentIndex = 0;
			this.maxDistance = allowedDistance;
		}

		public PathListAdapter(List<Vec3> path) {
			this(path, DEFAULT_MAX_DISTANCE);
		}

		public PathListAdapter(Vec3 nodes[], double allowedDistance) {
			Objects.requireNonNull(nodes);
			path = Arrays.asList(nodes);
			this.currentIndex = 0;
			this.maxDistance = allowedDistance;
		}

		public PathListAdapter(Vec3 nodes[]) {
			this(nodes, DEFAULT_MAX_DISTANCE);
		}

		@Override
		public void initialize(EntityT actor, Entity target) {
			currentIndex = 0;
		}

		@Override
		public Vec3 getCurrentWaypoint(EntityT actor, Entity target) {
			if (currentIndex < path.size())
				return path.get(currentIndex);
			else
				return null;
		}

		@Override
		public boolean hasWaypointReached(EntityT actor, Entity target) {
			Vec3 position = actor.getPosition(0);
			return position.subtract(getCurrentWaypoint(actor, target))
				.lengthVector() < maxDistance;
		}

		@Override
		public void onWaypointReached(EntityT actor, Entity target) {
			currentIndex++;
		}

	}

	public static class PathCircleAdapter<EntityT extends EntityLiving>
		extends
			PathListAdapter<EntityT> {

		public PathCircleAdapter(List<Vec3> path, double allowedDistance) {
			super(path, allowedDistance);
		}

		public PathCircleAdapter(List<Vec3> path) {
			super(path);
		}

		public PathCircleAdapter(Vec3[] nodes, double allowedDistance) {
			super(nodes, allowedDistance);
		}

		public PathCircleAdapter(Vec3[] nodes) {
			super(nodes);
		}

		@Override
		public void onWaypointReached(EntityT actor, Entity target) {
			super.onWaypointReached(actor, target);
			if (currentIndex == path.size())
				currentIndex = 0;
		}

	}
}
