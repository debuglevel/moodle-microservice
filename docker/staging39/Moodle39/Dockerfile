FROM moodlehq/moodle-php-apache:7.3

ADD --chown=root:root data/moodle-3.9.2.tgz /var/www/html/
#RUN rm /var/www/html/moodle/install.php

COPY data/webservices/config.php /var/www/html/moodle/config.php 
#ADD data/webservices/moodledata.tar.bz2 /var/www/

COPY wait-for-it.sh /
COPY docker-entrypoint.sh /
RUN chmod +x /wait-for-it.sh /docker-entrypoint.sh

# remove this line if no automatic upgrade on container startup is wanted
#CMD ["/docker-entrypoint.sh"]