package com.nuppu.LocalSearch;

import com.nuppu.LocalSearch.Structure.ContactLocal;

import java.util.ArrayList;


public  class Fields {
    public static final String BASE_URL="http://aqib.onstart.in/";
    public static final String URL_Electrician=BASE_URL+"electrician1.php"; //electrician file url
    public static final String URL_Flowers=BASE_URL+"florist1.php"; //flower file url
    public static final String URL_Laundry=BASE_URL+"Laundry.php"; //laundry url
   // public static final String URL_Homeopath=BASE_URL+"Laundry.php"; //laundry url
    //public static final String URL_Physician=BASE_URL+"Laundry.php"; //laundry url
    public static final String LAUNDRY="electrician";             //keys of LAundry
   // public static final String Homeopath="electrician";             //keys of LAundry
    public static final String Physician="electrician";             //keys of LAundry
    public static final String Electrician="electrician";
      // public static final String PUNCTURE = "puncture";
    public static final String Flowers = "electrician";
   // public static final String BARS = "bars";
    public static final String URL_Study = BASE_URL+"studypoint1.php";     //studypoint file
    public static final String StudyPoint = "electrician";
   // public static final String FUEL="fuel";
    public static final String PLACE = "place";
    public static final String SETTINGS = "settings";
    public static final String CONTACTS = "contacts";
    public static final String NUMBER = "number";
    public static final String URL_ADVERTISEMENT = BASE_URL+"nupuradvertisement/advertisement.php";

    public static double LATITUDE=0.0;
    public static double LONGITUDE=0.0;
    public static ArrayList<ContactLocal> contactLocalArrayList=new ArrayList<>();

}
