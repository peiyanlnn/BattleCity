# Game Development Report

Peiyan LIN 

20201287



This game is called Defend Bottles, which is a shooting game. Players win by dodging enemy shots and shooting at them.



First, I enrich the content of the menu page. Two buttons have been added, and two different game modes can be entered by clicking the buttons. To introduce the game, I put some pictures on the menu. The picture shows the main operation buttons in the game. Through these pictures, simply introduced the method of game operation.



![image-20230305181404182](/Users/linpeiyan/Library/Application Support/typora-user-images/image-20230305181404182.png)



The panel area in the upper left corner shows the current progress of the game. The player is initialized in the middle of the map. Enemies will spawn and attack from both sides of the game board.By randomly adding walls, each game has a different map display. 



![image-20230305181620505](/Users/linpeiyan/Library/Application Support/typora-user-images/image-20230305181620505.png)



The game is divided into single player mode and double player mode. Two-player mode allows two players to play at the same time. In the two-player mode, two players will belong to the same camp and fight against the enemy together.



During the game, I added some keyboard events so that the player can adjust the direction of the character and fire bullets according to the keyboard. Players can use 'A', 'W', 'S', 'D' and arrow key to modify the direction of players. 'Space' and 'K' are used to create bullet.



I also added collision detection. The game  generates some enemies. These enemies automatically adjust their direction and shoot. When the bullet hits, the bullet and the hit object disappear. And produce explosion effects and explosion sound effects. I added several different music effects to the game. For example, explosion sound, game failure sound, game success sound.



The number of bottles is also a reflection of lives. When all the bottles are destroyed, the player's lives are reset to zero and the game is over. After the game is over, you can return to the menu interface and restart the game by simply pressing a button.
