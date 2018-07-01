package com.badgeview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.badgeview.BadgeActionMenuItemView;
import com.badgeview.BadgeView;

public class MainActivity extends AppCompatActivity {

    private BadgeActionMenuItemView mBadgeActionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BadgeView badgeView = findViewById(R.id.test_badge_view);
        final TextView textView = findViewById(R.id.test_text_view);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeView.toggle();
            }
        });
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
