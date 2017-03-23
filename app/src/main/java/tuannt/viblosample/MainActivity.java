package tuannt.viblosample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageSwitcher;

import tuannt.viblosample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int[] mSlides = {
        R.drawable.anh_1,
        R.drawable.anh_2,
        R.drawable.anh_3
    };
    private int mCount = 1;
    private ImageSwitcher mImageSwitcher;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);
        mImageSwitcher = mainBinding.imageSwitcher;
        setUpSample_4();
    }

    private void setUpSample_1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageSwitcher.setImageResource(mSlides[mCount++ % mSlides.length]);
                        setUpSample_1();
                    }
                });
            }
        }).start();
    }

    private void setUpSample_2() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                mImageSwitcher.setImageResource(mSlides[mCount++ % mSlides.length]);
                setUpSample_2();
                return true;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = mHandler.obtainMessage();
                mHandler.sendMessage(message);
            }
        }).start();
    }

    private void setUpSample_3() {
        mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mImageSwitcher.setImageResource(mSlides[mCount++ % mSlides.length]);
                        setUpSample_3();
                    }
                });
            }
        }).start();
    }

    private void setUpSample_4() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageSwitcher.setImageResource(mSlides[mCount++ % mSlides.length]);
                setUpSample_4();
            }
        }, 3000);
    }
}
