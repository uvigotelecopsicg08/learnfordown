package com.uvigo.learnfordown.learnfordown.strokes.app.datatype;

public class Point2D implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3662542737123359655L;

	public float x;
	public float y;

	public Point2D(float mX, float mY) {
		this.x = mX;
		this.y = mY;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point2D) {
			Point2D d = (Point2D) obj;
			return (x == d.x) && (y == d.y);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hashsum = Double.valueOf(x + y).hashCode();
		return hashsum * (hashsum + 1) / 2 + Double.valueOf(x).hashCode();
	}

	@Override
	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + "]";
	}
}
