open AAAA,$ARGV[0];
my @array = <AAAA>; # read the file into an array of lines
close AAAA;
my $max = -1;
for my $line (@array) {
    if (length($line) > $max) {
        $max = length($line);
    }
}

print "#" x ($max+4) . "\n";
for my $s (@array) {
    chomp $s;
    print "# " . $s  . " " x ($max-length($s)) . " #\n";
}
print "#" x ($max+4) . "\n";