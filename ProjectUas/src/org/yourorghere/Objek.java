
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author Ashlihlana
 */
public class Objek {
   
    static void Bola(GL gl){
        float BODY_RADIUS=2.0f;
        int SLICES=30;
        int STACKS=30;
        GLU glu=new GLU();
        GLUquadric q=glu.gluNewQuadric();
        glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);
    }
   
    
}
