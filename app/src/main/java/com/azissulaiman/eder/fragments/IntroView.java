package com.azissulaiman.eder.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.azissulaiman.eder.R;

import java.util.List;

public class IntroView extends PagerAdapter {

    Context mContext;
    List<ScreenItem> mListSreen;

    public IntroView (Context mContext,List<ScreenItem> mListSreen){
        this.mContext = mContext;
        this.mListSreen = mListSreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView img1 = layoutScreen.findViewById(R.id.imgBoard);
        TextView title = layoutScreen.findViewById(R.id.txtitle);
        TextView desc = layoutScreen.findViewById(R.id.txtDesc);

        title.setText(mListSreen.get(position).getTitle());
        desc.setText(mListSreen.get(position).getDescription());
        img1.setImageResource(mListSreen.get(position).getGambar());

        container.addView(layoutScreen);
        return layoutScreen;


    }

    @Override
    public int getCount() {
        return mListSreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
