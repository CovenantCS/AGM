package coreAssets;

import android.content.Context;

public interface Score {
	String toString();

	void incScore(int points);

	int compareTo(Object o);
	
	void saveScore( Context context );
}
