package com.badgeview.sample;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.badgeview.BadgeActionMenuItemView;
import com.badgeview.sample.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BadgeActionMenuItemView mBadgeActionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.testTextView.setOnClickListener(v -> binding.testBadgeView.toggle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem badgeMenuItem = menu.findItem(R.id.action_test2);
        mBadgeActionView = (BadgeActionMenuItemView) badgeMenuItem.getActionView();
        mBadgeActionView.showBadge();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_test2:
                if (TextUtils.isEmpty(mBadgeActionView.getBadgeText())) {
                    mBadgeActionView.showBadge("123", false);
                } else {
                    mBadgeActionView.toggleBadge();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
