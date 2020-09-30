print "hi\n";
my $old = 'c"a"t';
#my $new = $old =~ s/"/X/g;
$old =~ s/"/\\"/g;
print "$old\n";
#print "$new\n";