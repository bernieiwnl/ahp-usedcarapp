package com.example.tugas_akhir.HOME;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeAppActivty extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestoredb;
    private Firestore firestore;
    private DocumentReference documentReference;
    private String getCurrentUserUid;
    private TextView txtViewNamaUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app_activty);

        //text view
        txtViewNamaUser = (TextView) findViewById(R.id.textViewNamaUser);

        // setting firebase Firestore
        firebaseFirestoredb = FirebaseFirestore.getInstance();
        firestore = new Firestore();
        getCurrentUserUid = firestore.getFirebaseAuth().getCurrentUser().getUid();
        documentReference = firebaseFirestoredb.collection("user").document(getCurrentUserUid);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                try {
                    if (documentSnapshot == null && !documentSnapshot.exists()) {
                        return;
                    }
                    // Document exists, retrieve the data
                    String namaUser = documentSnapshot.getString("namaUser");
                    // Do something with the retrieved data
                    txtViewNamaUser.setText(namaUser);

                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("ErrorMsg", e.getMessage());
            }
        });
    }
}