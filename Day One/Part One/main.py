def main():
    largestValue = 0

    with open("../inputData.txt", "r") as objFile:
        currentValue = 0

        while True:
            line = objFile.readline().splitlines()

            if not line:
                break

            if line[0] != "":
                currentValue += int(line[0])
            else:
                if currentValue > largestValue:
                    largestValue = currentValue
                
                currentValue = 0


        
        print("The answer is: " + str(largestValue))

if __name__ == "__main__":
    main()