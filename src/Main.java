import java.io.*;
import java.util.*;

public class Main {

	public static ArrayList<Double> slist = new ArrayList<Double>();
	public static ArrayList<Double> mlist = new ArrayList<Double>();
	public static ArrayList<Double> llist = new ArrayList<Double>();
	public static ArrayList<Double> maxlist = new ArrayList<Double>();

	public static void main(String[] args) throws IOException {
		ArrayList<Order> orderlist = new ArrayList<Order>();
		ArrayList<Double> largelist = new ArrayList<Double>();
		ArrayList<Double> mediumlist = new ArrayList<Double>();
		ArrayList<Double> smalllist = new ArrayList<Double>();
		ArrayList<Double> reducedLarge = new ArrayList<Double>();
		ArrayList<Double> reducedMedium = new ArrayList<Double>();
		ArrayList<Double> reducedSmall = new ArrayList<Double>();
		FileReader fr1 = new FileReader("large.txt");
		BufferedReader bf1 = new BufferedReader(fr1);
		FileReader fr2 = new FileReader("medium.txt");
		BufferedReader bf2 = new BufferedReader(fr2);
		FileReader fr3 = new FileReader("short.txt");
		BufferedReader bf3 = new BufferedReader(fr3);
		String s1 = "a";
		String s2 = "a";
		String s3 = "a";
		double maxS = 0;
		double maxM = 0;
		double maxL = 0;
		int dataCount = 68;
		int boxcount = 10;
		int count1 = 0;
		while (s1.equals("finish") == false) { // s1.equals("finish")==false
			s1 = bf1.readLine();
			if (s1.equals("finish")) { // s1.equals("finish")
				break;
			}
			double large = Double.parseDouble(s1);
			largelist.add(large);
			if (large > maxL) {
				maxL = large;
			}
			count1 += 1;
			if (count1 == dataCount) {
				break;
			}
		}
		int count2 = 0;
		while (s2.equals("finish") == false) { // s2.equals("finish")==false
			s2 = bf2.readLine();
			if (s2.equals("finish")) { // s2.equals("finish")
				break;
			}
			double medium = Double.parseDouble(s2);
			mediumlist.add(medium);
			if (maxM < medium) {
				maxM = medium;
			}
			count2 += 1;
			if (count2 == dataCount) {
				break;
			}
		}
		int count3 = 0;
		while (s3.equals("finish") == false) { // s3.equals("finish")==false
			s3 = bf3.readLine();
			if (s3.equals("finish")) { // s3.equals("finish")
				break;
			}
			double small = Double.parseDouble(s3);
			smalllist.add(small);
			if (maxS < small) {
				maxS = small;
			}
			count3 += 1;
			if (count3 == dataCount) {
				break;
			}
		}
		Random rand = new Random();
		for (int i = 0; i < dataCount; i++) {
			if (i < 68) {
				Order o = new Order(largelist.get(i), mediumlist.get(i), smalllist.get(i));
				o.setName(i + " ");
				orderlist.add(o);
			} else {
				int j = rand.nextInt(68);
				double l = orderlist.get(j).largest_edge;
				double m = orderlist.get(j).medium_edge;
				double s = orderlist.get(j).shortest_edge;
				Order o = new Order(l, m, s);
				orderlist.add(o);
			}
		}
		for (int i = 0; i < orderlist.size(); i++) {
			llist.add(orderlist.get(i).largest_edge);
			mlist.add(orderlist.get(i).medium_edge);
			slist.add(orderlist.get(i).shortest_edge);
		}
		reduceList(llist);
		reduceList(mlist);
		reduceList(slist);
		sort(llist);
		sort(mlist);
		sort(slist);
		ArrayList<Box> boxlist = new ArrayList<Box>();
		for (int i = 1; i <= boxcount; i++) { // 10
			if (i < boxcount) {
				Box b1 = new Box(0, 0, 0);
				b1.setIndex(llist, mlist, slist);
				boxlist.add(b1);
			} else {
				Box b1 = new Box(maxL, maxM, maxS);
				b1.setIndex(llist, mlist, slist);
				boxlist.add(b1);
				b1.ordersList.addAll(orderlist);
			}
		}
		Environment env = new Environment(boxlist);
		env.setLists(slist, mlist, llist);
		double startTime = System.nanoTime();
		Environment ef = bestSol(env);
		double endTime = System.nanoTime();
		ArrayList<Order> orderlist3 = new ArrayList<Order>();
		orderlist3.addAll(orderlist);
		System.out.println("Execution time:" + (endTime - startTime) / 1000000000);
		ef.updateVol();
		testValidity(ef);
	}

	private static String File(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void sortBoxByVol(ArrayList<Box> liste3) {
		for (int i = 0; i < liste3.size() - 1; i++) {
			for (int j = i + 1; j < liste3.size(); j++) {
				if (liste3.get(j).volume < liste3.get(i).volume) {
					Box bxi = liste3.get(i);
					Box bxj = liste3.get(j);
					liste3.remove(j);
					liste3.add(j, bxi);
					liste3.remove(i);
					liste3.add(i, bxj);
				}
			}
		}
	}

	public static void testValidity(Environment ef) {
		int sum = 0;
		for (int i = 0; i < ef.boxlist.size(); i++) {
			System.out.println(ef.boxlist.get(i).ordersList.size() + " " + ef.boxlist.get(i).largestEdge + " "
					+ ef.boxlist.get(i).mediumEdge + " " + ef.boxlist.get(i).shortestEdge + " b" + i);
			for (int j = 0; j < ef.boxlist.get(i).ordersList.size(); j++) {
				if (ef.boxlist.get(i).ordersList.get(j).shortest_edge > ef.boxlist.get(i).shortestEdge) {
					sum += 1;
				}
				if (ef.boxlist.get(i).ordersList.get(j).medium_edge > ef.boxlist.get(i).mediumEdge) {
					sum += 1;
				}
				if (ef.boxlist.get(i).ordersList.get(j).largest_edge > ef.boxlist.get(i).largestEdge) {
					sum += 1;
				}
			}
		}
		System.out.println(ef.volume + " total volume");
		if (sum == 0) {
			System.out.println("Algorithm is Valid");
		}
	}

	public static void placement(ArrayList<Order> sample_list, ArrayList<Box> sample_box) {
		for (int i = 0; i < sample_list.size(); i++) {
			boolean check = true;
			for (int j = 0; j < sample_box.size(); j++) {
				if (sample_list.get(i).largest_edge <= sample_box.get(j).largestEdge) {
					if (sample_list.get(i).medium_edge <= sample_box.get(j).mediumEdge) {
						if (sample_list.get(i).shortest_edge <= sample_box.get(j).shortestEdge) {
							if (check) {
								System.out.println(
										sample_box.get(j).ordersList.size() + " PLACEMENT BOX ORDER L�ST S�ZE");
								sample_box.get(j).ordersList.add(sample_list.get(i));
								sample_box.get(j).updateVol();
								check = false;
								break;
							}
						}
					}
				}
			}
		}
	}

	public static Environment bestSol(Environment e) {
		TreeMap<Double, Environment> envmap = new TreeMap<Double, Environment>();
		ArrayList<Environment> envlist = new ArrayList<Environment>();
		decreaseSizes(e);
		e.updateVol();
		double currentVol = e.volume;
		System.out.println(currentVol);
		envmap.put(e.volume, e);
		while (e.isImprovable) {
			for (int i = 0; i < e.boxlist.size(); i++) {
				for (int j = 0; j < e.boxlist.size(); j++) {
					if (i != j) {
						ArrayList<Box> boxlist2 = new ArrayList<Box>();
						boxlist2.addAll(e.boxlist);
						Box b1 = new Box(e.boxlist.get(i).largestEdge, e.boxlist.get(i).mediumEdge,
								e.boxlist.get(i).shortestEdge);
						b1.setIndex(llist, mlist, slist);
						Box b2 = new Box(e.boxlist.get(j).largestEdge, e.boxlist.get(j).mediumEdge,
								e.boxlist.get(j).shortestEdge);
						b2.setIndex(llist, mlist, slist);
						b1.ordersList.addAll(e.boxlist.get(i).ordersList);
						b2.ordersList.addAll(e.boxlist.get(j).ordersList);
						boxlist2.remove(i);
						boxlist2.add(i, b1);
						boxlist2.remove(j);
						boxlist2.add(j, b2);
						Environment env = new Environment(boxlist2);
						env.setLists(slist, mlist, llist);
						env.improve(i, j);
						env.updateVol();
						envmap.put(env.volume, env);
					}
				}
			}
			Environment e2 = envmap.get(envmap.firstKey());
			if (envmap.firstKey() != e.volume) {
				decreaseSizes(e2);
				System.out.println(e.volume);
				e = bestSol(e2);
			} else {
				e.changeImprovable();
			}
		}
		return e;
	}

	public static void sort(ArrayList<Double> liste) {
		for (int i = 0; i < liste.size() - 1; i++) {
			for (int j = i + 1; j < liste.size(); j++) {
				if (liste.get(j) < liste.get(i)) {
					double a = liste.get(j);
					liste.remove(j);
					liste.add(j, liste.get(i));
					liste.remove(i);
					liste.add(i, a);
				}
			}
		}
	}

	public static void decreaseSizes(Environment ef) {
		for (int i = 0; i < ef.boxlist.size(); i++) {
			double l1 = 0;
			double m1 = 0;
			double sh1 = 0;
			for (int j = 0; j < ef.boxlist.get(i).ordersList.size(); j++) {
				if (ef.boxlist.get(i).ordersList.get(j).largest_edge > l1) {
					l1 = ef.boxlist.get(i).ordersList.get(j).largest_edge;
				}
				if (ef.boxlist.get(i).ordersList.get(j).medium_edge > m1) {
					m1 = ef.boxlist.get(i).ordersList.get(j).medium_edge;
				}
				if (ef.boxlist.get(i).ordersList.get(j).shortest_edge > sh1) {
					sh1 = ef.boxlist.get(i).ordersList.get(j).shortest_edge;
				}
			}
			ef.boxlist.get(i).setEdges(l1, m1, sh1);
		}
	}

	public static void reduceList(ArrayList<Double> liste) {
		ArrayList<Double> reduced = new ArrayList<Double>();
		for (int i = 0; i < liste.size(); i++) {
			if (reduced.contains(liste.get(i)) == false) {
				reduced.add(liste.get(i));
			}
		}
		liste.clear();
		liste.addAll(reduced);
	}
}
