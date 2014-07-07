package peergl.math;

public class Vec3
{

	public float x, y, z;
	
	public Vec3()
	{
		x=y=z=0;
	}
	
	public Vec3(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float dot(Vec3 v)
	{
		return x*v.x + y*v.y + z*v.z;
	}
	
	public Vec3 add(Vec3 v)
	{
		return new Vec3(x+v.x, y+v.y, z+v.z);
	}
	
}
