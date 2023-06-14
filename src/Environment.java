import java.util.ArrayList;
import java.util.TreeMap;
public class Environment {
	public ArrayList<Box> boxlist;
	public ArrayList<Double> slist = new ArrayList<Double>();
	public ArrayList<Double> mlist = new ArrayList<Double>();
	public ArrayList<Double> llist = new ArrayList<Double>();
	public double volume;
	public boolean isImprovable = true;
	public Environment(ArrayList<Box> liste) {
		this.boxlist=liste;
		double vol = 0;
		for(int i=0;i<this.boxlist.size();i++) {
			vol+=this.boxlist.get(i).ordersList.size()*this.boxlist.get(i).shortestEdge*this.boxlist.get(i).mediumEdge*this.boxlist.get(i).largestEdge;
		}
		this.volume=vol;
	}
	public void changeImprovable() {
		this.isImprovable=false;
	}
	public void updateVol() {
		this.volume=0;
		for(int i=0;i<this.boxlist.size();i++) {
			this.volume+=this.boxlist.get(i).ordersList.size()*this.boxlist.get(i).shortestEdge*this.boxlist.get(i).mediumEdge*this.boxlist.get(i).largestEdge;
		}
	}
	public void changeBox(Box b1,Box b2s) {
		Box b2 = new Box(b2s.largestEdge,b2s.mediumEdge,b2s.shortestEdge);
		b2.ordersList.addAll(b2s.ordersList);
		b2.setIndex(llist, mlist, slist);
		int count = 0;
		if(b1.volume<=b2.volume) {
			for(int i=0;i<b2.ordersList.size();i++) {
				if(b2.ordersList.get(i).largest_edge<=b1.largestEdge) {
					if(b2.ordersList.get(i).medium_edge<=b1.mediumEdge) {
						if(b2.ordersList.get(i).shortest_edge<=b1.shortestEdge) {
							b1.ordersList.add(b2.ordersList.get(i));
							b2s.ordersList.remove(b1.ordersList.get(b1.ordersList.size()-1));
							count+=1;
						}
					}
				}
			}
		}	
	}
	public Environment cloneEnv() {
		ArrayList<Box> listeyeni = new ArrayList<Box>();
		for(int i=0;i<this.boxlist.size();i++) {
			Box b = new Box(this.boxlist.get(i).largestEdge,this.boxlist.get(i).mediumEdge,this.boxlist.get(i).shortestEdge);
			b.ordersList.addAll(this.boxlist.get(i).ordersList);
			listeyeni.add(b);
		}
		Environment env2 = new Environment(listeyeni);
		return env2;
	}
	public void  improve(int b1,int b2) {
		TreeMap<Double,ArrayList<Box>> impmap = new TreeMap<Double,ArrayList<Box>>();
		this.boxlist.get(b1).setIndex(this.llist, this.mlist, this.slist);
		this.boxlist.get(b2).setIndex(this.llist, this.mlist, this.slist);
		double initial = this.boxlist.get(b2).ordersList.size()*this.boxlist.get(b2).volume+this.boxlist.get(b1).ordersList.size()*this.boxlist.get(b1).volume;
		Box f1 = this.boxlist.get(b1);
		Box f2 = this.boxlist.get(b2);
		ArrayList<Box> initiallist = new ArrayList<Box>();
		initiallist.add(f1);
		initiallist.add(f2);
		impmap.put(initial, initiallist);
		if(this.boxlist.get(b1).volume<=this.boxlist.get(b2).volume) {
			for(int i=this.boxlist.get(b1).lD;this.llist.get(i)<=this.boxlist.get(b2).largestEdge;i++) {
				for(int j=this.boxlist.get(b1).mD;this.mlist.get(j)<=this.llist.get(i);j++) {
					for(int k=this.boxlist.get(b1).sD;this.slist.get(k)<=this.mlist.get(j);k++) {
						Box bx1 = new Box(llist.get(i),mlist.get(j),slist.get(k));
						bx1.ordersList.addAll(this.boxlist.get(b1).ordersList);
						bx1.setIndex(this.llist, this.mlist, this.slist);
						Box bx2 = new  Box(this.boxlist.get(b2).largestEdge,this.boxlist.get(b2).mediumEdge,this.boxlist.get(b2).shortestEdge);
						bx2.ordersList.addAll(this.boxlist.get(b2).ordersList);
						bx2.setIndex(this.llist, this.mlist, this.slist);
						this.changeBox(bx1, bx2);
						Environment sample = this.cloneEnv();
						bx2.decreaseSizeBox();
						ArrayList<Box> liste = new ArrayList<Box>();
						liste.add(bx1);
						liste.add(bx2);
						impmap.put(bx1.volume*bx1.ordersList.size()+bx2.volume*bx2.ordersList.size(), liste);
						if(k==this.slist.size()-1) {
							break;
						}
					}
					if(j==this.mlist.size()-1) {
						break;
					}
				}
				if(i==this.llist.size()-1) {
					break;
				}
			}
				if(impmap.size()!=0) {  
					this.boxlist.remove(b1);
					this.boxlist.add(b1, impmap.get(impmap.firstKey()).get(0));
					this.boxlist.remove(b2);
					this.boxlist.add(b2, impmap.get(impmap.firstKey()).get(1));
					this.updateVol();
				} 
		}
	}
	public void setLists(ArrayList<Double> l1,ArrayList<Double> l2,ArrayList<Double> l3) {
		this.slist=l1;
		this.mlist=l2;
		this.llist=l3;
	}	
}

