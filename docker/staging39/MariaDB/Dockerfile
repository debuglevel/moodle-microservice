# see: https://serverfault.com/questions/796762/creating-a-docker-mysql-container-with-a-prepared-database-scheme
# MySQL image with a database prepopulated at build time (usually import is done at first container start)
FROM mariadb:10.5.2 as builder

# That file does the DB initialization but also runs mysql daemon, by removing the last line it will only init
RUN ["sed", "-i", "s/exec \"$@\"/echo \"not running $@\"/", "/usr/local/bin/docker-entrypoint.sh"]

# needed for initialization
ENV MYSQL_ROOT_PASSWORD=root

COPY data/webservices/ /docker-entrypoint-initdb.d/
# sed has some permission issues if it cannot write into that directory
RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/

## add missing CREATE and USE
# RUN cd /docker-entrypoint-initdb.d/ && \
#     xzcat 500-moodle27.sql.xz > 500-moodle27.sql && \
#     cat 500-moodle27.sql-precode 500-moodle27.sql > 500-moodle27.sql.full && \
#     rm 500-moodle27.sql-precode 500-moodle27.sql 500-moodle27.sql.xz && \
#     mv 500-moodle27.sql.full 500-moodle27.sql

# set mysql client character set to utf8
COPY data/mysql.cnf /etc/mysql/conf.d/

# Need to change the datadir to something else that /var/lib/mysql because the parent docker file defines it as a volume.
# https://docs.docker.com/engine/reference/builder/#volume :
#       Changing the volume from within the Dockerfile: If any build steps change the data within the volume after
#       it has been declared, those changes will be discarded.
RUN ["/usr/local/bin/docker-entrypoint.sh", "mysqld", "--datadir", "/initialized-db", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci"]

# copy database from the builder (as in the builder image the SQL dump file would remain)
FROM mariadb:10.5.2
COPY --from=builder /initialized-db /initialized-db
CMD ["mysqld", "--datadir", "/initialized-db", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci"]