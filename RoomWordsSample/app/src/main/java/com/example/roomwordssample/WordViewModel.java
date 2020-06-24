package com.example.roomwordssample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepo;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepo = new WordRepository(application);
        mAllWords = mRepo.getmAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepo.insert(word);}

    public void deleteAll() { mRepo.deleteAll();}

    public void deleteWord(Word word) { mRepo.deleteWord(word);}
}
