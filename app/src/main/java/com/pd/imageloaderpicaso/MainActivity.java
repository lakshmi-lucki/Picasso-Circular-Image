package com.pd.imageloaderpicaso;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Button  btn_drawable, btn_placeholder, btn_url;
    Button  btn_error, btn_callback, btn_resize;
    Button  btn_rotate, btn_scale, btn_targets;
     int iam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        imageView = (ImageView) findViewById( R.id.image );

        callmethod();


    }

    private void callmethod() {
        btn_drawable = (Button)findViewById( R.id.btn_drawable );
        btn_placeholder = (Button)findViewById( R.id.btn_placeHolder );
        btn_url = (Button)findViewById( R.id.btn_url );
        btn_error = (Button)findViewById( R.id.btn_error );
        btn_callback = (Button)findViewById( R.id.btn_callback );
        btn_resize = (Button)findViewById( R.id.btn_resize );
        btn_rotate = (Button)findViewById( R.id.btn_rotate );
        btn_scale = (Button)findViewById( R.id.btn_scale );
        btn_targets = (Button)findViewById( R.id.btn_target );

        btn_drawable.setOnClickListener( this );
        btn_placeholder.setOnClickListener( this );
        btn_url.setOnClickListener( this );
        btn_error.setOnClickListener( this );
        btn_callback.setOnClickListener( this );
        btn_resize.setOnClickListener( this );
        btn_rotate.setOnClickListener( this );
        btn_scale.setOnClickListener( this );
        btn_targets.setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_drawable:
                Picasso.with( this ).load( R.drawable.imager ).into( imageView );
                break;
            case R.id.btn_placeHolder:
                Picasso.with( this ).load( "www.pokimann.com" ).placeholder( R.drawable.placeholder ).into( imageView );
                break;

            case R.id.btn_url:
                // image of ---believe in urself to fetch
                Picasso.with( this ).load( "https://www.prosperitycoaching.biz/blog/wp-content/uploads/2019/01/believe-in-yourself2.jpg" ).placeholder( R.drawable.placeholder ).into( imageView );
                break;

            case R.id.btn_error:

                Picasso.with( this ).load( "https://www.prosperityNNNcoaching.biz/blog/wp-content/uploads/2019/01/believe-in-yourself2.jpg" ).placeholder( R.drawable.placeholder ).error( R.drawable.errorimg ).into( imageView );
                break;

            case R.id.btn_callback:
                Picasso.with( this ).load( "https://www.prosperityNNNcoaching.biz/blog/wp-content/uploads/2019/01/believe-in-yourself2.jpg" )
                        .error( R.drawable.errorimg ).into( imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText( getApplicationContext(), "ONSUCCESS", Toast.LENGTH_SHORT ).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText( getApplicationContext(), "Sorry!.. An error occured", Toast.LENGTH_SHORT ).show();
                    }
                } );
                break;
            case R.id.btn_resize:
                Picasso.with( this ).load( R.drawable.imager ).resize( 600, 610 ).into( imageView );
        break;
            case R.id.btn_rotate:
                Picasso.with( this ).load( R.drawable.imager  ).rotate( 90f ).into( imageView );
                break;
            case R.id.btn_scale:
                if (iam==3)
                    iam=0;
                else {
                    if (iam==0){
                Picasso.with( this ).load( R.drawable.boss3 ).fit().into( imageView );
                        Toast.makeText( this, "FIT", Toast.LENGTH_SHORT ).show();
                    }
                    else if (iam==1){
                        Picasso.with( this ).load( R.drawable.boss3 ).resize( 200,250 ).centerCrop().into( imageView );
                        Toast.makeText( this, "CENTER CROP", Toast.LENGTH_SHORT ).show();
                    }
                    else if (iam==2){
                        Picasso.with( this ).load( R.drawable.boss3 ).resize( 200,250 ).centerInside().into( imageView );
                        Toast.makeText( this, "CENTER INSIDE", Toast.LENGTH_SHORT ).show();
                    }
                     iam++;
                    
                    }
                break;
            case R.id.btn_target:
                Picasso.with( this ).load( "https://www.prosperitycoaching.biz/blog/wp-content/uploads/2019/01/believe-in-yourself2.jpg" ).placeholder( R.drawable.cloud ).into( tar );
                break;
        }
    }
    private Target tar = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            imageView.setImageBitmap( bitmap );
            Toast.makeText( MainActivity.this, "Loaded Successfully", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

            imageView.setImageDrawable(errorDrawable );
            Toast.makeText( MainActivity.this, "Loading Unsuccessfully", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
imageView.setImageDrawable( placeHolderDrawable );
        }
    };
}