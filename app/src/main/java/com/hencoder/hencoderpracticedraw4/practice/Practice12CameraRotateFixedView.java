package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();
    private int height;
    private int width;

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        height = bitmap.getHeight() / 2;
        width = bitmap.getWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        camera.save();

        camera.rotateX(30);        //x轴方向 旋转30度
        canvas.translate(point1.x + width, point1.y + height);    //投影之后，返回到原先的位置，Canvas几何变换叠加要倒序;
        camera.applyToCanvas(canvas);   //把旋转投影到Canvas
        canvas.translate(-point1.x - width, -point1.y - height);   //投影之前将画布移动到三维坐标中心轴;
        camera.restore();

        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        canvas.save();
        camera.save();
        camera.rotateY(50);
        canvas.translate(point2.x + width, point2.y + height);
        camera.applyToCanvas(canvas);
        canvas.translate(-point2.x - width, -point2.y - height);

        camera.restore();
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();

    }
}
