package com.framgia.demodagger2.screen.list;

import android.content.Context;
import android.widget.Toast;
import com.example.vnnht.demodagger2.R;
import com.framgia.demodagger2.data.model.Student;
import com.framgia.demodagger2.data.repository.StudentRepository;
import com.framgia.demodagger2.screen.OnItemClickListen;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ListViewModel implements OnItemClickListen {
    private Context mContext;
    private ListStudentAdapter mListStudentAdapter;
    private StudentRepository mStudentRepository;
    private CompositeDisposable mCompositeDisposable;

    public ListViewModel(Context context, StudentRepository studentRepository,
            ListStudentAdapter listStudentAdapter) {
        mContext = context;
        mStudentRepository = studentRepository;
        mListStudentAdapter = listStudentAdapter;
        mCompositeDisposable = new CompositeDisposable();
        mListStudentAdapter.setOnItemClick(this);
    }

    public void onStart() {
        onRequestExecute();
    }

    private void onRequestExecute() {
        Disposable disposable = mStudentRepository.onGetListStudent(15)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(List<Student> studentList) throws Exception {
                        if (studentList.size() > 0) {
                            mListStudentAdapter.updateStudentList(studentList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mContext,
                                mContext.getString(R.string.text_error) + throwable.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void onStop() {
        mCompositeDisposable.clear();
    }

    public ListStudentAdapter getRecyclerAdapter() {
        return mListStudentAdapter;
    }

    @Override
    public void itemClicked(Object anything) {
        Toast.makeText(mContext, (int) anything, Toast.LENGTH_SHORT).show();
    }
}
