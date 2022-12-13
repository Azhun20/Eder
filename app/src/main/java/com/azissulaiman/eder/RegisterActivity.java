package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.azissulaiman.eder.fragments.EmailLoginFragment;
import com.azissulaiman.eder.fragments.EmailRegisterFragment;
import com.azissulaiman.eder.fragments.PhoneLoginFragment;
import com.azissulaiman.eder.fragments.PhoneRegisterFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout2;
    RegisterActivity activity;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewPager2 = findViewById(R.id.viewPagerReg);
        tabLayout2 = findViewById(R.id.tabLayoutReg);
        activity = this;

        initView();
    }

    private void initView() {
        adapter = new ViewPagerAdapter(activity.getSupportFragmentManager(),getLifecycle());
        adapter.addFragment(new EmailRegisterFragment(), "Email");
        adapter.addFragment(new PhoneRegisterFragment(), "Phone");
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(1);

        new TabLayoutMediator(tabLayout2, viewPager2,
                (tab, position)-> {
                    tab.setText(adapter.mFragmentTitleList.get(position));
                }).attach();

        for (int i = 0; i<tabLayout2.getTabCount(); i++){
            TextView textView = (TextView) LayoutInflater.from(activity).inflate(R.layout.custom_tab_login,null);
            tabLayout2.getTabAt(i).setCustomView(textView);
        }

    }

    private class ViewPagerAdapter extends FragmentStateAdapter {
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