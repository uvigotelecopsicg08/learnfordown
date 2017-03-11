package com.uvigo.learnfordown.learnfordown.util;

public class AngularDistance implements DistanceFunction {

	@Override
	public double calcDistance(double[] vector1, double[] vector2) {
		return Math.abs(Math.atan2(Math.sin(vector1[0]-vector2[0]), Math.cos(vector1[0]-vector2[0])));
	}
}
