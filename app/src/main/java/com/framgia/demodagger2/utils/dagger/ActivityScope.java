package com.framgia.demodagger2.utils.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

@Scope // define vòng đời của một Object
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
