# Line Shortenment Write-Up
## 561 lines -> 88 lines Via Modularization Proof
[This commit](https://github.com/bluelightspirit/Project4/commit/9a57954fbf176656cbfe004b49f4d7c2db0303ef) is right before the modularization rework. If you click here, then go to Browse Files, then go to line 424, or just click [here](https://github.com/bluelightspirit/Project4/blob/9a57954fbf176656cbfe004b49f4d7c2db0303ef/PokeGUI.java) then Ctrl+F or any other find tool "actionPerformed" and look for the second one that should redirect you to line 424, then you can see the old code for actions to do when a button is pressed. It looks something like this:

<img width="1173" height="906" alt="image" src="https://github.com/user-attachments/assets/2569d081-345f-42c8-9430-133c3aab1281" />

If you press the arrow key on the left, it spans from line 424 to line 984 - shown below. 

<img width="490" height="86" alt="image" src="https://github.com/user-attachments/assets/4682ad24-b065-49ca-9e31-550b24b874ad" />

Since lines 1 to 2 would be equal to 2 lines, and 2-1 = 1 line, where 1 has to be added to be correct, the math leads to 984-424+1=561 lines (I include the @Override).
[This commit](https://github.com/bluelightspirit/Project4/commit/562a183ce39125c19ccaccef9b082010889d138b) is the main final commit of the modularization rework. There are several commits related to modularization - but this write-up is meant to be simplistic to prove claims. If you follow the previous steps or just click [here](https://github.com/bluelightspirit/Project4/blob/562a183ce39125c19ccaccef9b082010889d138b/PokeGUI.java) then go to line 426 via Ctrl+F or any find tool looking up "actionPerformed" (the second actionPerformed found at line 426), then you can see the new refactored code.

<img width="483" height="89" alt="image" src="https://github.com/user-attachments/assets/184a2c62-0d1f-4a95-b8f8-332e22ec1fcc" />

With the same math, 513-426+1=88 lines. 

Note the final version of this actionPerformed found [here](https://github.com/bluelightspirit/Project4/blob/main/PokeGUI.java) is at 96 lines. The purpose of this write-up is to show it was drastically easier to write these lines and track down swapping and damage issues in the earlier if-else paradise.
<img width="540" height="101" alt="image" src="https://github.com/user-attachments/assets/effb2fec-9c96-4d51-ae2e-ff00ab435992" />