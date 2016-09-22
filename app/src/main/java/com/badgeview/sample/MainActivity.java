package com.badgeview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.badgeview.BadgeActionView;
import com.badgeview.BadgeView;

public class MainActivity extends AppCompatActivity {

    private BadgeActionView mBadgeActionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BadgeView badgeView = (BadgeView) findViewById(R.id.test_badge_view);
        final TextView textView = (TextView) findViewById(R.id.test_text_view);
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

        MenuItem menuItem = menu.findItem(R.id.action_test2);
        mBadgeActionView = (BadgeActionView) menuItem.getActionView();
        mBadgeActionView.setOnMenuItemClick(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        mBadgeActionView.setMenuItem(menuItem);
        mBadgeActionView.show();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_test2:
                if (TextUtils.isEmpty(mBadgeActionView.getText())) {
                    mBadgeActionView.setText("123");
                } else {
                    mBadgeActionView.toggle();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
