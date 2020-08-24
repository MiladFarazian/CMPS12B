import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Search {
	public static void main(String[] args) throws IOException {
      if(args.length != 1){
         System.err.println("Usage: LineCount file");
         System.exit(1);
      }
      
      Scanner in = new Scanner(new File(args[0]));
      int lineCount = 0;
      while( in.hasNextLine() ){
         in.nextLine();
         lineCount++;
      }
      in.close();
      
      // print out the number of lines in file
      System.out.println( args[0]+" contains "+lineCount+" lines" );
   }
}

