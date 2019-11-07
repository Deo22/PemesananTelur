package com.syncode.pemesanantelur.data.network.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.syncode.pemesanantelur.data.model.TrackingModel;

public class FirebaseTrackingRepository {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbRef;
    private MutableLiveData<TrackingModel> trackingModelMutableLiveData = new MutableLiveData<>();

    public FirebaseTrackingRepository() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference();
    }


    public MutableLiveData<TrackingModel> getTrackingValue(String idTransaction) {
        dbRef.child(idTransaction).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    TrackingModel trackingModel = dataSnapshot.getValue(TrackingModel.class);
                    trackingModelMutableLiveData.setValue(trackingModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return trackingModelMutableLiveData;
    }
}
