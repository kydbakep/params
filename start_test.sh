#! /bin/sh
Xvfb :99 -ac -screen 0 1920x1080x24 -nolisten tcp &
mvn test