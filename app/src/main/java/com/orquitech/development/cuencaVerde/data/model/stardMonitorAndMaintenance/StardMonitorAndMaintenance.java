package com.orquitech.development.cuencaVerde.data.model.stardMonitorAndMaintenance;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.survey.Contact;
import com.orquitech.development.cuencaVerde.data.model.survey.WaysOfAccess;

public class StardMonitorAndMaintenance implements Parcelable {

    private String id;
    private AppDate requestDate;
    private String propertyName;
    private String municipality;
    private String lane;
    private Contact contact;
    private String latitude;
    private String longitude;
    private WaysOfAccess waysOfAccess;
    private TravelTimeFromMunicipality travelTimeFromMunicipality;
    private TimeSinceLastMonitorMaintenance timeSinceLastMonitorMaintenance;
    private StardType stardType;
    private String distanceToStard;
    private String distanceToGreaseTrap;
    private StardTankCapacity stardTankCapacity;
    private StardDischargeType stardDischargeType;
    private MaintenanceType maintenanceType;
    private String comments;
    private boolean availabilityAndAcceptanceForMudUnloadInTheProperty;
    private String monitorAndMaintenanceId;
    private boolean pendingToBeSent;

    public String getId() {
        if (TextUtils.isEmpty(id)) {
            id = "-1";
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(AppDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public WaysOfAccess getWaysOfAccess() {
        return waysOfAccess;
    }

    public void setWaysOfAccess(WaysOfAccess waysOfAccess) {
        this.waysOfAccess = waysOfAccess;
    }

    public TravelTimeFromMunicipality getTravelTimeFromMunicipality() {
        return travelTimeFromMunicipality;
    }

    public void setTravelTimeFromMunicipality(TravelTimeFromMunicipality travelTimeFromMunicipality) {
        this.travelTimeFromMunicipality = travelTimeFromMunicipality;
    }

    public TimeSinceLastMonitorMaintenance getTimeSinceLastMonitorMaintenance() {
        return timeSinceLastMonitorMaintenance;
    }

    public void setTimeSinceLastMonitorMaintenance(TimeSinceLastMonitorMaintenance timeSinceLastMonitorMaintenance) {
        this.timeSinceLastMonitorMaintenance = timeSinceLastMonitorMaintenance;
    }

    public String getDistanceToStard() {
        return distanceToStard;
    }

    public void setDistanceToStard(String distanceToStard) {
        this.distanceToStard = distanceToStard;
    }

    public String getDistanceToGreaseTrap() {
        return distanceToGreaseTrap;
    }

    public void setDistanceToGreaseTrap(String distanceToGreaseTrap) {
        this.distanceToGreaseTrap = distanceToGreaseTrap;
    }

    public StardTankCapacity getStardTankCapacity() {
        return stardTankCapacity;
    }

    public void setStardTankCapacity(StardTankCapacity stardTankCapacity) {
        this.stardTankCapacity = stardTankCapacity;
    }

    public StardDischargeType getStardDischargeType() {
        return stardDischargeType;
    }

    public void setStardDischargeType(StardDischargeType stardDischargeType) {
        this.stardDischargeType = stardDischargeType;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isAvailabilityAndAcceptanceForMudUnloadInTheProperty() {
        return availabilityAndAcceptanceForMudUnloadInTheProperty;
    }

    public void setAvailabilityAndAcceptanceForMudUnloadInTheProperty(boolean availabilityAndAcceptanceForMudUnloadInTheProperty) {
        this.availabilityAndAcceptanceForMudUnloadInTheProperty = availabilityAndAcceptanceForMudUnloadInTheProperty;
    }

    public boolean hasValidCoordinates() {
        return !TextUtils.isEmpty(latitude) && !TextUtils.isEmpty(longitude) && Double.valueOf(latitude) != 0 && Double.valueOf(longitude) != 0;
    }

    public boolean hasValidName() {
        return !TextUtils.isEmpty(getPropertyName());
    }

    public boolean isNew() {
        return Integer.parseInt(getId()) <= 0;
    }

    public String getMonitorAndMaintenanceId() {
        return monitorAndMaintenanceId;
    }

    public void setMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        this.monitorAndMaintenanceId = monitorAndMaintenanceId;
    }

    public StardType getStardType() {
        return stardType;
    }

    public void setStardType(StardType stardType) {
        this.stardType = stardType;
    }

    public void initNullFields() {
        if (contact == null) {
            setContact(new Contact());
        }
        if (waysOfAccess == null) {
            setWaysOfAccess(new WaysOfAccess());
        }
        if (travelTimeFromMunicipality == null) {
            setTravelTimeFromMunicipality(new TravelTimeFromMunicipality());
        }
        if (timeSinceLastMonitorMaintenance == null) {
            setTimeSinceLastMonitorMaintenance(new TimeSinceLastMonitorMaintenance());
        }
        if (stardType == null) {
            setStardType(new StardType());
        }
        if (stardTankCapacity == null) {
            setStardTankCapacity(new StardTankCapacity());
        }
        if (stardDischargeType == null) {
            setStardDischargeType(new StardDischargeType());
        }
        if (maintenanceType == null) {
            setMaintenanceType(new MaintenanceType());
        }
    }

    public void setLocation(Location location) {
        this.latitude = String.valueOf(location.getLatitude());
        this.longitude = String.valueOf(location.getLongitude());
    }

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public StardMonitorAndMaintenance() {
        setContact(new Contact());
        setWaysOfAccess(new WaysOfAccess());
        setTravelTimeFromMunicipality(new TravelTimeFromMunicipality());
        setTimeSinceLastMonitorMaintenance(new TimeSinceLastMonitorMaintenance());
        setStardType(new StardType());
        setStardTankCapacity(new StardTankCapacity());
        setStardDischargeType(new StardDischargeType());
        setMaintenanceType(new MaintenanceType());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.requestDate, flags);
        dest.writeString(this.propertyName);
        dest.writeString(this.municipality);
        dest.writeString(this.lane);
        dest.writeParcelable(this.contact, flags);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeParcelable(this.waysOfAccess, flags);
        dest.writeParcelable(this.travelTimeFromMunicipality, flags);
        dest.writeParcelable(this.timeSinceLastMonitorMaintenance, flags);
        dest.writeParcelable(this.stardType, flags);
        dest.writeString(this.distanceToStard);
        dest.writeString(this.distanceToGreaseTrap);
        dest.writeParcelable(this.stardTankCapacity, flags);
        dest.writeParcelable(this.stardDischargeType, flags);
        dest.writeParcelable(this.maintenanceType, flags);
        dest.writeString(this.comments);
        dest.writeByte(this.availabilityAndAcceptanceForMudUnloadInTheProperty ? (byte) 1 : (byte) 0);
        dest.writeString(this.monitorAndMaintenanceId);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
    }

    protected StardMonitorAndMaintenance(Parcel in) {
        this.id = in.readString();
        this.requestDate = in.readParcelable(AppDate.class.getClassLoader());
        this.propertyName = in.readString();
        this.municipality = in.readString();
        this.lane = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.waysOfAccess = in.readParcelable(WaysOfAccess.class.getClassLoader());
        this.travelTimeFromMunicipality = in.readParcelable(TravelTimeFromMunicipality.class.getClassLoader());
        this.timeSinceLastMonitorMaintenance = in.readParcelable(TimeSinceLastMonitorMaintenance.class.getClassLoader());
        this.stardType = in.readParcelable(StardType.class.getClassLoader());
        this.distanceToStard = in.readString();
        this.distanceToGreaseTrap = in.readString();
        this.stardTankCapacity = in.readParcelable(StardTankCapacity.class.getClassLoader());
        this.stardDischargeType = in.readParcelable(StardDischargeType.class.getClassLoader());
        this.maintenanceType = in.readParcelable(MaintenanceType.class.getClassLoader());
        this.comments = in.readString();
        this.availabilityAndAcceptanceForMudUnloadInTheProperty = in.readByte() != 0;
        this.monitorAndMaintenanceId = in.readString();
        this.pendingToBeSent = in.readByte() != 0;
    }

    public static final Creator<StardMonitorAndMaintenance> CREATOR = new Creator<StardMonitorAndMaintenance>() {
        @Override
        public StardMonitorAndMaintenance createFromParcel(Parcel source) {
            return new StardMonitorAndMaintenance(source);
        }

        @Override
        public StardMonitorAndMaintenance[] newArray(int size) {
            return new StardMonitorAndMaintenance[size];
        }
    };
}
