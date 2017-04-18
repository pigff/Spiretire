package com.fjrcloud.sciencepro.data;

/**
 * Created by lin on 2017/2/28.
 */

public class CompanyResponse extends BaseBean<CompanyResponse.Company> {

    public static class Company {
        private String companyName;
        private String companyAvatar;
        private String industry;
        private String nature;

        public Company(String companyName, String companyAvatar, String industry, String nature) {
            this.companyName = companyName;
            this.companyAvatar = companyAvatar;
            this.industry = industry;
            this.nature = nature;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyAvatar() {
            return companyAvatar;
        }

        public void setCompanyAvatar(String companyAvatar) {
            companyAvatar = companyAvatar;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }
    }

}
