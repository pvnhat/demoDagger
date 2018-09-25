package com.framgia.demodagger2.screen.list;

import com.framgia.demodagger2.utils.AppComponent;
import com.framgia.demodagger2.utils.dagger.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ListStudentModule.class)
public interface ListStudentComponent {
    void inject(ListStudentActivity listStudentActivity);
}
