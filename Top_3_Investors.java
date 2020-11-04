import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//using Comparable Functional Interface to sort the Investors List based on networth
class Investors implements Comparable<Investors>{
	int id;
	String name;
	double networth;
	
	public Investors(int id, String name, double networth) {
		super();
		this.id = id;
		this.name = name;
		this.networth = networth;
	}
	
	@Override
	public int compareTo(Investors inv) {
		return this.networth < inv.networth ? 1: this.networth == inv.networth ? 0: -1;
	}
}
public class MainClass {

	public static void main(String[] args) {
		List<Investors> inv_list = new ArrayList<>();
		inv_list.add(new Investors(101, "Diksha", 23500.50));
		inv_list.add(new Investors(102, "Sujeet", 35000));
		inv_list.add(new Investors(103, "Neha", 84000.80));
		inv_list.add(new Investors(104, "Aman", 6500.78));
		inv_list.add(new Investors(105, "Juhi", 34940.40));
		
		Collections.sort(inv_list);
		System.out.println("The Top 3 Highest networth investors are ----> ");
		int count = 0;
		for(Investors in : inv_list) {
			if(count == 3)
				break;
			else
				System.out.println("id : " + in.id + ", name : "+ in.name +", networth : " + in.networth);
			count++;
		}

	}
}
