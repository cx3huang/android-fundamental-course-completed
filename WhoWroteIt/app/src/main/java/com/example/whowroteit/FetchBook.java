package com.example.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {
    //Create WeakReference to the TextViews to avoid leaking.
    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = new WeakReference<>(mTitleText);
        this.mAuthorText = new WeakReference<>(mAuthorText);
    }
    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while(i < itemsArray.length() && (authors == null && title == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                i++;
            }

            if(title != null && authors != null) {
                mTitleText.get().setText(title);
                mAuthorText.get().setText(authors);
            }
            else {
                mTitleText.get().setText(R.string.no_results);
                mAuthorText.get().setText("");
            }
        }
        catch (JSONException e) {
            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText("");
            e.printStackTrace();
        }
    }
}
