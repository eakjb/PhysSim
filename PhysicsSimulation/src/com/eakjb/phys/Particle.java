package com.eakjb.phys;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Particle {
	private double x;
	private double y;
	private double hsp=0;
	private double vsp=0;
	
	private Color color;
	private double radius;
	
	private boolean rectangle = false;
	
	private World w;
	
	private ArrayList<Behavior> behaviors = new ArrayList<Behavior>();
	
	public Particle(double x,double y,Color color,double radius,World w) {
		this.x=x;
		this.y=y;
		this.color=color;
		this.radius=radius;
		this.w=w;
	}
	
	public void step(long step) {
		for (Behavior b : behaviors) {
			b.step(step, this, w);
		}
		x+=hsp;
		y+=vsp;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getHsp() {
		return hsp;
	}

	public void setHsp(double hsp) {
		this.hsp = hsp;
	}

	public double getVsp() {
		return vsp;
	}

	public void setVsp(double vsp) {
		this.vsp = vsp;
	}

	public boolean isRectangle() {
		return rectangle;
	}

	public void setRectangle(boolean rectangle) {
		this.rectangle = rectangle;
	}
	
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(getX()-(getRadius()/2.0),getY()-(getRadius()/2.0),
					getRadius(),getRadius());
	}
	public Rectangle2D.Double getBounds(double hsp,double vsp) {
		Rectangle2D.Double bounds = getBounds();
		return new Rectangle2D.Double(bounds.getX()+hsp,bounds.getY()+vsp,
				bounds.getWidth(),bounds.getHeight());
	}
	public Shape getShape() {
		Rectangle2D.Double bounds = getBounds();
		return isRectangle() ? bounds: new Ellipse2D.Double(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
	}
	
	public Point2D.Double getPoint() {
		return new Point2D.Double(getX(),getY());
	}

	public ArrayList<Behavior> getBehaviors() {
		return behaviors;
	}

	public void setBehaviors(ArrayList<Behavior> behaviors) {
		this.behaviors = behaviors;
	}
}
