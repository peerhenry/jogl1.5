package peergl.textures;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.media.opengl.GL4;

public class TextureFactory extends Texture
{

	private TextureFactory(){}
	
	public static Texture generateTextureFromPNG(GL4 gl, String file) throws IOException
	{	
		String projectPath = new File("").getAbsolutePath();
		Path path = Paths.get( projectPath, new String[]{"//resources//menuatlas.png"} );
		
		BufferedImage image = ImageIO.read( new File(path.toUri()) );
		
		Texture tex = new Texture(gl, image.getWidth(), image.getHeight());
		tex.setData( getRGBADataFromPNG(image) );
		
		return tex;
	}
	
	private static byte[] getRGBADataFromPNG(BufferedImage image)
	{
		int pixels = image.getWidth()*image.getHeight();
		byte[] abgrData = ((DataBufferByte)image.getRaster().getDataBuffer() ).getData();
		byte[] rgbaData = new byte[4*pixels];
		for(int i = 0; i<pixels; i++) // PNG is ABGR
		{
			rgbaData[4*i+0] = abgrData[4*i+3];
			rgbaData[4*i+1] = abgrData[4*i+2];
			rgbaData[4*i+2] = abgrData[4*i+1];
			rgbaData[4*i+3] = abgrData[4*i+0];
		}
		return rgbaData;
	}
	
}
