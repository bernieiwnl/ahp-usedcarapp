package com.example.tugas_akhir.REGISTER;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.LOGIN.LoginActivity;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRegister;
    private TextInputEditText txtNamaLengkap, txtEmail, txtPassword;
    private TextView linkLogin;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private CircularImageView imgFotoProfil;
    private static final int IMAGE_REQUEST = 1;
    private Uri uri_image;//kumpulan data yang menjadikan sebuah gambar
    private StorageReference storageReference;
    private StorageTask storageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtNamaLengkap = (TextInputEditText) findViewById(R.id.inputNamaLengkap);
        txtEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.inputPassword);
        linkLogin = (TextView) findViewById(R.id.textLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        imgFotoProfil = (CircularImageView) findViewById(R.id.imageViewProfile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("fotoDB");

        btnRegister.setOnClickListener(this);
        linkLogin.setOnClickListener(this);
        imgFotoProfil.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegister:
                register(txtNamaLengkap.getText().toString().trim(), txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim());

                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            case R.id.textLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            case R.id.imageViewProfile:
                bukaFoto();
                break;
        }
    }
    public void register(final String namaLengkap, final String email, final String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> user = new HashMap<>();
                    user.put("iduser", firebaseAuth.getCurrentUser().getUid());
                    user.put("nama_lengkap", namaLengkap);
                    user.put("email", email);
                    user.put("password", password);
                    user.put("idFoto", "");

                    //nama entity
                    firebaseFirestore.collection("user").document(firebaseAuth.getCurrentUser().getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            uploadFotoDB(firebaseAuth.getCurrentUser().getUid());
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void bukaFoto(){
        Intent i = new Intent();
        i.setType("Image/*");//ambil data dari galer
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, IMAGE_REQUEST);
    }
    //pasang foto di imageview profile
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imgFotoProfil.getWidth();
        int targetH = imgFotoProfil.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile("fCurrentPath", bmOptions);
        imgFotoProfil.setImageBitmap(bitmap);
    }

    // ambil extension dari gambar yang sudah diupload
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            setPic();
            uri_image = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri_image));
                imgFotoProfil.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgFotoProfil.setImageBitmap(bitmap);
        }
    }
    private void uploadFotoDB(final String id){
        if(uri_image != null){
            final StorageReference emptyFile = storageReference.child(id + "_foto_profil." + getFileExtension(uri_image));
            storageTask = emptyFile.putFile(uri_image);
            storageTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return emptyFile.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri uri_upload = task.getResult();
                        String data_img = uri_upload.toString();
                        Map<String, Object> foto = new HashMap<>();
                        foto.put("idFoto", data_img);
                        firebaseFirestore.collection("user").document(id).update(foto).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                firebaseAuth.signOut();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                        });
                    }
                }
            });

        }else{
            firebaseAuth.signOut();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
    }
}