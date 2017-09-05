package re;

import java.util.Comparator;

public class MyComparators implements Comparator<MyMap<Shop,Double>>{

	@Override
	public int compare(MyMap arg0, MyMap arg1) {
		// TODO Auto-generated method stub
		if((double)arg0.getValue()<(double)arg1.getValue())
			return -1;
		else if((double)arg0.getValue()>(double)arg1.getValue())
			return +1;
		else
			return 0;
	}
}
