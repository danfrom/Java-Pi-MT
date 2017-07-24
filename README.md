# Java-Pi-MT
Adds multi touch capabilities to java on Raspberry Pi and possible others, by reading the output of xinput.

Run "xinput" to find the id of your touchscreen, then in the file "TouchServer.java", change the number 8 at the end of the line to your id:

String[] args2 = new String[] {"/bin/bash", "-c", "xinput test-xi2 --root 8"};

Then add TouchDelegatorPanel to your scene and you are almost ready to rock.

This panel makes it possible to recieve touches, but only in that container.

Now create some components that implemenets Touchable.

All that is left, is some listeners (TouchListener) that needs to be set.

# Dependencies: xinput
