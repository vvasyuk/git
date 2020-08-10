my @lines = split /\n/, @ARGV[0];
#print @lines[0];

my $max = -1;
for my $line (@lines) {
    if (length($line) > $max) {
        $max = length($line);
    }
}

print "+" . "-" x ($max+2) . "+\n";
for my $s (@lines) {
    chomp $s;
    print "| " . $s  . " " x ($max-length($s)) . " |\n";
}
print "+" . "-" x ($max+2) . "+\n";