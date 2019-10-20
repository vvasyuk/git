print $1;
open AAAA,$1;
my @array = <AAAA>; # read the file into an array of lines
close AAAA;
my $max = -1;
for my $line (@array) {
    if (length($line) > $max) {
        $max = length($line);
    }
}
print $max