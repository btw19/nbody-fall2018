
public class Body {
	
private double myXVel;
private double myYVel;
private double myYPos;
private double myXPos;
private double myMass;
private String myFilename;

/**
 * Creates a Body based on parameters
 * @param x
 * @param y
 * @param xv
 * @param yv
 * @param mass
 * @param filename
 */
 public Body( double x, double y, double xv, double yv,
		 double mass, String filename) {
	 myXVel = xv;
	 myYVel = yv;
	 myXPos = x;
	 myYPos = y;
	 myMass = mass;
	 myFilename = filename;
 }
 
 /**
  * Copy Constructor: copy instance variables from one 
  * body to this body
  * @param b used to initialize this body
  */
 public Body(Body b) {
	 this.myXVel = b.myXVel;
	 this.myYVel = b.myYVel;
	 this.myXPos = b.myXPos;
	 this.myYPos = b.myYPos;
	 this.myMass = b.myMass;
	 this.myFilename = b.myFilename;
 }
 
 /**
  * Get and return the location of the body in terms of X
  * @return returns the X position of this body
  */
 public double getX() {
	 return myXPos;
 }
 
 /**
  * Get and return the location of the body in terms of Y
  * @return returns the Y position of this body
  */
 public double getY() {
	 return myYPos;
 }
 
 /**
  * Get and return the velocity in the x direction of the body
  * @return returns the X velocity of this body
  */
 public double getXVel() {
	 return myXVel;
 }
 
 /**
  * Get and return the velocity in the y direction of the body
  * @return returns the Y velocity of this body
  */
 public double getYVel() {
	 return myYVel;
 }
 
 /**
  * Get and return the mass of the body
  * @return returns the mass of this body
  */
 public double getMass() {
	 return myMass;
 }
 
 /**
  * Get and return the name of the body from its file
  * @return returns the file name of this body
  */
 public String getName() {
	 return myFilename;
 }
 
 /**
  * Return the distance between this body and another
  * @param b the other body to which the distance is calculated
  * @return distance between this body and body b
  */
 public double calcDistance(Body b) {
	 double dx = myXPos - b.myXPos;
	 double dy = myYPos - b.myYPos;
	 return Math.sqrt(dx*dx + dy*dy);
 }
 
 /**
  * Return the force exerted by one body on another based on distance
  * @param b the other body for which the force is calculated
  * @return the gravitational force on the first body by body b
  */
 public double calcForceExertedBy(Body b) {
	 double r = calcDistance(b);
	 return (6.67*1e-11)*((myMass * b.myMass)/(r*r));
 }
 
 /**
  * Return the force exerted specifically in the x direction from another body
  * @param b the other body for which the force is calculated
  * @return the gravitational force in the x direction by body b
  */
 public double calcForceExertedByX(Body b) {
	 double dx = b.myXPos - myXPos;
	 double r = calcDistance(b);
	 return calcForceExertedBy(b) * (dx / r);
 }
 
 /**
  * Return the force exerted specifically in the y direction from another body
  * @param b the other body for which the force is calculated
  * @return the gravitational force in the y direction by body b
  */
 public double calcForceExertedByY(Body b) {
	 double dy = b.myYPos - myYPos;
	 double r = calcDistance(b);
	 return calcForceExertedBy(b) * (dy / r);
 }
 
 /**
  * Return the summation of all the forces in the x direction from all the bodies in the system
  * @param bodies the total number of other bodies present in the simulation
  * @return the total gravitational forces in the x direction from all the bodies in the system
  */
 public double calcNetForceExertedByX(Body[] bodies) {
	 double sum1 = 0;
	 for(Body b : bodies) {
		 if (! b.equals(this)) {
			 sum1 += calcForceExertedByX(b);
		 }
	 }
	 return sum1;
 }

 /**
  * Return the summation of all the forces in the y direction from all the bodies in the system
  * @param bodies the total number of other bodies present in the simulation
  * @return the total gravitational forces in the y direction from all the bodies in the system
  */
 public double calcNetForceExertedByY(Body[] bodies) {
	 double sum2 = 0;
	 for(Body b : bodies) {
		 if (! b.equals(this)) {
			 sum2 += calcForceExertedByY(b);
		 }
	 }
	 return sum2;
 }
 
 /**
  * Return the updated values of all the variables for each body based on a small movement in time deltaT
  * @param deltaT a small time step value
  * @param xforce force in the x direction used to calculate acceleration
  * @param yforce force in the y direction used to calculate acceleration
  */
 public void update(double deltaT, double xforce, double yforce) {
	 double ax = 0;
	 double ay = 0;
	 double nvx = 0;
	 double nvy = 0;
	 double nx = 0;
	 double ny = 0;
	 
	 ax = xforce/this.myMass;
	 ay = yforce/this.myMass;
	 
	 nvx = this.myXVel + deltaT * ax;
	 nvy = this.myYVel + deltaT * ay;
	 
	 nx = this.myXPos + deltaT * nvx;
	 ny = this.myYPos + deltaT * nvy;
	 
	 myXPos = nx;
	 myXVel = nvx;
	 myYPos = ny;
	 myYVel = nvy;
	 
 }
 
 /**
  * A method that draws each of the bodies using their file name and constantly updating X and Y positions
  */
 public void draw () {
	 StdDraw.picture(myXPos, myYPos, "images/"+myFilename);
 }
}
