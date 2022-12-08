def main():
    currentDirectory = []

    allDirectories = dict({})
    documentReverseIndex = dict({})

    with open("../inputData.txt", "r") as objFile:
        while True:
            line = objFile.readline().splitlines()
            if not line:
                break

            strLine = line[0]

            if strLine[0] == "$":
                strCommand = strLine.split(" ")[1]

                if strCommand == "cd":
                    if currentDirectory == "":
                        currentDirectory.append(strLine.split(" ")[2])
                        allDirectories[' '.join(currentDirectory)] = [dict()]
                    elif strLine.split(" ")[2] != "..":
                        currentDirectory.append(strLine.split(" ")[2])

                        allDirectories[strLine.split(" ")[2]] = ' '.join(currentDirectory)
                    else:
                        del currentDirectory[-1]


            else:
                if strLine.split(" ")[0] != "dir":
                    if currentDirectory[len(currentDirectory)-1] not in documentReverseIndex:
                        documentReverseIndex[currentDirectory[len(currentDirectory)-1]] = int(strLine.split(" ")[0])
                    else:
                        documentReverseIndex[currentDirectory[len(currentDirectory)-1]] += int(strLine.split(" ")[0])

    #print("The answer is: " + str(allDirectories))

    totalSized = dict()

    for key in allDirectories:
        totalValue = 0

        for strTest in str(allDirectories[key]).split(" "):
            if (strTest != "/"):
                if strTest in documentReverseIndex:
                    print(str(allDirectories[key]) + " - " + strTest + " - " + str(documentReverseIndex[strTest]))

    #for key in allDirectories:
    #    if str(allDirectories[key]) in documentReverseIndex:
    #        if int(documentReverseIndex[str(allDirectories[key])]) <= 100000:
    #            print(key + " - " + str(allDirectories[key]) + " - Values: " + str(documentReverseIndex[str(allDirectories[key])]))

    #print(str(allDirectories['tpnspw']).split(" "))

    #print(str(documentReverseIndex))

if __name__ == "__main__":
    main()

