package com.example.parsler.pycman;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.example.parsler.pycman.Commons.StringConstants;
import com.example.parsler.pycman.Components.Navigation.NavigationDrawerFragment;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private static View rootView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        //actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements
            GeoQueryEventListener, GoogleMap.OnCameraChangeListener {



        private GoogleMap mMap;
        private Circle searchCircle;
        private GeoFire geoFire;
        private GeoQuery geoQuery;
        private Map<String,Marker> markers;









        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = StringConstants.SECTION_NUMBER;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            switch(getArguments().getInt(ARG_SECTION_NUMBER)) {

                case 1:
                    rootView = inflater.inflate(R.layout.fragment_add_location, container, false);
                    break;

                case 2:
                    /*rootView = inflater.inflate(R.layout.fragment_home, container, false);
                    if (savedInstanceState == null) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
                        transaction.replace(R.id.sample_content_fragment, fragment);
                        transaction.commit();
                    }*/

                    rootView = inflater.inflate(R.layout.fragment_add_location, container, false);





                    // setup map and camera position
                    SupportMapFragment mapFragment = (SupportMapFragment)
                            getChildFragmentManager().findFragmentById(R.id.map);


                    this.mMap = mapFragment.getMap();
                    //LatLng latLngCenter = new LatLng(28.4701, 77.0301);
                    LatLng latLngCenter = new LatLng(28.4593302, 77.0765365);
                    searchCircle = mMap.addCircle(new CircleOptions().center(latLngCenter).radius(1000));
                    searchCircle.setFillColor(Color.argb(66, 172, 209, 233));
                    searchCircle.setStrokeColor(Color.argb(66, 0, 0, 0));
                    this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCenter, 14));
                    this.mMap.setOnCameraChangeListener(this);


                    Firebase.setAndroidContext(getActivity());
                    geoFire = new GeoFire(new Firebase("https://popping-torch-1459.firebaseio.com/"));
                    //geoFire = new GeoFire(new Firebase("https://publicdata-transit.firebaseio.com/_geofire"));

                    // Function call to get nodes within a specified radius
                    this.geoQuery = geoFire.queryAtLocation(new GeoLocation(28.4701, 77.0301), 1);

                    // setup markers
                    this.markers = new HashMap<String, Marker>();








                    break;

                case 3:
                    rootView = inflater.inflate(R.layout.fragment_history, container, false);
                    break;

                case 4:
                    rootView = inflater.inflate(R.layout.fragment_payment, container, false);
                    break;

                case 5:
                    rootView = inflater.inflate(R.layout.fragment_fare_chart, container, false);
                    break;

                case 6:
                    rootView = inflater.inflate(R.layout.fragment_refer, container, false);
                    break;

                case 7:
                    rootView = inflater.inflate(R.layout.fragment_help, container, false);
                    break;

                case 8:
                    rootView = inflater.inflate(R.layout.fragment_getting_started, container, false);
                    break;

                case 9:
                    rootView = inflater.inflate(R.layout.fragment_tnc, container, false);
                    break;
            }


            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }










        @Override
        public void onStart() {
            super.onStart();

            System.out.println("Please chheck value - " + this.geoQuery);

            if(this.geoQuery != null) {
                // add an event listener to start updating locations again
                this.geoQuery.addGeoQueryEventListener(this);
            }
        }


        @Override
        public void onGeoQueryReady() {
        }


        @Override
        public void onKeyEntered(String key, GeoLocation location) {
            System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
            // Add a new marker to the map
            Marker marker = this.mMap.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude)));
            this.markers.put(key, marker);
        }

        @Override
        public void onKeyExited(String key) {
            System.out.println(String.format("Key %s is no longer in the search area", key));
            Marker marker = this.markers.get(key);
            if (marker != null) {
                marker.remove();
                this.markers.remove(key);
            }
        }

        @Override
        public void onKeyMoved(String key, GeoLocation location) {
            System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
            Marker marker = this.markers.get(key);
            if (marker != null) {
                this.animateMarkerTo(marker, location.latitude, location.longitude);
            }
        }

        @Override
        public void onGeoQueryError(FirebaseError error) {
            System.err.println("There was an error with this query: " + error);
        }


        // Animation handler for old APIs without animation support
        private void animateMarkerTo(final Marker marker, final double lat, final double lng) {
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long DURATION_MS = 3000;
            final Interpolator interpolator = new AccelerateDecelerateInterpolator();
            final LatLng startPosition = marker.getPosition();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    float elapsed = SystemClock.uptimeMillis() - start;
                    float t = elapsed / DURATION_MS;
                    float v = interpolator.getInterpolation(t);

                    double currentLat = (lat - startPosition.latitude) * v + startPosition.latitude;
                    double currentLng = (lng - startPosition.longitude) * v + startPosition.longitude;
                    marker.setPosition(new LatLng(currentLat, currentLng));

                    // if animation is not finished yet, repeat
                    if (t < 1) {
                        handler.postDelayed(this, 16);
                    }
                }
            });
        }


        private double zoomLevelToRadius(double zoomLevel) {
            // Approximation to fit circle into view
            return 16384000/Math.pow(2, zoomLevel);
        }

        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
            // Update the search criteria for this geoQuery and the circle on the map
            LatLng center = cameraPosition.target;
            double radius = zoomLevelToRadius(cameraPosition.zoom);
            this.searchCircle.setCenter(center);
            this.searchCircle.setRadius(radius);
            this.geoQuery.setCenter(new GeoLocation(center.latitude, center.longitude));
            // radius in km
            this.geoQuery.setRadius(radius / 1000);

            GeoQuery abc = geoFire.queryAtLocation(new GeoLocation(center.latitude, center.longitude), 1);

            System.out.println("Search for nearby fe ....");
            System.out.println("Fe found - " + abc);
        }
    }

















    /**
     * Function to select category of item
     * @param view
     */
    public void getCategorySelection(View view) {

        Intent intent = new Intent(this, CategorySelectionActivity.class);
        startActivity(intent);
    }




    /**
     * Function to fetch Order Summary
     * @param view
     */
    public void getOrderSummary(View view) {

        Intent intent = new Intent(this, OrderSummaryActivity.class);
        startActivity(intent);
    }
}
