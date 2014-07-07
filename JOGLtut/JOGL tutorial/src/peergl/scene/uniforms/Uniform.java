package peergl.scene.uniforms;

public class Uniform
{

	private int loc;
	private UniformType type;
	private String name;
	
	public int getLocation(){return loc;}
	public UniformType getType(){return type;}
	public String getName(){return name;}
	
	public Uniform(int location, int glTypeNr, String name)
	{
		this.name = name;
		loc = location;
		this.type = UniformType.get(glTypeNr);
	}
	
}
