package edu.uga.cs.project4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class CountryInfoActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "CountryInfowithFrags";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d( DEBUG_TAG, "CountryInfoActivity.onCreate()" );
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled( true );

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            Log.d( DEBUG_TAG, "CountryInfoActivity.onCreate(): !!! Screen rotated. Entering landscape mode. Ending InfoActivity" );
            finish();
            return;
        }

        Log.d(DEBUG_TAG, "CountryInfoActivity.onCreate(): !!! Currently in portrait mode. About to create new InfoFragment object");

        CountryInfoFragment infoFragmentPotrait_obj = new CountryInfoFragment();
        Log.d(DEBUG_TAG, "CountryInfoActivity.onCreate(): CountriesInfoFragment: " + infoFragmentPotrait_obj);

        Log.d(DEBUG_TAG, "CountryInfoActivity.onCreate(): !!! New InfoFragment object created. Passing these arguments to it: " + getIntent().getExtras());
        infoFragmentPotrait_obj.setArguments( getIntent().getExtras() );

        Log.d(DEBUG_TAG, "CountryInfoActivity.onCreate(): !!! Replacing screen content with newly created infoFragment object");
        getSupportFragmentManager().beginTransaction().replace( android.R.id.content, infoFragmentPotrait_obj ).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if( id == android.R.id.home ) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

}