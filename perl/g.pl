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

# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('1','2', '4'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('1','4', '6'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('2','3', '24'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('3','4', '23'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('4','5', '5'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('3','5', '18'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('5','6', '11'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('3','6', '9'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('1','7', '16'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('4','7', '8'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('5','7', '10'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('6','8', '7'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('7','8', '14'); $edge->set_attributes( {arrowstyle => 'none'} );
# print $graph->as_ascii( );
# #print $graph->as_boxart();'

# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('1','2', '4'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('1','4', '6'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('2','3', '24'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('3','4', '23'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('4','5', '5'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('3','5', '18'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('5','6', '11'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('3','6', '9'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('1','7', '16'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('4','7', '8'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('5','7', '10'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('6','8', '7'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('7','8', '14'); $edge->set_attributes( {arrowstyle => 'none'} );
# print $graph->as_ascii( );
# #print $graph->as_boxart();

# #greedy
# #$first->set_attributes({ border=> 'double' });
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('0','7','0.16'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('2','3','0.17'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('1','7','0.19'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('0','2','0.26'); $edge->set_attributes( {arrowstyle => 'none', style => 'bold'} );
# my ($first,$second,$edge) = $graph->add_edge('5','7','0.28'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','0.29'); $edge->set_attributes( {arrowstyle => 'none', style => 'bold-dash'} );
# my ($first,$second,$edge) = $graph->add_edge('1','5','0.32'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('2','7','0.34'); $edge->set_attributes( {arrowstyle => 'none', style => 'bold-dash'} );
# my ($first,$second,$edge) = $graph->add_edge('4','5','0.35'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('1','2','0.36'); $edge->set_attributes( {arrowstyle => 'none', style => 'bold-dash'} );
# my ($first,$second,$edge) = $graph->add_edge('4','7','0.37'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('0','4','0.38'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('6','2','0.40'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('3','6','0.52'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('6','0','0.58'); $edge->set_attributes( {arrowstyle => 'none', style => 'bold-dash'} );
# my ($first,$second,$edge) = $graph->add_edge('6','4','0.93'); $edge->set_attributes( {arrowstyle => 'none', style => 'bold-dash'} );
# #print $graph->as_ascii( );
# print $graph->as_boxart();

# #greedy1
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('5','1','0.32'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); 
# my ($first,$second,$edge) = $graph->add_edge('5','7','0.28'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '1,2' });
# my ($first,$second,$edge) = $graph->add_edge('5','4','0.35'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '-1,6' });
# my ($first,$second,$edge) = $graph->add_edge('1','2','0.36'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','0.29'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '2,0' });
# my ($first,$second,$edge) = $graph->add_edge('1','7','0.19'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','2','0.17'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','6','0.52'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','2','0.34'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','0','0.16'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '7', offset=> '1,1' }); 
# my ($first,$second,$edge) = $graph->add_edge('7','4','0.37'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','0','0.26'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','6','0.40'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '1,6' });
# my ($first,$second,$edge) = $graph->add_edge('0','4','0.38'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('0','6','0.58'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('4','6','0.93'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# print $graph->as_boxart();

# #greedy2
#$second->set_attributes({ origin=> '1', offset=> '-1,1' });
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('5','1','0.32'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('5','7','0.28'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '1,2' });
# my ($first,$second,$edge) = $graph->add_edge('5','4','0.35'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '-1,6' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('1','2','0.36'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','0.29'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '2,0' });
# my ($first,$second,$edge) = $graph->add_edge('1','7','0.19'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','2','0.17'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','6','0.52'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','2','0.34'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','0','0.16'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '7', offset=> '1,1' }); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('7','4','0.37'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','0','0.26'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('2','6','0.40'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '1,6' });
# my ($first,$second,$edge) = $graph->add_edge('0','4','0.38'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('0','6','0.58'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('4','6','0.93'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# print $graph->as_boxart();

#greedy3
my $graph = Graph::Easy->new();
my ($first,$second,$edge) = $graph->add_edge('5','1','0.32'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $first->set_attributes({ border=> 'double' });
my ($first,$second,$edge) = $graph->add_edge('5','7','0.28'); $edge->set_attributes( {arrowstyle => 'none'} ); $second->set_attributes({ origin=> '5', offset=> '1,2' }); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
my ($first,$second,$edge) = $graph->add_edge('5','4','0.35'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '-1,6' });
my ($first,$second,$edge) = $graph->add_edge('1','2','0.36'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '0,2' });
my ($first,$second,$edge) = $graph->add_edge('1','3','0.29'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '2,0' });
my ($first,$second,$edge) = $graph->add_edge('1','7','0.19'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
my ($first,$second,$edge) = $graph->add_edge('3','2','0.17'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
my ($first,$second,$edge) = $graph->add_edge('3','6','0.52'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
my ($first,$second,$edge) = $graph->add_edge('7','2','0.34'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
my ($first,$second,$edge) = $graph->add_edge('7','0','0.16'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '7', offset=> '1,1' }); 
my ($first,$second,$edge) = $graph->add_edge('7','4','0.37'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
my ($first,$second,$edge) = $graph->add_edge('2','0','0.26'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
my ($first,$second,$edge) = $graph->add_edge('2','6','0.40'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '1,6' });
my ($first,$second,$edge) = $graph->add_edge('0','4','0.38'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
my ($first,$second,$edge) = $graph->add_edge('0','6','0.58'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
my ($first,$second,$edge) = $graph->add_edge('4','6','0.93'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
print $graph->as_boxart();

#MST
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('5','1','0.32'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('5','7','0.28'); $edge->set_attributes( {arrowstyle => 'none'} ); $second->set_attributes({ origin=> '5', offset=> '1,2' });
# my ($first,$second,$edge) = $graph->add_edge('5','4','0.35'); $edge->set_attributes( {arrowstyle => 'none'} ); $second->set_attributes({ origin=> '5', offset=> '-1,6' }); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('1','2','0.36'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','0.29'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '2,0' });
# my ($first,$second,$edge) = $graph->add_edge('1','7','0.19'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' }); 
# my ($first,$second,$edge) = $graph->add_edge('3','2','0.17'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' }); 
# my ($first,$second,$edge) = $graph->add_edge('3','6','0.52'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','2','0.34'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','0','0.16'); $edge->set_attributes( {arrowstyle => 'none'} ); $second->set_attributes({ origin=> '7', offset=> '1,1' }); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('7','4','0.37'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','0','0.26'); $edge->set_attributes( {arrowstyle => 'none'} );
# my ($first,$second,$edge) = $graph->add_edge('2','6','0.40'); $edge->set_attributes( {arrowstyle => 'none'} ); $second->set_attributes({ origin=> '3', offset=> '1,6' }); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('0','4','0.38'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('0','6','0.58'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('4','6','0.93'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# print $graph->as_boxart();