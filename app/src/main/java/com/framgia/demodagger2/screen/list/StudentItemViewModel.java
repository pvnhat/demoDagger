package com.framgia.demodagger2.screen.list;

import com.framgia.demodagger2.data.model.Student;
import com.framgia.demodagger2.screen.OnItemClickListen;

public class StudentItemViewModel {
    public Student mStudent;

    public StudentItemViewModel() {
        mStudent = new Student();
    }

    public void setItemData(Student student) {
        mStudent = student;
    }

    public String getAvaterUrl() {
        return mStudent.getAvatar();
    }
}
