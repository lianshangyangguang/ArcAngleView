package com.gwell.view.library;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiyingzhu on 2017/4/5.
 */
public class ArcAngleView extends View {
    private Context mContext;
    private Paint paint;
    private Path path;
    private float currentAngle;

    //指针颜色
    private int pointColor = Color.rgb(0x49,0x90,0xff);
    //圆弧颜色
    private int arcColor = Color.rgb(0xff,0xff,0xff);
    //指针下放圆的颜色
    private int circleColor = Color.rgb(0xff,0xff,0xff);
    //布局最小宽度
    private int mWidth = 85;
    //布局最小高度
    private int mHeight = 42;
    //指针下方圆的半径
    private float radius = 4.25f;
    //总弧度的一半
    private float radian = 55;
    //圆弧左边文字
    private String txtLeft = "0°";
    //圆弧右边文字
    private String txtRight = "110°";
    public ArcAngleView(Context context) {
        this(context, null,0);
    }

    public ArcAngleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ArcAngleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = dip2px (mWidth);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = dip2px (mHeight);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        drawArc(canvas);
        drawPoint(canvas);

        canvas.restore();
        super.onDraw(canvas);
    }

    private void drawPoint(Canvas canvas) {
        paint.reset();
        path.reset();
        paint.setColor(pointColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.rotate(currentAngle, dip2px(36.5f), dip2px(32.75f));
        path.moveTo(dip2px(36.5f), dip2px(21));
        path.lineTo(dip2px(33f), dip2px(30));
        path.lineTo(dip2px(40f), dip2px(30));
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(circleColor);
        canvas.drawCircle(dip2px(36.5f), dip2px(32.75f), dip2px(radius), paint);
    }

    private void drawArc(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(arcColor);
        paint.setStrokeWidth(dip2px(1));
        paint.setAntiAlias(true);
        paint.setTextSize(dip2px(9));
        RectF rect = new RectF(dip2px(9), dip2px(5), dip2px(64), dip2px(52));
        canvas.drawArc(rect, -(90-radian), -(radian*2), false, paint);
        canvas.drawText(txtLeft, dip2px(0), dip2px(19), paint);
        canvas.drawText(txtRight, dip2px(65), dip2px(19), paint);
        canvas.drawLine(dip2px(11), dip2px(12), dip2px(17), dip2px(18), paint);
        canvas.drawLine(dip2px(56), dip2px(18), dip2px(62), dip2px(12), paint);
    }

    /**
     * 带动画角度设置
     * @param percent 角度所占百分比
     */
    public void setAngle(float percent) {
        float angle = percent * (radian*2);
        angle -= radian;
        float from = 0;
        if (currentAngle != 0) {
            from = currentAngle;
        }
        angle = angle > 360 ? angle % 360 : angle;
        angle = angle < -360 ? angle % 360 : angle;
        if (angle > radian) {
            angle = radian;
        } else if (angle < -radian) {
            angle = -radian;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(from, angle);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(500);
        animator.start();
    }

    /**
     * 不带动画角度设置
     * @param percent  角度所占百分比
     */
    public void setCurrentAngle(float percent) {
        float angle = percent * (radian*2);
        angle -= radian;
        angle = angle > 360 ? angle % 360 : angle;
        angle = angle < -360 ? angle % 360 : angle;
        if (angle > radian) {
            angle = radian;
        } else if (angle < -radian) {
            angle = -radian;
        }
        currentAngle = angle;
        invalidate();
    }

    /**
     * 获取当前角度的百分比
     * @return
     */
    public float getCurrentAngle() {
        return (currentAngle+radian)/(radian*2);
    }

    private int dip2px(float dipValue) {
        final float scale =mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
