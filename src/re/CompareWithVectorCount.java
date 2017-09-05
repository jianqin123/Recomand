package re;

import java.util.Comparator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

public class CompareWithVectorCount implements Comparator<Map.Entry<Shop, Vector>>
{

	@Override
	public int compare(Entry<Shop, Vector> o1, Entry<Shop, Vector> o2) {
		// TODO Auto-generated method stub
		return new Integer((int) o2.getValue().get(1)).compareTo(new Integer((int) o1.getValue().get(1)));
	}
	
}
