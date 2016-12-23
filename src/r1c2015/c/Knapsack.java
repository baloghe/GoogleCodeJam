package r1c2015.c;

public class Knapsack {

	private int[] weigths;
	private int[] values;
	
	private boolean[] used;
	
	public Knapsack(int[] inWeigths, int[] inValues){
		weigths = inWeigths;
		values = inValues;
	}
	
	public int solve(int inMaxWeight){
		
		int[][] dyntbl = new int[weigths.length+1][inMaxWeight+1];
		
		for(int i=1; i<=weigths.length; i++){
			//other columns
			for(int w=1; w<=inMaxWeight; w++){
				//System.out.println("i=" + i + ", w=" + w + ", values[i-1]=" + values[i-1] + ", weigths[i-1]=" + weigths[i-1]);
				int leave = dyntbl[i-1][w];
				int take = (w-weigths[i-1] >= 0 ? values[i-1] + dyntbl[i-1][w-weigths[i-1]] : 0);
				dyntbl[i][w] = ( leave > take ? leave : take );
				//System.out.println("  leave=" + leave + ", take=" + take);
			}
		}//next i
		
		//calc which elems have been used
		used = new boolean[weigths.length];
		//last elem was taken or not?
		int i=weigths.length;
		int w = inMaxWeight;
		while(i>0 && w>0){
			int leave = dyntbl[i-1][w];
			int take = (w-weigths[i-1] >= 0 ? values[i-1] + dyntbl[i-1][w-weigths[i-1]] : 0);
			//System.out.println("calc :: leave=" + leave + ", take=" + take);
			used[i-1] = ( take > leave );
			if(used[i-1]){
				//System.out.println("  used: " + i );
				w -= weigths[i-1];
			}
			i--;
		}
		
		return dyntbl[weigths.length][inMaxWeight];
	}
	
	public boolean[] getUsed(){
		return used;
	}
	
}
