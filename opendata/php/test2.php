<?php

header('Content-Type: application/json');
$fileName = "selection.json";
$handle = fopen($fileName, "r");
$contents = fread($handle, filesize($fileName));
echo $contents;


?>
