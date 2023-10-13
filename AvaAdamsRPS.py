'''
CET3383C-Project3
Software Engineering 1
 
Project 3: Rock, Paper, Scissors, Saw Game & Design Specifications Document
 
Due Date: October 22, 2023

Description:   This project simulates a game of Rock, Paper, Scissors, Saw.
                The game output will be used to complete Sections 3-5 of the SSD
'''

# Functions

# Prompts for player names, validates input, call menu()
def initialScreen():
  print("Rock, Paper, Scissors, Saw")
  print("---------------------------")

# Displays menu options and prompts for user input
def menu():
  print("Menu")
  print("---------------------------")

# "Back button" - Accepts input to return to the Menu
def validateReturnToMenu():
    print("Enter 1 to return to the Menu")
    userInput = input()
    if userInput == '1':
       print("")
       menu()
    else:
        print("Invalid input.")
        validateReturnToMenu()

# Displays stats information
def stats():
  print("Statistics")
  print("---------------------------")
  validateReturnToMenu()

# Displays rules for winning and basic instructions
def rules():
  print("Winner of the round will be determined as follow:")
  print("   a. Rock breaks scissors and Saw therefore rock wins over scissors and saw. Rock loses against paper.")
  print("   b. Scissors cut paper therefore scissors win over paper. Scissors lose against rock and Saw.")
  print("   c. Paper covers rock therefore paper wins over rock. Paper loses against scissors and saw")
  print("   d. Saw cuts through scissors and paper therefore saw wins over scissors and paper. Saw loses against rock.")
  print("   e. If player and computer make the same selection, there is a tie")
  print("")
  print("Winner of the game against the computer is one who won more rounds (must account for ties)")
  print("")
  print("Overall human winner is based on the greater number of won games against the computer and least games lost (must account for tie between human players)")
  print("")
  validateReturnToMenu()    

# Play game
def play():
  pass

def main():
  initialScreen()

# *** BEGINNING OF PROGRAM LOGIC***
# call main() method here
main()
