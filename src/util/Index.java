package util;

public class Index {

	public int v;
	public int n;
	public int t;
	
	public Index(int v,int t,int n){
		this.v=v;
		this.n=n;
		this.t=t;
	}
	
	public boolean isEqual(Index i){
		if(v==i.v&&t==i.t&&n==i.n)return true;
		return false;
	}
}
