# Maze-Solver

The solver needs input txt files in the same style as those in the Testers folder
Compile and run the maze solver file and follow the instructions


# Maze input/output formats
=========================

The input is a maze description file in plain text.  
 1 - denotes walls
 0 - traversable passage way

INPUT:
<WIDTH> <HEIGHT><CR>
<START_X> <START_Y><CR>		(x,y) location of the start. (0,0) is upper left and (width-1,height-1) is lower right
<END_X> <END_Y><CR>		    (x,y) location of the end
<HEIGHT> rows where each row has <WIDTH> {0,1} integers space delimited

OUTPUT:
 the maze with a path from start to end
 walls marked by '\#', passages marked by ' ', path marked by 'X', start/end marked by 'S'/'E'

Example file:  
10 10\
1 1\
8 8\
1 1 1 1 1 1 1 1 1 1\
1 0 0 0 0 0 0 0 0 1\
1 0 1 0 1 1 1 1 1 1\
1 0 1 0 0 0 0 0 0 1\
1 0 1 1 0 1 0 1 1 1\
1 0 1 0 0 1 0 1 0 1\
1 0 1 0 0 0 0 0 0 1\
1 0 1 1 1 0 1 1 1 1\
1 0 1 0 0 0 0 0 0 1\
1 1 1 1 1 1 1 1 1 1

Example output:\
\##########\
\#SXX     #\
\# #X######\
\# #XX    #\
\# ##X# ###\
\# # X# # #\
\# # XX   #\
\# ###X####\
\# #  XXXE#\
\##########