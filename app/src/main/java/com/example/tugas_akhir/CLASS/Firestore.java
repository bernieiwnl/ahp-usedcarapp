package com.example.tugas_akhir.CLASS;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Firestore {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public Firestore(){
        this.setFirebaseFirestore(FirebaseFirestore.getInstance());
        this.setFirebaseAuth(FirebaseAuth.getInstance());
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

    public void setFirebaseFirestore(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }
}
