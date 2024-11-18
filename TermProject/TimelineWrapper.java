package termproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class TimelineWrapper {

	private Circle circle;
	private double vVelocity;
	private double hVelocity;
	private double gravity;
	private double timeOfFlight;
	private double scale;

	public TimelineWrapper(Circle circle, double vVelocity, double hVelocity, double gravity,
			double timeOfFlight, double scale) {
		this.circle = circle;
		this.vVelocity = vVelocity;
		this.hVelocity = hVelocity;
		this.gravity = gravity;
		this.timeOfFlight = timeOfFlight;
		this.scale = scale;
	}

	public Timeline buildTimeline() {
		// TODO Auto-generated method stub
		double frameRate = 60; //fps or hz
		double timeStep = 1 / frameRate;
		
		Timeline timeline = new Timeline();
		for (double time = 0; time <= timeOfFlight; time += timeStep) {
			double finalTime = time;
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(time), event -> updatePosition(finalTime));
			
			timeline .getKeyFrames().add(keyFrame);
		}
		
		timeline.setCycleCount(1);
		return timeline;
	}
	

	private void updatePosition(double time) {
		// TODO Auto-generated method stub
		double xTranslation = hVelocity * time;
		double yTranslation = (vVelocity * time) - (0.5 * gravity * time * time);
		circle.setTranslateX(xTranslation * scale);
		circle.setTranslateY(yTranslation * scale); 
	}
}
