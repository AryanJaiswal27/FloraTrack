package com.example.floratrack;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.os.Bundle;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;

        import com.google.zxing.BarcodeFormat;
        import com.google.zxing.MultiFormatWriter;
        import com.google.zxing.WriterException;
        import com.google.zxing.common.BitMatrix;
        import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);
        //Button for generating QR code
        Button btnGenerate = findViewById(R.id.btnGenerate);
        //Text will be entered here to generate QR code
        EditText etText = findViewById(R.id.etText);
        //ImageView for generated QR code
        ImageView imageCode = findViewById(R.id.imageCode);
        btnGenerate.setOnClickListener(v -> {
            //getting text from input text field.
            String myText = etText.getText().toString().trim();
            //initializing MultiFormatWriter for QR code
            MultiFormatWriter mWriter = new MultiFormatWriter();
            try {
                //BitMatrix class to encode entered text and set Width & Height
                BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400,400);
                BarcodeEncoder mEncoder = new BarcodeEncoder();
                Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
                imageCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView
                // to hide the keyboard
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });
    }
}