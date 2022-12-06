package com.lding.pmbok.common.enhance;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

public class MyLambdaQueryWrapper<T> extends LambdaQueryWrapper<T> {
    @SafeVarargs
    public final MyLambdaQueryWrapper<T> like(Object val, SFunction<T, ?>... funcs) {
        if (val == null) return this;
        String str = val.toString();
        if (str.length() == 0) return this;
        return (MyLambdaQueryWrapper<T>) nested((w) -> {
            for (SFunction<T, ?> func : funcs) {
                w.like(func, str).or();
            }
        });
    }
}
