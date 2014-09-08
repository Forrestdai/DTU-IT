package org.me;

     import java.applet.Applet;
     import java.awt.Graphics;

     public class NewApplet extends Applet {
         @Override
         public void paint(Graphics g) {
             g.drawString("Hello applet!", 50, 25);
         }
     }