package com.syncode.pemesanantelur.ui.maps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.TrackingModel;
import com.syncode.pemesanantelur.data.network.repository.FirebaseTrackingRepository;

public class MapsViewModel extends ViewModel {

    private FirebaseTrackingRepository firebaseTrackingRepository;

    public MapsViewModel() {
        this.firebaseTrackingRepository = new FirebaseTrackingRepository();
    }


    public LiveData<TrackingModel> getTracking(String idTransaction) {
        return firebaseTrackingRepository.getTrackingValue(idTransaction);
    }
}
