package com.orquitech.development.cuencaVerde.data.model.siembraDetailForm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.presentation.factories.AppViewsFactory;

public class SiembraDetailForm extends BaseItem implements Parcelable {

    private boolean pendingToBeSent;
    private boolean establishment;
    private boolean enrichment;
    private boolean insulation;
    private ActivityOptions establishmentOptions;
    private ActivityOptions enrichmentOptions;
    private ActivityOptions insulationOptions;
    private boolean livingFence;
    private boolean sparseTrees;
    private boolean lumberyard;
    private String scientificName;
    private String commonName;
    private String quantity;
    private AppDate sowingDate;
    private Feature feature;
    private String comments;
    private String taskId;

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(getScientificName()) && !TextUtils.isEmpty(getCommonName()) && !TextUtils.isEmpty(getQuantity());
    }

    public void initNullFields() {
        if (this.establishmentOptions == null) {
            this.establishmentOptions = new ActivityOptions();
        }
        if (this.enrichmentOptions == null) {
            this.enrichmentOptions = new ActivityOptions();
        }
        if (this.insulationOptions == null) {
            this.insulationOptions = new ActivityOptions();
        }
    }

    @Override
    public int getType() {
        return AppViewsFactory.SIEMBRA_DETAIL_LIST_ITEM_VIEW;
    }

    public SiembraDetailForm() {
        initNullFields();
    }

    public boolean getEstablishment() {
        return establishment;
    }

    public void setEstablishment(boolean establishment) {
        this.establishment = establishment;
    }

    public void setPendingToBeSent(boolean pendingToBeSent) {
        this.pendingToBeSent = pendingToBeSent;
    }

    public boolean isEstablishment() {
        return establishment;
    }

    public boolean isEnrichment() {
        return enrichment;
    }

    public void setEnrichment(boolean enrichment) {
        this.enrichment = enrichment;
    }

    public boolean isInsulation() {
        return insulation;
    }

    public void setInsulation(boolean insulation) {
        this.insulation = insulation;
    }

    public boolean isLivingFence() {
        return livingFence;
    }

    public void setLivingFence(boolean livingFence) {
        this.livingFence = livingFence;
    }

    public boolean isSparseTrees() {
        return sparseTrees;
    }

    public void setSparseTrees(boolean sparseTrees) {
        this.sparseTrees = sparseTrees;
    }

    public boolean isLumberyard() {
        return lumberyard;
    }

    public void setLumberyard(boolean lumberyard) {
        this.lumberyard = lumberyard;
    }

    public ActivityOptions getEstablishmentOptions() {
        return establishmentOptions;
    }

    public void setEstablishmentOptions(ActivityOptions establishmentOptions) {
        this.establishmentOptions = establishmentOptions;
    }

    public ActivityOptions getEnrichmentOptions() {
        return enrichmentOptions;
    }

    public void setEnrichmentOptions(ActivityOptions enrichmentOptions) {
        this.enrichmentOptions = enrichmentOptions;
    }

    public ActivityOptions getInsulationOptions() {
        return insulationOptions;
    }

    public void setInsulationOptions(ActivityOptions insulationOptions) {
        this.insulationOptions = insulationOptions;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public AppDate getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(AppDate sowingDate) {
        this.sowingDate = sowingDate;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getActivityName() {
        String name1 = "";
        String name2 = "";
        if (establishment) {
            name1 = "Establecimiento";
            name2 = getName2(establishmentOptions);
        } else if (enrichment) {
            name1 = "Enriquecimiento";
            name2 = getName2(enrichmentOptions);
        } else if (insulation) {
            name1 = "Aislamiento";
            name2 = getName2(insulationOptions);
        }
        return name1 + " de " + name2;
    }

    private String getName2(ActivityOptions activityOptions) {
        String name2 = "";
        if (activityOptions.isHillside()) {
            name2 = "Ladera";
        } else if (activityOptions.isRiverbank()) {
            name2 = "Ribera";
        } else if (activityOptions.isSpring()) {
            name2 = "Nacimiento";
        }
        return name2;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
        dest.writeByte(this.establishment ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enrichment ? (byte) 1 : (byte) 0);
        dest.writeByte(this.insulation ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.establishmentOptions, flags);
        dest.writeParcelable(this.enrichmentOptions, flags);
        dest.writeParcelable(this.insulationOptions, flags);
        dest.writeByte(this.livingFence ? (byte) 1 : (byte) 0);
        dest.writeByte(this.sparseTrees ? (byte) 1 : (byte) 0);
        dest.writeByte(this.lumberyard ? (byte) 1 : (byte) 0);
        dest.writeString(this.scientificName);
        dest.writeString(this.commonName);
        dest.writeString(this.quantity);
        dest.writeParcelable(this.sowingDate, flags);
        dest.writeParcelable(this.feature, flags);
        dest.writeString(this.comments);
        dest.writeString(this.taskId);
    }

    protected SiembraDetailForm(Parcel in) {
        this.id = in.readString();
        this.pendingToBeSent = in.readByte() != 0;
        this.establishment = in.readByte() != 0;
        this.enrichment = in.readByte() != 0;
        this.insulation = in.readByte() != 0;
        this.establishmentOptions = in.readParcelable(ActivityOptions.class.getClassLoader());
        this.enrichmentOptions = in.readParcelable(ActivityOptions.class.getClassLoader());
        this.insulationOptions = in.readParcelable(ActivityOptions.class.getClassLoader());
        this.livingFence = in.readByte() != 0;
        this.sparseTrees = in.readByte() != 0;
        this.lumberyard = in.readByte() != 0;
        this.scientificName = in.readString();
        this.commonName = in.readString();
        this.quantity = in.readString();
        this.sowingDate = in.readParcelable(AppDate.class.getClassLoader());
        this.feature = in.readParcelable(Feature.class.getClassLoader());
        this.comments = in.readString();
        this.taskId = in.readString();
    }

    public static final Creator<SiembraDetailForm> CREATOR = new Creator<SiembraDetailForm>() {
        @Override
        public SiembraDetailForm createFromParcel(Parcel source) {
            return new SiembraDetailForm(source);
        }

        @Override
        public SiembraDetailForm[] newArray(int size) {
            return new SiembraDetailForm[size];
        }
    };
}
