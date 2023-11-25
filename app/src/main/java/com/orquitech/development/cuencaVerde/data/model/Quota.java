package com.orquitech.development.cuencaVerde.data.model;

public class Quota {

    private int quota;
    private int predios;
    private double percentage;

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getPredios() {
        return predios;
    }

    public void setPredios(int predios) {
        this.predios = predios;
    }

    public float getQuotaValue() {
        return ((float) predios) / ((float) quota);
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
