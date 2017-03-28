package com.uvigo.learnfordown.learnfordown; /**
 * Created by Alexfed on 17/03/2017.
 */


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

public class MemoryButtonFacil extends Button
{

    protected int row;
    protected int column;
    protected int frontDrawableId;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected Drawable front;
    protected Drawable back;

    public MemoryButtonFacil(Context context, int r, int c, int frontImageDrawableId)
    {
        super(context);


        row = r;
        column = c;
        frontDrawableId = frontImageDrawableId;

        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.back);

        setBackgroundDrawable(back);//setBackground() para API > 16

        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));

        //tempParams.width = (int) getResources().getDisplayMetrics().density * 50;
        //tempParams.height = (int) getResources().getDisplayMetrics().density * 50;

        tempParams.width = (int) getResources().getDisplayMetrics().density * 200;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 200;

        setLayoutParams(tempParams);

    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    public void flip()
    {

        if(isMatched)
            return;

        if(isFlipped)
        {
            setBackgroundDrawable(back);//setBackground() para API > 16
            isFlipped = false;
        }
        else
        {
            setBackgroundDrawable(front);//setBackground() para API > 16
            isFlipped=true;
        }
    }
}

