package com.app.laqshya.studenttracker.activity.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.app.laqshya.studenttracker.activity.model.BatchDetails;
import com.app.laqshya.studenttracker.activity.model.BatchInformationResponse;
import com.app.laqshya.studenttracker.activity.model.EditBatchScheduleList;
import com.app.laqshya.studenttracker.activity.model.FacultyList;
import com.app.laqshya.studenttracker.activity.model.StudentInfo;
import com.app.laqshya.studenttracker.activity.service.EduTrackerService;

import java.io.IOException;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import timber.log.Timber;

public class EditBatchRepository {
    private EduTrackerService eduTrackerService;

    public EditBatchRepository(EduTrackerService eduTrackerService) {
        this.eduTrackerService = eduTrackerService;
    }

    public LiveData<BatchInformationResponse> getBatchForCounsellor(String center) {
        MutableLiveData<BatchInformationResponse> liveData = new MutableLiveData<>();
        eduTrackerService.getBatch(center).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BatchInformationResponse.BatchInformation>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<BatchInformationResponse.BatchInformation> batchInformations) {
                        liveData.postValue(new BatchInformationResponse(batchInformations));

                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue(new BatchInformationResponse(e));

                    }
                });
        return liveData;
    }
    public LiveData<BatchInformationResponse> getDeletedbatches(String center) {
        MutableLiveData<BatchInformationResponse> liveData = new MutableLiveData<>();
        eduTrackerService.getdeletedbatches(center).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BatchInformationResponse.BatchInformation>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<BatchInformationResponse.BatchInformation> batchInformations) {
                        liveData.postValue(new BatchInformationResponse(batchInformations));

                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue(new BatchInformationResponse(e));

                    }
                });
        return liveData;
    }
    public LiveData<BatchInformationResponse> getCompletedBatches(String center) {
        MutableLiveData<BatchInformationResponse> liveData = new MutableLiveData<>();
        eduTrackerService.getCompletedBatches(center).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BatchInformationResponse.BatchInformation>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<BatchInformationResponse.BatchInformation> batchInformations) {
                        liveData.postValue(new BatchInformationResponse(batchInformations));

                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue(new BatchInformationResponse(e));

                    }
                });
        return liveData;
    }
    public LiveData<String> markBatches(String bid,boolean deleteOrComplete) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        eduTrackerService.markBatchesasCompleted(bid,deleteOrComplete).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            liveData.postValue(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                            liveData.postValue("Error");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue(e.getMessage());

                    }
                });
        return liveData;
    }

    public LiveData<List<FacultyList>> getFacultyList() {
        MutableLiveData<List<FacultyList>> facultyLiveData = new MutableLiveData<>();
        eduTrackerService.getFacultyList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FacultyList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<FacultyList> facultyLists) {
                        if (facultyLists != null && facultyLists.size() > 0) {
                            facultyLiveData.setValue(facultyLists);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });

        return facultyLiveData;
    }

    public LiveData<EditBatchScheduleList> getBatchSchedule(String batchid) {
        MutableLiveData<EditBatchScheduleList> editBatchScheduleListMutableLiveData = new MutableLiveData<>();
        eduTrackerService.getSchedule(batchid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<EditBatchScheduleList.EditbatchSchedule>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<EditBatchScheduleList.EditbatchSchedule> editbatchSchedules) {
                        editBatchScheduleListMutableLiveData.postValue(new EditBatchScheduleList(editbatchSchedules));

                    }

                    @Override
                    public void onError(Throwable e) {
                        editBatchScheduleListMutableLiveData.postValue(new EditBatchScheduleList(e));
                    }
                });
        return editBatchScheduleListMutableLiveData;
    }

    public LiveData<List<StudentInfo>> getStudents(String coursename, String coursemodname) {
        MutableLiveData<List<StudentInfo>> liveData = new MutableLiveData<>();
        eduTrackerService.getStudentNameForEditBatches(coursename, coursemodname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<StudentInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<StudentInfo> studentInfos) {
                        liveData.postValue(studentInfos);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e);

                    }
                });
        return liveData;
    }


//    public LiveData<String> editBatches(BatchDetails details) {
//        MutableLiveData<String> liveData = new MutableLiveData<>();
//        eduTrackerService.editBatch(details)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<ResponseBody>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(ResponseBody responseBody) {
//                        try {
//                            liveData.postValue(responseBody.string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        liveData.postValue("Error occured");
//
//                    }
//                })
//        ;
//        return liveData;
//    }

    public LiveData<String> deleteBatches(String scheduleId) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        eduTrackerService.deleteBatch(scheduleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            liveData.postValue(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue("Error occured");

                    }
                })
        ;
        return liveData;
    }
    public LiveData<String> modifyBatches(String scheduleId,String facultyId,String startTime,String endTime,
                                          String dayId,String bid) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        eduTrackerService.updateBatch(scheduleId,facultyId,startTime,endTime,dayId,bid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            liveData.postValue(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue("Error occured");

                    }
                })
        ;
        return liveData;
    }
    public LiveData<String> insertEditedBatches(String startTime,String endTime,
                                          String dayId,String bid,int flagSwitched) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        eduTrackerService.insertnewSchedules(startTime,endTime,dayId,bid,flagSwitched)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            liveData.postValue(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue("Error occured");

                    }
                })
        ;
        return liveData;
    }
}
