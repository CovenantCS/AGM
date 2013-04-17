package coreAssets;

import java.io.FileOutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import edu.covenant.kepler.coreassets.R;
import android.annotation.SuppressLint;
import android.content.Context;

public class SimpleScore implements Score {
	private int score;

	public SimpleScore() {
		score = 0;
	}

	public SimpleScore(int score) {
		this.score = score;
	}

	public void incScore(int points) {
		score += points;
	}

	public int getScore() {
		return score;
	}

	public String toString() {
		return "" + score;
	}

	public int compareTo(Object o) {
		SimpleScore sc = (SimpleScore) o;
		return score - sc.score;
	}

    @SuppressLint( "NewApi" )
    public void saveScore( Context context )
    {
        String fileName = context.getString( R.string.app_name ) + "scores";
        LinkedList<Integer> scores = getScores( context );
        scores.add( Integer.parseInt( toString() ) );
        Collections.sort( scores );
        FileOutputStream outputStream;
        try
        {
            context.deleteFile( fileName );
            outputStream = context.openFileOutput( fileName, Context.MODE_PRIVATE );
            for( int i = 0; i < ScoreBoard.numScoresToKeep; i++ )
            {
                outputStream.write( ( scores.pollLast().toString() + " " ).getBytes() );
            }
            outputStream.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public LinkedList<Integer> getScores( Context context )
    {
        String fileName = context.getString( R.string.app_name ) + "scores";
        LinkedList<Integer> scores = new LinkedList<Integer>();
        try
        {
            Scanner scanner = new Scanner( context.openFileInput( fileName ) );
            while( scanner.hasNextInt() )
            {
                scores.add( scanner.nextInt() );
            }
            scanner.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return scores;
    }

}
