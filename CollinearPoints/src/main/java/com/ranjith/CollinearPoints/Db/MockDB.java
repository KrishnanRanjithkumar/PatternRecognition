package com.ranjith.CollinearPoints.Db;

import java.util.HashSet;
import java.util.Set;

import com.ranjith.CollinearPoints.Model.Point;

public class MockDB {
	
	public static Set<Point> pointsInPlane = new HashSet<Point>();

	public static  Set<Point> getPointsInPlane() {
		return MockDB.pointsInPlane;
	}

	public static void setPointsInPlane(Set<Point> pointsInPlane) {
		MockDB.pointsInPlane = pointsInPlane;
	}
	
	
}
