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
                
            yourInput = ""
            if line[0].split(" ")[1] == "X":
                yourInput = "Rock"
            elif line[0].split(" ")[1] == "Y":
                yourInput = "Paper"
            elif line[0].split(" ")[1] == "Z":
                yourInput = "Scissors"  

            roundScore = 0
            if yourInput == "Rock":
                roundScore = 1

                if opponentInput == "Rock":
                    roundScore += 3
                elif opponentInput == "Scissors":
                    roundScore += 6
            elif yourInput == "Paper":
                roundScore = 2

                if opponentInput == "Paper":
                    roundScore += 3
                elif opponentInput == "Rock":
                    roundScore += 6
            elif yourInput == "Scissors":
                roundScore = 3

                if opponentInput == "Scissors":
                    roundScore += 3
                elif opponentInput == "Paper":
                    roundScore += 6

            totalScore += roundScore
    print("Total score is: " + str(totalScore))

if __name__ == "__main__":
    main()

