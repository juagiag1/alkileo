package com.example.alkileov1.localDB;

public class CamposBBDD {
    public static final String TABLA_USUARIO = "usuario";
    public  static  final  String CAMPO_ROL = "rol";
    public  static  final  String CAMPO_ID = "id";
    public  static  final  String CAMPO_TRATO = "trato";
    public  static  final  String CAMPO_PADRE = "padre";
    public  static  final  String CAMPO_NUM_ALQ = "num_alq";


    public  static  final  String CREAR_TABLA_USUARIO = "CREATE TABLE" + " " + TABLA_USUARIO + "("
            + CAMPO_ID+" INTEGER,"+ CAMPO_ROL+" TEXT,"+ CAMPO_TRATO+" TEXT,"+ CAMPO_PADRE+" INTEGER,"+ CAMPO_NUM_ALQ+" INTEGER)";
}
