import java.util.ArrayList;
public class Box {
public double largestEdge;
public double mediumEdge;
public double shortestEdge;
public double volume;
public int sD;
public int mD;
public int lD;
ArrayList<Order> ordersList = new ArrayList<Order>();
public Box(double l, double m, double s) {
	this.largestEdge=l;
	this.mediumEdge=m;
	this.shortestEdge=s;
	this.volume = l*m*s;
}
public void setEdges(double l,double m,double s) {
	this.largestEdge=l;
	this.mediumEdge=m;
	this.shortestEdge=s;
	this.updateVol();	
}
public void updateVol() {
	this.volume=this.largestEdge*this.mediumEdge*this.shortestEdge;
}
public void setIndex(ArrayList<Double> a,ArrayList<Double> b,ArrayList<Double> c) {
	for(int i=0;i<a.size();i++) {
		if(this.largestEdge==a.get(i)) {
			this.lD=i;
		}
	}
	for(int i=0;i<b.size();i++) {
		if(this.mediumEdge== b.get(i)) {
			this.mD=i;
		}
	}
	for(int i=0;i<c.size();i++) {
		if(this.shortestEdge==c.get(i)) {
			this.sD=i;
		}
	}
}
public void decreaseSizeBox() {
	double a = 0;
	double b = 0;
	double c = 0;
	for(int i=0;i<this.ordersList.size();i++) {
		if(this.ordersList.get(i).largest_edge>a) {
			a=this.ordersList.get(i).largest_edge;
		}
		if(this.ordersList.get(i).medium_edge>b) {
			b=this.ordersList.get(i).medium_edge;
		}
		if(this.ordersList.get(i).shortest_edge>c) {
			c=this.ordersList.get(i).shortest_edge;
		}
	}
	this.setEdges(a, b, c);
}
}
