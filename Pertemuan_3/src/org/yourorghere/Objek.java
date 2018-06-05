package org.yourorghere;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
public class Objek {
static void Bola(GL gl){
float BODY_RADIUS=1.0f;
int SLICES=200;
int STACKS=200;
GLU glu=new GLU();
GLUquadric q=glu.gluNewQuadric();
glu.gluSphere(q, BODY_RADIUS, SLICES, STACKS);
}
}
