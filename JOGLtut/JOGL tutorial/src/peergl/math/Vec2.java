package peergl.math;

public class Vec2 
{

	public float x, y;
	
	public Vec2()
	{
		x=y=0;
	}
	
	public Vec2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float dot(Vec2 v)
	{
		return x*v.x + y*v.y;
	}
	
	public Vec2 add(Vec2 v)
	{
		return new Vec2(x+v.x, y+v.y);
	}
	
}
