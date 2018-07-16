package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;



/**
 * Coba.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Coba implements GLEventListener, KeyListener {
        //mengatur tombol
    static KeyListener key_listener = new KeyListener() {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_B) {
                if (balik) {
                    balik = false;
                } else {
                    balik = true;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_N) {
                if (stop) {
                    stop = false;
                } else {
                    stop = true;
                }
            }
            float speed = 4;
            if (e.getKeyCode() == KeyEvent.VK_W) {
                sudut_camerax = sudut_camerax - speed;

            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                sudut_cameray = sudut_cameray + speed;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                sudut_camerax = sudut_camerax + speed;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                sudut_cameray = sudut_cameray - speed;
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                sudut_cameraz = sudut_cameraz + speed;
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                sudut_cameraz = sudut_cameraz - speed;
            }
            
            
            if (e.getKeyCode() == KeyEvent.VK_Z) {
                zoom=zoom + 0.1f;
            }
            if (e.getKeyCode() == KeyEvent.VK_X) {
                zoom=zoom - 0.1f;
            }

        }

        public void keyReleased(KeyEvent e) {
        }
    };

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();
        Texture texture, texture1;
        canvas.addGLEventListener(new Coba());
        frame.add(canvas);
        //keylistener
        frame.addKeyListener(key_listener);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        try {
            Matahari = TextureIO.newTexture(new File("Sun.bmp"),true);
            Bumi = TextureIO.newTexture(new File("Earth.bmp"),true);
            bulan =TextureIO.newTexture(new File("moon.jpg"),true);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        gl.glEnable(GL.GL_DEPTH_TEST);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    float sudut = 0;
    float sudut1 = 0;
    Texture Matahari, Bumi,bulan;
    //variabel mengontrol balik arah putaran
    //variabel balik diatur dengan tombol b di fungsi keypressed
    static boolean balik = false;
    static boolean stop = false;

    static float sudut_camerax = 0;
    static float sudut_cameray = 0;
    static float sudut_cameraz = 0;
    
    static float zoom=0;
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        
       //mengecek putaran balik arah atau tidak
        if (!stop) {
            if (balik) {
                sudut += 2;
                sudut1 += 0.5;
            } else {
                sudut -= 2;
                sudut1 -= 0.5;
            }
        }

        GLU glu = new GLU();
        //glu.gluLookAt(camerax, cameray, cameraz+3, targetx, targety, targetz, 0, 1, 0 );
        GLUquadric q = glu.gluNewQuadric();
        //zoom out/in
        gl.glTranslatef(0, 0, -6+zoom);
        
        //perputaran kamera
        gl.glRotatef(sudut_camerax, 1, 0, 0);
        gl.glRotatef(sudut_cameray, 0, 1, 0);
        gl.glRotatef(sudut_cameraz, 0, 0, 1);

        //perputaran matahari dan planet2
        gl.glRotatef(sudut, 0, 0, 1);
        
        //matahari
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        Matahari.enable();
        Matahari.bind();
        glu.gluQuadricTexture(q, true);
        glu.gluSphere(q, 1, 60, 60);
        Matahari.disable();
        gl.glPopMatrix();
        
        //bumi
        
        gl.glPushMatrix();
        gl.glTranslatef(5.0f, 0.0f, 0f);
        Bumi.enable();
        Bumi.bind();
        gl.glRotatef(sudut, 0, 1, 0);
        glu.gluQuadricTexture(q, true);
        glu.gluSphere(q, 0.4, 60, 60);
        Bumi.disable();

        //putaran bulan
        gl.glRotatef(sudut*-2, 0, 0, 1);
        gl.glTranslatef(2.0f, 0.0f, 1f);
       
        //bulan
        gl.glPushMatrix();
        gl.glTranslatef(-3.0f, 0.0f, -1f);
        bulan.enable();
        bulan.bind();
        gl.glRotatef(sudut, 0, 1, 0);
        glu.gluQuadricTexture(q, true);
        glu.gluSphere(q, 0.2, 60, 60);
        bulan.disable();
        gl.glPopMatrix();
       
        gl.glFlush();
        
       
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

