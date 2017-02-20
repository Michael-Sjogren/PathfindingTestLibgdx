package com.mygdx.game;

import java.util.ArrayList;

/**
 * _______________________________________________________________________________________________
 * ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
 Add the start position to the open list

 While open list is not empty:

 Set the current to the tile with the lowest F cost

 If the current is at the end:
 While current has a parent:
 Set the current to its parent
 Add the current to the path list
 return path list inverted

 Put the current in the closed list and take it out of the open list

 Set adjacent list equal to current valid adjacent tiles (valid means not in a wall and withing grid parameters)

 For adjacent tile in adjacent list:
 If adjacent tile is in the closed list: skip this tile
 If adjacent tile is not in the open list: set its scores, set its parent, add it to the open list
 else If the new G score will be better than its current G score lower, update the tile

 If we get here, it means the open list is empty, and we didn't find a path :(
 Return nothing ;(
 _______________________________________________________________________________________________
 ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
 * **/
public class AStar {

    private ArrayList<Tile> openList = new ArrayList<>();
    private ArrayList<Tile> closedList = new ArrayList<>();

    public void getStartTile(Tile startTile){
        openList.add(startTile);
    }

    public void



}
