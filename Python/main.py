from jobmedia import *

def main():
    print("test-main")
    dataFile = "file:///usr/local/PPR-ALL.csv"
    
    jobmedia = jobmediaclass(dataFile)
    jobmedia.start()

    jobgap = jobgapclass(dataFile)
    jobgap.start()

if __name__ == "__main__":
    main()