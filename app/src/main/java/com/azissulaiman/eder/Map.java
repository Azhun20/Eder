package com.azissulaiman.eder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.OnCameraTrackingChangedListener;
import com.mapbox.mapboxsdk.location.OnLocationClickListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;

import java.util.Calendar;
import java.util.List;

public class Map extends AppCompatActivity implements
        OnMapReadyCallback, OnLocationClickListener, PermissionsListener, OnCameraTrackingChangedListener {

    private PermissionsManager permissionsManager;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private LocationComponent locationComponent;
    private boolean isInTrackingMode;
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

//    private GeoJsonSource source;
//    private static final String GEOJSON_SOURCE_ID = "GEOJSON_SOURCE_ID";
//    private static final String MARKER_IMAGE_ID = "MARKER_IMAGE_ID";
//    private static final String MARKER_LAYER_ID = "MARKER_LAYER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.token));
        setContentView(R.layout.activity_map);
        getSupportActionBar().hide();
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        Button btnhome1 = findViewById(R.id.btnhome1);
        Button btnhome2 = findViewById(R.id.btnhome2);
        Button btnhome3 = findViewById(R.id.btnhome3);
        Button btnread1 = findViewById(R.id.btnread1);
        Button btnread2 = findViewById(R.id.btnread2);
        Button btnread3 = findViewById(R.id.btnread3);
        Button back = findViewById(R.id.txback);
        btnhome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Map.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                // Do something with the date chosen by the user
                                startActivity(new Intent(Map.this, coba.class));
                            }
                        }, year, month, day);
                datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Book now", datePickerDialog);
                datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", datePickerDialog);
//                Button positiveButton = datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE);
//                positiveButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(Map.this, coba.class));
//                    }
//                });
//                positiveButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
//                positiveButton.setBackgroundColor(Color.MAGENTA);
//                positiveButton.setTextColor(Color.WHITE);
                datePickerDialog.show();
            }
        });
        btnhome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Map.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                // Do something with the date chosen by the user
                                startActivity(new Intent(Map.this, coba.class));
                            }
                        }, year, month, day);
                datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Book now", datePickerDialog);
                datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", datePickerDialog);
                datePickerDialog.show();
            }
        });
        btnread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Map.this, coba.class));
            }
        });
        btnread2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Map.this, coba.class));
            }
        });
        btnread3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Map.this, coba.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Map.this, coba.class));
            }
        });

//        mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
//            @Override
//            public boolean onMapClick(@NonNull LatLng point) {
//                Toast.makeText(Map.this, String.format("User clicked at: %s", point.toString()), Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);
            }
        });
        addMarkers();
    }
    private void addMarkers() {
        MarkerViewManager markerViewManager = new MarkerViewManager(mapView, mapboxMap);
        View customView = LayoutInflater.from(Map.this).inflate(
                R.layout.mapviewicon, null);
        TextView titleTextView = customView.findViewById(R.id.marker_window_title);
        titleTextView.setText("Perpus UM");
        ImageView img1 = customView.findViewById(R.id.img1);
        img1.setImageResource(R.drawable.book);
        TextView snippetTextView = customView.findViewById(R.id.marker_window_snippet);
        snippetTextView.setText("260 m");
        customView.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        MarkerView marker1 = new MarkerView(new LatLng(-7.961940644994314, 112.61656210416004), customView);
        markerViewManager.addMarker(marker1);

        View customView2 = LayoutInflater.from(Map.this).inflate(
                R.layout.mapviewicon, null);
        TextView titleTextView2 = customView2.findViewById(R.id.marker_window_title);
        titleTextView2.setText("Perpus Kota");
        ImageView img2 = customView2.findViewById(R.id.img1);
        img2.setImageResource(R.drawable.book);
        TextView snippetTextView2 = customView2.findViewById(R.id.marker_window_snippet);
        snippetTextView2.setText("300 m");
        customView2.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        MarkerView marker2 = new MarkerView(new LatLng(-7.972050276205999, 112.622064932996159), customView2);
        markerViewManager.addMarker(marker2);

        View customView3 = LayoutInflater.from(Map.this).inflate(
                R.layout.mapviewicon, null);
        TextView titleTextView3 = customView3.findViewById(R.id.marker_window_title);
        titleTextView3.setText("Perpus Dieng");
        ImageView img3 = customView3.findViewById(R.id.img1);
        img3.setImageResource(R.drawable.book);
        TextView snippetTextView3 = customView3.findViewById(R.id.marker_window_snippet);
        snippetTextView3.setText("280 m");
        customView3.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        MarkerView marker3 = new MarkerView(new LatLng(-7.972760608814014, 112.61273907623124), customView3);
        markerViewManager.addMarker(marker3);

    }

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(this)
                    .elevation(5)
                    .accuracyAlpha(.6f)
                    .accuracyColor(Color.RED)
                    .foregroundDrawable(R.drawable.locationon)
                    .build();
            locationComponent = mapboxMap.getLocationComponent();

            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(this, loadedMapStyle)
                            .locationComponentOptions(customLocationComponentOptions)
                            .build();
            locationComponent.activateLocationComponent(locationComponentActivationOptions);
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);
            locationComponent.addOnLocationClickListener(this);
            locationComponent.addOnCameraTrackingChangedListener(this);

            findViewById(R.id.back_to_camera_tracking_mode).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isInTrackingMode) {
                        isInTrackingMode = true;
                        locationComponent.setCameraMode(CameraMode.TRACKING);
                        locationComponent.zoomWhileTracking(16f);
                        Toast.makeText(Map.this, getString(R.string.tracking_enabled),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Map.this, getString(R.string.tracking_already_enabled),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }

    }

    @SuppressWarnings( {"MissingPermission"})
    @Override
    public void onLocationComponentClick() {
        if (locationComponent.getLastKnownLocation() != null) {
            Toast.makeText(this, String.format(getString(R.string.current_location),
                    locationComponent.getLastKnownLocation().getLatitude(),
                    locationComponent.getLastKnownLocation().getLongitude()), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCameraTrackingDismissed() {
        isInTrackingMode = false;
    }

    @Override
    public void onCameraTrackingChanged(int currentMode) {
// Empty on purpose
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}