package com.vkhooda24.knowyourcountry.dagger;

import android.arch.lifecycle.ViewModel;
import dagger.MapKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vikram Hooda on 2019-07-25.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKey {

    Class<? extends ViewModel> ViewModelKey();
}
