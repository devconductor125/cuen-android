package com.orquitech.development.cuencaVerde.presentation.views;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.Log;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.SerializationManager;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.Croquis;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenanceCommentPoint;
import com.orquitech.development.cuencaVerde.data.model.geoJson.FeatureProperties;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GenericFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.GeoJson;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Point;
import com.orquitech.development.cuencaVerde.data.model.geoJson.PointFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.orquitech.development.cuencaVerde.logic.AppAccionesManager;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.orquitech.development.cuencaVerde.logic.utils.GeoUtils;
import com.orquitech.development.cuencaVerde.presentation.views.interfaces.MapParentView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapViewHelper {

    public static final String LIST_OF_MAP_ACTIONS = "listOfMapActions";
    public static final String REQUESTING_LOCATION_UPDATES = "requestingLocationUpdates";

    private final MapParentView mapParentView;
    private GoogleMap googleMap;

    private List<Circle> circles;
    private List<Circle> baseCircles;
    private List<Circle> editedCircles;

    private List<Polyline> lines;
    private List<Polyline> baseLines;
    private List<Polyline> editedLines;

    private List<Polygon> polygons;
    private List<Polygon> basePolygons;
    private List<Polygon> editedPolygons;

    private List<Circle> managementLayerCircles;
    private List<Polyline> managementLayerLines;
    private List<Polygon> managementLayerPolygons;

    private List<Polygon> croquisPolyongs;
    private List<Polyline> trackedLocationsLines;
    private Polygon trackedLocationsPolygon;

    private LatLng lastLocation;
    private List<Circle> practicesCircles;
    public LatLng rawLastLocation;
    private List<Marker> markers;
    private List<LatLng> allCoordinates;

    private int circleColor = R.color.colorAccent;

    public MapViewHelper(MapParentView mapParentView) {
        this.mapParentView = mapParentView;

        circles = new ArrayList<>();
        baseCircles = new ArrayList<>();
        editedCircles = new ArrayList<>();

        lines = new ArrayList<>();
        baseLines = new ArrayList<>();
        editedLines = new ArrayList<>();

        polygons = new ArrayList<>();
        basePolygons = new ArrayList<>();
        editedPolygons = new ArrayList<>();

        croquisPolyongs = new ArrayList<>();
        practicesCircles = new ArrayList<>();
        markers = new ArrayList<>();

        managementLayerCircles = new ArrayList<>();
        managementLayerLines = new ArrayList<>();
        managementLayerPolygons = new ArrayList<>();

        allCoordinates = new ArrayList<>();
    }

    public void setUpMap(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setOnPolylineClickListener(polyline -> mapParentView.showFeatureDialog((MultiLineStringFeature) polyline.getTag()));
        googleMap.setOnPolygonClickListener(polygon -> {
            if (polygon.getTag() instanceof PolygonFeature) {
                mapParentView.showFeatureDialog((PolygonFeature) polygon.getTag());
            }
            if (polygon.getTag() instanceof MultiPolygonFeature) {
                mapParentView.showFeatureDialog((MultiPolygonFeature) polygon.getTag());
            }
        });
        googleMap.setOnCircleClickListener(circle -> mapParentView.showFeatureDialog((PointFeature) circle.getTag()));
        googleMap.setOnMarkerClickListener(marker -> {
            mapParentView.onMarkerClick(marker.getTag());
            return true;
        });
        enableMyLocationOnMap();

        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            mapParentView.getContext(), R.raw.map_style));
            if (!success) {
                Log.e(getClass().getSimpleName(), "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(getClass().getSimpleName(), "Can't find style. Error: ", e);
        }
    }

    private void enableMyLocationOnMap() {
        try {
            if (!googleMap.isMyLocationEnabled()) {
                googleMap.setMyLocationEnabled(true);
            }
        } catch (SecurityException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void disableMyLocationOnMap() {
        try {
            if (googleMap.isMyLocationEnabled()) {
                googleMap.setMyLocationEnabled(false);
            }
        } catch (SecurityException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void addNewTrackedLocationToMap(Location trackedLocation) {
        LatLng latLng = LocationUtils.locationToLatLng(trackedLocation);
        drawCircle(latLng);
        animateCameraToLocation(latLng);
    }

    public void addMultiLineFeatureToMap(MultiLineStringFeature feature) {
        addMultiLineFeatureToMap(feature, true);
    }

    public void addBaseMultiLineFeatureToMap(MultiLineStringFeature feature) {
        addBaseMultiLineFeatureToMap(feature, false);
    }

    public void addEditedMultiLineFeatureToMap(MultiLineStringFeature feature) {
        addEditedMultiLineFeatureToMap(feature, false);
    }

    public void addGeoJsonToMap(GeoJson geoJson) {
        addMultiLineStringFeaturesToMap(geoJson.getMultiLineStringFeatures());
        addMultiPolygonFeaturesToMap(geoJson.getMultiPolygonFeatures());
        addPolygonFeaturesToMap(geoJson.getPolygonFeatures());
        addPointFeaturesToMap(geoJson.getPointFeatures());
        centerViewOnFeatures(allCoordinates);
    }

    /**
     * Base layer from execution tasks
     */
    public void addBaseGeoJsonToMap(GeoJson geoJson) {
        addBaseMultiLineStringFeaturesToMap(geoJson.getMultiLineStringFeatures());
        addBaseMultiPolygonFeaturesToMap(geoJson.getMultiPolygonFeatures());
        addBasePolygonFeaturesToMap(geoJson.getPolygonFeatures());
        addBasePointFeaturesToMap(geoJson.getPointFeatures());
    }

    /**
     * Edited layer from execution tasks
     */
    public void addEditedGeoJsonToMap(GeoJson geoJson) {
        addEditedMultiLineStringFeaturesToMap(geoJson.getMultiLineStringFeatures());
        addEditedMultiPolygonFeaturesToMap(geoJson.getMultiPolygonFeatures());
        addEditedPolygonFeaturesToMap(geoJson.getPolygonFeatures());
        addEditedPointFeaturesToMap(geoJson.getPointFeatures());
    }

    public void addMultiLineFeatureToMap(MultiLineStringFeature feature, boolean centerView) {
        drawLine(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
        removeCircles();
    }

    public void addBaseMultiLineFeatureToMap(MultiLineStringFeature feature, boolean centerView) {
        drawBaseLine(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
    }

    public void addEditedMultiLineFeatureToMap(MultiLineStringFeature feature, boolean centerView) {
        drawEditedLine(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
    }

    public void addManagementLayerMultiLineFeatureToMap(MultiLineStringFeature feature, boolean centerView) {
        drawManagementLayerLine(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
        removeCircles();
    }

    public void addPolygonFeatureToMap(PolygonFeature feature) {
        addPolygonFeatureToMap(feature, true);
    }

    public void addBasePolygonFeatureToMap(PolygonFeature feature) {
        addBasePolygonFeatureToMap(feature, false);
    }

    public void addEditedPolygonFeatureToMap(PolygonFeature feature) {
        addEditedPolygonFeatureToMap(feature, false);
    }

    public void addPolygonFeatureToMap(PolygonFeature feature, boolean centerView) {
        drawPolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
        removeCircles();
    }

    public void addBasePolygonFeatureToMap(PolygonFeature feature, boolean centerView) {
        drawBasePolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
    }

    public void addEditedPolygonFeatureToMap(PolygonFeature feature, boolean centerView) {
        drawEditedPolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
    }

    public void addManagementLayerPolygonFeatureToMap(PolygonFeature feature, boolean centerView) {
        drawManagementLayerPolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
    }

    public void addMultiPolygonFeatureToMap(MultiPolygonFeature feature) {
        addMultiPolygonFeatureToMap(feature, true);
    }

    public void addBaseMultiPolygonFeatureToMap(MultiPolygonFeature feature) {
        addBaseMultiPolygonFeatureToMap(feature, false);
    }

    public void addEditedMultiPolygonFeatureToMap(MultiPolygonFeature feature) {
        addEditedMultiPolygonFeatureToMap(feature, false);
    }

    public void addMultiPolygonFeatureToMap(MultiPolygonFeature feature, boolean centerView) {
        drawMultiPolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
        removeCircles();
    }

    public void addBaseMultiPolygonFeatureToMap(MultiPolygonFeature feature, boolean centerView) {
        drawBaseMultiPolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
    }

    public void addEditedMultiPolygonFeatureToMap(MultiPolygonFeature feature, boolean centerView) {
        drawEditedMultiPolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
    }

    public void addManagementLayerMultiPolygonFeatureToMap(MultiPolygonFeature feature, boolean centerView) {
        drawManagementLayerMultiPolygonFromPolygonFeature(feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometryList());
        }
        removeCircles();
    }

    public void addPointFeatureToMap(PointFeature feature) {
        addPointFeatureToMap(feature, true);
    }

    public void addBasePointFeatureToMap(PointFeature feature) {
        addBasePointFeatureToMap(feature, false);
    }

    public void addEditedPointFeatureToMap(PointFeature feature) {
        addEditedPointFeatureToMap(feature, false);
    }

    public void addPointFeatureToMap(PointFeature feature, boolean centerView) {
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(feature.getGeometry().coordinates.get(1));
        coordinates.add(feature.getGeometry().coordinates.get(0));
        LatLng latLng = new LatLng(coordinates.get(0), coordinates.get(1));
        drawCircle(latLng, feature);
        if (centerView) {
            centerViewOnFeature(feature.getGeometry());
        }
    }

    public void addBasePointFeatureToMap(PointFeature feature, boolean centerView) {
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(feature.getGeometry().coordinates.get(1));
        coordinates.add(feature.getGeometry().coordinates.get(0));
        LatLng latLng = new LatLng(coordinates.get(0), coordinates.get(1));
        int color = TextUtils.isEmpty(feature.getProperties().getAccionId()) ? R.color.colorAccent : 0;
        drawBaseCircle(latLng, feature, color);
        if (centerView) {
            centerViewOnFeature(feature.getGeometry());
        }
    }

    public void addEditedPointFeatureToMap(PointFeature feature, boolean centerView) {
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(feature.getGeometry().coordinates.get(1));
        coordinates.add(feature.getGeometry().coordinates.get(0));
        LatLng latLng = new LatLng(coordinates.get(0), coordinates.get(1));
        int color = TextUtils.isEmpty(feature.getProperties().getAccionId()) ? R.color.colorAccent : 0;
        drawEditedCircle(latLng, feature, color);
        if (centerView) {
            centerViewOnFeature(feature.getGeometry());
        }
    }

    public void addManagementLayerPointFeatureToMap(PointFeature feature, boolean centerView) {
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(feature.getGeometry().coordinates.get(1));
        coordinates.add(feature.getGeometry().coordinates.get(0));
        LatLng latLng = new LatLng(coordinates.get(0), coordinates.get(1));
        int color = TextUtils.isEmpty(feature.getProperties().getAccionId()) ? R.color.colorAccent : 0;
        drawManagementLayerCircle(latLng, feature, color);
        if (centerView) {
            centerViewOnFeature(feature.getGeometry());
        }
    }

    public void addMultiLineStringFeaturesToMap(List<MultiLineStringFeature> features) {
        for (MultiLineStringFeature feature : features) {
            addMultiLineFeatureToMap(feature, false);
        }
    }

    public void addPolygonFeaturesToMap(List<PolygonFeature> features) {
        for (PolygonFeature feature : features) {
            addPolygonFeatureToMap(feature, false);
        }
    }

    public void addMultiPolygonFeaturesToMap(List<MultiPolygonFeature> features) {
        for (MultiPolygonFeature feature : features) {
            addMultiPolygonFeatureToMap(feature, false);
        }
    }

    public void addPointFeaturesToMap(List<PointFeature> features) {
        for (PointFeature feature : features) {
            addPointFeatureToMap(feature, false);
        }
    }

    public void addBaseMultiLineStringFeaturesToMap(List<MultiLineStringFeature> features) {
        for (MultiLineStringFeature feature : features) {
            addBaseMultiLineFeatureToMap(feature);
        }
    }

    public void addEditedMultiLineStringFeaturesToMap(List<MultiLineStringFeature> features) {
        for (MultiLineStringFeature feature : features) {
            addEditedMultiLineFeatureToMap(feature);
        }
    }

    public void addBasePolygonFeaturesToMap(List<PolygonFeature> features) {
        for (PolygonFeature feature : features) {
            addBasePolygonFeatureToMap(feature);
        }
    }

    public void addEditedPolygonFeaturesToMap(List<PolygonFeature> features) {
        for (PolygonFeature feature : features) {
            addEditedPolygonFeatureToMap(feature);
        }
    }

    public void addBaseMultiPolygonFeaturesToMap(List<MultiPolygonFeature> features) {
        for (MultiPolygonFeature feature : features) {
            addBaseMultiPolygonFeatureToMap(feature);
        }
    }

    public void addEditedMultiPolygonFeaturesToMap(List<MultiPolygonFeature> features) {
        for (MultiPolygonFeature feature : features) {
            addEditedMultiPolygonFeatureToMap(feature);
        }
    }

    public void addBasePointFeaturesToMap(List<PointFeature> features) {
        for (PointFeature feature : features) {
            addBasePointFeatureToMap(feature);
        }
    }

    public void addEditedPointFeaturesToMap(List<PointFeature> features) {
        for (PointFeature feature : features) {
            addEditedPointFeatureToMap(feature);
        }
    }

    public void drawLine(MultiLineStringFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                allCoordinates.addAll(points);
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.addAll(points);
                polylineOptions.color(Color.parseColor(feature.getProperties().getColor()));
                Polyline polyline = googleMap.addPolyline(polylineOptions);
                polyline.setGeodesic(true);
                polyline.setClickable(true);
                polyline.setTag(feature);
                lines.add(polyline);
            }
        }
    }

    public void drawBaseLine(MultiLineStringFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.addAll(points);
                polylineOptions.color(Color.parseColor(feature.getProperties().getColor()));
                Polyline polyline = googleMap.addPolyline(polylineOptions);
                polyline.setGeodesic(true);
                polyline.setClickable(true);
                polyline.setTag(feature);
                baseLines.add(polyline);
            }
        }
    }

    public void drawEditedLine(MultiLineStringFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.addAll(points);
                polylineOptions.color(Color.parseColor(feature.getProperties().getColor()));
                Polyline polyline = googleMap.addPolyline(polylineOptions);
                polyline.setGeodesic(true);
                polyline.setClickable(true);
                editedLines.add(polyline);
            }
        }
    }

    public void drawManagementLayerLine(MultiLineStringFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.addAll(points);
                polylineOptions.color(Color.parseColor(feature.getProperties().getColor()));
                Polyline polyline = googleMap.addPolyline(polylineOptions);
                polyline.setGeodesic(true);
                polyline.setClickable(true);
                polyline.setTag(feature);
                polyline.setZIndex(-2);
                managementLayerLines.add(polyline);
            }
        }
    }

    public void drawPolygonFromPolygonFeature(PolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                allCoordinates.addAll(points);
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setTag(feature);
                polygons.add(polygon);
            }
        }
    }

    public void drawBasePolygonFromPolygonFeature(PolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setTag(feature);
                basePolygons.add(polygon);
            }
        }
    }

    private PolygonOptions getPolygonOptionsColors(PolygonFeature feature, PolygonOptions polygonOptions) {
        if (!TextUtils.isEmpty(feature.getProperties().getColor())) {
            polygonOptions.strokeColor(Color.parseColor(feature.getProperties().getColor()));
        } else {
            polygonOptions.strokeWidth(0);
        }
        if (!TextUtils.isEmpty(feature.getProperties().getFillColor())) {
            polygonOptions.fillColor(Color.parseColor(feature.getProperties().getFillColor()));
        }
        return polygonOptions;
    }

    private PolygonOptions getPolygonOptionsColors(MultiPolygonFeature feature, PolygonOptions polygonOptions) {
        if (!TextUtils.isEmpty(feature.getProperties().getColor())) {
            polygonOptions.strokeColor(Color.parseColor(feature.getProperties().getColor()));
        } else {
            polygonOptions.strokeWidth(0);
        }
        if (!TextUtils.isEmpty(feature.getProperties().getFillColor())) {
            polygonOptions.fillColor(Color.parseColor(feature.getProperties().getFillColor()));
        }
        return polygonOptions;
    }

    public void drawEditedPolygonFromPolygonFeature(PolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setTag(feature);
                editedPolygons.add(polygon);
            }
        }
    }

    public void drawManagementLayerPolygonFromPolygonFeature(PolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setTag(feature);
                managementLayerPolygons.add(polygon);
            }
        }
    }

    public void drawMultiPolygonFromPolygonFeature(MultiPolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                allCoordinates.addAll(points);
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setTag(feature);
                polygons.add(polygon);
            }
        }
    }

    public void drawBaseMultiPolygonFromPolygonFeature(MultiPolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setTag(feature);
                basePolygons.add(polygon);
            }
        }
    }

    public void drawEditedMultiPolygonFromPolygonFeature(MultiPolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setTag(feature);
                editedPolygons.add(polygon);
            }
        }
    }

    public void drawManagementLayerMultiPolygonFromPolygonFeature(MultiPolygonFeature feature) {
        List<List<LatLng>> coordinates = feature.getGeometryList();
        if (coordinates != null) {
            for (List<LatLng> points : coordinates) {
                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.addAll(points);
                polygonOptions = getPolygonOptionsColors(feature, polygonOptions);
                Polygon polygon = googleMap.addPolygon(polygonOptions);
                polygon.setGeodesic(true);
                polygon.setClickable(true);
                polygon.setZIndex(-3);
                managementLayerPolygons.add(polygon);
            }
        }
    }

    public void drawSamplingPointCircle(LatLng latLng, PointFeature feature) {
        drawTreeCircle(latLng, feature);
    }

    public void drawTreeCircle(LatLng latLng, PointFeature feature) {
        if (googleMap != null && latLng != null) {
            List<LatLng> coordinates = new ArrayList<>();
            coordinates.add(latLng);
            allCoordinates.addAll(coordinates);
            Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(2)
                    .strokeWidth(0)
                    .fillColor(Color.parseColor(mapParentView.getContext().getString(R.string.tree_marker_color)))
                    .zIndex(4)
                    .clickable(true));
            circle.setTag(feature);
            practicesCircles.add(circle);
        }
    }

    public void drawCircle(LatLng latLng, PointFeature feature) {
        if (googleMap != null && latLng != null) {
            List<LatLng> coordinates = new ArrayList<LatLng>();
            coordinates.add(latLng);
            allCoordinates.addAll(coordinates);
            Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(2)
                    .strokeWidth(0)
                    .fillColor(Color.parseColor(feature.getProperties().getColor()))
                    .zIndex(3)
                    .clickable(true));
            circle.setTag(feature);
            practicesCircles.add(circle);
        }
    }

    private void drawCircle(LatLng location) {
        if (googleMap != null && location != null) {
            Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(location)
                    .radius(2)
                    .strokeWidth(0)
                    .strokeColor(ContextCompat.getColor(mapParentView.getContext(), circleColor))
                    .fillColor(ContextCompat.getColor(mapParentView.getContext(), circleColor))
                    .clickable(true));
            circles.add(circle);
        }
    }

    public void drawBaseCircle(LatLng latLng, PointFeature feature, int strokeColor) {
        if (googleMap != null && latLng != null) {
            Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(2)
                    .strokeWidth(0)
                    .strokeColor(strokeColor != 0 ? strokeColor : Color.parseColor(feature.getProperties().getColor()))
                    .fillColor(Color.parseColor(feature.getProperties().getColor()))
                    .zIndex(3)
                    .clickable(true));
            circle.setTag(feature);
            practicesCircles.add(circle);
        }
    }

    public void drawEditedCircle(LatLng latLng, PointFeature feature, int strokeColor) {
        if (googleMap != null && latLng != null) {
            Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(2)
                    .strokeWidth(0)
                    .strokeColor(strokeColor != 0 ? strokeColor : Color.parseColor(feature.getProperties().getColor()))
                    .fillColor(Color.parseColor(feature.getProperties().getColor()))
                    .zIndex(3)
                    .clickable(true));
            circle.setTag(feature);
            baseCircles.add(circle);
        }
    }

    public void drawManagementLayerCircle(LatLng latLng, PointFeature feature, int strokeColor) {
        if (googleMap != null && latLng != null) {
            Circle circle = googleMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius(2)
                    .strokeWidth(0)
                    .strokeColor(strokeColor != 0 ? strokeColor : Color.parseColor(feature.getProperties().getColor()))
                    .fillColor(Color.parseColor(feature.getProperties().getColor()))
                    .zIndex(3)
                    .clickable(true));
            circle.setTag(feature);
            editedCircles.add(circle);
        }
    }

    public void removeCircles() {
        for (Circle circle : circles) {
            if (circle != null) {
                circle.remove();
            }
        }
        circles = new ArrayList<>();

        for (Circle circle : baseCircles) {
            if (circle != null) {
                circle.remove();
            }
        }
        baseCircles = new ArrayList<>();

        for (Circle circle : editedCircles) {
            if (circle != null) {
                circle.remove();
            }
        }
        editedCircles = new ArrayList<>();
    }

    public void removeManagementLayerCircles() {
        for (Circle circle : managementLayerCircles) {
            if (circle != null) {
                circle.remove();
            }
        }
        managementLayerCircles = new ArrayList<>();
    }

    private void removeLines() {
        for (Polyline line : lines) {
            if (line != null) {
                line.remove();
            }
        }
        lines = new ArrayList<>();

        for (Polyline line : baseLines) {
            if (line != null) {
                line.remove();
            }
        }
        baseLines = new ArrayList<>();

        for (Polyline line : editedLines) {
            if (line != null) {
                line.remove();
            }
        }
        editedLines = new ArrayList<>();
    }

    private void removeManagementLayerLines() {
        for (Polyline line : managementLayerLines) {
            if (line != null) {
                line.remove();
            }
        }
        managementLayerLines = new ArrayList<>();
    }

    private void removePolygons() {
        for (Polygon polygon : polygons) {
            if (polygon != null) {
                polygon.remove();
            }
        }
        polygons = new ArrayList<>();

        for (Polygon polygon : croquisPolyongs) {
            if (polygon != null) {
                polygon.remove();
            }
        }
        croquisPolyongs = new ArrayList<>();

        for (Polygon polygon : basePolygons) {
            if (polygon != null) {
                polygon.remove();
            }
        }
        basePolygons = new ArrayList<>();

        for (Polygon polygon : editedPolygons) {
            if (polygon != null) {
                polygon.remove();
            }
        }
        editedPolygons = new ArrayList<>();
    }

    private void removeManagementLayerPolygons() {
        for (Polygon polygon : managementLayerPolygons) {
            if (polygon != null) {
                polygon.remove();
            }
        }
        managementLayerPolygons = new ArrayList<>();
    }

    private void removeMarkers() {
        for (Marker marker : markers) {
            if (marker != null) {
                marker.remove();
            }
        }
        markers = new ArrayList<>();
    }

    private void removePracticesCircles() {
        for (Circle circle : practicesCircles) {
            if (circle != null) {
                circle.remove();
            }
        }
        practicesCircles = new ArrayList<>();
    }

    private void centerViewOnFeature(Point point) {
        if (point.coordinates.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            LatLng latLng = new LatLng(point.coordinates.get(1), point.coordinates.get(0));
            builder.include(latLng);
            LatLngBounds bounds = builder.build();
            int padding = (int) mapParentView.getContext().getResources().getDimension(R.dimen.dimen_xxxxlarge); // offset from edges of the map in pixels
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            googleMap.moveCamera(cameraUpdate);
        }
    }

    private void centerViewOnFeature(List<List<LatLng>> coordinates) {
        if (coordinates.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (List<LatLng> coordinate : coordinates) {
                for (LatLng point : coordinate) {
                    builder.include(point);
                }
            }
            LatLngBounds bounds = builder.build();
            int padding = (int) mapParentView.getContext().getResources().getDimension(R.dimen.dimen_xxxxlarge); // offset from edges of the map in pixels
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            googleMap.moveCamera(cameraUpdate);
        }
    }

    public void animateCameraToLocation(LatLng location) {
        if (googleMap != null && mapParentView.canAnimateMapCamera()) {
            if (lastLocation == null) {
                lastLocation = location;
                CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(19).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            } else if (GeoUtils.isFurtherThan(location, lastLocation, 25)) {
                lastLocation = location;
                CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(19).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }
    }

    public void moveCameraToLocation(LatLng location) {
        if (mapParentView.canMoveMapCamera()) {
            if (lastLocation == null) {
                lastLocation = location;
                CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(19).build();
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            } else {
                if (GeoUtils.isFurtherThan(location, lastLocation, 25)) {
                    lastLocation = location;
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(19).build();
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            }
        }
    }

    public GoogleMap getMap() {
        return googleMap;
    }

    public void switchCircleColor() {
        circleColor = circleColor == R.color.colorAccent ? R.color.colorPrimary : R.color.colorAccent;
    }

    public void resetCircleColor() {
        circleColor = R.color.colorAccent;
    }

    public LatLng getLastLocation() {
        return lastLocation;
    }

    public void onDestroy() {
        clearMeasurementLayer();
        clearManagementLayer();
    }

    public void clearMeasurementLayer() {
        removeCircles();
        removeLines();
        removePolygons();
        removePracticesCircles();
        removeMarkers();
        clearTrackedLocations();
        allCoordinates = new ArrayList<>();
    }

    public void clearManagementLayer() {
        removeManagementLayerCircles();
        removeManagementLayerLines();
        removeManagementLayerPolygons();
    }

    public void onResume() {
        enableMyLocationOnMap();
    }

    public void onPause() {
        disableMyLocationOnMap();
    }

    public void onNewLocation(LatLng lastLocation) {
        rawLastLocation = lastLocation;
        if (googleMap != null && googleMap.getCameraPosition().zoom > 3) {
            animateCameraToLocation(lastLocation);
        } else if (googleMap != null) {
            moveCameraToLocation(lastLocation);
        }
    }

    public void onLocationResult(Location location) {
        if (location != null) {
            rawLastLocation = LocationUtils.locationToLatLng(location);
            if (googleMap != null && googleMap.getCameraPosition().zoom < 3) {
                moveCameraToLocation(LocationUtils.locationToLatLng(location));
            } else {
                if (!UserLocationService.isInstanceCreated()) {
                    animateCameraToLocation(LocationUtils.locationToLatLng(location));
                }
            }
        }
    }

    public void setExistingTrackedLocations(List<List<Location>> trackedLocations, Action chosenAction) {
        if (chosenAction.getAccionType().equals(AppAccionesManager.ACCIONES)) {
            drawTrackedLocationLine(trackedLocations, chosenAction);
        } else if (chosenAction.getAccionType().equals(AppAccionesManager.AREAS)) {
            drawTrackedLocationPolygon(trackedLocations, chosenAction);
        }
    }

    public void drawTrackedLocationLine(List<List<Location>> trackedLocations, Action chosenAction) {
        if (chosenAction != null) {
            if (googleMap != null && trackedLocations.size() > 0) {

                if (trackedLocationsLines == null) {
                    trackedLocationsLines = new ArrayList<>();
                }

                for (List<Location> locations : trackedLocations) {
                    List<LatLng> points = new ArrayList<>();
                    for (Location location : locations) {
                        points.add(LocationUtils.locationToLatLng(location));
                    }
                    PolylineOptions polylineOptions = new PolylineOptions();
                    polylineOptions.addAll(points);
                    polylineOptions.color(Color.parseColor(chosenAction.getColor()));

                    Polyline trackedLocationsLine = googleMap.addPolyline(polylineOptions);
                    trackedLocationsLine.setGeodesic(true);
                    trackedLocationsLine.setClickable(true);
                    MultiLineStringFeature multiLineStringFeature = new MultiLineStringFeature();
                    FeatureProperties featureProperties = new FeatureProperties();
                    featureProperties.setAccionTitle(chosenAction.getTitle());
                    featureProperties.setMaterialTitle(chosenAction.getMaterial().getTitle());
                    multiLineStringFeature.setProperties(featureProperties);
                    trackedLocationsLine.setTag(multiLineStringFeature);

                    trackedLocationsLines.add(trackedLocationsLine);
                }
            }
        }
    }

    public void drawTrackedLocationPolygon(List<List<Location>> trackedLocations, Action chosenAction) {
        if (chosenAction != null) {
            if (googleMap != null && trackedLocations.size() > 0) {
                List<LatLng> points = new ArrayList<>();
                for (List<Location> locationList : trackedLocations) {
                    for (Location location : locationList) {
                        points.add(LocationUtils.locationToLatLng(location));
                    }
                }
                drawTrackedPolygon(points, chosenAction);
            }
        }
    }

    private void drawTrackedPolygon(List<LatLng> points, Action action) {
        if (points.size() > 2) {
            PolygonOptions polygonOptions = new PolygonOptions();
            polygonOptions.addAll(points);
            if (!TextUtils.isEmpty((action.getColor()))) {
                polygonOptions.strokeColor(Color.parseColor(action.getColor()));
            } else {
                polygonOptions.strokeWidth(0);
            }
            if (!TextUtils.isEmpty((action.getFillColor()))) {
                polygonOptions.fillColor(Color.parseColor(action.getFillColor()));
            }
            trackedLocationsPolygon = googleMap.addPolygon(polygonOptions);
            trackedLocationsPolygon.setGeodesic(true);
            trackedLocationsPolygon.setClickable(true);
            MultiLineStringFeature multiLineStringFeature = new MultiLineStringFeature();
            FeatureProperties featureProperties = new FeatureProperties();
            featureProperties.setAccionTitle(action.getTitle());
            featureProperties.setMaterialTitle(action.getMaterial().getTitle());
            multiLineStringFeature.setProperties(featureProperties);
            trackedLocationsPolygon.setTag(multiLineStringFeature);
        }
    }

    public void drawTrackedFeature(List<List<Location>> trackedLocations, Action chosenAction, boolean isNewSegment) {
        if (chosenAction != null) {
            if (googleMap != null) {

                clearTrackedLocations();

                if (chosenAction.getAccionType().equals(AppAccionesManager.ACCIONES)) {

                    for (List<Location> locations : trackedLocations) {

                        List<LatLng> points = new ArrayList<>();
                        for (Location location : locations) {
                            points.add(LocationUtils.locationToLatLng(location));
                        }
                        Polyline polyline = drawPolylineForAcciones(points, chosenAction);
                        trackedLocationsLines.add(polyline);
                    }

                } else if (chosenAction.getAccionType().equals(AppAccionesManager.AREAS)) {

                    for (List<Location> locations : trackedLocations) {

                        List<LatLng> points = new ArrayList<>();
                        for (Location location : locations) {
                            points.add(LocationUtils.locationToLatLng(location));
                        }

                        drawTrackedPolygon(points, chosenAction);
                    }
                }
            }
        }
    }

    private Polyline drawPolylineForAcciones(List<LatLng> points, Action action) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(points);
        polylineOptions.color(Color.parseColor(action.getColor()));
        Polyline polyline = googleMap.addPolyline(polylineOptions);
        polyline.setGeodesic(true);
        polyline.setClickable(true);
        MultiLineStringFeature multiLineStringFeature = new MultiLineStringFeature();
        FeatureProperties featureProperties = new FeatureProperties();
        featureProperties.setAccionTitle(action.getTitle());
        featureProperties.setMaterialTitle(action.getMaterial().getTitle());
        multiLineStringFeature.setProperties(featureProperties);
        polyline.setTag(multiLineStringFeature);
        return polyline;
    }

    public void centerViewOnFeatures(List<LatLng> coordinates) {
        if (coordinates.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (LatLng coordinate : coordinates) {
                builder.include(coordinate);
            }
            LatLngBounds bounds = builder.build();
            int padding = mapParentView.getDimension(R.dimen.dimen_xxxxlarge); // offset from edges of the map in pixels
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            googleMap.moveCamera(cameraUpdate);
        }
    }

    public void clearTrackedLocations() {
        if (trackedLocationsLines != null) {
            for (Polyline polyline : trackedLocationsLines) {
                polyline.remove();
            }
        }
        trackedLocationsLines = new ArrayList<>();

        if (trackedLocationsPolygon != null) {
            trackedLocationsPolygon.remove();
            trackedLocationsPolygon = null;
        }
    }

    public boolean hasTrackedPoints() {
        boolean hasLine = false;
        if (trackedLocationsLines != null && trackedLocationsLines.size() > 0) {
            int totalPoints = 0;
            for (Polyline polyline : trackedLocationsLines) {
                totalPoints = totalPoints + polyline.getPoints().size();
            }
            hasLine = totalPoints > 1;
        }
        return hasLine || (trackedLocationsPolygon != null);
    }

    public void addMonitorAndMaintenanceCommentPointMarker(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        monitorAndMaintenanceCommentPoint.setId(monitorAndMaintenanceCommentPoint.getCleanedId());
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(monitorAndMaintenanceCommentPoint.getLatLngPosition())
                .title(monitorAndMaintenanceCommentPoint.getComment())
                .icon(vectorToBitmap(R.drawable.ic_camera_photo, ContextCompat.getColor(mapParentView.getContext(), R.color.white))));

        marker.setTag(monitorAndMaintenanceCommentPoint);
        markers.add(marker);
    }

    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(mapParentView.getContext().getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void drawCroquis(List<Croquis> croquisList) {
        for (Croquis croquis : croquisList) {
            List<List<LatLng>> coordinates = croquis.getGeometryList();
            if (coordinates != null) {
                for (List<LatLng> points : coordinates) {
                    PolygonOptions polygonOptions = new PolygonOptions();
                    polygonOptions.addAll(points);
                    polygonOptions.strokeColor(Color.parseColor("#87D7E0"));
                    Polygon polygon = googleMap.addPolygon(polygonOptions);
                    polygon.setGeodesic(true);
                    croquisPolyongs.add(polygon);
                }
            }
        }
    }

    public void drawBoundingPolygon(Location location) {
        double offset = location.getLatitude() * 0.0015;
        double maxLat = location.getLatitude() - offset;
        double minLat = location.getLatitude() + offset;
        double maxLng = location.getLongitude() - offset;
        double minLng = location.getLongitude() + offset;

        LatLng point1 = new LatLng(maxLat, maxLng);
        LatLng point2 = new LatLng(minLat, minLng);

        List<LatLng> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(points);
        Polyline polyline = googleMap.addPolyline(polylineOptions);
        polyline.setGeodesic(true);
        polyline.setClickable(false);
        lines.add(polyline);
    }

    public void addPrediosManagementLayer(GeoJson prediosManagementLayer, SerializationManager serializationManager) {
        clearManagementLayer();
        for (Object object : prediosManagementLayer.features) {
            String featureString = serializationManager.toJson(object);
            GenericFeature feature = (GenericFeature) serializationManager.fromJson(featureString, GenericFeature.class);
            if (feature != null) {
                switch (feature.geometry.getType()) {
                    case GeoJson.MULTI_LINE_STRING:
                        featureString = serializationManager.toJson(object);
                        MultiLineStringFeature feature1 = (MultiLineStringFeature) serializationManager.fromJson(featureString, MultiLineStringFeature.class);
                        feature1.setManagementLayerColors();
                        addManagementLayerMultiLineFeatureToMap(feature1, false);
                        break;
                    case GeoJson.POINT:
                        featureString = serializationManager.toJson(object);
                        PointFeature feature2 = (PointFeature) serializationManager.fromJson(featureString, PointFeature.class);
                        feature2.setManagementLayerColors();
                        addManagementLayerPointFeatureToMap(feature2, false);
                        break;
                    case GeoJson.POLYGON:
                        featureString = serializationManager.toJson(object);
                        PolygonFeature feature3 = (PolygonFeature) serializationManager.fromJson(featureString, PolygonFeature.class);
                        feature3.setManagementLayerColors();
                        addManagementLayerPolygonFeatureToMap(feature3, false);
                        break;
                    case GeoJson.MULTI_POLYGON:
                        featureString = serializationManager.toJson(object);
                        MultiPolygonFeature feature4 = (MultiPolygonFeature) serializationManager.fromJson(featureString, MultiPolygonFeature.class);
                        feature4.setManagementLayerColors();
                        addManagementLayerMultiPolygonFeatureToMap(feature4, false);
                        break;
                }
            }
        }
    }
}
