package in.vaksys.pugnotificationdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import br.com.goncalves.pugnotification.interfaces.ImageLoader;
import br.com.goncalves.pugnotification.interfaces.OnImageLoadingCompleted;
import br.com.goncalves.pugnotification.notification.PugNotification;


public class MainActivity extends AppCompatActivity implements ImageLoader {

    private Button click;
    private String url = "http://www.pixelstalk.net/wp-content/uploads/2016/03/computer-hd-wallpaper-full-size-free-download-3D.jpg";
    private Target viewTarget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        click = (Button) findViewById(R.id.btnClick);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PugNotification.with(MainActivity.this).load()
                        .title("PugNotification")
                        .message("Hello")
                        .smallIcon(R.mipmap.ic_launcher)
                        .largeIcon(R.mipmap.ic_launcher)
                        .color(android.R.color.background_dark)
                        .custom()
                        .setImageLoader(MainActivity.this)
                        .background(url)
                        .setPlaceholder(R.drawable.pugnotification_ic_placeholder)
                        .build();
            }
        });
    }

    private static Target getViewTarget(final OnImageLoadingCompleted onCompleted) {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                onCompleted.imageLoadingCompleted(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }

    @Override
    public void load(String uri, OnImageLoadingCompleted onCompleted) {
        viewTarget = getViewTarget(onCompleted);
        Picasso.with(this).load(uri).into(viewTarget);
    }

    @Override
    public void load(int imageResId, OnImageLoadingCompleted onCompleted) {
        viewTarget = getViewTarget(onCompleted);
        Picasso.with(this).load(imageResId).into(viewTarget);
    }
}