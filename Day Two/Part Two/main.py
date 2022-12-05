def main():
    totalScore = 0
    with open("../inputData.txt", "r") as objFile:
        while True:
            line = objFile.readline().splitlines()
            if not line:
                break

            opponentInput = ""
            if line[0].split(" ")[0] == "A":
                opponentInput = "Rock"
            elif line[0].split(" ")[0] == "B":
                opponentInput = "Paper"
            elif line[0].split(" ")[0] == "C":
                opponentInput = "Scissors"
                
            #yourInput = ""
            if line[0].split(" ")[1] == "X":
                #Lose
                if opponentInput == "Rock":
                    totalScore += 3
                elif opponentInput == "Paper":  
                    totalScore += 1
                elif opponentInput == "Scissors":
                    totalScore += 2
            elif line[0].split(" ")[1] == "Y":
                #Tie
                if opponentInput == "Rock":
                    totalScore += (1 + 3)
                elif opponentInput == "Paper":  
                    totalScore += (2 + 3)
                elif opponentInput == "Scissors":
                    totalScore += (3 + 3)
            elif line[0].split(" ")[1] == "Z":
                #Win
                if opponentInput == "Rock":
                    totalScore += (2 + 6)
                elif opponentInput == "Paper":  
                    totalScore += (3 + 6)
                elif opponentInput == "Scissors":
                    totalScore += (1 + 6)
        print("The Answer is: " + str(totalScore))

if __name__ == "__main__":
    main()