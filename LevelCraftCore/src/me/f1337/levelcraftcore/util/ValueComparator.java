package me.f1337.levelcraftcore.util;

import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("rawtypes")
class ValueComparator implements Comparator {

	Map base;

	public ValueComparator(Map base) {
		this.base = base;
	}

	public int compare(Object a, Object b) {

		if ((Double) base.get(a) < (Double) base.get(b)) {
			return 1;
		} else if ((Double) base.get(a) == (Double) base.get(b)) {
			return 0;
		} else {
			return -1;
		}
	}
}
