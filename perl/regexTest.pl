$mystring = "abc123!";

# $mystring =~ s/hello/Goodbye/;
# print $mystring;

# if($mystring =~ m/b(.*)2/) {
# 	print $1;
# }

# if($mystring =~ m/(abc|123)+/) {
# 	print $1;
# }

if($mystring =~ m/((?:abc|123)+)/) {    # if we remove ?: we will have two groups s result #1 abc123 and #123
	print $1;
}