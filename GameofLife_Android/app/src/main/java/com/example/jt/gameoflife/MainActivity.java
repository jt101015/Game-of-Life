package com.example.jt.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.Display;
import android.graphics.Point;
import android.view.View.OnClickListener;
import android.view.View;
import android.util.Log;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Bitmap;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "Grid_State";
    GridState gridState;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridState = new GridState();

        if (null != savedInstanceState) { gridState.setState(savedInstanceState.getIntegerArrayList(KEY_INDEX)); }

        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setGridState(gridState);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("next_button", "click");

                GridView gridView = (GridView) findViewById(R.id.grid_view);
                GridState gridState = gridView.getGridState();
                gridState.nextGeneration();
                gridView.invalidate();
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("reset_button", "click");
                dialog();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putIntegerArrayList(KEY_INDEX, gridState.getStateArrayList());
    }


    private void dialog() {

        AlertDialog.Builder builder = new Builder(this);
        builder.setMessage("Do you want to reset the game?");
        builder.setTitle("Alert");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                gridState.initGrid();
                gridView.invalidate();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                }
            });
        builder.create().show();
    }

}
