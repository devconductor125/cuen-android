package com.orquitech.development.cuencaVerde.data.model.geoJson;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.api.model.CuencaVerdeResponse;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineString;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygon;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonFeature;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.Polygon;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonFeature;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

public class GeoJson extends CuencaVerdeResponse implements Parcelable {

    public static final Creator<GeoJson> CREATOR = new Creator<GeoJson>() {
        @Override
        public GeoJson createFromParcel(Parcel source) {
            return new GeoJson(source);
        }

        @Override
        public GeoJson[] newArray(int size) {
            return new GeoJson[size];
        }
    };

    public static final String POLYGON = "Polygon";
    public static final String MULTI_POLYGON = "MultiPolygon";
    public static final String MULTI_LINE_STRING = "MultiLineString";
    public static final String POINT = "Point";

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("crs")
    @Expose
    public Crs crs;
    @SerializedName("features")
    @Expose
    public List<Object> features;

    private List<PolygonFeature> polygonFeatures;
    private List<MultiPolygonFeature> multiPolygonFeatures;
    private List<MultiLineStringFeature> multiLineStringFeatures;
    private List<PointFeature> pointFeatures;
    private Feature lastAddedFeature;

    public GeoJson() {
        type = "FeatureCollection";
        polygonFeatures = new ArrayList<>();
        multiPolygonFeatures = new ArrayList<>();
        multiLineStringFeatures = new ArrayList<>();
        pointFeatures = new ArrayList<>();
    }

    public GeoJson(Parcel in) {
        this.type = in.readString();
        this.crs = in.readParcelable(Crs.class.getClassLoader());
        this.polygonFeatures = new ArrayList<>();
        in.readTypedList(this.polygonFeatures, PolygonFeature.CREATOR);
        this.multiPolygonFeatures = new ArrayList<>();
        in.readTypedList(this.multiPolygonFeatures, MultiPolygonFeature.CREATOR);
        this.multiLineStringFeatures = new ArrayList<>();
        in.readTypedList(this.multiLineStringFeatures, MultiLineStringFeature.CREATOR);
        this.pointFeatures = new ArrayList<>();
        in.readTypedList(this.pointFeatures, PointFeature.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.type);
        out.writeParcelable(this.crs, flags);
        out.writeTypedList(this.polygonFeatures);
        out.writeTypedList(this.multiPolygonFeatures);
        out.writeTypedList(this.multiLineStringFeatures);
        out.writeTypedList(this.pointFeatures);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Crs getCrs() {
        return crs;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }

    public List<MultiLineStringFeature> getMultiLineStringFeatures() {
        return multiLineStringFeatures;
    }

    public List<PolygonFeature> getPolygonFeatures() {
        return polygonFeatures;
    }

    public List<MultiPolygonFeature> getMultiPolygonFeatures() {
        return multiPolygonFeatures;
    }

    public List<PointFeature> getPointFeatures() {
        return pointFeatures;
    }

    public void addMultiLineStringFeature(MultiLineStringFeature feature) {
        this.multiLineStringFeatures.add(feature);
    }

    public void addPolygonFeature(PolygonFeature feature) {
        this.polygonFeatures.add(feature);
    }

    public void addMultiPolygonFeature(MultiPolygonFeature feature) {
        this.multiPolygonFeatures.add(feature);
    }

    public void addPointFeature(PointFeature feature) {
        this.pointFeatures.add(feature);
    }

    public void addPolygonFeature(Action action, List<List<Location>> coordinates, String hash) {
        PolygonFeature feature = new PolygonFeature();

        FeatureProperties featureProperties = new FeatureProperties();

        featureProperties.setAccionId(action.getId());
        featureProperties.setAccionTitle(action.getTitle());

        featureProperties.setMaterialId(action.getMaterial().getId());
        featureProperties.setMaterialTitle(action.getMaterial().getTitle());
        featureProperties.setFeatureType(POLYGON);
        featureProperties.setColor(action.getColor());
        featureProperties.setFillColor(action.getFillColor());
        featureProperties.setAccionHash(hash);

        List<LatLng> areaCoordinates = new ArrayList<>();
        for (List<Location> locations : coordinates) {
            for (Location location : locations) {
                areaCoordinates.add(LocationUtils.locationToLatLng(location));
            }
        }

        double area = SphericalUtil.computeArea(areaCoordinates);
        featureProperties.setShapeArea(area);

        feature.setProperties(featureProperties);

        Polygon polygon = new Polygon();
        polygon.setType(POLYGON);

        List<List<Location>> tempCoordinates = new ArrayList<>();
        for (List<Location> segmentCoordinates : coordinates) {
            if (segmentCoordinates.size() > 1) {
                tempCoordinates.add(segmentCoordinates);
            }
        }

        polygon.setCoordinates(tempCoordinates);
        feature.setGeometry(polygon);

        if (tempCoordinates.size() > 0) {
            polygonFeatures.add(feature);
            lastAddedFeature = feature;
        }
    }

    public void addMultiPolygonFeature(Action action, List<List<Location>> coordinates, String hash) {
        MultiPolygonFeature feature = new MultiPolygonFeature();

        FeatureProperties featureProperties = new FeatureProperties();

        featureProperties.setAccionId(action.getId());
        featureProperties.setAccionTitle(action.getTitle());

        featureProperties.setMaterialId(action.getMaterial().getId());
        featureProperties.setMaterialTitle(action.getMaterial().getTitle());
        featureProperties.setFeatureType(MULTI_POLYGON);
        featureProperties.setColor(action.getColor());
        featureProperties.setAccionHash(hash);

        List<LatLng> lengthCoordinates = new ArrayList<>();
        for (List<Location> locations : coordinates) {
            for (Location location : locations) {
                lengthCoordinates.add(LocationUtils.locationToLatLng(location));
            }
        }

        double length = SphericalUtil.computeLength(lengthCoordinates);
        featureProperties.setShapeLength(length);

        feature.setProperties(featureProperties);

        MultiPolygon multiPolygon = new MultiPolygon();
        multiPolygon.setType(MULTI_POLYGON);

        List<List<Location>> tempCoordinates = new ArrayList<>();
        for (List<Location> segmentCoordinates : coordinates) {
            if (segmentCoordinates.size() > 1) {
                tempCoordinates.add(segmentCoordinates);
            }
        }

        multiPolygon.setCoordinates(tempCoordinates);
        feature.setGeometry(multiPolygon);

        if (tempCoordinates.size() > 0) {
            multiPolygonFeatures.add(feature);
            lastAddedFeature = feature;
        }
    }

    public void addMultiLineString(Action action, List<List<Location>> coordinates, String hash) {
        MultiLineStringFeature feature = new MultiLineStringFeature();

        FeatureProperties featureProperties = new FeatureProperties();

        featureProperties.setAccionId(action.getId());
        featureProperties.setAccionTitle(action.getTitle());

        featureProperties.setMaterialId(action.getMaterial().getId());
        featureProperties.setMaterialTitle(action.getMaterial().getTitle());
        featureProperties.setFeatureType(MULTI_LINE_STRING);
        featureProperties.setColor(action.getColor());
        featureProperties.setAccionHash(hash);

        List<LatLng> lengthCoordinates = new ArrayList<>();
        for (List<Location> locations : coordinates) {
            for (Location location : locations) {
                lengthCoordinates.add(LocationUtils.locationToLatLng(location));
            }
        }

        double length = SphericalUtil.computeLength(lengthCoordinates);
        featureProperties.setShapeLength(length);

        feature.setProperties(featureProperties);

        MultiLineString multiLineString = new MultiLineString();
        multiLineString.setType(MULTI_LINE_STRING);

        List<List<Location>> tempCoordinates = new ArrayList<>();
        for (List<Location> segmentCoordinates : coordinates) {
            if (segmentCoordinates.size() > 1) {
                tempCoordinates.add(segmentCoordinates);
            }
        }

        multiLineString.setCoordinates(tempCoordinates);
        feature.setGeometry(multiLineString);

        if (tempCoordinates.size() > 0) {
            multiLineStringFeatures.add(feature);
            lastAddedFeature = feature;
        }
    }

    public void addPoint(Action action, LatLng coordinate, String hash) {
        PointFeature feature = new PointFeature();

        FeatureProperties featureProperties = new FeatureProperties();

        featureProperties.setAccionId(action.getId());
        featureProperties.setAccionTitle(action.getTitle());

        if (action.getMaterial() != null) {
            featureProperties.setMaterialId(action.getMaterial().getId());
            featureProperties.setMaterialTitle(action.getMaterial().getTitle());
        }
        featureProperties.setFeatureType(POINT);
        featureProperties.setColor(action.getColor());
        featureProperties.setAccionHash(hash);

        if (action.isTree()) {
            featureProperties.setAsTree();
        }
        if (action.isSamplingPoint()) {
            featureProperties.setSamplingPoint(true);
        }

        feature.setProperties(featureProperties);

        Point point = new Point();
        point.setType(POINT);

        List<Double> coordinatesList = new ArrayList<>();
        coordinatesList.add(coordinate.longitude);
        coordinatesList.add(coordinate.latitude);

        point.setCoordinates(coordinatesList);
        feature.setGeometry(point);

        pointFeatures.add(feature);
        lastAddedFeature = feature;
    }

    public Parcelable getLastAddedFeature() {
        return lastAddedFeature;
    }

    public void mergeFeatures() {
        features = new ArrayList<>();
        features.addAll(multiPolygonFeatures);
        features.addAll(polygonFeatures);
        features.addAll(multiLineStringFeatures);
        features.addAll(pointFeatures);
    }

    public boolean hasValidFeatures() {
        return multiPolygonFeatures.size() > 0 || polygonFeatures.size() > 0 || multiLineStringFeatures.size() > 0 || pointFeatures.size() > 0;
    }

    public boolean hasStard() {
        List<Feature> features = new ArrayList<>();
        features.addAll(polygonFeatures);
        features.addAll(multiPolygonFeatures);
        features.addAll(multiLineStringFeatures);
        features.addAll(pointFeatures);

        for (Feature feature : features) {
            if (Double.valueOf(feature.getProperties().getAccionId()) == 40) {
                return true;
            }
        }
        return false;
    }

    public void clearSpikes() {
        for (MultiLineStringFeature feature : multiLineStringFeatures) {
            feature.clearSpikes();
        }
        /*for (PolygonFeature feature : polygonFeatures) {
            feature.clearSpikes();
        }
        for (MultiPolygonFeature feature : multiPolygonFeatures) {
            feature.clearSpikes();
        }*/
    }
}
