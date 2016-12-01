from time import gmtime, strftime
import datetime
# timeStamp = strftime("%Y-%m-%d %H:%M:%S", gmtime())
timeStamp = datetime.datetime.now()
of = open('../mail data copy.txt', 'w')
z = True
for x in range(110800):
    t = datetime.timedelta(seconds = 10)
    timeStamp = timeStamp + t
    timeStampFormated = timeStamp.strftime("%b %d %H:%M:%S %Y")
    of.write(timeStampFormated+"\n")
    z= not z
of.close()
