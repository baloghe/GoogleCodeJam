package test.r1a2016.c;

public class BestFriends {

	public Integer from;
	public Integer to;
	
	public BestFriends(Integer inFrom, Integer inTo){
		this.from = inFrom;
		this.to = inTo;
	}
	
	public boolean equals(BestFriends inBF){
		return (this.from.intValue() == inBF.from
					&& this.to.intValue() == inBF.to)
			|| (this.to.intValue() == inBF.from
					&& this.from.intValue() == inBF.to)
			;
	}
	
	public boolean connectsTo(BestFriends inBF){
		return (this.from.intValue() == inBF.from)
				|| (this.to.intValue() == inBF.from);
	}
	
}
