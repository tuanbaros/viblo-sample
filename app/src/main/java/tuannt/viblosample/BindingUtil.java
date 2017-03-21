package tuannt.viblosample;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

/**
 * Created by tuannt on 21/03/2017.
 * Project: viblo-sample
 * Package: tuannt.viblosample
 */
public class BindingUtil {
    @BindingAdapter({"factory"})
    public static void setImage(final ImageSwitcher imageSwitcher, final Drawable drawable) {
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(imageSwitcher.getContext());
                imageView.setImageDrawable(drawable);
                imageView.setLayoutParams(
                    new ImageSwitcher.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });
    }

    @BindingAdapter({"slide"})
    public static void setSlide(final ImageSwitcher imageSwitcher, final MainActivity activity) {
        final int pos = (Integer) imageSwitcher.getTag();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageSwitcher
                            .setImageResource(
                                activity.getSlides()[pos % activity.getSlides().length]);
                        imageSwitcher.setTag(pos + 1);
                        setSlide(imageSwitcher, activity);
                    }
                });
            }
        }).start();
    }
}
