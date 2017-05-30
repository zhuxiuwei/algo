package study.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Amazon online test。 170530
 * Have lunchMenuPairs and friends Preference, show all pairs of lunches and friends.
 */
public class I170530_Amazon_CuisineForFriends {

	public String[][] matchLunches(String[][] lunchMenuPairs, 
            String[][] teamCuisinePreference)
	{
		if(lunchMenuPairs == null || lunchMenuPairs.length == 0 || teamCuisinePreference == null || teamCuisinePreference.length == 0)
			return new String[][]{};
		Map<String, List<String>> map = new HashMap<String, List<String>>(); //key: cuisine type, value: list of its lunch options
		map.put("*", new ArrayList<String>()); //for team members who has no particular preference
		for (int i = 0; i < lunchMenuPairs.length; i++) {	//Fill map values.
			String cuisine = lunchMenuPairs[i][1];
			String lunchOp = lunchMenuPairs[i][0];
			List<String> lunchOps = map.getOrDefault(cuisine, new ArrayList<String>());
			lunchOps.add(lunchOp);
			map.put(cuisine, lunchOps);
			map.get("*").add(lunchOp);
		}
		List<String[]> tempRes = new ArrayList<String[]>();
		for (int i = 0; i < teamCuisinePreference.length; i++) {
			List<String> lunchOps = map.get(teamCuisinePreference[i][1]);
			if(null != lunchOps){
				for(String lunchOp: lunchOps)
					tempRes.add(new String[]{teamCuisinePreference[i][0], lunchOp});
			}
		}
		return (String[][])tempRes.toArray(new String[tempRes.size()][]);
	}
	
	public static void main(String[] args) {
		I170530_Amazon_CuisineForFriends a = new I170530_Amazon_CuisineForFriends();
		String[][] res = a.matchLunches(new String[][]{{"Pizza","Ita"}, {"Curry","Ina"},{"Masala","Ina"}}, new String[][]{{"Jose","Ita"},{"John","Ina"},{"Sarah","Thai"},{"Mary","*"}});
		System.out.println(Arrays.deepToString(res));
	}

}
