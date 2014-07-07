package peergl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileReader
{

	public static void testPath()
	{
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
	}
	
	public static String readFile(String file)
	{
		String output = "";
		try{
			String projectPath = new File("").getAbsolutePath();
			Path path = Paths.get( projectPath, new String[]{file} );
			output = new String(Files.readAllBytes(path));
		}catch(IOException e){}
		return output;
	}
	
}
