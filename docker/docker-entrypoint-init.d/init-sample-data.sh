#!bin/sh
echo =========================================
echo "Running init-sample-data.sh to create a Moodle sample site"
php /bitnami/moodle/admin/tool/generator/cli/maketestsite.php --size=S --bypasscheck
echo =========================================