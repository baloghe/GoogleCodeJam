package r1a2015.c;

import java.util.Comparator;

public class Point2DXAxisComparator implements Comparator<Point2D> {

	@Override
	public int compare(Point2D o1, Point2D o2) {
		/* +1 if o1 > o2  ==  
		 *  0 if o1 = o2 
		 * -1 if o1 < o2
		 * 
		 * X axis base vector = (1,0)
		 * <P(x,y) , (1,0)> == x for any point P
		 * cos Theta = <P(x,y) , (1,0)> / sqrt(<P(x,y) , P(x,y)>) == x / sqrt(x*x+y*y)
		 */
		
		double costh1 = o1.x() / Math.sqrt(o1.x() * o1.x() + o1.y() * o1.y());
		double costh2 = o2.x() / Math.sqrt(o2.x() * o2.x() + o2.y() * o2.y());
		
		if(costh1 > costh2) return -1;
		else if(costh1 < costh2) return 1;
		else return 0;
	}

}
