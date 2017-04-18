package com.fjrcloud.sciencepro.data;

import java.util.List;

/**
 * Created by lin on 2017/2/28.
 */

public class WorkZipItem {

    private String categoryName;

    private List<SciencePolicyResponse.SciencePolicy> sciencePolicies;

    public WorkZipItem(String categoryName, List<SciencePolicyResponse.SciencePolicy> sciencePolicies) {
        this.categoryName = categoryName;
        this.sciencePolicies = sciencePolicies;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SciencePolicyResponse.SciencePolicy> getSciencePolicies() {
        return sciencePolicies;
    }

    public void setSciencePolicies(List<SciencePolicyResponse.SciencePolicy> sciencePolicies) {
        this.sciencePolicies = sciencePolicies;
    }
}
