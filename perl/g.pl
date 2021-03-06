use Graph::Easy;

#p294
my $graph = Graph::Easy->new();
my ($first,$second,$edge) = $graph->add_edge('plant','A','1');
my ($first,$second,$edge) = $graph->add_edge('plant','B','5');
my ($first,$second,$edge) = $graph->add_edge('plant','C','20');
my ($first,$second,$edge) = $graph->add_edge('A','C','15');
my ($first,$second,$edge) = $graph->add_edge('B','C','10');
print $graph->as_ascii( );

#p294
#my $graph = Graph::Easy->new();
#my ($first,$second,$edge) = $graph->add_edge('0','1','10');
#my ($first,$second,$edge) = $graph->add_edge('0','2','8');
#my ($first,$second,$edge) = $graph->add_edge('0','3','15');
#my ($first,$second,$edge) = $graph->add_edge('1','3','12');
#my ($first,$second,$edge) = $graph->add_edge('2','4','10');
#my ($first,$second,$edge) = $graph->add_edge('3','4','5');
#my ($first,$second,$edge) = $graph->add_edge('3','0','17');
#my ($first,$second,$edge) = $graph->add_edge('4','0','10'); 
#print $graph->as_ascii( );
 
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

# #greedy3
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('5','1','0.32'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $first->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('5','7','0.28'); $edge->set_attributes( {arrowstyle => 'none'} ); $second->set_attributes({ origin=> '5', offset=> '1,2' }); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('5','4','0.35'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '-1,6' });
# my ($first,$second,$edge) = $graph->add_edge('1','2','0.36'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','0.29'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '2,0' });
# my ($first,$second,$edge) = $graph->add_edge('1','7','0.19'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','2','0.17'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','6','0.52'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','2','0.34'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('7','0','0.16'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '7', offset=> '1,1' }); 
# my ($first,$second,$edge) = $graph->add_edge('7','4','0.37'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','0','0.26'); $edge->set_attributes( {arrowstyle => 'none'} ); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('2','6','0.40'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '1,6' });
# my ($first,$second,$edge) = $graph->add_edge('0','4','0.38'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('0','6','0.58'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('4','6','0.93'); $edge->set_attributes( {arrowstyle => 'none', style => 'dotted'} );
# print $graph->as_boxart();

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


#SHORTEST PATHS
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('5','1','0.32'); $edge->set_attributes( {style => 'dotted'} ); 
# my ($first,$second,$edge) = $graph->add_edge('5','7','0.28'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('5','4','0.35'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '5', offset=> '-2,2' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','0.29'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '2,0' });
# my ($first,$second,$edge) = $graph->add_edge('3','6','0.29'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '2,4' });
# my ($first,$second,$edge) = $graph->add_edge('7','3','0.29'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('4','7','0.29'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','7','0.29'); $edge->set_attributes( {style => 'dotted'} ); $first->set_attributes({ origin=> '7', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('0','2','0.29'); $edge->set_attributes( {style => 'dotted'} ); $first->set_attributes({ origin=> '2', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('0','4','0.29'); $edge->set_attributes( {style => 'dotted'} ); 
# my ($first,$second,$edge) = $graph->add_edge('6','0','0.29'); $edge->set_attributes( {style => 'dotted'} ); 
# my ($first,$second,$edge) = $graph->add_edge('6','4','0.29'); $edge->set_attributes( {style => 'dotted'} ); 
# my ($first,$second,$edge) = $graph->add_edge('6','2','0.29'); $edge->set_attributes( {style => 'dotted'} ); 
# print $graph->as_boxart();

#Dijkstra
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('0','1','5'); $edge->set_attributes( {} ); $first->set_attributes({ border=> 'double', size=>'2,1' }); $second->set_attributes({ border=> 'double', origin=> '0', offset=> '3,-1', size=>'1,2' });
# my ($first,$second,$edge) = $graph->add_edge('0','7','8'); $edge->set_attributes( {} ); $second->set_attributes({ border=> 'double', origin=> '0', offset=> '1,2', size=>'3,2' });
# my ($first,$second,$edge) = $graph->add_edge('0','4','9'); $edge->set_attributes( {} ); $second->set_attributes({ origin=> '0', offset=> '0,3', border=> 'double', size=>'1,3' });
# my ($first,$second,$edge) = $graph->add_edge('1','2','12'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '0,2' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','15'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '3,0' });
# my ($first,$second,$edge) = $graph->add_edge('1','7','4'); $edge->set_attributes( {style => 'dotted'} ); 
# my ($first,$second,$edge) = $graph->add_edge('2','3','3'); $edge->set_attributes( {} ); $first->set_attributes({ border=> 'double' }); 
# my ($first,$second,$edge) = $graph->add_edge('3','6','9'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '2,4', size=>'2,1' }); $first->set_attributes({ border=> 'double' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('7','2','7'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','6','11'); $edge->set_attributes( {} );
# my ($first,$second,$edge) = $graph->add_edge('7','5','6'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '7', offset=> '0,2', size=>'1,2' }); $second->set_attributes({ border=> 'double' });
# my ($first,$second,$edge) = $graph->add_edge('4','6','20'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('4','5','4'); $edge->set_attributes( {} );
# my ($first,$second,$edge) = $graph->add_edge('4','7','5'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('5','6','13'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('5','2','1'); $edge->set_attributes( {} );

# print $graph->as_boxart();


#MinCut
#  my $graph = Graph::Easy->new();
#  my ($first,$second,$edge) = $graph->add_edge('s','1','10'); $edge->set_attributes( {style => 'dotted'} ); $first->set_attributes({ border=> 'double', size=>'1,1' }); $second->set_attributes({ border=> 'double', origin=> 's', offset=> '3,-3', size=>'2,1' });
#  my ($first,$second,$edge) = $graph->add_edge('s','3','5'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> 's', offset=> '3,0', size=>'2,1' });
#  my ($first,$second,$edge) = $graph->add_edge('s','5','15'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> 's', offset=> '3,3', size=>'2,1' });
#  my ($first,$second,$edge) = $graph->add_edge('1','2','9'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> '1', offset=> '3,0', size=>'2,1' });
#  my ($first,$second,$edge) = $graph->add_edge('3','4','8'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> '3', offset=> '3,-1', size=>'2,2' });
#  my ($first,$second,$edge) = $graph->add_edge('5','6','16'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> '5', offset=> '2,0', size=>'3,1' });
#  my ($first,$second,$edge) = $graph->add_edge('4','t','10'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> '3', offset=> '6,0' });
#  my ($first,$second,$edge) = $graph->add_edge('1','3','4'); $edge->set_attributes( {style => 'dotted'} );
#  my ($first,$second,$edge) = $graph->add_edge('3','5','4'); $edge->set_attributes( {style => 'dotted'} );
#  my ($first,$second,$edge) = $graph->add_edge('2','4','15'); $edge->set_attributes( {style => 'dotted'} );
#  my ($first,$second,$edge) = $graph->add_edge('4','6','15'); $edge->set_attributes( {style => 'dotted'} );
#  my ($first,$second,$edge) = $graph->add_edge('2','t','10'); $edge->set_attributes( {style => 'dotted'} );
#  my ($first,$second,$edge) = $graph->add_edge('6','t','10'); $edge->set_attributes( {style => 'dotted'} );
#  my ($first,$second,$edge) = $graph->add_edge('1','4','15'); $edge->set_attributes( {style => 'dotted'} );
#  my ($first,$second,$edge) = $graph->add_edge('3','6','15'); $edge->set_attributes( {style => 'dotted'} );

#  print $graph->as_boxart();

 #MaxFlow
# my $graph = Graph::Easy->new();
# my ($first,$second,$edge) = $graph->add_edge('s','1','10/10'); $edge->set_attributes( {style => 'dotted'} ); $first->set_attributes({ border=> 'double', size=>'1,1' }); $second->set_attributes({  origin=> 's', offset=> '3,-3', size=>'2,1' });
# my ($first,$second,$edge) = $graph->add_edge('s','3','5/5'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> 's', offset=> '3,0', size=>'2,1' });
# my ($first,$second,$edge) = $graph->add_edge('s','5','13/15'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> 's', offset=> '3,3', size=>'2,1' });
# my ($first,$second,$edge) = $graph->add_edge('1','2','0/9'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '1', offset=> '3,0', size=>'2,1' });
# my ($first,$second,$edge) = $graph->add_edge('3','4','8/8'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '3,-1', size=>'2,2' });
# my ($first,$second,$edge) = $graph->add_edge('5','6','0/16'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ border=> 'double', origin=> '5', offset=> '2,0', size=>'3,1' });
# my ($first,$second,$edge) = $graph->add_edge('4','t','10/10'); $edge->set_attributes( {style => 'dotted'} ); $second->set_attributes({ origin=> '3', offset=> '6,0' });
# my ($first,$second,$edge) = $graph->add_edge('1','3','0/4'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','5','3/4'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','4','0/15'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('4','6','0/15'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('2','t','10/10'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('6','t','10/10'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('1','4','2/15'); $edge->set_attributes( {style => 'dotted'} );
# my ($first,$second,$edge) = $graph->add_edge('3','6','6/6'); $edge->set_attributes( {style => 'dotted'} );
# 
# print $graph->as_boxart();