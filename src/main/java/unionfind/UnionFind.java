package unionfind;

import java.util.HashMap;
import java.util.Map;

public class UnionFind<T> {

	public static final String ERROR_MESSAGE = "is not in the set";

	private class SetIdentifier {
		private int nodeCount;
		private T set;

		public SetIdentifier(T set) {
			this.nodeCount = 1;
			this.set = set;
		}

		public T getSet() {
			return this.set;
		}

		public void setSet(T set) {
			this.set = set;
		}

		public void increaseNodeCountBy(int val) {
			this.nodeCount += val;
		}

		public int getNodeCount() {
			return this.nodeCount;
		}
	}

	private Map<T, SetIdentifier> map = new HashMap<>();

	private T findSet(T node) {

		SetIdentifier set = map.get(node);
		T setOfNode = set.getSet();

		if (setOfNode == node) {
			return node;
		}

		T newSet = findSet(setOfNode);
		set.setSet(newSet);
		return newSet;
	}

	private T mergeSet(T nodeA, T nodeB) {
		T setOfA = findSet(nodeA);
		T setOfB = findSet(nodeB);
		T setToReturn = setOfA;
		if (setOfA != setOfB) {
			SetIdentifier forA = map.get(setOfA);
			SetIdentifier forB = map.get(setOfB);

			if (forA.getNodeCount() >= forB.getNodeCount()) {
				forA.increaseNodeCountBy(1);
				forB.setSet(setOfA);
			} else {
				forB.increaseNodeCountBy(1);
				forA.setSet(setOfB);
				setToReturn = setOfB;
			}
		}

		return setToReturn;

	}

	public boolean belongsToSameSet(T itemA, T itemB) {
		T setABelongsTo = find(itemA);
		T setBBelongsTo = find(itemB);
		return setABelongsTo.equals(setBBelongsTo);
	}

	public void add(T key) {
		map.put(key, new SetIdentifier(key));
	}

	public T union(T nodeA, T nodeB) {
		if (!map.containsKey(nodeA)) {
			throw new IllegalArgumentException(nodeA + " " + ERROR_MESSAGE);
		}

		if (!map.containsKey(nodeB)) {
			throw new IllegalArgumentException(nodeB + " " + ERROR_MESSAGE);
		}
		return mergeSet(nodeA, nodeB);
	}

	public T find(T item) {
		if (!map.containsKey(item)) {
			throw new IllegalArgumentException(item + " " + ERROR_MESSAGE);
		}
		return findSet(item);
	}

}
