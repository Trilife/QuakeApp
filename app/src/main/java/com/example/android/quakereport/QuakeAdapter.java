package com.example.android.quakereport;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by JRStrele on 12/11/2016.
 */

public class QuakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";
    String primaryLocation;
    String locationOffset;

    public QuakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the Earthquake object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the Magnitude of the Quake
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        //format magnitude to 1 decimal point
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Get the magnitude from the Earthquake object, convert to String and
        // set this text on the name TextView
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // Find the TextView in the list_item.xml layout with the locations
        String originalLocation = currentEarthquake.getLocation();
        // separate originalLocation into primaryLocation and locationOffset at LOCATION_SEPARATOR
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        // fill the primaryLocation
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);
        //fill the locationOffset
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        //Create a Date object
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        // Find the TextView in the list_item.xml layout with the attraction description
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        //Format the String
        String formattedDate = formatDate(dateObject);
        // Get the attraction description from the Attraction object and
        // set this text on the description TextView
        dateTextView.setText(formattedDate);
        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
    /** Define the color of the magnitude background
     *
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId =R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId =R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId =R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId =R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId =R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId =R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId =R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId =R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId =R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId =R.color.magnitude10plus;
                break;
        }
        return  ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm ");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
