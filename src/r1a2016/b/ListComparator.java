package r1a2016.b;

import java.util.ArrayList;
import java.util.Comparator;

public class ListComparator< T extends Comparable > 
implements Comparator< ArrayList<T> >{

@Override
public int compare(ArrayList<T> o1,
		ArrayList<T> o2) {
	
	int ret = 0;
	
	for(int i=0; i<o1.size() && i<o2.size() && ret==0; i++){
//		Comparable<T> e1 = o1.get(i);
//		Comparable<T> e2 = o2.get(i);
//		int act = e1.compareTo( e2 );
		ret = o1.get(i).compareTo( (T)(o2.get(i)) );
	}
	
	return ret;
}

}

/*
public class ListComparator< T extends Comparable<T> > 
	implements Comparator< ArrayList<Comparable<T>> >{

	@Override
	public int compare(ArrayList<Comparable<T>> o1,
			ArrayList<Comparable<T>> o2) {
		
		int ret = 0;
		
		for(int i=0; i<o1.size() && i<o2.size() && ret==0; i++){
//			Comparable<T> e1 = o1.get(i);
//			Comparable<T> e2 = o2.get(i);
//			int act = e1.compareTo( e2 );
			ret = o1.get(i).compareTo( (T)(o2.get(i)) );
		}
		
		return ret;
	}

}
*/


