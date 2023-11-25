package com.orquitech.development.cuencaVerde.data.model.meetingsWithActors;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;

public class MeetingsWithActorsForm extends BaseItem implements Parcelable {

    private boolean laFe;
    private boolean rioGrande;
    private String municipality;
    private String lane;
    private boolean kids;
    private boolean studentKids;
    private boolean teenagers;
    private boolean adults;
    private boolean studentAdults;
    private boolean seniors;
    private AppDate eventDate;
    private String contributor;
    private String assistants;
    private String trees;
    private String experienceType;
    private boolean experience;
    private boolean workshop;
    private boolean sowing;
    private boolean training;
    private boolean socialization;
    private String eventDescription;
    private boolean assistanceList;
    private boolean canBeSaved;
    private String taskId;

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public boolean isLaFe() {
        return laFe;
    }

    public void setLaFe(boolean laFe) {
        this.laFe = laFe;
    }

    public boolean isRioGrande() {
        return rioGrande;
    }

    public void setRioGrande(boolean rioGrande) {
        this.rioGrande = rioGrande;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public boolean isKids() {
        return kids;
    }

    public void setKids(boolean kids) {
        this.kids = kids;
    }

    public boolean isStudentKids() {
        return studentKids;
    }

    public void setStudentKids(boolean studentKids) {
        this.studentKids = studentKids;
    }

    public boolean isTeenagers() {
        return teenagers;
    }

    public void setTeenagers(boolean teenagers) {
        this.teenagers = teenagers;
    }

    public boolean isAdults() {
        return adults;
    }

    public void setAdults(boolean adults) {
        this.adults = adults;
    }

    public boolean isStudentAdults() {
        return studentAdults;
    }

    public void setStudentAdults(boolean studentAdults) {
        this.studentAdults = studentAdults;
    }

    public boolean isSeniors() {
        return seniors;
    }

    public void setSeniors(boolean seniors) {
        this.seniors = seniors;
    }

    public AppDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(AppDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getAssistants() {
        return assistants;
    }

    public void setAssistants(String assistants) {
        this.assistants = assistants;
    }

    public String getTrees() {
        return trees;
    }

    public void setTrees(String trees) {
        this.trees = trees;
    }

    public String getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(String experienceType) {
        this.experienceType = experienceType;
    }

    public boolean isSocialization() {
        return socialization;
    }

    public void setSocialization(boolean socialization) {
        this.socialization = socialization;
    }

    public boolean isExperience() {
        return experience;
    }

    public void setExperience(boolean experience) {
        this.experience = experience;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public boolean isWorkshop() {
        return workshop;
    }

    public void setWorkshop(boolean workshop) {
        this.workshop = workshop;
    }

    public boolean isSowing() {
        return sowing;
    }

    public void setSowing(boolean sowing) {
        this.sowing = sowing;
    }

    public boolean isTraining() {
        return training;
    }

    public void setTraining(boolean training) {
        this.training = training;
    }

    public boolean isAssistanceList() {
        return assistanceList;
    }

    public void setAssistanceList(boolean assistanceList) {
        this.assistanceList = assistanceList;
    }

    public boolean canBeSaved() {
        return canBeSaved;
    }

    public void setAsCanBeSaved(boolean canBeSaved) {
        this.canBeSaved = canBeSaved;
    }

    public boolean isValid() {
        return true;
    }

    public String getBasin() {
        return laFe ? "La Fe" : "Rio Grande";
    }

    public String getExperienceConsolidated() {
        return socialization ? "Socialización" : "Experiencia";
    }

    public String getObjectiveGroup() {
        String result = "";
        if (kids) {
            result = result + "Niños ";
        }
        if (studentKids) {
            result = result + "Estudiantes-niños ";
        }
        if (teenagers) {
            result = result + "Jovenes ";
        }
        if (adults) {
            result = result + "Adultos ";
        }
        if (studentAdults) {
            result = result + "Adultos-estudiantes ";
        }
        if (seniors) {
            result = result + "Tercera Edad ";
        }
        return result;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public MeetingsWithActorsForm() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByte(this.laFe ? (byte) 1 : (byte) 0);
        dest.writeByte(this.rioGrande ? (byte) 1 : (byte) 0);
        dest.writeString(this.municipality);
        dest.writeString(this.lane);
        dest.writeByte(this.kids ? (byte) 1 : (byte) 0);
        dest.writeByte(this.studentKids ? (byte) 1 : (byte) 0);
        dest.writeByte(this.teenagers ? (byte) 1 : (byte) 0);
        dest.writeByte(this.adults ? (byte) 1 : (byte) 0);
        dest.writeByte(this.studentAdults ? (byte) 1 : (byte) 0);
        dest.writeByte(this.seniors ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.eventDate, flags);
        dest.writeString(this.contributor);
        dest.writeString(this.assistants);
        dest.writeString(this.trees);
        dest.writeString(this.experienceType);
        dest.writeByte(this.experience ? (byte) 1 : (byte) 0);
        dest.writeByte(this.workshop ? (byte) 1 : (byte) 0);
        dest.writeByte(this.sowing ? (byte) 1 : (byte) 0);
        dest.writeByte(this.training ? (byte) 1 : (byte) 0);
        dest.writeByte(this.socialization ? (byte) 1 : (byte) 0);
        dest.writeString(this.eventDescription);
        dest.writeByte(this.assistanceList ? (byte) 1 : (byte) 0);
        dest.writeByte(this.canBeSaved ? (byte) 1 : (byte) 0);
        dest.writeString(this.taskId);
    }

    protected MeetingsWithActorsForm(Parcel in) {
        this.id = in.readString();
        this.laFe = in.readByte() != 0;
        this.rioGrande = in.readByte() != 0;
        this.municipality = in.readString();
        this.lane = in.readString();
        this.kids = in.readByte() != 0;
        this.studentKids = in.readByte() != 0;
        this.teenagers = in.readByte() != 0;
        this.adults = in.readByte() != 0;
        this.studentAdults = in.readByte() != 0;
        this.seniors = in.readByte() != 0;
        this.eventDate = in.readParcelable(AppDate.class.getClassLoader());
        this.contributor = in.readString();
        this.assistants = in.readString();
        this.trees = in.readString();
        this.experienceType = in.readString();
        this.experience = in.readByte() != 0;
        this.workshop = in.readByte() != 0;
        this.sowing = in.readByte() != 0;
        this.training = in.readByte() != 0;
        this.socialization = in.readByte() != 0;
        this.eventDescription = in.readString();
        this.assistanceList = in.readByte() != 0;
        this.canBeSaved = in.readByte() != 0;
        this.taskId = in.readString();
    }

    public static final Creator<MeetingsWithActorsForm> CREATOR = new Creator<MeetingsWithActorsForm>() {
        @Override
        public MeetingsWithActorsForm createFromParcel(Parcel source) {
            return new MeetingsWithActorsForm(source);
        }

        @Override
        public MeetingsWithActorsForm[] newArray(int size) {
            return new MeetingsWithActorsForm[size];
        }
    };
}
