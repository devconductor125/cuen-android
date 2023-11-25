package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartaIntencion extends BaseItem implements Parcelable {

    private int processId;
    private AppDate propertyVisitDate;
    private String title;
    private String formLetter;
    private int userId;
    private String createdAt;
    private String updatedAt;
    private int taskTypeId;
    private String taskTypeName;
    private Process process;
    private User user;
    private SubType subType;
    private String propertyCorrelation;
    private int propertyCorrelationId;
    private String propertyName;
    private String margins;
    private String municipality;
    private String lane;
    private String extension;
    private String registryNumber;
    private String embalse;
    private String program;
    private HillSideForest hillSideForest;
    private RiverBankForest riverBankForest;
    private OriginsForest originsForest;
    private DeforestationPrevention deforestationPrevention;
    private SustainableCattleRaisingAndGoodPractices sustainableCattleRaisingAndGoodPractices;
    private IntegralBasicSanitation integralBasicSanitation;
    private boolean pendingToBeSent;
    private boolean isValidObject;
    private String others;
    private String contactName;
    private String idCardNumber;
    private String email;
    private String mobileNumber;
    private int dueDateStatus;
    private boolean isNew;
    private int predioPotencialId = -999;
    private boolean hasBeenSent;
    private List<Map> programs;
    private String receiverEmail;

    public AppDate getPropertyVisitDate() {
        return propertyVisitDate;
    }

    public void setPropertyVisitDate(AppDate propertyVisitDate) {
        this.propertyVisitDate = propertyVisitDate;
    }

    public int getDueDateStatus() {
        return dueDateStatus;
    }

    public void setDueDateStatus(int dueDateStatus) {
        this.dueDateStatus = dueDateStatus;
    }


    public RiverBankForest getRiverBankForest() {
        return riverBankForest;
    }

    public void setRiverBankForest(RiverBankForest riverBankForest) {
        this.riverBankForest = riverBankForest;
    }

    public OriginsForest getOriginsForest() {
        return originsForest;
    }

    public void setOriginsForest(OriginsForest originsForest) {
        this.originsForest = originsForest;
    }

    public DeforestationPrevention getDeforestationPrevention() {
        return deforestationPrevention;
    }

    public void setDeforestationPrevention(DeforestationPrevention deforestationPrevention) {
        this.deforestationPrevention = deforestationPrevention;
    }

    public SustainableCattleRaisingAndGoodPractices getSustainableCattleRaisingAndGoodPractices() {
        return sustainableCattleRaisingAndGoodPractices;
    }

    public void setSustainableCattleRaisingAndGoodPractices(SustainableCattleRaisingAndGoodPractices sustainableCattleRaisingAndGoodPractices) {
        this.sustainableCattleRaisingAndGoodPractices = sustainableCattleRaisingAndGoodPractices;
    }

    public IntegralBasicSanitation getIntegralBasicSanitation() {
        return integralBasicSanitation;
    }

    public void setIntegralBasicSanitation(IntegralBasicSanitation integralBasicSanitation) {
        this.integralBasicSanitation = integralBasicSanitation;
    }

    public HillSideForest getHillSideForest() {
        return hillSideForest;
    }

    public void setHillSideForest(HillSideForest hillSideForest) {
        this.hillSideForest = hillSideForest;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormLetter() {
        return formLetter;
    }

    public void setFormLetter(String formLetter) {
        this.formLetter = formLetter;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(int taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubType getSubType() {
        return subType;
    }

    public void setSubType(SubType subType) {
        this.subType = subType;
    }

    public String getPropertyCorrelation() {
        return propertyCorrelation;
    }

    public void setPropertyCorrelation(String propertyCorrelation) {
        this.propertyCorrelation = propertyCorrelation;
        switch (propertyCorrelation) {
            case "Propietario":
                this.propertyCorrelationId = 1;
                break;
            case "Poseedor":
                this.propertyCorrelationId = 2;
                break;
            case "Representante legal":
                this.propertyCorrelationId = 3;
                break;
            case "Arrendatario":
                this.propertyCorrelationId = 4;
                break;
            case "Tenedor":
                this.propertyCorrelationId = 5;
                break;
            case "Otro":
                this.propertyCorrelationId = 6;
                break;
        }
    }

    public int getPropertyCorrelationId() {
        return propertyCorrelationId;
    }

    public void setPropertyCorrelationId(int propertyCorrelationId) {
        this.propertyCorrelationId = propertyCorrelationId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getMargins() {
        return margins;
    }

    public void setMargins(String margins) {
        this.margins = margins;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public void setReceiverEmail(String email) {
        this.receiverEmail = email;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isValidObject() {
        return isValidObject;
    }

    public void setValidObject(boolean validObject) {
        isValidObject = validObject;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(contactName) &&
                !TextUtils.isEmpty(idCardNumber) &&
                !TextUtils.isEmpty(mobileNumber);
    }

    public void initNullFields() {
        if (this.process == null) {
            this.process = new Process();
        }
        if (this.user == null) {
            this.user = new User();
        }
        if (this.subType == null) {
            this.subType = new SubType();
        }
        if (this.hillSideForest == null) {
            this.hillSideForest = new HillSideForest();
        }
        if (this.riverBankForest == null) {
            this.riverBankForest = new RiverBankForest();
        }
        if (this.originsForest == null) {
            this.originsForest = new OriginsForest();
        }
        if (this.deforestationPrevention == null) {
            this.deforestationPrevention = new DeforestationPrevention();
        }
        if (this.sustainableCattleRaisingAndGoodPractices == null) {
            this.sustainableCattleRaisingAndGoodPractices = new SustainableCattleRaisingAndGoodPractices();
        }
        if (this.integralBasicSanitation == null) {
            this.integralBasicSanitation = new IntegralBasicSanitation();
        }
        this.isValidObject = true;
    }

    public int getPredioPotencialId() {
        return predioPotencialId;
    }

    public void setPredioPotencialId(int predioPotencialId) {
        this.predioPotencialId = predioPotencialId;
    }

    public String getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
    }

    public String getEmbalse() {
        return embalse;
    }

    public void setEmbalse(String embalse) {
        this.embalse = embalse;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public boolean isNew() {
        return this.isNew;
    }

    public void setAsNew() {
        this.isNew = true;
    }

    public boolean isHasBeenSent() {
        return hasBeenSent;
    }

    public void setHasBeenSent() {
        this.hasBeenSent = true;
    }

    public List<Map> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Map> programs) {
        this.programs = programs;
    }

    public CartaIntencion() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.propertyVisitDate, flags);
        dest.writeInt(this.processId);
        dest.writeString(this.title);
        dest.writeString(this.formLetter);
        dest.writeInt(this.userId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeInt(this.taskTypeId);
        dest.writeString(this.taskTypeName);
        dest.writeParcelable(this.process, flags);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.subType, flags);
        dest.writeString(this.propertyCorrelation);
        dest.writeInt(this.propertyCorrelationId);
        dest.writeString(this.propertyName);
        dest.writeString(this.margins);
        dest.writeString(this.municipality);
        dest.writeString(this.lane);
        dest.writeString(this.extension);
        dest.writeString(this.registryNumber);
        dest.writeString(this.embalse);
        dest.writeString(this.program);
        dest.writeParcelable(this.hillSideForest, flags);
        dest.writeParcelable(this.riverBankForest, flags);
        dest.writeParcelable(this.originsForest, flags);
        dest.writeParcelable(this.deforestationPrevention, flags);
        dest.writeParcelable(this.sustainableCattleRaisingAndGoodPractices, flags);
        dest.writeParcelable(this.integralBasicSanitation, flags);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isValidObject ? (byte) 1 : (byte) 0);
        dest.writeString(this.others);
        dest.writeString(this.contactName);
        dest.writeString(this.idCardNumber);
        dest.writeString(this.email);
        dest.writeString(this.mobileNumber);
        dest.writeInt(this.dueDateStatus);
        dest.writeByte(this.isNew ? (byte) 1 : (byte) 0);
        dest.writeInt(this.predioPotencialId);
        dest.writeByte(this.hasBeenSent ? (byte) 1 : (byte) 0);
        dest.writeList(this.programs);
    }

    protected CartaIntencion(Parcel in) {
        this.id = in.readString();
        this.propertyVisitDate = in.readParcelable(AppDate.class.getClassLoader());
        this.processId = in.readInt();
        this.title = in.readString();
        this.formLetter = in.readString();
        this.userId = in.readInt();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.taskTypeId = in.readInt();
        this.taskTypeName = in.readString();
        this.process = in.readParcelable(Process.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
        this.subType = in.readParcelable(SubType.class.getClassLoader());
        this.propertyCorrelation = in.readString();
        this.propertyCorrelationId = in.readInt();
        this.propertyName = in.readString();
        this.margins = in.readString();
        this.municipality = in.readString();
        this.lane = in.readString();
        this.extension = in.readString();
        this.registryNumber = in.readString();
        this.embalse = in.readString();
        this.program = in.readString();
        this.hillSideForest = in.readParcelable(HillSideForest.class.getClassLoader());
        this.riverBankForest = in.readParcelable(RiverBankForest.class.getClassLoader());
        this.originsForest = in.readParcelable(OriginsForest.class.getClassLoader());
        this.deforestationPrevention = in.readParcelable(DeforestationPrevention.class.getClassLoader());
        this.sustainableCattleRaisingAndGoodPractices = in.readParcelable(SustainableCattleRaisingAndGoodPractices.class.getClassLoader());
        this.integralBasicSanitation = in.readParcelable(IntegralBasicSanitation.class.getClassLoader());
        this.pendingToBeSent = in.readByte() != 0;
        this.isValidObject = in.readByte() != 0;
        this.others = in.readString();
        this.contactName = in.readString();
        this.idCardNumber = in.readString();
        this.email = in.readString();
        this.mobileNumber = in.readString();
        this.dueDateStatus = in.readInt();
        this.isNew = in.readByte() != 0;
        this.predioPotencialId = in.readInt();
        this.hasBeenSent = in.readByte() != 0;
        this.programs = new ArrayList<>();
        in.readList(this.programs, Object.class.getClassLoader());
    }

    public static final Creator<CartaIntencion> CREATOR = new Creator<CartaIntencion>() {
        @Override
        public CartaIntencion createFromParcel(Parcel source) {
            return new CartaIntencion(source);
        }

        @Override
        public CartaIntencion[] newArray(int size) {
            return new CartaIntencion[size];
        }
    };
}
