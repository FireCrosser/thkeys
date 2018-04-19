package ua.edu.ukma.thkeys.services;

import java.util.HashSet;

public class WeeksParser {

	//ǳ ������, �� ����� ������ �����, ������ ������ ������ �����
	public static HashSet<Integer> getWeeksNumbers(String weeks) {
		
		//���� ������� ����� ����
		String[] weeks_split = weeks.split(",");
		
		HashSet<Integer> res = new HashSet<Integer>();
		
		for(String wk : weeks_split) {
			if(isNumeric(wk))
				res.add(Integer.parseInt(wk));
			else if(wk.indexOf('-') != -1) { //7-11
				HashSet<Integer> temp = getWeeksFromRange(wk);
				for(int w : temp) {
					res.add(w);
				}
			}
			else {
				System.out.println(wk+" is undefined");
			}
				
		}
			
		return res;
	}

	private static boolean isNumeric(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	//7-11
	private static HashSet<Integer> getWeeksFromRange(String str) {
		
		HashSet<Integer> res = new HashSet<Integer>();
		
		String[] weeks_spl = str.replaceAll("\\s+","").split("-");
		
		int start = Integer.parseInt(weeks_spl[0]);
		int end = Integer.parseInt(weeks_spl[1]);
		
		for(int i = start; i <= end; i++) {
			res.add(i);
		}
		
		if(res.contains(8)) res.remove(8);
		
		return res;
	}
	
	public static void main(String[] args) {
		HashSet<Integer> weeks = WeeksParser.getWeeksNumbers("1 - 12");
		
		for(int w : weeks) {
			System.out.print(w+" ");
		}
		
		/*String s = "1 - 12";
		System.out.println(s.replaceAll("\\s+",""));*/
	}

}
