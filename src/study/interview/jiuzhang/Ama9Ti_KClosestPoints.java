package study.interview.jiuzhang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import LeetCode.round1.common.Point;
/**
 * 170609 medium
 * http://www.cnblogs.com/zcy-backend/p/6734304.html
 * 给出一组点，和一个目标点，找出这组点中距离目标点最近的k个点。
 * 总体顺利。注意一个bug。
 */
public class Ama9Ti_KClosestPoints {
	
	/**
	 * @param points a list of points
	 * @param k an integer
	 * @param origin origin point
	 * @return
	 */
	public Point[] kClosest(Point[] points, int k, Point origin){
		if(points == null || origin == null || points.length == 0 || k <= 0)
			return new Point[]{};
		
		if(k > points.length)
			k = points.length;
		
		Point[] res = new Point[k];
		PriorityQueue<Double> pq = new PriorityQueue<Double>();
		Map<Double, List<Point>> destToPoint = new HashMap<Double, List<Point>>();
		for (int i = 0; i < points.length; i++) {
			Point other = points[i];
			double dest = Math.sqrt((origin.y - other.y) * (origin.y - other.y) 
					+ (origin.x - other.x) * (origin.x - other.x) );
			if(pq.size() < k){
				pq.offer(dest);
				List<Point> suchPoints = destToPoint.getOrDefault(dest, new LinkedList<Point>());
				suchPoints.add(other);
				destToPoint.put(dest, suchPoints);
			}else{
				if(dest > pq.peek()){
					pq.poll();
					pq.offer(dest);
					List<Point> suchPoints = destToPoint.getOrDefault(dest, new LinkedList<Point>());
					suchPoints.add(other);
					destToPoint.put(dest, suchPoints);
				}
			}
		}
		for (int i = 0; i < k; i++) {
			double dest = pq.poll();
			Iterator<Point> iter = destToPoint.get(dest).iterator();
			boolean breakFor = false;
			while(iter.hasNext()){
				Point p = iter.next();
				res[i] = p;
				if(iter.hasNext()) i ++;	//！！！！！注意bug。不加上if，会导致i跳过一个。！！！！！！！
				if(i == k){
					breakFor = true;
					break;
				}
			}
			if(breakFor)
				break;
		}
		return res;
	}
	
	public static void main(String[] args) {
		Ama9Ti_KClosestPoints p = new Ama9Ti_KClosestPoints();
		Point origin = new Point(0, 0);
		Point[] points = new Point[]{
			new Point(5,6),
			new Point(-5,-6),
			new Point(6,5),
			new Point(5,6),
			new Point(1,1),
			new Point(2,2),
			new Point(3,3)
		};
		System.out.println(Arrays.toString(p.kClosest(points, 6, origin))); //[[2,2], [3,3], [5,6], [-5,-6], [6,5], [5,6]]
		System.out.println(Arrays.toString(p.kClosest(points, 1, origin))); //[[5,6]]
	}

}
