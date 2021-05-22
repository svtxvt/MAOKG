package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;


public class Castle implements ActionListener {
    private float upperEyeLimit = 5.0f; // 5.0
    private float lowerEyeLimit = 1.0f; // 1.0
    private float farthestEyeLimit = 6.0f; // 6.0
    private float nearestEyeLimit = 3.0f; // 3.0

    private TransformGroup treeTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new Castle();
    }

    private Castle() {
        Timer timer = new Timer(50, this);
        SimpleUniverse universe = new SimpleUniverse();

        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        timer.start();
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildCastleSkeleton();
        objRoot.addChild(treeTransformGroup);

        Background background = new Background(new Color3f(0.0f, 0.0f, 0.0f));
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);


        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);


        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }

    private void buildCastleSkeleton() {
        Box body1 = CastleBody.getBody(0.125f, 1.0f);
        Transform3D body1T = new Transform3D();
        body1T.setTranslation(new Vector3f());
        TransformGroup body1TG = new TransformGroup();
        body1TG.setTransform(body1T);
        body1TG.addChild(body1);
        treeTransformGroup.addChild(body1TG);

        setOneLevelOfTowers(0.8f, 0.25f);
        setOneLevelOfTowers(0.4f, 0.5f);

        Box body2 = CastleBody.getBody(0.125f, 0.6f);
        Transform3D body2T = new Transform3D();
        body2T.setTranslation(new Vector3f(.0f, .0f, 0.25f));
        TransformGroup body2TG = new TransformGroup();
        body2TG.setTransform(body2T);
        body2TG.addChild(body2);
        treeTransformGroup.addChild(body2TG);

        setCylinderTowers();

        setOneLevelOf4Fetches(0.4f, .0f);
        setOneLevelOf4Fetches(0.8f, -0.25f);

        setUpperProtectFetches();
        setLowerProtectFetches();
    }

    private void setUpperProtectFetches(){
        float distanceFromCentre = 0.592f;
        float zPos = 0.38f; // 0.33f
        TransformGroup protectFetch1 = CastleBody.getProtectFetch(distanceFromCentre, .0f, zPos, true);
        treeTransformGroup.addChild(protectFetch1);
        TransformGroup protectFetch2 = CastleBody.getProtectFetch(-distanceFromCentre, .0f, zPos, true);
        treeTransformGroup.addChild(protectFetch2);
        TransformGroup protectFetch3 = CastleBody.getProtectFetch(.0f, distanceFromCentre, zPos, false);
        treeTransformGroup.addChild(protectFetch3);
        TransformGroup protectFetch4 = CastleBody.getProtectFetch(.0f, -distanceFromCentre, zPos, false);
        treeTransformGroup.addChild(protectFetch4);
    }

    private void setLowerProtectFetches(){
        float distanceFromCentre1 = 0.992f;
        float distanceFromCentre2 = 0.4f; // 0.392
        float zPos = 0.133f;

        TransformGroup protectFetch1 = CastleBody.getProtectFetch(distanceFromCentre1, .0f, zPos, true);
        treeTransformGroup.addChild(protectFetch1);
        TransformGroup protectFetch2 = CastleBody.getProtectFetch(distanceFromCentre1, distanceFromCentre2, zPos, true);
        treeTransformGroup.addChild(protectFetch2);
        TransformGroup protectFetch3 = CastleBody.getProtectFetch(distanceFromCentre1, -distanceFromCentre2, zPos, true);
        treeTransformGroup.addChild(protectFetch3);

        TransformGroup protectFetch4 = CastleBody.getProtectFetch(-distanceFromCentre1, .0f, zPos, true);
        treeTransformGroup.addChild(protectFetch4);
        TransformGroup protectFetch5 = CastleBody.getProtectFetch(-distanceFromCentre1, distanceFromCentre2, zPos, true);
        treeTransformGroup.addChild(protectFetch5);
        TransformGroup protectFetch6 = CastleBody.getProtectFetch(-distanceFromCentre1, -distanceFromCentre2, zPos, true);
        treeTransformGroup.addChild(protectFetch6);

        TransformGroup protectFetch7 = CastleBody.getProtectFetch(.0f, distanceFromCentre1, zPos, false);
        treeTransformGroup.addChild(protectFetch7);
        TransformGroup protectFetch8 = CastleBody.getProtectFetch(distanceFromCentre2, distanceFromCentre1, zPos, false);
        treeTransformGroup.addChild(protectFetch8);
        TransformGroup protectFetch9 = CastleBody.getProtectFetch(-distanceFromCentre2, distanceFromCentre1, zPos, false);
        treeTransformGroup.addChild(protectFetch9);

        TransformGroup protectFetch10 = CastleBody.getProtectFetch(0.f, -distanceFromCentre1, zPos, false);
        treeTransformGroup.addChild(protectFetch10);
        TransformGroup protectFetch11 = CastleBody.getProtectFetch(distanceFromCentre2, -distanceFromCentre1, zPos, false);
        treeTransformGroup.addChild(protectFetch11);
        TransformGroup protectFetch12 = CastleBody.getProtectFetch(-distanceFromCentre2, -distanceFromCentre1, zPos, false);
        treeTransformGroup.addChild(protectFetch12);
    }

    private void setCylinderTowers(){
        float cylTowDistFromCentre = 0.4f;
        float cylTowDistFromCentre2 = 0.8f;
        TransformGroup cylinderTower1 = CastleBody.getCylinderTowerMain(1.8f, .0f, .0f);
        treeTransformGroup.addChild(cylinderTower1);
        TransformGroup cylinderTower2 = CastleBody.getCylinderTower(1.0f, -cylTowDistFromCentre, cylTowDistFromCentre);
        treeTransformGroup.addChild(cylinderTower2);
        TransformGroup cylinderTower3 = CastleBody.getCylinderTower(1.0f, cylTowDistFromCentre, cylTowDistFromCentre);
        treeTransformGroup.addChild(cylinderTower3);
        TransformGroup cylinderTower4 = CastleBody.getCylinderTower(1.0f, cylTowDistFromCentre, -cylTowDistFromCentre);
        treeTransformGroup.addChild(cylinderTower4);
        TransformGroup cylinderTower5 = CastleBody.getCylinderTower(1.0f, -cylTowDistFromCentre, -cylTowDistFromCentre);
        treeTransformGroup.addChild(cylinderTower5);
        TransformGroup cylinderTower6 = CastleBody.getCylinderTower(0.6f, -cylTowDistFromCentre2, cylTowDistFromCentre2);
        treeTransformGroup.addChild(cylinderTower6);
        TransformGroup cylinderTower7 = CastleBody.getCylinderTower(0.6f, cylTowDistFromCentre2, cylTowDistFromCentre2);
        treeTransformGroup.addChild(cylinderTower7);
        TransformGroup cylinderTower8 = CastleBody.getCylinderTower(0.6f, cylTowDistFromCentre2, -cylTowDistFromCentre2);
        treeTransformGroup.addChild(cylinderTower8);
        TransformGroup cylinderTower9 = CastleBody.getCylinderTower(0.6f, -cylTowDistFromCentre2, -cylTowDistFromCentre2);
        treeTransformGroup.addChild(cylinderTower9);
    }

    private void setOneLevelOf4Fetches(float distanceFromCentre, float height){
        TransformGroup fourFetches1 = CastleBody.getFourFetches();
        Transform3D tower1T = new Transform3D();
        tower1T.setTranslation(new Vector3f(distanceFromCentre, distanceFromCentre, height));
        fourFetches1.setTransform(tower1T);
        treeTransformGroup.addChild(fourFetches1);

        TransformGroup fourFetches2 = CastleBody.getFourFetches();
        Transform3D tower2T = new Transform3D();
        tower2T.setTranslation(new Vector3f(-distanceFromCentre, -distanceFromCentre, height));
        fourFetches2.setTransform(tower2T);
        treeTransformGroup.addChild(fourFetches2);

        TransformGroup fourFetches3 = CastleBody.getFourFetches();
        Transform3D tower3T = new Transform3D();
        tower3T.setTranslation(new Vector3f(distanceFromCentre, -distanceFromCentre, height));
        fourFetches3.setTransform(tower3T);
        treeTransformGroup.addChild(fourFetches3);

        TransformGroup fourFetches4 = CastleBody.getFourFetches();
        Transform3D tower4T = new Transform3D();
        tower4T.setTranslation(new Vector3f(-distanceFromCentre, distanceFromCentre, height));
        fourFetches4.setTransform(tower4T);
        treeTransformGroup.addChild(fourFetches4);
    }

    private void setOneLevelOfTowers(float distanceFromCentre, float height){
        Box tower1 = CastleBody.getTower();
        Transform3D tower1T = new Transform3D();
        tower1T.setTranslation(new Vector3f(distanceFromCentre, distanceFromCentre, height));
        TransformGroup tower1TG = new TransformGroup();
        tower1TG.setTransform(tower1T);
        tower1TG.addChild(tower1);
        treeTransformGroup.addChild(tower1TG);

        Box tower2 = CastleBody.getTower();
        Transform3D tower2T = new Transform3D();
        tower2T.setTranslation(new Vector3f(-distanceFromCentre, -distanceFromCentre, height));
        TransformGroup tower2TG = new TransformGroup();
        tower2TG.setTransform(tower2T);
        tower2TG.addChild(tower2);
        treeTransformGroup.addChild(tower2TG);

        Box tower3 = CastleBody.getTower();
        Transform3D tower3T = new Transform3D();
        tower3T.setTranslation(new Vector3f(distanceFromCentre, -distanceFromCentre, height));
        TransformGroup tower3TG = new TransformGroup();
        tower3TG.setTransform(tower3T);
        tower3TG.addChild(tower3);
        treeTransformGroup.addChild(tower3TG);

        Box tower4 = CastleBody.getTower();
        Transform3D tower4T = new Transform3D();
        tower4T.setTranslation(new Vector3f(-distanceFromCentre, distanceFromCentre, height));
        TransformGroup tower4TG = new TransformGroup();
        tower4TG.setTransform(tower4T);
        tower4TG.addChild(tower4);
        treeTransformGroup.addChild(tower4TG);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        float delta = 0.03f;

        // rotation of the castle
        treeTransform3D.rotZ(angle);
        treeTransformGroup.setTransform(treeTransform3D);
        angle += delta;

        // change of the camera position up and down within defined limits
        if (eyeHeight > upperEyeLimit){
            descend = true;
        }else if(eyeHeight < lowerEyeLimit){
            descend = false;
        }
        if (descend){
            eyeHeight -= delta;
        }else{
            eyeHeight += delta;
        }

        // change camera distance to the scene
        if (eyeDistance > farthestEyeLimit){
            approaching = true;
        }else if(eyeDistance < nearestEyeLimit){
            approaching = false;
        }
        if (approaching){
            eyeDistance -= delta;
        }else{
            eyeDistance += delta;
        }

        Point3d eye = new Point3d(eyeDistance, eyeDistance, eyeHeight); // spectator's eye
        Point3d center = new Point3d(.0f, .0f ,0.5f); // sight target
        Vector3d up = new Vector3d(.0f, .0f, 1.0f);; // the camera frustum
        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
    }
}
