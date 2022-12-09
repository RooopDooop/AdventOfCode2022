class AdventDir:
    def __init__(self, name, numericValue, children):
        self.name = name
        self.numericValue = numericValue
        self.children = children
    def findChildren(self):
        for objChild in self.children:
            objChild.findChildren()

            #f int(objChild.numericValue) <= 100000:
            print(objChild.name + " + " + str(objChild.numericValue))
    def findParentAddChild(self, strParent, objChild):
        if self.name == strParent:
            self.children.append(objChild)
            return
        else:
            for objSubChild in self.children:
                if objSubChild.name == strParent:
                    objSubChild.findParentAddChild(strParent, objChild)
                    #print(objChild.name + " - " + objSubChild.name)
    def fillParentsAndChild(self, treeDirectory, fileSize):
        #Iterate through all the parents in the tree, add the file size to all of them
        #finally, add weight to target, which is lowest level directory

        self.numericValue += int(fileSize)

        print(self)

        if (len(treeDirectory) == 0):
            return

        strTree = treeDirectory[0]

        for objSubChild in self.children:
            if (objSubChild.name == strTree):
                objSubChild.fillParentsAndChild(treeDirectory[1:], fileSize)

        #self.numericValue += int(fileSize)
        #for objSubChild in self.children:
            #for strTree in treeDirectory:
                #if (objSubChild.name == strTree):
                   # objSubChild.numericValue += int(fileSize)
                    #objSubChild.fillParentsAndChild(treeDirectory[1:], fileSize)

                    

def main():
    currentDirectory = []

    MainDir = AdventDir
    print(MainDir)

    with open("../inputData.txt", "r") as objFile:
        while True:
            line = objFile.readline().splitlines()
            if not line:
                break

            strLine = line[0]

            #print(currentDirectory)

            if strLine[0] == "$":
                strCommand = strLine.split(" ")[1]

                if strCommand == "cd":
                    if strLine == "$ cd /":
                        currentDirectory.append(strLine.split(" ")[2])
                        MainDir = AdventDir("/", 0, [])
                    elif strLine.split(" ")[2] != "..":
                        currentDirectory.append(strLine.split(" ")[2])
                        if len(currentDirectory) == 1:
                            MainDir.findParentAddChild("/", AdventDir(strLine.split(" ")[2], 0, []))
                        else:
                            MainDir.findParentAddChild(currentDirectory[len(currentDirectory)-2], AdventDir(strLine.split(" ")[2], 0, []))
                    else:
                        del currentDirectory[-1]

            else:
                if strLine.split(" ")[0] != "dir":
                    MainDir.fillParentsAndChild(currentDirectory[1:], strLine.split(" ")[0])


    MainDir.findChildren()

if __name__ == "__main__":
    main()

