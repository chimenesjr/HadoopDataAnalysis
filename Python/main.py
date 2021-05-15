from jobmedia import *

def main():
    print("test-main")
    dataFile = "file:///usr/local/PPR-ALL.csv"  # Should be some file on your system
    
    job = jobmediaclass(dataFile)
    job.start()

if __name__ == "__main__":
    main()