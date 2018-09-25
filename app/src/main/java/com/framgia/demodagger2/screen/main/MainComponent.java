package com.framgia.demodagger2.screen.main;

import com.framgia.demodagger2.utils.dagger.ActivityScope;
import com.framgia.demodagger2.utils.AppComponent;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
