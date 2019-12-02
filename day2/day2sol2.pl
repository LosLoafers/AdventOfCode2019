#!/usr/bin/perl

use strict;
use warnings;
use Data::Dumper;

open(my $fh, "<", "input.txt")
	or die "Can't open file input.txt";

my @code_orig = <$fh> =~ /(\d+)/g;
my ($noun,$verb,$count) = (0,0,0);
close($fh);

while ($verb < 100) {
  my @code = @code_orig;
  @code[1..2] = ($noun,$verb);
  my $i = 0;
  print("$count, $noun, $verb\n");
  #print("@code\n");

  while ($i <= $#code) {
    if ($code[$i] == 99) { 
      if ($code[0] == 19690720) {
        my $res = 100*$noun+$verb;
        print("Done, the value at position 0 is: $code[0]\n"
              . "The noun = $noun and the verb = $verb \n"
              . "100 * noun + verb = 100 * $noun + $verb = " 
              . $res);
        exit;
      }
      last;
    }
    elsif ($code[$i] !~ /^(1|2)$/) {
      print("$code[$i] is not a valid instruction.");
      exit;
    }
    else {
      $code[$code[$i+3]] = op($code[$i],$code[$code[$i+1]],$code[$code[$i+2]],$code[$i+3]);
    }
    $i = $i + 4;
  }
  $count += 1;
  $noun = $count % 100;
  $verb = $verb + 1 if $noun == 0;
}

sub op {
  my ($opcode,$term1,$term2,$term3) = @_;
  return $opcode == 1 ? $term1 + $term2 : $term1 * $term2;
}
