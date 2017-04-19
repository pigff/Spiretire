package com.fjrcloud.sciencepro.data.net;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class StaffEntity {

    private List<LeaderEntity> staff;
    private DepartmentEntity department;

    public List<LeaderEntity> getStaff() {
        return staff;
    }

    public void setStaff(List<LeaderEntity> staff) {
        this.staff = staff;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
