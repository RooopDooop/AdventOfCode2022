def main():
    arrSumValues = []

    with open("../inputData.txt", "r") as objFile:
        currentValue = 0

        while True:
            line = objFile.readline().splitlines()

            if not line:
                break

            if line[0] != "":
                currentValue += int(line[0])
            else:
                arrSumValues.append(currentValue)
                #if currentValue > largestValue:
                #    largestValue = currentValue
                
                currentValue = 0

        arrSumValues.sort()
        totalSum = arrSumValues[len(arrSumValues) - 1] + arrSumValues[len(arrSumValues) - 2] + arrSumValues[len(arrSumValues) - 3]

        print("The answer is: " + str(totalSum))

if __name__ == "__main__":
    main()