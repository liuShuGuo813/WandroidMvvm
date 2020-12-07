package com.lsg.wandroidmvvm.http;

import com.lsg.wandroidmvvm.base.BaseResponse;
import com.lsg.wandroidmvvm.bean.PageList;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;

/**
 * Created by lsg on 2020/10/30
 */
@Parser(name = "Response",wrappers = {List.class, PageList.class})
public class ResponseParser<T> extends AbstractParser<T> {

    //注意，以下两个构造方法是必须的
    protected ResponseParser() { super(); }
    public ResponseParser(Type type) { super(type); }

    @Override
    public T onParse(@NotNull Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(BaseResponse.class, mType); //获取泛型类型
        BaseResponse<T> data = convert(response, type);
        T t = data.getData(); //获取data字段
        if (data.getErrorCode() != 0 || t == null) {//这里假设code不等于0,代表数据不正确，抛出异常(Wandroid code为0代表成功)
            throw new ParseException(String.valueOf(data.getErrorCode()), data.getErrorMsg(), response);
        }
        return t;
    }
}
