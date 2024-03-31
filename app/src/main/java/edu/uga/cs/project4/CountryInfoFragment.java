package edu.uga.cs.project4;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.InputStream;

public class CountryInfoFragment extends Fragment {

    private static final String DEBUG_TAG = "CountryInfowithFrags";
    private final String[] countryInfo = {
            "Country details for : Greece",
            "Country details for : Sweden",
            "Country details for : India",
            "Country details for : Netherlands",
            "Country details for : Japan",
            "Country details for : Australia"
    };


    public CountryInfoFragment()
    {
        // required default constructor
    }
    public static CountryInfoFragment newInstance (int countrylist_index ) {

        Log.d( DEBUG_TAG, "CountriesInfoFragment.newInstance(): countrylist_index: " + countrylist_index );

        CountryInfoFragment fragment = new CountryInfoFragment();
        Log.d(DEBUG_TAG, "CountriesInfoFragment.newInstance(): fragment: " + fragment);

        Bundle args = new Bundle();
        args.putInt( "countrylist_index", countrylist_index );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onAttach()" );
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onCreate()" );
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onCreateView()" );

        Context context = getActivity();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Fetch country index
        int countryIndex = getDisplayedCountryIndex();
        String countryName = getResources().getStringArray(R.array.country_names)[countryIndex];

        ImageView imageView1 = new ImageView(context);
        ImageView imageView2 = new ImageView(context);

        String fileName = countryName.toLowerCase() + "_details";


        // Dynamically determine image resources
        int overviewResId = context.getResources().getIdentifier(countryName.toLowerCase() + "_overview", "drawable", context.getPackageName());
        int detailsResId = context.getResources().getIdentifier(countryName.toLowerCase() + "_details", "drawable", context.getPackageName());
        imageView1.setImageDrawable(ContextCompat.getDrawable(context, overviewResId));
        imageView2.setImageDrawable(ContextCompat.getDrawable(context, detailsResId));

        TextView textView = new TextView(context);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        textView.setPadding(padding, padding, padding, padding);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);

        // Dynamically load text from raw folder
        int textResId = context.getResources().getIdentifier(fileName, "raw", context.getPackageName());
        try {
            InputStream inputStream = context.getResources().openRawResource(textResId);
            byte[] txt_data = new byte[inputStream.available()];
            inputStream.read(txt_data);
            textView.setText(new String(txt_data));
        } catch (Exception e) {
            textView.setText("Error: can't show info text.");
        }

        ScrollView scroller = new ScrollView(context);
        scroller.addView(layout);

        layout.addView(imageView1);
        layout.addView(imageView2);
        layout.addView(textView);

        return scroller;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onViewCreated()" );
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onStart()" );
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onResume()"  );
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onPause()" );
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onStop()" );
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onDestroyView()" );
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onDestroy()" );
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d( DEBUG_TAG, "CountriesInfoFragment.onDetach()" );
        super.onDetach();
    }

    public int getDisplayedCountryIndex() {
        //Log.d( DEBUG_TAG, "CountriesInfoFragment.getDisplayedCountryIndex(); Return value is :" + getArguments().getInt("countrylist_index", 0 ) );
        return getArguments().getInt("countrylist_index", 0 );
    }
}
