package sample;

import java.awt.Container;
import javax.media.j3d.*; // for transform
import javax.vecmath.Color3f;
import java.awt.Color;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*; // for Vector3f
import com.sun.j3d.utils.image.TextureLoader;

public class CastleBody {
    public static Box getBody(float height, float width) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(width, width, height, primflags, getBodyAppearence());
    }

    public static Box getTower() {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Box(0.2f, 0.2f, 0.125f, primflags, getCubeTowersAppearence());
    }

    private static Cylinder getCentralTower(float cylinderHeight) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Cylinder(0.10f, cylinderHeight, primflags, getCylTowersAppearence());
    }

    private static Cone getCentralTowerRoof() {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Cone(0.125f, 0.3f, primflags, getRoofAppearence());
    }
    
    private static Cylinder getCentralTowerMain(float cylinderHeight) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Cylinder(0.40f, cylinderHeight, primflags, getCylTowersAppearence());
    }

    private static Cone getCentralTowerRoofMain() {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        return new Cone(0.525f, 0.3f, primflags, getRoofAppearence());
    }

    private static TransformGroup getCover(float x) {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

        TransformGroup tg = new TransformGroup();
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(x, .0f, 0.012f));
        tg.setTransform(transform);
        tg.addChild(new Box(0.008f, 0.008f, 0.008f, primflags, getRoofAppearence()));
        return tg;
    }

    private static TransformGroup getWall() {
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

        TransformGroup tg = new TransformGroup();
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3f(.0f, .0f, .0f));
        tg.setTransform(transform);
        tg.addChild(new Box(0.2f, 0.008f, 0.008f, primflags, getRoofAppearence()));
        return tg;
    }

    private static TransformGroup getFetch() {
        TransformGroup tg = new TransformGroup();
        TransformGroup wall = getWall();
        tg.addChild(wall);
        for (float i=0.192f; i>-0.193f; i-=0.032f){
            TransformGroup cover = getCover(i);
            tg.addChild(cover);
        }
        return tg;
    }

    public static TransformGroup getProtectFetch(float xPos, float yPos, float zPos, boolean turn){
        TransformGroup tg = new TransformGroup();

        TransformGroup fetch3 = CastleBody.getFetch();
        Transform3D fetch3trans = new Transform3D();
        fetch3trans.setTranslation(new Vector3f(xPos, yPos, zPos));
        if (turn){
            fetch3trans.setRotation(new AxisAngle4d(0, 0, 1, Math.toRadians(90)));
        }
        fetch3.setTransform(fetch3trans);
        tg.addChild(fetch3);

        return tg;
    }

    public static TransformGroup getFourFetches() {
        TransformGroup tg = new TransformGroup();
        float distanceFromCentre = 0.192f;
        float zPos = 0.63f;

        TransformGroup fetch1 = CastleBody.getFetch();
        Transform3D fetch1trans = new Transform3D();
        fetch1trans.setTranslation(new Vector3f(.0f, distanceFromCentre, zPos));
        fetch1.setTransform(fetch1trans);
        tg.addChild(fetch1);

        TransformGroup fetch2 = CastleBody.getFetch();
        Transform3D fetch2trans = new Transform3D();
        fetch2trans.setTranslation(new Vector3f(.0f, -distanceFromCentre, zPos));
        fetch2.setTransform(fetch2trans);
        tg.addChild(fetch2);

        TransformGroup fetch3 = CastleBody.getFetch();
        Transform3D fetch3trans = new Transform3D();
        fetch3trans.setTranslation(new Vector3f(distanceFromCentre, .0f, zPos));
        fetch3trans.setRotation(new AxisAngle4d(0, 0, 1, Math.toRadians(90)));
        fetch3.setTransform(fetch3trans);
        tg.addChild(fetch3);

        TransformGroup fetch4 = CastleBody.getFetch();
        Transform3D fetch4trans = new Transform3D();
        fetch4trans.setTranslation(new Vector3f(-distanceFromCentre, .0f, zPos));
        fetch4trans.setRotation(new AxisAngle4d(0, 0, 1, Math.toRadians(90)));
        fetch4.setTransform(fetch4trans);
        tg.addChild(fetch4);
        return tg;
    }

    public static TransformGroup getCylinderTower(float height, float xPos, float yPos){
        TransformGroup tg = new TransformGroup();

        Cylinder centralTower = CastleBody.getCentralTower(height);
        Transform3D centralTowerT = new Transform3D();
        centralTowerT.setTranslation(new Vector3f(xPos, yPos, height*0.5f));
        centralTowerT.setRotation(new AxisAngle4d(1, 0, 0, Math.toRadians(90)));
        TransformGroup centralTowerTG = new TransformGroup();
        centralTowerTG.setTransform(centralTowerT);
        centralTowerTG.addChild(centralTower);
        tg.addChild(centralTowerTG);

        Cone centralTowerRoof = CastleBody.getCentralTowerRoof();
        Transform3D centralTowerRoofT = new Transform3D();
        centralTowerRoofT.setTranslation(new Vector3f(xPos, yPos, height+0.15f));
        centralTowerRoofT.setRotation(new AxisAngle4d(1, 0, 0, Math.toRadians(90)));
        TransformGroup centralTowerRoofTG = new TransformGroup();
        centralTowerRoofTG.setTransform(centralTowerRoofT);
        centralTowerRoofTG.addChild(centralTowerRoof);
        tg.addChild(centralTowerRoofTG);

        return tg;
    }
    
    public static TransformGroup getCylinderTowerMain(float height, float xPos, float yPos){
        TransformGroup tg = new TransformGroup();

        Cylinder centralTower = CastleBody.getCentralTowerMain(height);
        Transform3D centralTowerT = new Transform3D();
        centralTowerT.setTranslation(new Vector3f(xPos, yPos, height*0.5f));
        centralTowerT.setRotation(new AxisAngle4d(1, 0, 0, Math.toRadians(90)));
        TransformGroup centralTowerTG = new TransformGroup();
        centralTowerTG.setTransform(centralTowerT);
        centralTowerTG.addChild(centralTower);
        tg.addChild(centralTowerTG);

        Cone centralTowerRoof = CastleBody.getCentralTowerRoofMain();
        Transform3D centralTowerRoofT = new Transform3D();
        centralTowerRoofT.setTranslation(new Vector3f(xPos, yPos, height+0.15f));
        centralTowerRoofT.setRotation(new AxisAngle4d(1, 0, 0, Math.toRadians(90)));
        TransformGroup centralTowerRoofTG = new TransformGroup();
        centralTowerRoofTG.setTransform(centralTowerRoofT);
        centralTowerRoofTG.addChild(centralTowerRoof);
        tg.addChild(centralTowerRoofTG);

        return tg;
    }

    private static Appearance getBodyAppearence() {

        TextureLoader loader = new TextureLoader("source_folder\\stoneWall.jpg", "LUMINANCE", new
                Container());

        Texture texture = loader.getTexture();

        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(51f, 48f, 48f, 1f));


        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(72,72,72));
        Color3f diffuse = new Color3f(new Color(230,230, 230));
        Color3f specular = new Color3f(new Color(0,0, 0));

        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getRoofAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(72,72,72));
        Color3f diffuse = new Color3f(new Color(20,38, 38));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getCubeTowersAppearence() {

        TextureLoader loader = new TextureLoader("source_folder\\woodenFloor.jpg", "LUMINANCE", new
                Container());

        Texture texture = loader.getTexture();

        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));


        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(72,72,72));
        Color3f diffuse = new Color3f(new Color(230,230, 230));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getCylTowersAppearence() {

        TextureLoader loader = new TextureLoader("source_folder\\WhiteWall.jpg", "LUMINANCE", new Container());
        Texture texture = loader.getTexture();
        

        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));


        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(230,230, 230));
        Color3f diffuse = new Color3f(new Color(72,72,72));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}
