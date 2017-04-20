package com.fjrcloud.sciencepro.data;

import com.fjrcloud.sciencepro.data.net.TypeEntity;
import com.fjrcloud.sciencepro.data.net.WorkEntity;

import java.util.List;

/**
 * Created by lin on 2017/2/28.
 */

public class WorkZipItem {

    private TypeEntity typeEntity;

    private List<WorkEntity> sciencePolicies;

    public WorkZipItem(TypeEntity typeEntity, List<WorkEntity> sciencePolicies) {
        this.typeEntity = typeEntity;
        this.sciencePolicies = sciencePolicies;
    }

    public WorkZipItem(TypeEntity typeEntity) {
        this(typeEntity, null);
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    public List<WorkEntity> getSciencePolicies() {
        return sciencePolicies;
    }

    public void setSciencePolicies(List<WorkEntity> sciencePolicies) {
        this.sciencePolicies = sciencePolicies;
    }
}
