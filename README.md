# 5004Project
5004_drawboard_final_project


## What is this project and how it works

This project displays animation following users' valid instructions by producing and showing the text description of the animation. In this application, in order to display an animation, an animation interface allows a user to add shapes and to define moves on the existing shapes.

In this application, there are 3 interfaces in total, they are: IAnimation, IShape and IMovement. An animation can have zero to many shapes, and a shape can have zero to many movements. We will discuss the purpose of each interface and each class in the following section.


## Purpose behind each interface and each class

### IAnimation and AnimationImpl
#### IAnimation
IAnimation interface contains all the operations that apply to all animations. This interface allows an user:
-	to add a shape to the animation by passing in a shape; 
-	to define a movement on a shape (possible movements includes to change color of a shape, to move its position or to scale its dimensions); 
-	to show (initial status) of all shapes that are existing in this animation;
-	to get all movements or to display the animation;
-	to collect and show the status of all appearing shapes at certain point of time

#### AnimationImpl
This IAnimation interface is implemented in AnimationImpl class, where it has list of all shapes that ever existed in the animation to keep and track data to achieve all the above operations in an animation. 

### IShape, AbstractShape and concrete shape classes 
#### IShape
IShape interface contains operations that applies to all shapes. As mentioned above, a shape can have zero to many movements as long as 1, the shape is appearing during the desired movement time, and 2, the shape is available for certain move during the desired movement time, which means the shape is not occupied with the same type of movement during the desired movement time. Therefore, a shape should be able to operate the following on itself:
-	to make a movement on itself. Possible change includes to change its color, to change its dimension, to move its position;
-	to get a copy of itself;
-	to generate a list of all movements that apply to itself.

#### Rectangle, Circle and Oval
This IShape interface is implemented in many specific concrete shape classes, such as Oval class, Circle class, Rectangle class, etc., and each of the concrete class has the following fields to help track data and achieve all the operations mentioned in the IShape interface:
-	basic data (name, type, color, appear time, disappear time) that used to initialize itself;
-	list of 1’s and 0’s that indicates a status that whether if itself is available for certain movement at a specific time point;
-	lists of Color, Point2D and its dimension to track the data of itself at any point of time.

#### AbstractShape
As there are many common operations and fields that all the these concrete shapes share, we created an abstract class called AbstractedShape that implements IShape interface to represent all the common fields and common methods, and all concrete shape classes extend the AbstractShape.

### Movement and concrete movement classes
#### Movement
Movement interface includes operations that applies to all movement classes. The only operations included in a Movement interface is to display itself and get the start time of when the movement starts. As for now we are using a text description to display animations, so the display operation in Movement also returns a text description in String. 

#### Move, ColorChange, Scale
Concrete movement classes includes Move, ColorChange and Scale. Each of them performs a specific type of movement to a shape. All of them implements the display methods in String format. 
-	Move: it moves a shape to a target position by the end of the movement;
-	ColorChange: it changes the color of a shape to a target color by the end of the movement;
-	Scale: it scales the dimensions of a shape by multiplying its current dimension to a scaling factor by the end of the movement.

### Other classes
#### Point2D
To represent the position of a shape, we need a x value and y value which represents the shape’s position on x axis and y axis. As x value and y value are always showing or using in a pair, we created a Point2D class that represents the position of (x, y). 

#### ShapeType
A shape type would help us to organize and track data of various shape types. As we want to avoid random types of shape that the application does not recognize, we decided to restrict the type of shapes that are allowed in this animation. Therefore, we made ShapeType enum. For now, ShapeType includes CIRCLE, OVAL and RECTANGLE.
