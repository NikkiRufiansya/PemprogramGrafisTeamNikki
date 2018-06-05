package org.yourorghere;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
public class GLRenderer implements GLEventListener {
Objek Tabung=null;
float R_Z=0.0f, R_X=0.0f, R_Y=0.0f;
float T_Z= -16.0f, T_X=0.0f, T_Y=0.0f;
GLAutoDrawable this_drawable=null;
public void init(GLAutoDrawable drawable) {
this_drawable=drawable;
GL gl = drawable.getGL();
gl.setSwapInterval(1);
float ambient[] = {1.0f,1.0f,1.0f,1.0f };
float diffuse[] = {1.0f,1.0f,1.0f,1.0f };
float position[] = {1.0f,1.0f,1.0f,0.0f };
gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient,0);
gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse,0);
gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position,0);
gl.glEnable(GL.GL_LIGHT0);
gl.glEnable(GL.GL_LIGHTING);
gl.glEnable(GL.GL_DEPTH_TEST);
gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
gl.glShadeModel(GL.GL_SMOOTH);
Tabung=new Objek();
}
public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
GL gl = drawable.getGL();
GLU glu = new GLU();
if (height <= 0) { height = 1;}
final float h = (float) width / (float) height;
gl.glViewport(30, 6, width, height);
gl.glMatrixMode(GL.GL_PROJECTION);
gl.glLoadIdentity();
glu.gluPerspective(45.0f, h, 1.0, 20.0);
gl.glMatrixMode(GL.GL_MODELVIEW);
gl.glLoadIdentity();
}
public void display(GLAutoDrawable drawable) {
GL gl = drawable.getGL();
gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
gl.glLoadIdentity();
//Perintah untuk Translasi sesuai nilai X,Y dan Z
gl.glTranslatef(-T_X, T_Y, T_Z);
// Membuat posisi objek tabung berdiri tegak
gl.glRotatef(-90.0f,1.0f,0.0f,0.0f);
gl.glRotatef(180.0f,0.0f,0.0f,1.0f);
//Perintah untuk Rotasi sesuai nilai X,Y dan Z
gl.glRotatef(R_Z,0.0f,0.0f,1.0f);
gl.glRotatef(R_X,1.0f,0.0f,0.0f);
gl.glRotatef(R_Y,0.0f,1.0f,0.0f);
Objek.Bola(gl);
gl.glFlush();
}
public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
}
public void setBasicRotation(float z,float x)
{
R_Z=z;
R_X=x;
}
public void R_Tambah_Z(boolean on){ R_Z=R_Z+30.0f;}
public void R_Tambah_X(boolean on){ R_X=R_X+30.0f;}
public void R_Tambah_Y(boolean on){ R_Y=R_Y+30.0f;}
public void T_Tambah_Z(boolean on){ T_Z=T_Z+ 1.0f;}
public void T_Tambah_X(boolean on){ T_X=T_X+ 1.0f;}
public void T_Tambah_Y(boolean on){ T_Y=T_Y+ 1.0f;}
}
