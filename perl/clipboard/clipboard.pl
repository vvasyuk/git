use Win32::Clipboard;
 
$CLIP = Win32::Clipboard();
 
# print "Clipboard contains: ", $CLIP->Get(), "\n";
$max = 5;
$s = "+" . "-" x ($max+2) . "+\n";
print "$s";

$CLIP->Set("$s");

# $CLIP->Empty();
 
# $CLIP->WaitForChange();
# print "Clipboard has changed!\n";