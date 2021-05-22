package sample;

import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import java.util.Hashtable;
import java.awt.Dimension;
import java.util.Enumeration;
import com.sun.j3d.utils.image.TextureLoader;

public class Car extends JFrame{
    public Canvas3D myCanvas3D;

    public Car(){
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);
        
        simpUniv.getViewingPlatform().setNominalViewingTransform();
        
        createSceneGraph(simpUniv);
        addLight(simpUniv);
        
        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        setTitle("Car");
        setSize(700,700);

        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }

    public void createSceneGraph(SimpleUniverse su){
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
        String name;
        BranchGroup carBranchGroup = new BranchGroup();
        Background carBackground = new Background();
        
        Scene carScene = null;

        try{
            carScene = f.load("models/car.obj");

        }
        catch (Exception e){
            System.out.println("File loading failed:" + e);
        }
        Hashtable roachNamedObjects = carScene.getNamedObjects();
        Enumeration enumer = roachNamedObjects.keys();
        while (enumer.hasMoreElements()){
            name = (String) enumer.nextElement();
            System.out.println("Name: " + name);
        }

        Appearance textureApp = new Appearance();

        TextureAttributes textureAttr = new TextureAttributes();
        textureAttr.setTextureMode(TextureAttributes.REPLACE);
        textureApp.setTextureAttributes(textureAttr);
        Material mat = new Material();
        mat.setShininess(120.0f);
        mat.setSpecularColor(new Color3f(0.7f,0.2f,0.1f));
        textureApp.setMaterial(mat);
        
        Transform3D tfLampTable = new Transform3D();
        tfLampTable.setTranslation(new Vector3d(0.0f,0.955f,0.0f));
        TransformGroup tgLampTable = new TransformGroup(tfLampTable);

       
        Transform3D startTransformation = new Transform3D();
        startTransformation.setScale(1.0/6);
        Transform3D combinedStartTransformation = new Transform3D();
        combinedStartTransformation.rotY(-3*Math.PI/2);
        combinedStartTransformation.mul(startTransformation);
        
        TransformGroup carStartTransformGroup = new TransformGroup(combinedStartTransformation);

      
        int movesCount = 100;
        int movesDuration = 500;
        int startTime = 0;

        Alpha leg1_1RotAlpha = new Alpha(movesCount, Alpha.INCREASING_ENABLE, startTime, 0, movesDuration,0,0,0,0,0);
        
        Shape3D wheel1 = (Shape3D) roachNamedObjects.get("wheel1");
        TransformGroup wheelTG1 = new TransformGroup();
        wheelTG1.addChild(wheel1.cloneTree());
        
        Transform3D legRotAxis = new Transform3D();
        legRotAxis.set(new Vector3d(0, -0.1, -0.644));
        legRotAxis.setRotation(new AxisAngle4d(0, 0, -0.1, Math.PI/2));

        RotationInterpolator wheel1rot = new RotationInterpolator(leg1_1RotAlpha, wheelTG1, legRotAxis,(float) 0.0f, (float) Math.PI*2); // Math.PI*2
        wheel1rot.setSchedulingBounds(bs);
        wheelTG1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheelTG1.addChild(wheel1rot);
        Alpha whAlpha2 = new Alpha(movesCount, Alpha.INCREASING_ENABLE, startTime, 0, movesDuration,0,0,0,0,0);
        
        Shape3D wheel2 = (Shape3D) roachNamedObjects.get("wheel2");
        TransformGroup wheelTG2 = new TransformGroup();
        wheelTG2.addChild(wheel2.cloneTree());
        
        Transform3D legRotAxis2 = new Transform3D();
        legRotAxis2.set(new Vector3d(0, -0.101, 0.52));
        legRotAxis2.setRotation(new AxisAngle4d(0, 0, -0.1, Math.PI/2));

        RotationInterpolator wheel2rot = new RotationInterpolator(whAlpha2, wheelTG2, legRotAxis2,(float) 0.0f, (float) Math.PI*2); // Math.PI*2
        wheel2rot.setSchedulingBounds(bs);
        wheelTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheelTG2.addChild(wheel2rot);
        Alpha whAlpha3 = new Alpha(movesCount, Alpha.INCREASING_ENABLE, startTime, 0, movesDuration,0,0,0,0,0);
        
        Shape3D wheel3 = (Shape3D) roachNamedObjects.get("wheel3");
        TransformGroup wheelTG3 = new TransformGroup();
        wheelTG3.addChild(wheel3.cloneTree());
        
        Transform3D legRotAxis3 = new Transform3D();
        legRotAxis3.set(new Vector3d(0, -0.1, -0.625));
        legRotAxis3.setRotation(new AxisAngle4d(0, 0, -0.1, Math.PI/2));

        RotationInterpolator wheel3rot = new RotationInterpolator(whAlpha3, wheelTG3, legRotAxis3,(float) 0.0f, (float) Math.PI*2); // Math.PI*2
        wheel3rot.setSchedulingBounds(bs);
        wheelTG3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheelTG3.addChild(wheel3rot);
        Alpha whAlpha4 = new Alpha(movesCount, Alpha.INCREASING_ENABLE, startTime, 0, movesDuration,0,0,0,0,0);
        
        Shape3D wheel4 = (Shape3D) roachNamedObjects.get("wheel4");
        TransformGroup wheelTG4 = new TransformGroup();
        wheelTG4.addChild(wheel4.cloneTree());
        
        Transform3D legRotAxis4 = new Transform3D();
        legRotAxis4.set(new Vector3d(0, -0.101, 0.535));
        legRotAxis4.setRotation(new AxisAngle4d(0, 0, -0.1, Math.PI/2));

        RotationInterpolator wheel4rot = new RotationInterpolator(whAlpha4, wheelTG4, legRotAxis4,(float) 0.0f, (float) Math.PI*2); // Math.PI*2
        wheel4rot.setSchedulingBounds(bs);
        wheelTG4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wheelTG4.addChild(wheel4rot);

        TransformGroup sceneGroup = new TransformGroup();
        sceneGroup.addChild(wheelTG1);
        sceneGroup.addChild(wheelTG2);
        sceneGroup.addChild(wheelTG3);
        sceneGroup.addChild(wheelTG4);
        TransformGroup tgBody = new TransformGroup();
        Shape3D carBodyShape = (Shape3D) roachNamedObjects.get("platinum1");
        carBodyShape.setAppearance(textureApp);
        tgBody.addChild(carBodyShape.cloneTree());
        sceneGroup.addChild(tgBody.cloneTree());

        TransformGroup whiteTransXformGroup = translate(
                            carStartTransformGroup,
                            new Vector3f(0.0f,0.0f,0.5f));

        TransformGroup whiteRotXformGroup = rotate(whiteTransXformGroup, new Alpha(10,5000));
        carBranchGroup.addChild(whiteRotXformGroup);
        carStartTransformGroup.addChild(sceneGroup);
        

        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0),Double.MAX_VALUE);
        ClassLoader cl = this.getClass().getClassLoader();
        java.net.URL u = cl.getResource("roud.png");
        final java.awt.Image backGround = getToolkit().getImage(u);
        TextureLoader starsTexture = new TextureLoader(backGround, this);
        
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double hauteur = tailleEcran.getHeight();
        double largeur = tailleEcran.getWidth();
        Background stars = new Background(starsTexture.getScaledImage((int) largeur, (int) hauteur));
        stars.setApplicationBounds(bounds);
        carBranchGroup.addChild(stars);
        carBackground.setApplicationBounds(bounds);
        carBranchGroup.addChild(tgLampTable);
        carBranchGroup.addChild(carBackground);
        
        carBranchGroup.compile();
        su.addBranchGraph(carBranchGroup);
    }

    public void addLight(SimpleUniverse su){
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }
    
    TransformGroup translate(Node node,Vector3f vector){

        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        TransformGroup transformGroup = 
                                     new TransformGroup();
        transformGroup.setTransform(transform3D);

        transformGroup.addChild(node);
        return transformGroup;
    }
    
    TransformGroup rotate(Node node,Alpha alpha){

      TransformGroup xformGroup = new TransformGroup();
      xformGroup.setCapability(
                    TransformGroup.ALLOW_TRANSFORM_WRITE);


      RotationInterpolator interpolator = 
               new RotationInterpolator(alpha,xformGroup);


      interpolator.setSchedulingBounds(new BoundingSphere(
                           new Point3d(0.0,0.0,0.0),1.0));


      xformGroup.addChild(interpolator);
      xformGroup.addChild(node);

      return xformGroup;

    }
    
    public static void main(String[] args) {
        Car start = new Car();
    }
    
}

