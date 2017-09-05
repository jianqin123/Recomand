package re;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class CompareWithCount implements  Comparator<Map.Entry<?,Integer>>
{

	@Override
	public int compare(Entry<?, Integer> o1, Entry<?, Integer> o2) {
		// TODO Auto-generated method stub
		return o2.getValue().compareTo(o1.getValue());
	}
	
}
