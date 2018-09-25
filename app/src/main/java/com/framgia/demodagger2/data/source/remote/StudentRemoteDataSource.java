package com.framgia.demodagger2.data.source.remote;

import com.framgia.demodagger2.data.model.Student;
import com.framgia.demodagger2.data.source.StudentDataSource;
import io.reactivex.Observable;
import java.util.List;

public class StudentRemoteDataSource implements StudentDataSource.RemoteDataSource {

    private Api mApi;

    public StudentRemoteDataSource(Api api) {
        mApi = api;
    }

    @Override
    public Observable<List<Student>> getStudentData(int limit) {
        return mApi.getAllStudent(limit);
    }

    @Override
    public Observable<List<Student>> getLoginData(String userName, String password) {
        return mApi.getLoginData(userName, password);
    }
}
