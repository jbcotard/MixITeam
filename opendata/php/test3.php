<?php

header('Content-Type: application/json');
$fileName = "detail.json";
$handle = fopen($fileName, "r");
$contents = fread($handle, filesize($fileName));
echo $contents;


?>
