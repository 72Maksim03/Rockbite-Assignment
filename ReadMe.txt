Inventory Management System
Description
This is a console-based Inventory Management System for a game. The system allows users to manage game items of various rarities (Common, Great, Rare, Epic, Legendary). Users can perform the following actions:

Create new items.
Add items with random rarities.
Upgrade items by merging other items of the same rarity.
Drop items.
Save and load the inventory from a file.
Features
Item Creation: Add items with custom or random rarities.
Item Upgrades: Merge items to upgrade them by rarity.
Inventory Management: Drop items from the inventory.
Save/Load Functionality: Save the inventory to a file and load it back.
Sorting: Items are sorted by rarity and upgrade count for easy management.
Error Handling: Invalid inputs are handled with clear error messages, prompting the user to try again.
Compilation and Running Instructions
Compiling the Program:
Open a terminal or command prompt.
Navigate to the directory containing your .java files.
Compile the program with the following command:
javac *.java

Running the Program:
Once the program is compiled, run it using the following command:

java Main

The program will prompt you to choose whether to create a new inventory or load an existing one, followed by options to manage your inventory by adding, upgrading, or dropping items. You will also have the option to save the inventory to a file.
User Input
Rarity Input: When entering the rarity of an item, it can be typed in either uppercase or lowercase (e.g., COMMON or common).
Error Handling: If the user provides invalid input (e.g., an incorrect rarity or an invalid index), the program will prompt the user to enter the correct value again.
Design Choices
Binary Serialization: The program uses binary serialization (ObjectOutputStream and ObjectInputStream) for saving and loading the inventory. This approach efficiently stores and retrieves complex objects like Item and Inventory.
Sorting: Items are sorted by rarity and upgrade count using the Comparable interface to simplify inventory management and display.
Exception Handling: The program includes robust error handling for invalid user input (e.g., invalid rarity, out-of-range index) and file I/O errors.
Example Usage

Example Interaction:
Do you want to load the inventory or create a new one? [l-load]/[n-new]
n
Inventory created.

Inventory:
[Common 0 Sword, Rare 0 Shield]

What do you want to do? (Choose the index)
1. Add an item
2. Upgrade item by merging 3 items
3. Add item with random rarity
4. Drop an item
5. Save the inventory
6. Save the inventory and exit
7. Exit
Common Errors:
Invalid rarity input: If you enter an invalid rarity (e.g., "SuperRare"), the program will notify you to use one of the valid rarities: COMMON, GREAT, RARE, EPIC, or LEGENDARY.
Invalid index: If you try to access an item by an out-of-bounds index, the program will ask you to enter a valid index.
File Structure
Main.java: The entry point for the program, managing user input and program flow.
Item.java: Defines the Item class, representing individual items in the inventory.
Inventory.java: Defines the Inventory class, which manages the collection of items and item-related operations (adding, upgrading, dropping).
InventoryManager.java: Provides methods for saving and loading the inventory using serialization.
