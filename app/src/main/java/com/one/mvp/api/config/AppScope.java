package com.one.mvp.api.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 *
 * 构造单例模式
 * Created by swplzj on 17/5/25.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
@interface AppScope {
}
