package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.azissulaiman.eder.databinding.ActivityLoginBinding;
import com.azissulaiman.eder.fragments.EmailLoginFragment;
import com.azissulaiman.eder.fragments.PhoneLoginFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    LoginActivity activity;
    ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewPager2 = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        activity = this;
        
        initView();

    }

    private void initView() {
        adapter = new ViewPagerAdapter(activity.getSupportFragmentManager(),getLifecycle());
        adapter.addFragment(new EmailLoginFragment(), "Email");
        adapter.addFragment(new PhoneLoginFragment(), "Phone");
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(1);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position)-> {
                    tab.setText(adapter.mFragmentTitleList.get(position));
                }).attach();

        for (int i = 0; i<tabLayout.getTabCount(); i++){
            TextView textView = (TextView) LayoutInflater.from(activity).inflate(R.layout.custom_tab,null);
            tabLayout.getTabAt(i).setCustomView(textView);
        }
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#171212"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.parseColor("#2F4F4F")));
    }

    class ViewPagerAdapter extends FragmentStateAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NotNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @NotNull
        @Override
        public Fragment createFragment(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragmentList.size();
        }
    }
}