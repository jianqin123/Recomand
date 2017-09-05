package re;

import java.util.Comparator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

class CompareWithDistance implements Comparator<Map.Entry<Shop, Vector>>
{

	@Override
	public int compare(Entry<Shop, Vector> o1, Entry<Shop, Vector> o2) {
		// TODO Auto-generated method stub
		return new Double((double) o1.getValue().get(2)).compareTo(new Double((double) o2.getValue().get(2)));
	}
}
