package com.framgia.demodagger2.screen.main;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;
import com.example.vnnht.demodagger2.R;
import com.framgia.demodagger2.data.model.Student;
import com.framgia.demodagger2.data.repository.StudentRepository;
import com.framgia.demodagger2.screen.list.ListStudentActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MainViewModel {
    private Context mContext;
    private StudentRepository mStudentRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Observable.OnPropertyChangedCallback mOnPropertyChangedCallback = getChangedCallBack();

    public ObservableField<String> mTextUsername = new ObservableField<>();
    public ObservableField<String> mTextPassword = new ObservableField<>();

    public MainViewModel(Context context, StudentRepository studentRepository) {
        mContext = context;
        mStudentRepository = studentRepository;
    }

    private Observable.OnPropertyChangedCallback getChangedCallBack() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                validateTextInput();
            }
        };
    }

    private boolean validateTextInput() {
        String usernameInput = this.mTextUsername.get();
        String passwordInput = this.mTextPassword.get();
        if (usernameInput == null || passwordInput == null) {
            return false;
        }
        return true;
    }

    public void onLoginButtonClick(View view) {
        boolean isInputEmpty = validateTextInput();
        if (isInputEmpty) {
            checkLogin(mTextUsername.get(), mTextPassword.get());
        }
    }

    public void onSignupButtonClick(View view) {
        Toast.makeText(mContext, mContext.getString(R.string.text_comming), Toast.LENGTH_SHORT)
                .show();
    }

    private void checkLogin(String username, String pass) {
        Disposable disposable = mStudentRepository.onGetLoginData(username, pass)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Student>>() {
                    @Override
                    public void accept(List<Student> students) throws Exception {
                        if (students.size() > 0) {
                            Toast.makeText(mContext,
                                    mContext.getString(R.string.text_login_success),
                                    Toast.LENGTH_SHORT).show();
                            mContext.startActivity(ListStudentActivity.newInstance(mContext));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mContext, mContext.getString(R.string.text_failed_login)
                                + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void onStart() {
        mTextUsername.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mTextPassword.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
    }

    public void onStop() {
        mTextPassword.removeOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mTextPassword.removeOnPropertyChangedCallback(mOnPropertyChangedCallback);
        mCompositeDisposable.clear();
    }
}
