import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Collections;


public class SimpleParse
{
    public static void main(String args[])
    {

        ArrayList<String> lst=new ArrayList<String>();
        try
        {
            File file=new File("/Users/agustayachaturvedi/Documents/SimpleParser/SimpleParser/lib/target.txt");
            Scanner sc=new Scanner(file);
            
            while(sc.hasNext())
            {
                String str=sc.nextLine().toLowerCase();
                StringTokenizer tok=new StringTokenizer(str," .");
                while(tok.hasMoreTokens())
                {
                    lst.add(tok.nextToken());
                }
            }
            
            sc.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error occured in reading");
            e.printStackTrace();
        }

        
        Collections.sort(lst);
        System.out.println(lst);



        try {
            File file=new File("/Users/agustayachaturvedi/Documents/SimpleParser/SimpleParser/lib/result.txt");
            if(file.createNewFile())
            {
                System.out.println("File created: "+file.getName());
            }
            else
            {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occured in creating");
            e.printStackTrace();
        }



        try {
            FileWriter mywrite=new FileWriter("/Users/agustayachaturvedi/Documents/SimpleParser/SimpleParser/lib/result.txt");
            for(String str:lst)
            {
                mywrite.write(str+" ");
            }
            mywrite.close();
            System.out.println("Successfully written");
        } catch (IOException e) {
            System.out.println("An error occured in writing");
            e.printStackTrace();
        }






    }
}
