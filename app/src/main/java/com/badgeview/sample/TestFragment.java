package com.badgeview.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.badgeview.BadgeActionView;
import com.badgeview.BadgeView;

/**
 * Created by Viнt@rь on 14.09.2016
 */
public class TestFragment extends Fragment {

    private BadgeActionView mBadgeActionView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);


        final BadgeView badgeView = (BadgeView) view.findViewById(R.id.test_badge_view);

        final TextView textView = (TextView) view.findViewById(R.id.test_text_view);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBadgeActionView.setText("1");
                //badge.setText("123");
                //textView.animate().translationX(100f).start();
                //mBadge.hide();
                //badgeView.hide();
                //startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        MenuItem testItem = menu.findItem(R.id.action_test2);

        mBadgeActionView = (BadgeActionView) testItem.getActionView();
        mBadgeActionView.setMenuItem(testItem);
        mBadgeActionView.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("TEST", "onOptionsItemSelected");

        return super.onOptionsItemSelected(item);
    }

    /*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ((MainActivity) getActivity()).openFragment(new TestFragment());
        return super.onOptionsItemSelected(item);
    }*/
}
