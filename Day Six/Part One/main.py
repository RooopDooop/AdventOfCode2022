def main():
    totalCount = 0
    with open("../inputData.txt", "r") as objFile:
        while True:
            line = objFile.readline().splitlines()
            if not line:
                break

            listSignal = list(str(line[0])[3:])
            
            for a, i in enumerate(listSignal):
                try:
                    splicedArray = set(listSignal[a:a+4])
                    if len(splicedArray) == 4:
                        totalCount = a + 4
                        break
                except:
                    print("End of file, Bailing out. Packet not found...")
                    break
                

    print("The answer is: " + str(totalCount + 3))

if __name__ == "__main__":
    main()

