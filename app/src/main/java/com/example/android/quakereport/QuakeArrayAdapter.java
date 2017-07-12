package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.inAnimation;
import static android.R.attr.resource;

/**
 * Created by Admin on 7/10/2017.
 */

public class QuakeArrayAdapter extends ArrayAdapter<QuakeInfo> {
    public QuakeArrayAdapter(@NonNull Context context ,ArrayList<QuakeInfo> objects) {
        super(context,0,objects);
    }
    private  static  final String LOCATION_SEPERATOR = "of";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
            QuakeInfo currentQuakeInfo = getItem(position);
            TextView quakeMagnitudeView = (TextView) listitemView.findViewById(R.id.quake_mag_view);
            String formattedMagnitude = formatMagnitude(currentQuakeInfo.getqMagnitude());
            quakeMagnitudeView.setText(formattedMagnitude);

            TextView quakeLocationView = (TextView) listitemView.findViewById(R.id.quake_location_view);
            quakeLocationView.setText(currentQuakeInfo.getqLocation());

            TextView quakeDateView = (TextView) listitemView.findViewById(R.id.quake_date_view);

            TextView quakeTimeView = (TextView) listitemView.findViewById(R.id.quake_time_view);
            Date dateObject = new Date(currentQuakeInfo.getqTimeInMilliseconds());
            String formattedDate = formatDate(dateObject);
            quakeDateView.setText(formattedDate);

            String formattedTime = formatTime(dateObject);
            quakeTimeView.setText(formattedTime);

            String originalLocation = currentQuakeInfo.getqLocation();
            String primaryLocation;
            String locationOffset;
            if(originalLocation.contains(LOCATION_SEPERATOR)){
                String[] parts = originalLocation.split(LOCATION_SEPERATOR);
                locationOffset = parts[0] + LOCATION_SEPERATOR;
                primaryLocation = parts[1];
            }
            else{
                locationOffset = getContext().getString(R.string.near_the);
                primaryLocation = originalLocation;
            }

            TextView primaryLocationView = (TextView) listitemView.findViewById(R.id.quake_location_view);
            primaryLocationView.setText(primaryLocation);

            TextView locationOffSetView = (TextView) listitemView.findViewById(R.id.location_offset_view);
            locationOffSetView.setText(locationOffset);
            GradientDrawable magnitudeCirle = (GradientDrawable) quakeMagnitudeView.getBackground();
            int magnitudeColor = getMagnitudeColor(currentQuakeInfo.getqMagnitude());
            magnitudeCirle.setColor(magnitudeColor);

        }
        return listitemView;
    }

    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject){

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return  magnitudeFormat.format(magnitude);
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
