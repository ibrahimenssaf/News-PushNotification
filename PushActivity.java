package com.example.user.gridl_cardview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class PushActivity extends AppCompatActivity {

    ImageView iv_push;
    EditText et_pushname, et_pushnews;
    private Uri imageUrl;
    String b = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        iv_push = (ImageView)findViewById(R.id.iv_push);
        et_pushname = (EditText)findViewById(R.id.et_pushname);
        et_pushnews = (EditText)findViewById(R.id.et_pushnews);
    }

    public void capture(View view) {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select Picture"),123);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123){

//            Bitmap b = (Bitmap)data.getExtras().get("Data");
  //          iv_push.setImageBitmap(b);

            imageUrl = data.getData();
            iv_push.setImageURI(imageUrl);
         //   iv_push.setImageBitmap(imageUrl);
        }
    }

    public void upload(View view) {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("photoNews");
        StorageReference image = storageReference.child("pic.jpg");

        iv_push.setDrawingCacheEnabled(true);
        iv_push.buildDrawingCache();

        Bitmap bitmap = iv_push.getDrawingCache();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = image.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(PushActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(PushActivity.this, taskSnapshot.getDownloadUrl().toString(), Toast.LENGTH_SHORT).show();

                 b = taskSnapshot.getDownloadUrl().toString();
            }
        });




        String url="http://192.168.0.7/department/department_php/insertHos_news.php"+"?name="+et_pushname.getText().toString()+
                "&News="+et_pushnews.getText().toString()+"&pic=";
        RequestQueue rq = Volley.newRequestQueue(PushActivity.this);

        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(PushActivity.this, "SUCCESSFULLY INSERTED", Toast.LENGTH_SHORT).show();            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(PushActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



rq.add(sr);
















    }
}














