package com.example.farm;
import android.provider.BaseColumns;

public final class Database {
    public static final class CreateDB implements BaseColumns{
        public static final String POTID = "potID";
        public static final String WCON = "wcon";
        public static final String VCON = "vcon";
        public static final String _TABLENAME0 = "contable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +POTID+" text not null, "
                +WCON+" boolean not null, "
                +VCON+" boolean not null, );";
    }
}
