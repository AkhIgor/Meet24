package com.example.meet24;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable animation;
    TextView BaseXml, BaseClass;

    ValueAnimator imageSizeAnimator,imageSizeAnimator2, imageSizeAnimator3, imageSizeAnimator4,imageSizeAnimator5;
    Animation anim;

    ImageView valueAnim, valueAnim2, valueAnim3, valueAnim4, valueAnim5;
    Button updateButton;

    AnimatorSet animationSet;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        valueAnim = (ImageView) findViewById(R.id.imageView2);
        valueAnim2 = (ImageView) findViewById(R.id.imageView3);
        valueAnim3 = (ImageView) findViewById(R.id.imageView4);
        valueAnim4 = (ImageView) findViewById(R.id.imageView5);
        valueAnim5 = (ImageView) findViewById(R.id.imageView6);

        BaseXml = (TextView) findViewById(R.id.textView2);
        BaseClass = (TextView) findViewById(R.id.textView3);
        final ImageView image = (ImageView) findViewById(R.id.imageView);
        updateButton = (Button) findViewById(R.id.button);
        image.setBackgroundResource(R.drawable.jump);
        animation = (AnimationDrawable) image.getBackground();

        anim = new RotateAnimation(0f, 720f);
        anim.setDuration(3000);
        anim.setInterpolator(new AccelerateInterpolator());

        imageSizeAnimator = ValueAnimator.ofInt(10,300);
        imageSizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnim.getLayoutParams().width = (int) animation.getAnimatedValue();
                valueAnim.getLayoutParams().height = (int) animation.getAnimatedValue();
                valueAnim.requestLayout();
            }
        });
        imageSizeAnimator.setDuration(700);
        imageSizeAnimator.setInterpolator(new BounceInterpolator());

        imageSizeAnimator2 = ValueAnimator.ofInt(10,300);
        imageSizeAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnim2.getLayoutParams().width = (int) animation.getAnimatedValue();
                valueAnim2.getLayoutParams().height = (int) animation.getAnimatedValue();
                valueAnim2.requestLayout();
            }
        });
        imageSizeAnimator2.setDuration(700);
        imageSizeAnimator2.setInterpolator(new CycleInterpolator(5.5f));

        imageSizeAnimator3 = ValueAnimator.ofInt(100,300);
        imageSizeAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnim3.getLayoutParams().width = (int) animation.getAnimatedValue();
                valueAnim3.getLayoutParams().height = (int) animation.getAnimatedValue();
                valueAnim3.requestLayout();
            }
        });
        imageSizeAnimator3.setDuration(700);
        imageSizeAnimator3.setInterpolator(new LinearInterpolator());

        imageSizeAnimator4 = ValueAnimator.ofInt(10,300);
        imageSizeAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnim4.getLayoutParams().width = (int) animation.getAnimatedValue();
                valueAnim4.getLayoutParams().height = (int) animation.getAnimatedValue();
                valueAnim4.requestLayout();
            }
        });
        imageSizeAnimator4.setDuration(700);
        imageSizeAnimator4.setInterpolator(new OvershootInterpolator());

        imageSizeAnimator5 = ValueAnimator.ofInt(10,300);
        imageSizeAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                valueAnim5.getLayoutParams().width = (int) animation.getAnimatedValue();
                valueAnim5.getLayoutParams().height = (int) animation.getAnimatedValue();
                valueAnim5.requestLayout();
            }
        });
        imageSizeAnimator5.setDuration(700);
        imageSizeAnimator5.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(updateButton, View.X, 10f, 110f, 160f, 510f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(updateButton, View.Y, 1000f, 1100f, 1250f, 1500f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(updateButton, View.Z, 0f, 100f, 250f, 500f);

        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new AccelerateInterpolator());

        objectAnimator1.setDuration(3000);
        objectAnimator1.setInterpolator(new AccelerateInterpolator());

        objectAnimator2.setDuration(3000);
        objectAnimator2.setInterpolator(new AccelerateInterpolator());

        animationSet = new AnimatorSet();

        animationSet.play(objectAnimator);
        animationSet.play(objectAnimator1);
        animationSet.play(objectAnimator2);
        animationSet.play(imageSizeAnimator).before(imageSizeAnimator2);
        animationSet.play(imageSizeAnimator2).before(imageSizeAnimator3);
        animationSet.play(imageSizeAnimator3).before(imageSizeAnimator4);
        animationSet.play(imageSizeAnimator4).before(imageSizeAnimator5);
        animationSet.play(imageSizeAnimator5).after(imageSizeAnimator4);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationSet.start();
                BaseXml.startAnimation(AnimationUtils.loadAnimation(context, R.anim.my_base_animation));
                BaseClass.startAnimation(anim);
                if(animation.isRunning())
                    animation.stop();
                else
                    animation.start();
            }
        });
    }

    public void SceneA(View view) {
        ViewGroup rootScene = (ViewGroup) findViewById(R.id.container);
        View square = rootScene.findViewById(R.id.transition_square);

        TransitionManager.beginDelayedTransition(rootScene);

        ViewGroup.LayoutParams params = square.getLayoutParams();
        params.width = 1000;
        params.height = 1000;

        square.setLayoutParams(params);
    }

    public void SceneB(View view) {
        ViewGroup sceneA = (ViewGroup) findViewById(R.id.container);
        View square = sceneA.findViewById(R.id.transition_square);

        TransitionManager.beginDelayedTransition(sceneA);

        ViewGroup.LayoutParams params = square.getLayoutParams();
        params.width = 500;
        params.height = 500;

        square.setLayoutParams(params);
    }

    public void SceneC(View view) {
        ViewGroup sceneB = (ViewGroup) findViewById(R.id.container);
        View square = sceneB.findViewById(R.id.transition_square);

        TransitionManager.beginDelayedTransition(sceneB);

        ViewGroup.LayoutParams params = square.getLayoutParams();
        params.width = 1500;
        params.height = 1500;

        square.setLayoutParams(params);
    }
}
