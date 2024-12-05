package com.vanier.TermProject.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Physics {

	private Shape object;
	
	public Shape getObject() {
		return object;
	}

	public void setObject(Shape object) {
		this.object = object;
		updatePosition();
	}

	//physics variables
	private double startHorizontalVelocity;
	private double startVerticalVelocity;
	private double startHeight;
	private double gravitationAcceleration;
	
	//other ingredients
	private Timeline timeline;
	
	//exclusive use for timeline and timeline-related purposes
	private DoubleProperty elapsedTime;

	public final void setElapsedTime(double value) {
		elapsedTimeProperty().set(value);
	}

	public final double getElapsedTime() {
		return elapsedTime == null ? 0.0 : elapsedTime.get();
	}

	public final DoubleProperty elapsedTimeProperty() {
		if (elapsedTime == null) {
			elapsedTime = new DoublePropertyBase(0.0) {

				@Override
				public Object getBean() {
					return Physics.this;
				}

				@Override
				public String getName() {
					return "elapsedTime";
				}
			};
		}
		return elapsedTime;
	}
	
	private static volatile Physics instance;

	Physics() {
		timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
			updatePosition();
			setElapsedTime(getElapsedTime() + 0.001);
			if (calculateY(getElapsedTime()) == 0 && getElapsedTime() > 0)
				timeline.stop();
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
	}

	public static Physics getInstance() {
		if (instance == null) {
			synchronized (Physics.class) {
				if (instance == null) {
					instance = new Physics();
				}
			}
		}
		return instance;
	}


	public void updatePosition() {
		object.setLayoutX(calculateX(getElapsedTime()) + getObjectWidth()/2);
		object.setLayoutY(calculateY(getElapsedTime()) + getObjectHeight()/2);
	}

	public double getObjectHeight() {
		// TODO Auto-generated method stub
	    if (object == null) {
	        return 0;
	    }
	    if (object instanceof Circle) {
	        Circle circle = (Circle) object;
	        return circle.getRadius() * 2;
	    } else if (object instanceof Rectangle) {
	        Rectangle rectangle = (Rectangle) object;
	        return rectangle.getWidth();
	    } else {
	        System.out.println("The object is using an unsupported Shape type: " + object.getClass().getSimpleName());
	        return 0;
	    }
	}

	public double getObjectWidth() {
	    if (object == null) {
	        return 0;
	    }
	    if (object instanceof Circle) {
	        Circle circle = (Circle) object;
	        return circle.getRadius() * 2;
	    } else if (object instanceof Rectangle) {
	        Rectangle rectangle = (Rectangle) object;
	        return rectangle.getWidth();
	    } else {
	        System.out.println("The object is using an unsupported Shape type: " + object.getClass().getSimpleName());
	        return 0;
	    }
	}

	public double calculateX(double time) {
	    System.out.println("Debugging Calculate X:");
	    System.out.println("Initial Horizontal Velocity: " + startHorizontalVelocity);
	    System.out.println("Elapsed Time: " + time);
	    
	    double result = startHorizontalVelocity * time;
	    System.out.println("Calculated X Position: " + result);
	    
	    return result;
	}


	public double calculateY(double time) {
	    System.out.println("Debugging Calculate Y:");
	    System.out.println("Initial Height: " + startHeight);
	    System.out.println("Initial Vertical Velocity: " + startVerticalVelocity);
	    System.out.println("Gravitational Acceleration: " + gravitationAcceleration);
	    System.out.println("Elapsed Time: " + time);

	    double result = startHeight + (startVerticalVelocity * time) - (0.5 * gravitationAcceleration * time * time);
	    if (result < 0)
	    	result = 0;
	    System.out.println("Calculated Y position: " + result);

	    return result;
	}


	public double getStartHorizontalVelocity() {
	    System.out.println("Getting Initial Horizontal Velocity: " + startHorizontalVelocity);
	    return startHorizontalVelocity;
	}

	public void setStartHorizontalVelocity(double startHorizontalVelocity) {
	    System.out.println("Setting Initial Horizontal Velocity: " + startHorizontalVelocity);
	    this.startHorizontalVelocity = startHorizontalVelocity;
	}

	public double getStartVerticalVelocity() {
	    System.out.println("Getting Initial Vertical Velocity: " + startVerticalVelocity);
	    return startVerticalVelocity;
	}

	public void setStartVerticalVelocity(double startVerticalVelocity) {
	    System.out.println("Setting Initial Vertical Velocity: " + startVerticalVelocity);
	    this.startVerticalVelocity = startVerticalVelocity;
	}

	public double getStartHeight() {
	    System.out.println("Getting Initial Height: " + startHeight);
	    return startHeight;
	}

	public void setStartHeight(double startHeight) {
	    System.out.println("Setting Initial Height: " + startHeight);
	    this.startHeight = startHeight;
	}

	public double getGravitationAcceleration() {
	    System.out.println("Getting Gravitational Acceleration: " + gravitationAcceleration);
	    return gravitationAcceleration;
	}

	public void setGravitationAcceleration(double gravitationAcceleration) {
	    System.out.println("Setting Gravitational Acceleration: " + gravitationAcceleration);
	    this.gravitationAcceleration = gravitationAcceleration;
	}

	public void play() {
		// TODO Auto-generated method stub
		timeline.play();
	}

	public void pause() {
		// TODO Auto-generated method stub
		timeline.pause();
	}

	public void reset() {
		// TODO Auto-generated method stub
		jumpTo(0);
	}

	public void jumpTo(double destination) {
		// TODO Auto-generated method stub
		timeline.pause();
		setElapsedTime(destination);;
		updatePosition();
	}

	public double getVerticalDisplacement() {
	    return calculateY(getElapsedTime()) - startHeight;
	}

	public double getHorizontalDisplacement() {
	    return calculateX(getElapsedTime());
	}

	public double getTimeOfFlight() {
	    System.out.println("Calculating time of flight...");
	    if (gravitationAcceleration <= 0) {
	        throw new IllegalArgumentException("Gravitational acceleration must be positive.");
	    }
	    if (startVerticalVelocity == 0 && startHeight == 0) {
	        System.out.println("No vertical motion. Time of flight is 0.");
	        return 0;
	    }
	    double g = gravitationAcceleration;
	    double vy = startVerticalVelocity;
	    double h = startHeight;
	    double discriminant = Math.pow(vy, 2) + 2 * g * h;
	    if (discriminant < 0) {
	        throw new IllegalStateException("Invalid state: Discriminant is negative, cannot compute time of flight.");
	    }
	    double time = (vy + Math.sqrt(discriminant)) / g;
	    System.out.println("Time of flight calculated: " + time + " seconds.");
	    return time;
	}

	public double calculateVerticalVelocity(double time) {
	    return startVerticalVelocity - gravitationAcceleration * time;
	}

}
