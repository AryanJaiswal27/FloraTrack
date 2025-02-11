package com.example.floratrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GenerateActivity2 extends AppCompatActivity {

   ImageView imageCode;
   String myText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen2);
         Intent intent = getIntent();
        myText = intent.getStringExtra("qr_in_progress");
         String msg = myText;
        msg= msg.substring(5);
        String[] arrOfStr = msg.split(",", 6);





        imageCode = findViewById(R.id.imageCode);
     MultiFormatWriter mWriter = new MultiFormatWriter();
        try {
            //BitMatrix class to encode entered text and set Width & Height
            BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400,400);
            BarcodeEncoder mEncoder = new BarcodeEncoder();
            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);



            //Point p =new Point(20,370);
            //Bitmap b =  drawStringonBitmap(mBitmap,"Text By Jaiswal",p, Color.BLACK,1,10,false,300,20);


            // Create a mutable bitmap with the same dimensions
            Bitmap bitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);

            // Create a Canvas from the bitmap to draw on it
            Canvas canvas = new Canvas(bitmap);

            // Create Paint for text
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(30f);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setAntiAlias(true);



            // Your string to be added
            String text = arrOfStr[0] + " " + arrOfStr[1];




            // Measure the width of the text
            float textWidth = paint.measureText(text);

            // Calculate position to draw the text at the center bottom
            float x = (bitmap.getWidth() / 2);
            float y = bitmap.getHeight() - 20f; // 20 pixels from bottom

            // Draw the text on the canvas
            canvas.drawText(text, x, y, paint);

            // Now 'bitmap' contains your original image with the text added at the center bottom
            // You can use 'bitmap' wherever you need it




            //creating bitmap of code
            imageCode.setImageBitmap(bitmap);//Setting generated QR code to imageView
            // to hide the keyboard
//            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
        } catch (WriterException e) {
            e.printStackTrace();
        }



    }


    public void download(View v){
        Toast.makeText(this, "Downloaded!!", Toast.LENGTH_SHORT).show();
//     MultiFormatWriter mWriter = new MultiFormatWriter();
//        try {
//            //BitMatrix class to encode entered text and set Width & Height
//            BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400,400);
//            BarcodeEncoder mEncoder = new BarcodeEncoder();
//            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
//            imageCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView
//            // to hide the keyboard
////            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////            manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }

        BitmapDrawable draw = (BitmapDrawable) imageCode.getDrawable();
        Bitmap bmp = draw.getBitmap();

        File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //context.getExternalFilesDir(null);
        String filename = String.format("%d.jpg", System.currentTimeMillis());
        File file = new File(storageLoc, filename + ".jpg");

        try{
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            scanFile(GenerateActivity2.this, Uri.fromFile(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//

    }



    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);

    }

public void share (View view){
    BitmapDrawable draw = (BitmapDrawable) imageCode.getDrawable();
    Bitmap icon = draw.getBitmap();

    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("image/jpeg");

    ContentValues values = new ContentValues();
    values.put(MediaStore.Images.Media.TITLE, "title");
    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
    Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values);


    OutputStream outstream;
    try {
        outstream = getContentResolver().openOutputStream(uri);
        icon.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
        outstream.close();
    } catch (Exception e) {
        System.err.println(e.toString());
    }

    share.putExtra(Intent.EXTRA_STREAM, uri);
    startActivity(Intent.createChooser(share, "Share Image"));


}

//    public static Bitmap drawStringonBitmap(Bitmap src, String string, Point location, int color, int alpha, int size, boolean underline, int width , int height) {
//
//        Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());
//
//        Canvas canvas = new Canvas(result);
//        canvas.drawBitmap(src, 0, 0, null);
//        Paint paint = new Paint();
//        paint.setColor(color);
//        paint.setAlpha(alpha);
//        paint.setTextSize(size);
//        paint.setAntiAlias(true);
//        paint.setUnderlineText(underline);
//        canvas.drawText(string, location.x, location.y, paint);
//
//        return result;
//    }




}