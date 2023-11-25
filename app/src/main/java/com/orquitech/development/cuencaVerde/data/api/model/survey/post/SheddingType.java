
package com.orquitech.development.cuencaVerde.data.api.model.survey.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SheddingType {

    @SerializedName("distributed")
    @Expose
    private boolean distributed;
    @SerializedName("gathered")
    @Expose
    private boolean gathered;

    public boolean isDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    public boolean isGathered() {
        return gathered;
    }

    public void setGathered(boolean gathered) {
        this.gathered = gathered;
    }

}
