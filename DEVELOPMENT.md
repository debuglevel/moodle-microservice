## Bring up Moodle

`docker-compose -f docker-compose.moodle.yml down --volumes`

## Bring down Moodle and remove volumes

`docker-compose -f docker-compose.moodle.yml down --volumes`

TODO: the bitnami image seems to not save some configurations like enabling web services

## Create MariaDB snapshot

```
mysqldump \
  -h 127.0.0.1 -u root -p \
  --events \
  --routines \
  --triggers \
  --add-drop-database \
  --compress \
  --hex-blob \
  --opt \
  --skip-comments \
  --single-transaction \
  --skip-set-charset \
  --default-character-set=utf8mb4 \
  --databases moodle | xz -2 > 500-moodle39.sql.xz
```

## Initial Moodle set up

- remove MariaDB data/\*.sql.xz
- start up moodle
- go to localhost:8082/moodle
- go into moodle container and run `php admin/tool/generator/cli/maketestsite.php --size=S --bypass`
- enable `enablewebservices`
- enable REST protocol
- enable `enablewsdocumentation`

## Get JSON from Moodle REST

`curl "http://localhost:8082/moodle/webservice/rest/server.php?wstoken=32bc217501ebb9de79ffffbfa13eda81&wsfunction=core_webservice_get_site_info&moodlewsrestformat=json" | jq`

`curl -X POST -d wstoken="32bc217501ebb9de79ffffbfa13eda81" -d wsfunction=core_webservice_get_site_info 'http://localhost:8082/moodle/webservice/rest/server.php?moodlewsrestformat=json' | jq`

`curl -X POST -d wstoken="32bc217501ebb9de79ffffbfa13eda81" -d wsfunction=core_course_get_courses_by_field 'http://localhost:8082/moodle/webservice/rest/server.php?moodlewsrestformat=json' -d 'field=id' -d 'value=12' | jq`

## API docs

http://localhost:8082/moodle/admin/webservice/documentation.php
