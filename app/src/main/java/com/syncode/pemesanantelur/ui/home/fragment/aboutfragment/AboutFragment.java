package com.syncode.pemesanantelur.ui.home.fragment.aboutfragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syncode.pemesanantelur.R;


public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LinearLayout rootLayout = view.findViewById(R.id.container);
        String[] VisionAndMission = this.getResources().getStringArray(R.array.visiandmisi);
        for (String VNM : VisionAndMission) {
            TextView txtVisionAndMission = new TextView(getContext());
            LinearLayout content = new LinearLayout(getContext());
            ImageView icon = new ImageView(getContext());
            icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow));
            content.setOrientation(LinearLayout.HORIZONTAL);
            content.addView(icon);
            content.addView(txtVisionAndMission);
            txtVisionAndMission.setText(VNM);
            rootLayout.addView(content);
        }
        super.onViewCreated(view, savedInstanceState);
    }
}
