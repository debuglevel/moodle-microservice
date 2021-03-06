CREATE USER 'moodle'@'%' IDENTIFIED WITH mysql_native_password;
GRANT USAGE ON *.* TO 'moodle'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
SET PASSWORD FOR 'moodle'@'%' = PASSWORD('moodle');

CREATE DATABASE IF NOT EXISTS `moodle`;
GRANT ALL PRIVILEGES ON `moodle`.* TO 'moodle'@'%';
GRANT ALL PRIVILEGES ON `moodle\_%`.* TO 'moodle'@'%';