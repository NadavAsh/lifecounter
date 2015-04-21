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
    public static final int DEFAULT_NUM_PLAYERS = 4;

    public static final String PLAYER_LIVES = "player_lives";

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

        if (savedInstanceState != null && savedInstanceState.containsKey(PLAYER_LIVES)) {
            int[] playerLives = savedInstanceState.getIntArray(PLAYER_LIVES);
            for (int i = 0; i < playerLives.length; ++i) {
                addPlayer(playerLives[i]);
            }
        } else {
            for (int i = 0; i < DEFAULT_NUM_PLAYERS; ++i) {
                addPlayer(Player.DEFAULT_LIFE);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        int[] lives = new int[playerLayouts.size()];
        for (int i = 0; i < lives.length; ++i) {
            lives[i] = playerLayouts.get(i).getPlayer().getLife();
        }

        bundle.putIntArray(PLAYER_LIVES, lives);
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
                addPlayer(Player.DEFAULT_LIFE);
                break;
            case R.id.action_remove_player:
                removePlayer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addPlayer(int life) {
        PlayerLayout layout = new PlayerLayout(this, playerLayouts.size() + 1, life);
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
