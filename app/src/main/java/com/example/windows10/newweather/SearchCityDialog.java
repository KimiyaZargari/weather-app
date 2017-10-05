package com.example.windows10.newweather;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


/**
 * Created by Windows 10 on 30/09/2017.
 */

public class SearchCityDialog extends Dialog {
    private PlaceAutocompleteFragment places;
    CitySelectedCallback citySelectedCallback;

    public SearchCityDialog(Context context, FragmentManager fragmentManager, CitySelectedCallback citySelectedCallback) {
        super(context);
        this.citySelectedCallback = citySelectedCallback;
        setContentView(R.layout.search_diaog);
        setTitle("Search Location");
        places = (PlaceAutocompleteFragment) fragmentManager.findFragmentById(R.id.place_autocomplete_fragment);
        setFilter();
        setListener(context);
    }

    private void setFilter() {
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        places.setFilter(typeFilter);
    }

    private void setListener(final Context context) {

        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                citySelectedCallback.citySelected(place.getName().toString());
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(context, status.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public interface CitySelectedCallback {
        public void citySelected(String city);
    }

}

