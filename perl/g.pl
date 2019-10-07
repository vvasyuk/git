use Graph::Easy;
 
# my $graph = Graph::Easy->new('[A]-- {label: "21"; } [B]--[C]--[D]');
# $graph->set_attribute('border', 'solid 1px black');
# my $graph = Graph::Easy->new();
#$graph->set_attribute('arrowstyle', 'none');
# # $graph->add_edge ('1', '2');
# # $graph->add_edge ('2', '1');
# # $graph->add_edge ('1', '3');
#print $graph->as_ascii( );

# my $graph = Graph::Easy->new();
# my $bonn = $graph->add_node('Bonn');
# $bonn->set_attribute('border', 'solid 1px black');
# my $berlin = $graph->add_node('Berlin');
# $graph->add_edge ($bonn, $berlin);
# print $graph->as_ascii( );

# my $graph = Graph::Easy->new();
# my $node = $graph->add_node('Koblenz');
# my $second = $graph->add_node('Frankfurt');
# my ($first,$second,$edge) = $graph->add_edge('Mainz','Ulm');    #my ($first,$second,$edge) = $graph->add_edge('Mainz','Ulm', 'xyi');
# $edge->set_attribute('label','blue');
# print $graph->as_ascii( );

# my $graph = Graph::Easy->new();
# my $edge = Graph::Easy::Edge->new();
# $edge->set_attributes( {arrowstyle => 'none'} );
# $graph->add_edge ('1', '2', $edge);

my $graph2 = Graph::Easy->new();
my ($first,$second,$edge) = $graph2->add_edge('1','2', 'label');
$edge->set_attributes( {arrowstyle => 'none'} );
print $graph2->as_ascii( );