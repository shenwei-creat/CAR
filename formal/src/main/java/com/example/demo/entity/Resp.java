package com.example.demo.entity;
//<E>模板类型
public class Resp<E> {
    private String code;
    private String messsage;
    private E bodt;

    Resp(String code, String messsage, E bodt) {
        this.code = code;
        this.messsage = messsage;
        this.bodt = bodt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public E getBodt() {
        return bodt;
    }

    public void setBodt(E bodt) {
        this.bodt = bodt;
    }
    public static <E>Resp<E> success(E body){
        return new Resp("200","",body);
    }
    public static <E>Resp<E> fail(String code,String messsage){
        return new Resp(code,messsage,(Object) null);
    }
}
