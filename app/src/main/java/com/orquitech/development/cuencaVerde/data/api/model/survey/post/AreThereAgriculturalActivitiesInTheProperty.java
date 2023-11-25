
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AreThereAgriculturalActivitiesInTheProperty {

    @SerializedName("chemical_fertilizers")
    @Expose
    private boolean chemicalFertilizers;
    @SerializedName("fertilizers_brands")
    @Expose
    private String fertilizersBrands;
    @SerializedName("fertilizers_description")
    @Expose
    private String fertilizersDescription;
    @SerializedName("organic_fertilizers")
    @Expose
    private boolean organicFertilizers;
    @SerializedName("pesticides")
    @Expose
    private boolean pesticides;

    public boolean isChemicalFertilizers() {
        return chemicalFertilizers;
    }

    public void setChemicalFertilizers(boolean chemicalFertilizers) {
        this.chemicalFertilizers = chemicalFertilizers;
    }

    public String getFertilizersBrands() {
        return fertilizersBrands;
    }

    public void setFertilizersBrands(String fertilizersBrands) {
        this.fertilizersBrands = fertilizersBrands;
    }

    public String getFertilizersDescription() {
        return fertilizersDescription;
    }

    public void setFertilizersDescription(String fertilizersDescription) {
        this.fertilizersDescription = fertilizersDescription;
    }

    public boolean isOrganicFertilizers() {
        return organicFertilizers;
    }

    public void setOrganicFertilizers(boolean organicFertilizers) {
        this.organicFertilizers = organicFertilizers;
    }

    public boolean isPesticides() {
        return pesticides;
    }

    public void setPesticides(boolean pesticides) {
        this.pesticides = pesticides;
    }

}
