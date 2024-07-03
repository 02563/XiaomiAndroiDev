package com.example.androidstudy;

public class CommonData<T> {
    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "CommonData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
