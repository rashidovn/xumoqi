package org.incava.xumoqi;

import java.io.*;

import android.content.res.Resources;
import android.util.SparseArray;
import android.util.SparseIntArray;

/**
 * Represents a list of words. The input file must be sorted, in the format:
 *
 * <pre>
 * abc
 * def
 * ghij
 * klmno</pre>
 *
 * and must be in ascending order of the number of characters.
 */
public class Dictionary {
    public static final String TWL_SORTED = "twl-sorted-by-length.txt";
    private static SparseIntArray lenToRes = new SparseIntArray();
    // private static final SparseArray<WordList> wordListsByLength = new SparseArray<WordList>(); 
    
    static {
    	lenToRes.put(2, R.raw.twl2);
    	lenToRes.put(3, R.raw.twl3);
    	lenToRes.put(4, R.raw.twl4);
    	lenToRes.put(5, R.raw.twl5);
    	lenToRes.put(6, R.raw.twl6);
    	lenToRes.put(7, R.raw.twl7);
    	lenToRes.put(8, R.raw.twl8);
    	lenToRes.put(9, R.raw.twl9);
    	lenToRes.put(10, R.raw.twl10);
    	lenToRes.put(11, R.raw.twl11);
    	lenToRes.put(12, R.raw.twl12);
    	lenToRes.put(13, R.raw.twl13);
    	lenToRes.put(14, R.raw.twl14);
    	lenToRes.put(15, R.raw.twl15);
    }
    
    public static WordList getWordList(Resources resources, int length) {
    	int twlRes = lenToRes.get(length);
		InputStream is = resources.openRawResource(twlRes);
		return new WordList(is);
    }
    
    private final static InputStream getInputStream(Resources resources, int length) {
    	return null;
    }
    
    private final SparseArray<WordList> wordListsByLength;
    
    public Dictionary(String dictFileName, Integer maxLength) throws Exception {
        this(new FileInputStream(dictFileName), maxLength);
    }

    public Dictionary(InputStream dictStream, Integer maxLength) {
        this(dictStream, maxLength, null);
    }

    public Dictionary(Resources resources, int length) {
    	this(getInputStream(resources, length), length);
    }

    public Dictionary(InputStream dictStream, Integer maxLength, Integer minLength) {
        this.wordListsByLength = new SparseArray<WordList>();
        
        IOReader io = new IOReader() {
        	public void onRead(String str, Integer len) {
        		addWord(str, len);
        	}
        };
        io.readStream(dictStream, maxLength);
    }

    private void addWord(String word, int len) {
        WordList wl = wordListsByLength.get(len);
        if (wl == null) {
            wl = new WordList();
            wordListsByLength.put(len, wl);
        }
        wl.addWord(word);
    }

    public WordList getWordList(int len) {
        return wordListsByLength.get(len);
    }
}
