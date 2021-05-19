class avgResult:
    def __init__(self, year, city, avg):
        self.year = year
        self.city = city
        self.avg = avg


if __name__ == "__main__":
    list = []

    list.append(avgResult(2010, "Dublin", 15))
    list.append(avgResult(2011, "Cork", 16))

    curr = avgResult
    curr.xuxu = "galway"
    list.append(curr)

    print(len(list))
    print(list[1].city)
