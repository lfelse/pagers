package io.lf.pagers;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewPager;
    private TextView text;
    private LinearLayout dots;

    private ArrayList<Page> pages = new ArrayList<>();
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //Log.v(TAG, "" + position);
        }

        @Override
        public void onPageSelected(int position) {
            updateText();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        text = (TextView) findViewById(R.id.text);
        dots = (LinearLayout) findViewById(R.id.dots);
    }
    private void initData() {
        pages.add(new Page(R.drawable.bird_red, "red bird"));
        pages.add(new Page(R.drawable.images, "normal bird"));
        pages.add(new Page(R.drawable.red_image, "bird bird bird"));
        pages.add(new Page(R.drawable.story_image, "we are family"));
        pages.add(new Page(R.drawable.website_image, "don't worry, be happy"));

        initDots();

        viewPager.setAdapter(new CustomPagerAdapter());
        //viewPager.setOnPageChangeListener();
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setCurrentItem(Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2 % pages.size());
        updateText();

    }

    private void initDots() {
        for (int i = 0; i < pages.size(); i++) {
            View view = new View(this);
            float density = getResources().getDisplayMetrics().density;
            int x = (int)(10 * density);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(x, x);
            if(i != 0){
                params.leftMargin = x;
            }
            //params.gravity = Gravity.CENTER_HORIZONTAL;
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.selecter);
            dots.addView(view);
        }
    }

    private void updateText() {
        int position = viewPager.getCurrentItem() % pages.size();
        text.setText(pages.get(position).getInfo());

        for (int i = 0; i < dots.getChildCount(); i++) {
            dots.getChildAt(i).setEnabled(position == i);
            /*if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                dots.getChildAt(i).setBackground(getDrawableCompat(R.drawable.selecter));
            }else {
                dots.getChildAt(i).setBackgroundResource(R.drawable.selecter);
            }*/
        }

    }

    private Drawable getDrawableCompat(int resId){
        return ContextCompat.getDrawable(this, resId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    private class CustomPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            ViewGroup.LayoutParams layouts = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layouts);
            imageView.setImageResource(pages.get(position % pages.size()).getResId());
            //imageView.setBackgroundColor(Color.MAGENTA);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }
    }
}
