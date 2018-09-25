package com.framgia.demodagger2.data.repository;

import com.framgia.demodagger2.data.model.Student;
import com.framgia.demodagger2.data.source.remote.StudentRemoteDataSource;
import io.reactivex.Observable;
import java.util.List;

public class StudentRepository {

    private StudentRemoteDataSource mStudentRemoteDataSource;

    public StudentRepository(StudentRemoteDataSource studentRemoteDataSource) {
        mStudentRemoteDataSource = studentRemoteDataSource;
    }

    public Observable<List<Student>> onGetLoginData(String username, String password) {
        return mStudentRemoteDataSource.getLoginData(username, password);
    }

    public Observable<List<Student>> onGetListStudent(int limit) {
        return mStudentRemoteDataSource.getStudentData(limit);
    }
}
