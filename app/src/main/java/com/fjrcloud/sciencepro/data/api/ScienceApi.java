package com.fjrcloud.sciencepro.data.api;

import com.fjrcloud.sciencepro.data.base.HttpListResut;
import com.fjrcloud.sciencepro.data.base.HttpResult;
import com.fjrcloud.sciencepro.data.net.AdEntity;
import com.fjrcloud.sciencepro.data.net.DepartmentEntity;
import com.fjrcloud.sciencepro.data.net.EnterpriseEntity;
import com.fjrcloud.sciencepro.data.net.EnterpriseTypeEntity;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.data.net.ManagementEntity;
import com.fjrcloud.sciencepro.data.net.ScienceDyEntity;
import com.fjrcloud.sciencepro.data.net.StaffEntity;
import com.fjrcloud.sciencepro.data.net.TypeEntity;
import com.fjrcloud.sciencepro.data.net.WorkEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lin on 2017/2/24.
 */

public interface ScienceApi {

    @GET("structure/management/getManagement")
    Observable<HttpResult<ManagementEntity>> getManagement();

    @GET("structure/department/findAll")
    Observable<HttpResult<List<ManagementEntity>>> departmentFindAll();

    @GET("download/findAll")
    Observable<HttpListResut<List<FileEntity>>> downloadFindAll(@Query("pageNum") Integer pageNum,
                                                                @Query("pageSize") Integer pageSize);

    @POST("download/searchByName")
    Observable<HttpResult<List<FileEntity>>> downloadSearchByName(@Query("KeyWord") String keyWord);

    @GET("enterprise/type/findAll")
    Observable<HttpResult<List<EnterpriseTypeEntity>>> enterTpyeFindAll();

    @GET("enterprise/findAll")
    Observable<HttpListResut<List<EnterpriseEntity>>> enterpriseFindAll(@Query("pageNum") Integer pageNum,
                                                                        @Query("pageSize") Integer pageSize);

    @GET("enterprise/searchByName")
    Observable<HttpResult<List<EnterpriseEntity>>> enterpriseSearchByName(@Query("KeyWord") String keyWord);

    @GET("enterprise/findByType")
    Observable<HttpResult<List<EnterpriseEntity>>> enterpriseFindByType(
            @Query("enterpriseTypeId") Integer enterpriseTypeId);

    @GET("ad/find")
    Observable<HttpResult<List<AdEntity>>> adFind(@Query("plate") String plate);

    @GET("gov/affair/findAll")
    Observable<HttpListResut<List<ScienceDyEntity>>> affairFindAll(@Query("pageNum") Integer pageNum,
                                                                   @Query("pageSize") Integer pageSize);

    @GET("gov/affair/findById")
    Observable<HttpResult<ScienceDyEntity>> affairFindById(@Query("id") Integer id);

    @GET("structure/department/find")
    Observable<HttpResult<StaffEntity>> deparmentFind(@Query("id") Integer id);

    @GET("structure/department/findByOneScopes")
    Observable<HttpResult<List<DepartmentEntity>>> findByOneScopes(@Query("scopes") String scopes);

    @GET("vision/type/findAll")
    Observable<HttpResult<List<TypeEntity>>> type1FindAll();

    @GET("vision/type2/findAll")
    Observable<HttpResult<List<TypeEntity>>> type2FindAll();

    @GET("guide/findByDepartment")
    Observable<HttpListResut<List<WorkEntity>>> guideFindByDepartment(@Query("departmentId") Integer departmentId, @Query("pageNum") Integer pageNum,
                                                                      @Query("pageSize") Integer pageSize);

    @GET("policy/findByDepartment")
    Observable<HttpListResut<List<WorkEntity>>> policyFindByDepartment(@Query("departmentId") Integer departmentId, @Query("pageNum") Integer pageNum,
                                                                       @Query("pageSize") Integer pageSize);

}
