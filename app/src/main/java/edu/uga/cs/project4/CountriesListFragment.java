package edu.uga.cs.project4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.lang.reflect.Array;

public class CountriesListFragment extends ListFragment {
    private static final String DEBUG_TAG = "CountryInfowithFrags";
    private final String[] country_names = {
            "Greece", "Sweden", "India", "Netherlands", "Japan", "Australia"
    } ;

    boolean landscape_mode = false;

    int countrylist_index = 0;

    public CountriesListFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        Log.d( DEBUG_TAG, "CountriesListFragment.onViewCreated(): savedInstanceState: " + savedInstanceState );

        setListAdapter( new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_activated_1, country_names ) );

        View countryInfoFrame = getActivity().findViewById(R.id.countryInfo);
        Log.d( DEBUG_TAG, "CountriesListFragment.onViewCreated(): countryInfoFrame: " + countryInfoFrame );

        landscape_mode = countryInfoFrame != null && countryInfoFrame.getVisibility() == View.VISIBLE;

        if( savedInstanceState != null ) {
            countrylist_index = savedInstanceState.getInt("selected_country_index", 0 );
            Log.d( DEBUG_TAG, "CountriesListFragment.onViewCreated(): restored countrylist_index: " + countrylist_index );
        }

        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
        getListView().setItemChecked( countrylist_index, true );
        Log.d( DEBUG_TAG, "CountriesListFragment.setItemChecked; Selected country index shown is : " + countrylist_index );

        if(landscape_mode) {
            showcountryInfo(countrylist_index);
            Log.d( DEBUG_TAG, "CountriesListFragment.onViewCreated(): Finished execution of showcountryInfo()" );
            getListView().smoothScrollToPosition( countrylist_index );
        }
    }

    @Override
    public void onListItemClick( ListView l, View v, int position, long id ) {
        Log.d(DEBUG_TAG, "CountriesListFragment.onListItemClick(). !!! Value of position is :" + position);
        showcountryInfo( position );
    }

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        super.onSaveInstanceState(outState);

        outState.putInt( "selected_country_index", countrylist_index);
        Log.d( DEBUG_TAG, "CountriesListFragment.onSaveInstanceState(): saved versionIndex: " + countrylist_index );
    }

    void showcountryInfo(int countrylist_index){

        this.countrylist_index = countrylist_index;
        Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo(). !!! Value of this.countrylist_index is : " + this.countrylist_index);
        Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo(). !!! Value of JUST countrylist_index is : " + countrylist_index);
        if(landscape_mode){

            Log.d(DEBUG_TAG, "CountriesListFragment.showcountryInfo(): --> Landscape mode detected! Highlighting index of list : " + countrylist_index);
            getListView().setItemChecked( countrylist_index, true );
            CountryInfoFragment infoFragment_obj =
                    (CountryInfoFragment) getParentFragmentManager().findFragmentById( R.id.countryInfo );
            Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo(): infoFragment_obj: " + infoFragment_obj );

            if( infoFragment_obj == null || infoFragment_obj.getDisplayedCountryIndex() != countrylist_index ){
                //Array AndroidVersionInfoFragment;
                infoFragment_obj = CountryInfoFragment.newInstance(countrylist_index);

                Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo() !!! Beginning fragment Transaction");
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

                Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo() !!! Replacing old infofragment with new one of country index = " + countrylist_index);
                fragmentTransaction.replace( R.id.countryInfo, infoFragment_obj );

                fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );

                Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo() !!! Commiting fragment transaction");
                fragmentTransaction.commit();

                Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo: !!! Finished execution of showcountryInfo()" );
            }
        }else{
            Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo() !!! Inside ELSE since Potrait Not Landscape");
            Intent intent = new Intent();
            intent.setClass( getActivity(), CountryInfoActivity.class );
            intent.putExtra("countrylist_index", countrylist_index);
            Log.d( DEBUG_TAG, "CountriesListFragment.showcountryInfo() !!! Starting CountryInfoActivity. Index value passed as intent extras = " + countrylist_index);
            startActivity( intent );
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.d( DEBUG_TAG, "CountriesListFragment.onAttach()" );
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d( DEBUG_TAG, "CountriesListFragment.onCreate()" );
    }

    @Override
    public void onStart() {
        Log.d( DEBUG_TAG, "CountriesListFragment.onStart()" );
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d( DEBUG_TAG, "CountriesListFragment.onResume()" );
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d( DEBUG_TAG, "CountriesListFragment.onPause()" );
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d( DEBUG_TAG, "CountriesListFragment.onStop()" );
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d( DEBUG_TAG, "CountriesListFragment.onDestroyView()" );
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d( DEBUG_TAG, "CountriesListFragment.onDestroy()" );
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d( DEBUG_TAG, "CountriesListFragment.onDetach()" );
        super.onDetach();
    }
}
