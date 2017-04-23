# Introduction to rubik

I started this project because I can't finish my Rubik's cube.

I happened to be learning Clojure at the time I was struggling with the cube,
so I thought I'd spend some time modelling the cube in code and see if that
helped me understand it a little better. 

I hope the project will have three stages.

First, I'll build a data structure to hold the cube and write some functions to manipulate it.
This stage will result in an ASCII interface which will let you write something like
`rotate row E1 clockwise` and `print E`, and it'll show you the E face of the cube.

The cube will be modelled as having four faces: North, South, East and West, plus Up and Down.

I'm going to use the data structure outlined in this Stack Exchange Software Engineering answer:
https://softwareengineering.stackexchange.com/a/262847

Second, I'll render the output of the program using some fancy Java library or other.

Finally, I'll look into how I can get it to solve the cube.
