package com.ranjith.CollinearPoints.Service;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ranjith.CollinearPoints.Db.MockDB;
import com.ranjith.CollinearPoints.Model.Line;
import com.ranjith.CollinearPoints.Model.Point;


/**
 * @author Ranjith
 *
 */
public class PlaneRestService {
	
	 
	 
	 /**
	 * @param pt - received from the post request
	 */
	public void addPointToPlane(Point pt) 
	 {
		 boolean duplicate = false;
		 
		 		//Check for duplicate
		 		Set<Point> points = MockDB.getPointsInPlane();
		 		if(points.size()>=1)
		 		{
		 				for(Point point : points) 
		 				{
			 			    if((point.getX()==pt.getX())&&(point.getY()==pt.getY())) {
		 					duplicate = true;
		 					break;
		 					}
			 			    
		 				}
 	 		
		 		}

		 		//if not duplicate add point in the plane
		 		if(!duplicate) {
		 			MockDB.pointsInPlane.add(pt);
		 		}
	
		 
	 }
	 

	/**
	 * @return all the points in the space
	 */

	public Set<Point> getAllPointsInPlane(){
		 
			 return MockDB.getPointsInPlane();
	 }
		 
	 /**
	 * @return simple text after removing all the points
	 */

	public String removeAllPointsInSpace() {
		 MockDB.pointsInPlane.clear();
		 return "All points in the plane are removed";
	 }
	 
	
	/**
	 * @param n - Integer represents the number of atleast points in the line
	 * @return  - All the lines which has atleast n points
	 */
	public Set<Line> getAllLinesOfSize(int n){
		 
		 Set<Point> points = MockDB.getPointsInPlane();
		 return findCollinear(points,n);
		 
		 
	 }
	 
	
	/**
	 * @param points - set of points in the space
	 * @param n - Integer represents the number of atleast points in the line 
	 * @return -  All the lines which has atleast n points
	 */
	private Set<Line> findCollinear(Set<Point> points, int n){
		 
		 Set<Line> lineSet = new HashSet<Line>();
		 for (Point point: points) 
		 {
			Point p = point;
			Map<Point,Double> slopePointsMap = new HashMap<Point,Double>();
			
			double curSlope = 0;
			for(Point nextPoint : points)
			{
				if(p!=nextPoint)
				{
					Point q = nextPoint;
					curSlope=findSlope(p,q);
					slopePointsMap.put( q,curSlope);
				}
			}
			
			
		
			Set<Double> slopeSet = new HashSet<Double>(slopePointsMap.values());
			
			
			for (Double slope : slopeSet)
			{
				Set<Point> pointSet = new HashSet<Point>();
				for (Map.Entry<Point,Double> entry : slopePointsMap.entrySet())  
				{
						
					if(Double.compare(slope, entry.getValue())== 0)
						{
						pointSet.add(entry.getKey());
						
						}
											
				}
				pointSet.add(p);
				
				if (pointSet.size()>=n) {
					Line line = new Line(pointSet);
					if(lineSet.size()>=1) {
						
						if(!checkAlreadyExist(lineSet,line))
						{
							lineSet.add(line);
						}
							
					}else {
					
					lineSet.add(line);
					}
				}
	
				
			}
					
		 }
		 
		 return lineSet;
	 }
	 

	/**
	 * @param lineSet - set of lines already exist
	 * @param line    - newly found line 
	 * @return 		  - true if already exist 
	 */
	private boolean checkAlreadyExist(Set<Line> lineSet, Line line) {
		
		for(Line lines : lineSet) {
			
			if(lines.getPoints().containsAll(line.getPoints()))
			{
				return true;
			}
		} 
		 
		 
		 return false;
	
	}


	/**
	 * @param p - point1
	 * @param q - point2
	 * @return  - slope of two points
	 */
	private double findSlope(Point p,Point q) {
		 
		 double x1 = p.getX();
		 double x2 = q.getX();
		 double y1 = p.getY();
		 double y2 = q.getY();
		 double slope = 0;
		 
		 try 
		 {
			 	if ((x2-x1)==0) {
			 	// Just a random number to identify, the two points share same x- coordinate
			 			slope = 111111;
			 	}
			 	else if((y2-y1)==0){
			 	// Just a random number to identify, the two points share same y- coordinate
			 		slope = 222222;
			 	}
		 
			 	else{
			 		slope = (y2-y1)/(x2-x1);
			 	}
		 }
		 
		 catch(Exception e)
		 {
			 System.out.println("Exception while finding slope :" );
			 e.printStackTrace();
		 }
				
		 return slope;
	 
	 }
	 
	 

}
