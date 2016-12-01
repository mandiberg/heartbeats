from time import gmtime, strftime
import datetime
# timeStamp = strftime("%Y-%m-%d %H:%M:%S", gmtime())
timeStamp = datetime.datetime.now()
of = open('timestamp_heartrate.txt', 'w')
z = 50
for x in range(20800):
    
    t = datetime.timedelta(minutes=1)
    timeStamp = timeStamp + t
    timeStampFormated = timeStamp.strftime("%Y-%m-%d %H:%M:%S")
    of.write(timeStampFormated+","+str(z)+"\n")
    if z < 150:
    	z= z+50
    else:
    	z= 50

of.close()
