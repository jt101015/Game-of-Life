package com.example.jt.gameoflife;

/**
 * Created by jt on 2/21/17.
 */

import android.widget.ImageView;
import android.view.Display;
import android.content.Context;
import android.graphics.Paint;
import android.R.color;
import android.graphics.Canvas;
import android.app.Activity;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.util.DisplayMetrics;


public class GridView extends ImageView{

    private static final int STROKE_WIDTH = 1;
    private static final int MARGIN_OFFSET_NUMBER = 2;
    private Paint mPaint;
    private int imageSide = 0;
    private GridState gridState = null;
    private float marginOffsetLength = 0;
    private float cellSideLength = 0;

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPaintParam();

    }

    private void setPaintParam() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(STROKE_WIDTH);

    }

    public void setGridState(GridState gridState) {
        Log.d("11","11111");
        this.gridState = gridState;
    }

    public GridState getGridState() { return this.gridState; }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        if (height < width) {
            imageSide = height;
        } else {
            imageSide = width;
        }

        marginOffsetLength = (float)imageSide / (gridState.COLUMN_NUMBER + MARGIN_OFFSET_NUMBER) * MARGIN_OFFSET_NUMBER / 2;
        cellSideLength = (float)imageSide / (gridState.COLUMN_NUMBER + MARGIN_OFFSET_NUMBER);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = 0;
        int j = 0;

        mPaint.setColor(getResources().getColor(color.black));
        for (i = 0; i <= gridState.ROW_NUMBER; i++) {
            canvas.drawLine (marginOffsetLength, marginOffsetLength + i * cellSideLength,
                    imageSide - marginOffsetLength, marginOffsetLength + i * cellSideLength, mPaint);
        }

        for (i = 0; i <= gridState.COLUMN_NUMBER; i++) {
            canvas.drawLine (marginOffsetLength + i * cellSideLength, marginOffsetLength,
                    marginOffsetLength + i * cellSideLength, marginOffsetLength + gridState.COLUMN_NUMBER * cellSideLength, mPaint);
        }

        mPaint.setColor(getResources().getColor(color.holo_red_light));

        if (null == gridState)  { return; }

        for (i = 0; i < gridState.ROW_NUMBER; i++) {
            for (j = 0; j < gridState.COLUMN_NUMBER; j++) {


                if (gridState.isLive(i, j)) {

                    canvas.drawCircle(marginOffsetLength + j * cellSideLength + cellSideLength / 2,
                            marginOffsetLength + i * cellSideLength + cellSideLength / 2,
                            cellSideLength / 2 * 90 / 100, mPaint);
                }

            }
        }

    }

    private int[] getIndex(int x, int y){
        int i = (int) ((x - marginOffsetLength) / cellSideLength);
        int j = (int) ((y - marginOffsetLength) / cellSideLength);
        int[] index = new int[2];

        if ((i >= gridState.ROW_NUMBER) || (j >= gridState.COLUMN_NUMBER)) { return null; }

        index[0] = j;
        index[1] = i;

        return index;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = 0;
        int y = 0;
        int[] index = null;

        if (MotionEvent.ACTION_UP == action) {
            x = (int) event.getX();
            y = (int) event.getY();

            index = getIndex(x, y);
        }


        if (null != index) {
            gridState.updateState(index[0], index[1]);
            invalidate();
        }


        return true;
    }
}

