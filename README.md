# 5004Project
5004_drawboard_final_project


## Whhat is this project and how it works

This project displays animation following users' valid instructions by producing and showing the text description of the animation. In this application, in order to display an animation, an animation interface allows a user to add shapes and to define moves on the existing shapes.

In this application, there are 3 interfaces in total, they are: IAnimation, IShape and IMovement. An animation can have zero to many shapes, and a shape can have zero to many movements. We will discuss the purpose of each interface and each class in the following section.


## Purpose behind each interface and each class

### IAnimation and AnimationImpl
IAnimation interface contains all the operations that apply to all animations. 
