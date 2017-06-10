package study.interview.jiuzhang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 170609 medium
 * http://www.cnblogs.com/zcy-backend/p/6734304.html
 * 每个学生有两个属性 id 和 scores。找到每个学生最高的5个分数的平均值。
 * 比较容易。
 */
public class Ama9Ti_HighFive {
	public static class Record{
		int id;
		int score;
	    public Record(int id, int score){
	    	this.id = id;
	    	this.score = score;
	    }
	}
	
	/**
	 * @param results a list of Records
	 * @return find the average of 5 highest scores for each person
	 */
	public Map<Integer, Double> highFive(List<Record> results){
		Map<Integer, Double> res = new HashMap<Integer, Double>();
		Map<Integer, PriorityQueue<Integer>> pqMap = new HashMap<Integer, PriorityQueue<Integer>>();
		for(Record r: results){
			PriorityQueue<Integer> pq = pqMap.getOrDefault(r.id, new PriorityQueue<Integer>(Collections.reverseOrder()));
			pq.offer(r.score);
			pqMap.put(r.id, pq);
		}
		for(int id: pqMap.keySet()){
			PriorityQueue<Integer> pq =  pqMap.get(id);
			double avgScore = 0, sum = 0, count = 0;
			for (int i = 0; i < 5 && !pq.isEmpty(); i++) {
				sum += pq.poll();
				count ++;
			}
			avgScore = count == 0 ? 0: sum/count;
			res.put(id, avgScore);
		}
		return res;
	}
	
	public static void main(String[] args) {
		Ama9Ti_HighFive p = new Ama9Ti_HighFive();
		List<Record> records = new ArrayList<Record>();
		records.add(new Record(1, 10));
		records.add(new Record(1, 100));
		records.add(new Record(2, 80));
		records.add(new Record(2, 73));
		records.add(new Record(1, 20));
		records.add(new Record(1, 80));
		records.add(new Record(1, 70));
		records.add(new Record(1, 90));
		records.add(new Record(1, 60));
		System.out.println(p.highFive(records));
	}
}
