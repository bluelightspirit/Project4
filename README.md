# Pokémane Battle Simulator (Group Project 4)
## A Java game displayed in JFrame where the player chooses their own 3 pokemon to battle against another 3 enemy pokemon also selectable, unless when running from QuickstartGame instead

## How does it work and how does anyone play?

This is based on using `JFrame` & `JOptionPane` from Java, along with the console to take in input on what player pokemon and enemy pokemon to play with.
The console asks for the file name to store data within the PokemonData folder, then two things can happen:
1) Within [QuickstartGame](https://github.com/bluelightspirit/Project4/blob/main/QuickstartGame.java), the game is started with randomized Pokemon moves. For more details on the GUI portion specifics, check the [GUI Portion](https://github.com/bluelightspirit/Project4/new/main?readme=1#gui-portion).

2) Within [PokemonGame](https://github.com/bluelightspirit/Project4/blob/main/PokemonGame.java), the console continues to ask for Pokemon names, levels, and move names until 6 total Pokemon are inputted, then the game is started.
For more details on the console portion specifics, check the [Console Portion](https://github.com/bluelightspirit/Project4/new/main?readme=1#console-portion).

NOTE: The internet is required for the sprites to show properly on the screen.

### Console Portion
First, for the console portion before the game starts, the name of the game file is asked.

![image](https://user-images.githubusercontent.com/22280271/236382805-fb5a3a39-b85a-4c15-b33c-58e9915c2157.png)

Then, with [QuickstartGame](https://github.com/bluelightspirit/Project4/blob/main/QuickstartGame.java), the game will automatically start from then on. Skip to [here](https://github.com/bluelightspirit/Project4/new/main?readme=1#gui-portion) if you plan to run with QuickstartGame rather than PokemonGame.

Second, within [PokemonGame](https://github.com/bluelightspirit/Project4/blob/main/PokemonGame.java) only, the name of the pokemon is requested to be added. Capitalization of the name perfectly along with the symbols dependent on the Pokemon name are required. This can be found in [name.txt](https://github.com/bluelightspirit/Project4/blob/main/.idea/name.txt).
Inputting "R" will randomize the Pokemon name, set the level to 50 automatically, AND randomize the moves, skipping the third and fourth steps.
For better reference, the Pokemon used are up to Generation 7.
![image](https://user-images.githubusercontent.com/22280271/236382989-f5c0ac89-1564-4b96-a991-708a9371e5dc.png)

Third, the level of the Pokemon will be requested from 1-100. A higher level would mean the moves would be stronger, while a lower level would mean the moves would be weaker.
![image](https://user-images.githubusercontent.com/22280271/236383218-f5642724-c051-4c57-8857-f60c4a675fd3.png)

Fourth, the moves of the Pokemon will be requested. Four moves will be requested to be inputted and the capitalization of the move name perfectly along with the symbols dependent on the Pokemon name are required. This can be found in [moveName.txt](https://github.com/bluelightspirit/Project4/blob/main/.idea/moveName.txt).
Inputting "R" will randomize the Pokemon move.
![image](https://user-images.githubusercontent.com/22280271/236383350-55edd0d4-013d-4803-a8e4-c5053cebbaf0.png)

From then on until 6 Pokemon are inputted, 3 for the player's team and 3 for the enemy's team, repeating the second to fourth steps until 6 total Pokemon are inputted.

After 6 total Pokemon are inputted, the game shall start.

### GUI Portion
After the game is started, it will display a GUI that looks like this:
![image](https://user-images.githubusercontent.com/22280271/236383998-05e202d4-1d4d-4940-ac5e-5e331ddca965.png)

Pressing moves will cause either the enemy Pokemon or the player Pokemon to attack first, depending on who has battle priority which is determined by their speed in [Battle.java](https://github.com/bluelightspirit/Project4/blob/main/Battle.java).
After one Pokemon attacks first, the other Pokemon will attack right after UNLESS they have fainted from the first Pokemon's attack.
![image](https://user-images.githubusercontent.com/22280271/236384226-1dceee87-98fb-47d9-905a-9bffe2b1a49f.png)

This should be somewhat like the GUI looks like after pressing a move.

On Pokemon faint, the Pokemon will automatically be swapped either for the enemy or the player:

![image](https://user-images.githubusercontent.com/22280271/236384283-b43333af-0b49-40e6-aed9-15f374038694.png)

As seen here, Pichu (the enemy in this case) attempted to cast a move first, but did not have priority over Tauros so fainted before doing anything:

![image](https://user-images.githubusercontent.com/22280271/236384535-7849d53d-02e4-46c1-bf74-cf10e5effcf6.png)

The Swap Pokemon button would display a `JOptionPane` looking like this:

![image](https://user-images.githubusercontent.com/22280271/236384604-42a5f9a0-728b-4236-932f-59594f3ddf0e.png)

Swapping to a dead Pokemon will not swap your Pokemon, as shown here:

![image](https://user-images.githubusercontent.com/22280271/236384726-50d387d0-b451-49e4-b1ac-97abc1ad16b8.png)

The player can swap between Pokemon as much as they want, however, as soon as they choose to perform a move, the battle will go on.

A successful swap is found in the console in the background here:

![image](https://user-images.githubusercontent.com/22280271/236384935-f67caafb-7cf7-42c1-85b3-8ca3087ed8ab.png)

After all 3 Pokemon faint on either the Player team or Enemy team, that team loses and the other side wins.

For example here, all 3 player Pokemon died so the player lost, both shown in the console and the GUI:

![image](https://user-images.githubusercontent.com/22280271/236385087-659e9261-52e4-48dd-85ef-62f40d5605a9.png)

The moves will automatically be disabled after any side wins and the Swap Pokemon button will only be disabled if the player loses, in case they're curious on the health of their Pokemon.

## Why is the game titled "Pokémane Battle Simulator" at the top?

Because one of my friends was debating to name it "Pokimane Battle Simulator," another was debating to name it "Pokémon Battle Simulator," and I settled for an in-between.

## What did I (bluelightspirit) learn?

1) How to combine skills from my MUSIX project (GUI based) and my SpacedRepetitionSystem (Multi-Object based) project.
2) How to connect back-end (Pokemon data) and front-end (the GUI), given that the back-end was mostly handled by my 2 friends.
3) How to make an organized GUI class, seperating many tasks into methods.
4) How to get color brightness given Color parameters.
5) How to use a HashMap.
6) How to use BigDecimal for the percentages in the Pokemon health bars.
7) How to use JProgressBar.
8) How to make animations using a Java Swing Timer ActionListener within JProgessBar.
9) How to change the text color from white to black if the brightness of a move's background was high enough.
10) How to disable buttons.
11) How to set fonts outside of system-default fonts using Java's GraphicsEnvironment.registerFont() method.
12) How to use FlowLayout within the movesPanel JPanel.
13) How to use BoxLayout by having four JPanel's be connected by a main JPanel, which then could be organized with BoxLayout.
14) How to use the internet to get sprite images from the PokeAPI's sprites.
15) How to change the cursor on mouse hover.
16) How to make the panels pack using this.pack() and CSS to align the text to the center.
17) (Subset of 1) How to connect colors of moves given from a Pokemon to the background.
18) (Subset of 1) How to swap Pokemon given from a Pokemon ArrayList.
19) (Subset of 1) How to overall connect Pokemon objects in an ArrayList from a class outside of the GUI and get and use those within the GUI.
20) (Subset of 1) How to connect methods from outside classes within the PokeGUI class, such as Battle.priority which took in two Pokemon object parameters.

## What goal(s) did I accomplish?

I (bluelightspirit) along with 2 friends created a game where the player plays a Pokemon Battle Simulator to battle AI pokemon and determine which 3 Pokemon may be stronger than another 3, which
  depends on luck and strategies on what moves to use along with what Pokemon to use!

## Compiling

This program uses solely Java to compile.
