FROM maven:latest

WORKDIR /usr/src/novaposhta

RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
RUN apt-get update

RUN apt-get install -qqy xvfb gconf2 google-chrome-stable apt-utils mc nano \
    && apt-get clean

COPY ./chromedriver /usr/local/bin

CMD Xvfb :10 -ac -screen 0 1920x1080x24 & \
    && export DISPLAY=:10 \
    && sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome \
    && rm -f /tmp/.X1-lock \
    && chown -R tester:tester /usr/src/novaposhta \

