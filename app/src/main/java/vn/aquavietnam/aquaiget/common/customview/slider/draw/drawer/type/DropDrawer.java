package vn.aquavietnam.aquaiget.common.customview.slider.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import vn.aquavietnam.aquaiget.common.customview.slider.animation.data.Value;
import vn.aquavietnam.aquaiget.common.customview.slider.animation.data.type.DropAnimationValue;
import vn.aquavietnam.aquaiget.common.customview.slider.draw.data.Indicator;
import vn.aquavietnam.aquaiget.common.customview.slider.draw.data.Orientation;


public class DropDrawer extends BaseDrawer {

    public DropDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        super(paint, indicator);
    }

    public void draw(
            @NonNull Canvas canvas,
            @NonNull Value value,
            int coordinateX,
            int coordinateY) {

        if (!(value instanceof DropAnimationValue)) {
            return;
        }

        DropAnimationValue v = (DropAnimationValue) value;
        int unselectedColor = indicator.getUnselectedColor();
        int selectedColor = indicator.getSelectedColor();
        float radius = indicator.getRadius();

        paint.setColor(unselectedColor);
        canvas.drawCircle(coordinateX, coordinateY, radius, paint);

        paint.setColor(selectedColor);
        if (indicator.getOrientation() == Orientation.HORIZONTAL) {
            canvas.drawCircle(v.getWidth(), v.getHeight(), v.getRadius(), paint);
        } else {
            canvas.drawCircle(v.getHeight(), v.getWidth(), v.getRadius(), paint);
        }
    }
}
