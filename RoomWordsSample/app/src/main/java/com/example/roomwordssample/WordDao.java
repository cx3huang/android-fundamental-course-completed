package com.example.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    // SQL query passed as the string parameter to @Query
    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();

    @Delete
    void deleteWord(Word word);
}
