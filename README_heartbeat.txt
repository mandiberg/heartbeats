README for HeartBeat code

v0.1, LACMA Testing Version
-Autostarts on Raspberry PI
-Plays email alerts based off of timestamps (currently set to every 10 seconds for testing)
-Plays heartrate, switching between three states (50,100,150 BPM) based off of JSON file. Currently using Foley audio, and dummy JSON files, but it is same format as live data files, which we have.

ROADMAP

v0.2, Full Skeleton
-Will have files for all ~20 heartrates, from 50 to 150 BPM, in 5 beat increments. Will be Foley sounds. Will include accurate data (transposing January 1-31 data to December 1-31)

v0.3, Real Audio
-Will replace Foley sounds with actual recordings of my heartbeat. First 180 days (Jan-June) data will be in place This may be the final version, or we may need to refine the code if bugs appear.

v0.4, All the Data
-After Jan 1 2017, we will compile the full 365 days of data in JSON files.

INSTALL INFO

1) Move HeartBeat folder to the Desktop of raspberry pi. heartbeat.jar is the code that does the stuff, and lives inside that folder, along with the audio files.

2) Move myjar.sh to /home/pi directory. This is the shell script that makes the jar file executable.

3) Add one line to the end of the hidden file ~/.bashrc using "sudo nano ~/.bashrc" command. The line is: bash myjar.sh  -this is what makes the pi start the code on boot. For a visual demo of adding this line with the nano text editor, go to 0:45 in this video:
 https://www.youtube.com/watch?v=VQkvh6d41Y0 (step 3, you only need to do that one step from the video) Nano codes: http://www.codexpedia.com/text-editor/nano-text-editor-command-cheatsheet/
 
KNOWN ISSUES

-The heartbeat sometimes doesn't start immediately when the app is launched.
-The audio is a little crunchy: audio quality is poor, and glitches periodically. Source could be hardware or software. We are going to get a HiFiBerry card (https://www.hifiberry.com/) and test to see if that improves things.
-We haven't tested enough with quality audio to know if the transitions are smooth enough to be passable.