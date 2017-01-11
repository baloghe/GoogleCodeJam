package r1a2014.a;

public class SwitchArrangement {

	private int device;
	private int outlet;
	private Boolean[] sw;
	
	public SwitchArrangement(int inDevice, int inOutlet, Boolean[] inSwitchArr){
		device = inDevice;
		outlet = inOutlet;
		sw = new Boolean[inSwitchArr.length];
		for(int i=0; i<inSwitchArr.length; i++){
			sw[i] = inSwitchArr[i];
		}
	}
	
	public String getKey(){
		return ProblemSolver.booleanArrToString(sw);
	}
	
	public int getSwitchNumber(){
		return ProblemSolver.countTrue(sw);
	}
	
	public Integer getDeviceId(){return device;}
	public Integer getOutletId(){return outlet;}
	
	public String toString(){
		return "[" + device + "," + outlet + "]->" + this.getKey();
	}
}
