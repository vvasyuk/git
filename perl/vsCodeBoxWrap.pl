my $argument_list = join(" ", @ARGV);
#print $argument_list
#my @lines = split /\n/, @ARGV[0];

 my @lines = split /\n/, $argument_list;
 my $max = -1;
 for my $line (@lines) {
     if (length($line) > $max) {
         $max = length($line);
     }
 }

#  for my $s (@lines) {
#      print $s;
#  }
#$s =~ s/"/'/g;

# print "+" . "-" x ($max+2) . "+\n";
# for my $s (@lines) {
#     chomp $s;
#     print "| " . $s  . " " x ($max-length($s)) . " |\n";
# }
# print "+" . "-" x ($max+2) . "+\n";

print "+" . "-" x ($max+2) . "+\n";
for my $i (0 .. $#lines){
    chomp $lines[$i];
    my $line = $lines[$i];
    if ($i < $#lines) {
        print "| " . $line  . " " x ($max-length($line)) . " |\n";
    }
    
}
print "+" . "-" x ($max+2) . "+\n";