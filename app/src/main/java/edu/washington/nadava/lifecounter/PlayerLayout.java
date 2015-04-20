package edu.washington.nadava.lifecounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by nadavash on 4/19/15.
 */
public class PlayerLayout extends RelativeLayout {
    private Player player;

    private Resources res;

    private TextView playerText;
    private TextView lifeText;

    public PlayerLayout(Context context, final int playerId) {
        super(context);
        View.inflate(context, R.layout.layout_player, this);

        res = getResources();
        player = new Player(playerId);

        playerText = (TextView)findViewById(R.id.text_player_num);
        playerText.setText(String.format(res.getString(R.string.player_num), player.getId()));
        lifeText = (TextView)findViewById(R.id.text_life);
        updateLifeTextView();

        Button addBtn = (Button)findViewById(R.id.button_add);
        Button addFiveBtn = (Button)findViewById(R.id.button_add5);
        Button removeBtn = (Button)findViewById(R.id.button_remove);
        Button removeFiveBtn = (Button)findViewById(R.id.button_remove5);

        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_add:
                        increment(1);
                        break;
                    case R.id.button_add5:
                        increment(5);
                        break;
                    case R.id.button_remove:
                        decrement(1);
                        break;
                    case R.id.button_remove5:
                        decrement(5);
                        break;
                }
            }
        };

        addBtn.setOnClickListener(clickListener);
        addFiveBtn.setOnClickListener(clickListener);
        removeBtn.setOnClickListener(clickListener);
        removeFiveBtn.setOnClickListener(clickListener);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void increment(int value) {
        player.incrementLife(value);
        updateLifeTextView();
    }

    public void decrement(int value) {
        player.decrementLife(value);
        updateLifeTextView();

        if (player.isDead()) {
            player.setLife(0);
            updateLifeTextView();

            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getContext());
            dlgAlert.setMessage("Player " + player.getId() + " lost!");
            dlgAlert.setTitle("Game Over");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    public void updateLifeTextView() {
        lifeText.setText(String.format(res.getString(R.string.life), player.getLife()));
    }
}
