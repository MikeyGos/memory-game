# Project overview 
The game was created for a Polish school in Dublin for internal use. They couldn't use commercial games, so I took the initiative to create a game for them. 

## Game recording
https://www.youtube.com/watch?v=cFkvu2rMd-w

Without downloading the game, you can see in the video (above) how it looks in practice.

## How to open
```git clone https://github.com/MikeyGos/memory-game.git```

```cd \memory-game\out\artifacts\memory_game_jar ```

```java -jar memory-game.jar```

## Creating a game
Creating the game gave me a lot of joy (I like playing), but also a lot of problems. Before I started the JavaFX project, it was completely unknown to me, but with the help of Gluon I managed to create the first window, the first tiles.

Later I encountered many problems, such as:

 - animation errors:
After the game ends, a window pops up about winning or losing (unfortunately, the recording program did not record it).
![Windows on the screen](src/main/resources/pl/PolishSchoolInDublin/windowsPlayAgain.jpg)
When the AI ​​won, an error popped up.

To solve this problem, I invented the function 


Platform.runLater(this::showWinningMessage)


which told the GUI to display the window only after the cards finished animating.

 - possibility to click during the AI's turn:
 
the player could accidentally click during the AI's turn,
which caused disruptions in the game, sometimes the game stopped working, sometimes an error appeared.
The method that helped me is:

protected void disableUserInteraction(boolean disable) {

	for (Node node : imageFlowPane.getChildren()) {
 
			node.setDisable(disable);
	}
}

This method disables the possibility of user interaction during AI movement.
It was enabled when the AI ​​movement started and disabled after it ended.
## Tech/framework used

 - Java
 - JavaFX
 - Gluon
 - Maven
 - JUnit 5

