package com.orquitech.development.cuencaVerde.data.model.survey.sheddingCharacteristics;

import android.os.Parcel;
import android.os.Parcelable;

public class AreThereAgriculturalActivitiesInTheProperty implements Parcelable {

    private boolean pesticides;
    private boolean chemicalFertilizers;
    private boolean organicFertilizers;
    private String fertilizersDescription;
    private String fertilizersBrands;

    public boolean isPesticides() {
        return pesticides;
    }

    public void setPesticides(boolean pesticides) {
        this.pesticides = pesticides;
    }

    public boolean isChemicalFertilizers() {
        return chemicalFertilizers;
    }

    public void setChemicalFertilizers(boolean chemicalFertilizers) {
        this.chemicalFertilizers = chemicalFertilizers;
    }

    public boolean isOrganicFertilizers() {
        return organicFertilizers;
    }

    public void setOrganicFertilizers(boolean organicFertilizers) {
        this.organicFertilizers = organicFertilizers;
    }

    public String getFertilizersDescription() {
        return fertilizersDescription;
    }

    public void setFertilizersDescription(String fertilizersDescription) {
        this.fertilizersDescription = fertilizersDescription;
    }

    public String getFertilizersBrands() {
        return fertilizersBrands;
    }

    public void setFertilizersBrands(String fertilizersBrands) {
        this.fertilizersBrands = fertilizersBrands;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.pesticides ? (byte) 1 : (byte) 0);
        dest.writeByte(this.chemicalFertilizers ? (byte) 1 : (byte) 0);
        dest.writeByte(this.organicFertilizers ? (byte) 1 : (byte) 0);
        dest.writeString(this.fertilizersDescription);
        dest.writeString(this.fertilizersBrands);
    }

    public AreThereAgriculturalActivitiesInTheProperty() {
    }

    protected AreThereAgriculturalActivitiesInTheProperty(Parcel in) {
        this.pesticides = in.readByte() != 0;
        this.chemicalFertilizers = in.readByte() != 0;
        this.organicFertilizers = in.readByte() != 0;
        this.fertilizersDescription = in.readString();
        this.fertilizersBrands = in.readString();
    }

    public static final Creator<AreThereAgriculturalActivitiesInTheProperty> CREATOR = new Creator<AreThereAgriculturalActivitiesInTheProperty>() {
        @Override
        public AreThereAgriculturalActivitiesInTheProperty createFromParcel(Parcel source) {
            return new AreThereAgriculturalActivitiesInTheProperty(source);
        }

        @Override
        public AreThereAgriculturalActivitiesInTheProperty[] newArray(int size) {
            return new AreThereAgriculturalActivitiesInTheProperty[size];
        }
    };
}
