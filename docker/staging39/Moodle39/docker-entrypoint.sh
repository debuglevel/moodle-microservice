#!/bin/bash

echo "== Executing upgrade script..."
# see also: https://docs.moodle.org/32/en/Administration_via_command_line#Upgrading
/wait-for-it.sh db:3306 -t 0
cd /var/www/html/moodle
php admin/cli/upgrade.php --non-interactive
echo "== Executed upgrade script"

exec apache2-foreground