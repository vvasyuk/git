use Win32::Clipboard;
$CLIP = Win32::Clipboard();

# use Data::Dumper
# $Data::Dumper::Terse = 1;
# $Data::Dumper::Useqq = 1;

$text = "" . Win32::Clipboard::GetText();
#print Data::Dumper::qquote($text);
#print $text;

my @lines = split /\r\n/, $text;
my $max = -1;
for my $line (@lines) {
    #print "$line\n";
    if (length($line) > $max) {
        $max = length($line);
    }
}

$res = "+" . "-" x ($max+2) . "+\n";
for my $i (0 .. $#lines){
    #print "$lines[$i]\n";
    chomp $lines[$i];
    my $line = $lines[$i];
    if ($i < ($#lines+1)) {
        $res = $res . "| " . $line  . " " x ($max-length($line)) . " |\n";
    }
}
$res = $res . "+" . "-" x ($max+2) . "+\n";
print "$res";
$CLIP->Set("$res");