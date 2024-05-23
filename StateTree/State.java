/**
 *	The object to store US state information.
 *
 *	@author	
 *	@since	
 */
public class State implements Comparable<State>
{
	private String name;
	private String abbreviation;
	private int population;
	private int area;
	private int reps;
	private String capital;
	private int month;
	private int day;
	private int year;
	
	public State(String n, String a, int p, int ar, int r, String c, int m, int d, int y) {
		name = n;
		abbreviation = a;
		population = p;
		area = ar;
		reps = r;
		capital = c;
		month = m;
		day = d;
		year = y;
	}
	
	public int compareTo(State other) {
		return name.compareTo(other.getName());
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		String ret = name;
		for(int i = 0; i < 20 - name.length(); i++) {
			ret += " ";
		}
		ret += abbreviation + "\t\t" + population;
		String pop = "" + population;
		for(int j = 0; j < 12 - pop.length(); j++) {
			ret += " ";
		}
		ret += area + "\t" + reps + "\t" + capital;
		for(int k = 0; k < 20 - capital.length(); k++) {
			ret += " ";
		}
		ret += month + "\t" + day;
		String d = "" + day;
		for(int l = 0; l < 4 - d.length(); l++) {
			ret += " ";
		}
		ret += year;
		return ret;
	}
}
