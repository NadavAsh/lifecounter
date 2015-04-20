package edu.washington.nadava.lifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    public static final String TAG = "MainActivity";

    private LayoutInflater inflater;
    private LinearLayout playersLayout;
    private ScrollView scroll;
    private List<PlayerLayout> playerLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = getLayoutInflater();
        playersLayout = (LinearLayout)findViewById(R.id.layout_players);
        scroll = (ScrollView)findViewById(R.id.scrollView);
        playerLayouts = new ArrayList<PlayerLayout>();

        playersLayout.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        for (int i = 0; i < 4; ++i) {
            addPlayer();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_player:
                addPlayer();
                break;
            case R.id.action_remove_player:
                removePlayer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addPlayer() {
        PlayerLayout layout = new PlayerLayout(this, playerLayouts.size() + 1);
        playerLayouts.add(layout);
        playersLayout.addView(layout);
        Log.i(TAG, "Added player #" + playerLayouts.size());
    }

    public void removePlayer() {
        if (playerLayouts.size() > 0) {
            PlayerLayout player = playerLayouts.remove(playerLayouts.size() - 1);
            playersLayout.removeView(player);
        }
    }
}
