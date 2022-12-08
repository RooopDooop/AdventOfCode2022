def main():
    totalCount = 0

    dataZones = [
        ['Z','P','M','H','R'],
        ['P','C','J','B'],
        ['S','N','H','G','L','C','D'],
        ['F','T','M','D','Q','S','R','L'],
        ['F','S','P','Q','B','T','Z','M'],
        ['T','F','S','Z','B','G'],
        ['N','R','V'],
        ['P','G','L','T','D','V','C','M'],
        ['W','Q','N','J','F','M','L'],
    ]

    with open("../inputData.txt", "r") as objFile:
        while True:
            line = objFile.readline().splitlines()
            if not line:
                break

            _, strMove, _,  fromValue, _, toValue = line[0].split(" ")
            moveQuantity = int(strMove)

            arrIntermediate = []
            while moveQuantity > 0:
                if len(dataZones[int(fromValue) - 1])  > 0:
                    arrIntermediate.append(dataZones[int(fromValue) - 1].pop())
                moveQuantity -= 1
                
            arrIntermediate.reverse()
            dataZones[int(toValue) - 1] += arrIntermediate

    strAnswer = ""
    for arrZones in dataZones:
        strAnswer += arrZones.pop()

    print("The answer is: " + strAnswer)

if __name__ == "__main__":
    main()

