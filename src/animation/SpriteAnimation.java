package animation;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

	private final ImageView imgV;
	private final int count;
	private final int columns;
	private int delta;
	private final int width;
	private final int height;
	
	private int lastIndex;
	
	public SpriteAnimation(ImageView imgV,
			Duration dur, 
			int count,
			int columns,
			int delta,
			int width,
			int height) {
		super();
		this.imgV = imgV;
		this.count = count;
		this.columns = columns;
		this.delta = delta;
		this.width = width;
		this.height = height;
		
		lastIndex = 0;
		
		setCycleDuration(dur);
		setCycleCount(Animation.INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		imgV.setViewport(new Rectangle2D(delta, 0, width, height));
		
	}
	
	public void setDelta(int d) {
		delta = d;
	}



	@Override
	protected void interpolate(double frac) {
		
		final int index = Math.min((int) Math.floor(frac * count), count - 1);
		if (index != lastIndex) {
			final int x = (index % columns) * width + delta;
			imgV.setViewport( (new Rectangle2D(x, 0, width, height)));
			lastIndex = index;
		}
		
	}

	
	
}
