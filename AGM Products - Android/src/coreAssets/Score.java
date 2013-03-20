package coreAssets;

public interface Score {
	String toString();

	void incScore(int points);

	int compareTo(Object o);
}
