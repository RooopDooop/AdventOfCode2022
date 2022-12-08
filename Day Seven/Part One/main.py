class AdventDir:
    def __init__(self, name, numericValue, children):
        self.name = name
        self.numericValue = numericValue
        self.children = children
    #def findChildren(self, strTarget):
        #for objChild in self.children:
            #if objChild.name == strTarget:
                #print("Found Child: " + objChild.name)
                #break
            #else:
                #objChild.findChildren(strTarget)
    def findParentAddChild(self, strParent, objChild):
        print(self.name + " - " + strParent)
        if self.name == strParent:
            self.children.append(objChild)
        else:
            for objChild in self.children:
                objChild.findParentAddChild(strParent, objChild)

    #def addChild(self, objChild):
        #self.children.append(objChild)

def main():
    currentDirectory = []

    MainDir = AdventDir

    #allDirectories = dict({})
    #documentReverseIndex = dict({})

    with open("../inputData.txt", "r") as objFile:
        while True:
            line = objFile.readline().splitlines()
            if not line:
                break

            strLine = line[0]

            if strLine[0] == "$":
                strCommand = strLine.split(" ")[1]

                if strCommand == "cd":
                    if strLine == "$ cd /":
                        currentDirectory.append(strLine.split(" ")[2])
                        MainDir = AdventDir("/", 0, [])

                        #allDirectories[' '.join(currentDirectory)] = [dict()]
                    elif strLine.split(" ")[2] != "..":
                        currentDirectory.append(strLine.split(" ")[2])

                        #print(str(currentDirectory[len(currentDirectory)-2]))
                        
                        print(str(currentDirectory))
                        if len(currentDirectory) == 1:
                            MainDir.findParentAddChild("/", AdventDir(strLine.split(" ")[2], 0, []))
                        else:
                            MainDir.findParentAddChild(currentDirectory[len(currentDirectory)-2], AdventDir(strLine.split(" ")[2], 0, []))


                        #allDirectories[strLine.split(" ")[2]] = ' '.join(currentDirectory)
                    else:
                        del currentDirectory[-1]


            #else:
                #if strLine.split(" ")[0] != "dir":
                    #if currentDirectory[len(currentDirectory)-1] not in documentReverseIndex:
                    #    documentReverseIndex[currentDirectory[len(currentDirectory)-1]] = int(strLine.split(" ")[0])
                    #else:
                    #    documentReverseIndex[currentDirectory[len(currentDirectory)-1]] += int(strLine.split(" ")[0])

    print(MainDir.children)


    #totalSized = dict()

    #for key in allDirectories:
    #    totalValue = 0

    #    for strTest in str(allDirectories[key]).split(" "):
    #        if (strTest != "/"):
    #            if strTest in documentReverseIndex:
    #                print(str(allDirectories[key]) + " - " + strTest + " - " + str(documentReverseIndex[strTest]))

    #for key in allDirectories:
    #    if str(allDirectories[key]) in documentReverseIndex:
    #        if int(documentReverseIndex[str(allDirectories[key])]) <= 100000:
    #            print(key + " - " + str(allDirectories[key]) + " - Values: " + str(documentReverseIndex[str(allDirectories[key])]))

    #print(str(allDirectories['tpnspw']).split(" "))

    #print(str(documentReverseIndex))

if __name__ == "__main__":
    main()

