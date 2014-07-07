package jogltut;

import javax.media.opengl.GLAutoDrawable;

import peergl.GLInfoProvider;
import peergl.GLSceneRenderer;
import peergl.math.Vec3;
import peergl.scene.ShaderScene;
import peergl.scene.UniformFactory;
import peergl.scene.uniforms.NoUniformException;
import peergl.scene.uniforms.objects.UniformObject;
import peergl.scene.uniforms.objects.UniformVec3;
import peergl.shaderprogram.ShaderProgram;
import peergl.shaderprogram.shaderobjects.ShaderObject;
import peergl.shaderprogram.shaderobjects.ShaderType;

public class MySceneRenderer extends GLSceneRenderer
{

	// - make shader scene with shader object
	// - initialize the scene
	// - get the uniformobject and make the triangle
	// - initialize the triangle
	
	private ShaderScene shaderScene;
	
	@Override
	public void init(GLAutoDrawable drawable)
	{
		//System.out.println("initializing scene");
		showInfo(drawable);
		makeShaderProgram(drawable);
		makeRenderObjects(drawable);
	}
	
	private void showInfo(GLAutoDrawable drawable)
	{
		GLInfoProvider provider = new GLInfoProvider(drawable);
		System.out.println(provider.getViewportSize());
	}
	
	private void makeShaderProgram(GLAutoDrawable drawable)
	{
		ShaderProgram program = new ShaderProgram();
		program.attachShader(new ShaderObject(ShaderType.VERTEX, "//shaders//vertex.glsl"));
		program.attachShader(new ShaderObject(ShaderType.FRAGMENT, "//shaders//fragment.glsl"));
		shaderScene = new ShaderScene(program); 
		addScene(shaderScene);
		shaderScene.init(drawable);
	}
	
	private void makeRenderObjects(GLAutoDrawable drawable)
	{
		/*UniformFactory factory = shaderScene.getUniformFactory();
		try{
			Triangle triangle = new Triangle();
			UniformVec3 u = (UniformVec3)factory.generateUniformObject(0);
			u.vec = new Vec3(1,0,0);
			riangle.attachUniformObject(u);
			shaderScene.addRenderObject(triangle);
			triangle.init(drawable);
		} catch(NoUniformException e){System.out.println("No Uniform yo");}//*/
		
		Triangle triangle = new Triangle();
		shaderScene.addRenderObject(triangle);
		triangle.init(drawable);
	}

}
