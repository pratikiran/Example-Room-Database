package com.e4.explainroomdatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Provide an abstract method to get the DAO
    public abstract UserDao userDao();

    // Singleton instance to prevent having multiple instances of the database
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "my_database")
                            .fallbackToDestructiveMigration() // For simplicity; handle migrations in real apps
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}