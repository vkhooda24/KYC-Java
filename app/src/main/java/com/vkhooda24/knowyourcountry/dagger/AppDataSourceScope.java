package com.vkhooda24.knowyourcountry.dagger;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Vikram Hooda on 2019-07-20.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppDataSourceScope {
}
