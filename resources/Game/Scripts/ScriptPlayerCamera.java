package Game.Scripts;

import Engine.Main.Entity;
import Engine.Main.Material;
import Engine.System.Component.Transform;
import Engine.System.Graphics.Component.Mesh3D;
import Engine.System.Scripting.BaseScript;
import Engine.System.Scripting.Callback;
import Engine.System.Sound.Component.Source;
import org.joml.Vector3f;

import java.util.List;

/**
 * @author Noemy Artigouha
 */
public class ScriptPlayerCamera extends BaseScript {

    private List<Integer> entititesPlayer;
    private List<Integer> componentsCamera;
    private List<Integer> componentsPlayer;
    private float rayon;

    public void awake() {
        this.entititesPlayer = this.getEntitiesWithTag("player");
        this.componentsCamera = this.getComponents(Transform.class);
        this.componentsPlayer = this.getComponentsFromEntity(this.entititesPlayer.get(0),Transform.class);
        rayon = 2.0f;
    }

    public void start() {
        System.out.println("Methode Start called !!");
    }

    public void update() {

        //set camera rotation thanks to player rotation
        Callback callbackRotation = new Callback() {
            @Override
            public void call(Object result) {
                Vector3f rot = (Vector3f) result;
                // Call a specific method on this component with his id.
                callMethodComponent(componentsCamera.get(0), "setRotation", new Vector3f(rot.x+30, rot.y, rot.z));

                //set camera position thanks to player position
                Callback callbackPosition = new Callback() {
                    @Override
                    public void call(Object result) {
                        Vector3f pos = (Vector3f) result;
                        float angle = (float) (rot.y * (Math.PI / 180));
                        // Call a specific method on this component with his id.
                        callMethodComponent(componentsCamera.get(0), "setPosition",
                                new Vector3f(pos.x - (float)Math.sin(angle)*rayon,
                                        pos.y + 1.5f,
                                        pos.z + rayon + ( (float)Math.cos(angle)*rayon - rayon) ));
                    }
                };
                //get player position
                callReturnMethodComponent(componentsPlayer.get(0), "getPosition", null, callbackPosition);
            }
        };
        //get player rotation
        callReturnMethodComponent(componentsPlayer.get(0), "getRotation", null, callbackRotation);
    }

}