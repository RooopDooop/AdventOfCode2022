def main():
    totalCount = 0
    with open("../inputData.txt", "r") as objFile:
        while True:
            line = objFile.readline().splitlines()
            if not line:
                break

            arrScheduleOne = line[0].split(",")[0].split("-")
            arrScheduleTwo = line[0].split(",")[1].split("-")

            baseScheduleLow = int(arrScheduleOne[0])
            baseScheduleHigh = int(arrScheduleOne[1])

            targetScheduleLow = int(arrScheduleTwo[0])
            targetScheduleHigh = int(arrScheduleTwo[1])

            if baseScheduleHigh >= targetScheduleLow and baseScheduleLow <= targetScheduleHigh:
                totalCount += 1

    print("The answer is: " + str(totalCount))

if __name__ == "__main__":
    main()

