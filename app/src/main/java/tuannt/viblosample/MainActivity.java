package tuannt.viblosample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tuannt.viblosample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int[] mSlides = {
        R.drawable.anh_1,
        R.drawable.anh_2,
        R.drawable.anh_3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setActivity(this);
    }

    public int[] getSlides() {
        return mSlides;
    }
}
