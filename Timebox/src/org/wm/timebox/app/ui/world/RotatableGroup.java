/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wm.timebox.app.ui.world;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 *
 * @author Andi
 */
public class RotatableGroup extends Group{
        public final Rotate rotateX = new Rotate(0,Rotate.X_AXIS);
        public final Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);
        public final Rotate rotateZ = new Rotate(0,Rotate.Z_AXIS);

        public final Translate translate = new Translate();
        
        public RotatableGroup(){
            getTransforms().addAll(rotateX, rotateY, rotateZ, translate);
        }
}
