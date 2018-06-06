package vn.aquavietnam.aquaiget.common.customview.slider;

import android.support.annotation.Nullable;

import vn.aquavietnam.aquaiget.common.customview.slider.animation.AnimationManager;
import vn.aquavietnam.aquaiget.common.customview.slider.animation.controller.ValueController;
import vn.aquavietnam.aquaiget.common.customview.slider.animation.data.Value;
import vn.aquavietnam.aquaiget.common.customview.slider.draw.DrawManager;
import vn.aquavietnam.aquaiget.common.customview.slider.draw.data.Indicator;


public class IndicatorManager implements ValueController.UpdateListener {

    private DrawManager drawManager;
    private AnimationManager animationManager;
    private Listener listener;

    interface Listener {
        void onIndicatorUpdated();
    }

    IndicatorManager(@Nullable Listener listener) {
        this.listener = listener;
        this.drawManager = new DrawManager();
        this.animationManager = new AnimationManager(drawManager.indicator(), this);
    }

    public AnimationManager animate() {
        return animationManager;
    }

    public Indicator indicator() {
        return drawManager.indicator();
    }

    public DrawManager drawer() {
        return drawManager;
    }

    @Override
    public void onValueUpdated(@Nullable Value value) {
        drawManager.updateValue(value);
        if (listener != null) {
            listener.onIndicatorUpdated();
        }
    }
}
