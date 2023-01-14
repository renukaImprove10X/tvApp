package com.example.vutvapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.vutvapplication.databinding.ActivityTvBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TvActivity extends AppCompatActivity {

    private ActivityTvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("data")
                .document("record")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot,
                                        @Nullable FirebaseFirestoreException error) {
                        if (documentSnapshot != null && documentSnapshot.exists()) {
                            Record record = documentSnapshot.toObject(Record.class);
                            if (record.type.equals("image") && record.imageUrl != null && !record.imageUrl.isEmpty()) {
                                binding.setRecord(record);
                                binding.textview.setVisibility(View.GONE);
                                binding.imageView.setVisibility(View.VISIBLE);
                            } else if (record.type.equals("text") && record.text != null && !record.text.isEmpty()) {
                                binding.setRecord(record);
                                binding.textview.setVisibility(View.VISIBLE);
                                binding.imageView.setVisibility(View.GONE);
                            }
                        }
                    }
                });


    }
}