package com.example.animation1;

        import android.animation.AnimatorSet;
        import android.animation.ObjectAnimator;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button uploadButton = findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        Button animateButton = findViewById(R.id.animateButton);
        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnimationOptions();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), uri));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAnimationOptions() {
        String[] options = {"Rotate Animation", "Move Animation", "Slide Up Animation",
                "Slide Down Animation", "Bounce Animation", "Sequential Animation", "Together Animation",
                "Fade In Animation", "Fade Out Animation", "Cross Fading Animation",
                "Blink Animation", "Zoom In Animation", "Zoom Out Animation"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Animation");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        performRotateAnimation();
                        break;
                    case 1:
                        performMoveAnimation();
                        break;
                    case 2:
                        performSlideUpAnimation();
                        break;
                    case 3:
                        performSlideDownAnimation();
                        break;
                    case 4:
                        performBounceAnimation();
                        break;
                    case 5:
                        performSequentialAnimation();
                        break;
                    case 6:
                        performTogetherAnimation();
                        break;
                    case 7:
                        performFadeInAnimation();
                        break;
                    case 8:
                        performFadeOutAnimation();
                        break;
                    case 9:
                        performCrossFadingAnimation();
                        break;
                    case 10:
                        performBlinkAnimation();
                        break;
                    case 11:
                        performZoomInAnimation();
                        break;
                    case 12:
                        performZoomOutAnimation();
                        break;
                }
            }
        });
        builder.show();
    }

    private void performRotateAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        animator.setDuration(1000);
        animator.start();
    }

    private void performMoveAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f);
        animator.setDuration(1000);
        animator.start();
    }

    private void performSlideUpAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationY", 0f, -200f);
        animator.setDuration(1000);
        animator.start();
    }

    private void performSlideDownAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationY", 0f, 200f);
        animator.setDuration(1000);
        animator.start();
    }

    private void performBounceAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.5f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.5f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    private void performSequentialAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    private void performTogetherAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.5f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2, animator3);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    private void performFadeInAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        animator.setDuration(1000);
        animator.start();
    }

    private void performFadeOutAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
        animator.setDuration(1000);
        animator.start();
    }

    private void performCrossFadingAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    private void performBlinkAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        animator.setDuration(500);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }

    private void performZoomInAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.5f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.5f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    private void performZoomOutAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.5f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.5f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
}




